package com.callsign.autoticketcronservice.scheduler;

import com.callsign.autoticketcronservice.feign.OrderDeliveryFeignClient;
import com.callsign.autoticketcronservice.feign.TicketFeignClient;
import com.callsign.autoticketcronservice.model.OrderDeliveryDto;
import com.callsign.autoticketcronservice.model.OrderPriorityEnum;
import com.callsign.autoticketcronservice.model.TicketDto;
import com.callsign.autoticketcronservice.model.TicketStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.util.CollectionUtils.isEmpty;

/** If The Expected Time Of Delivery Is Passed And The Order Is Still Not Delivered, Its Priority Automatically Becomes Higher Then Others.
 *  An Auto Ticket Is Raised In The System So That Agent Can Follow Up And Expedite The Food Preparation Process.
 * @author Waqas Ahmed
 * @date 4/18/2022
 */
@Configuration
@EnableScheduling
@Slf4j
public class RaiseTicketScheduler {


    private TicketFeignClient ticketFeignClient;
    private OrderDeliveryFeignClient orderDeliveryFeignClient;

    public RaiseTicketScheduler(TicketFeignClient ticketFeignClient, OrderDeliveryFeignClient orderDeliveryFeignClient) {
        this.ticketFeignClient = ticketFeignClient;
        this.orderDeliveryFeignClient = orderDeliveryFeignClient;
    }

    @Scheduled(fixedDelay = 50000)
    public void raiseTicketForUndeliveredOrdersScheduler() {
        log.info("RaiseTicketScheduler Scheduler Started...");
        try {
            log.info("Fetch Those Order Whose Status Is Not Delivered.");
            List<OrderDeliveryDto> lateOrderList = orderDeliveryFeignClient.findOrdersNotDelivered();
            log.info("Total Number Of Orders Fetched {} : ",lateOrderList.size());
            changeOrderPriorityToOneAndRaiseHighPriorityTicket(lateOrderList);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.info("RaiseTicketScheduler Scheduler Ended...");
    }

    /** This method will scan all those orders whose deliveryStatus is not Delivered and expected delivery time is also passed.
     *  If criteria matched a High priority ticket will be created.
     * @param lateOrderList
     */
    private void changeOrderPriorityToOneAndRaiseHighPriorityTicket(List<OrderDeliveryDto> lateOrderList) {
        AtomicInteger counter = new AtomicInteger();
        if(!isEmpty(lateOrderList)) {
            lateOrderList.stream()
                         .filter(order -> Timestamp.from(Instant.now()).after(order.getExpectedDeliveryTime()))
                         .filter(Objects::nonNull)
                         .forEach(order -> {
                             // Check if a ticket was previously created for this order. And Ticket Is Still not Solved then only create a new ticket.
                             List<TicketDto> ticketList = ticketFeignClient.findByDeliveryId(order.getId());
                             if(isEmpty(ticketList)) {
                                 changeOrderPriorityAndCreateTicket(order);
                                 counter.getAndIncrement();
                             } else {
                                    Long count = ticketList.stream()
                                                            .filter(ticket -> !ticket.getStatus().equalsIgnoreCase(TicketStatusEnum.CLOSED.getName()))
                                                            .count();
                                    if(count == 0) {
                                        changeOrderPriorityAndCreateTicket(order);
                                        counter.getAndIncrement();
                                    }
                             }
                        });
            if(counter.get() > 0) {
                log.info("Total Number of Ticker Created For Late Orders are {} :", counter);
            } else {
                log.info("No Such Order Eligible For Ticket Creation -->  {} :", counter);
            }
        } else {
            log.info("No Such Order Eligible For Ticket Creation -->  {} :", counter);
        }

    }

    private void changeOrderPriorityAndCreateTicket(OrderDeliveryDto order) {
        // If Order Is Low Priority Change the Order Priority.
        if(order.getOrderPriority() > 1) {
            order.setOrderPriority(OrderPriorityEnum.HIGH.getValue());
            ResponseEntity<OrderDeliveryDto> updatedOrder = orderDeliveryFeignClient.update(order);
            log.info("Order {} Priority Changed From {} To {} :", order.getId(), order.getOrderPriority(), OrderPriorityEnum.HIGH.getValue());
        }
        // Raise A Ticket.
        log.info("Ticker Creation Request Initiated...");
        ticketFeignClient.add(createTicketForOrder(order));
        log.info("Ticker Creation Done Successfully...");
    }

    private TicketDto createTicketForOrder(OrderDeliveryDto order) {
        TicketDto ticket = new TicketDto();
        ticket.setCustomerId(1l);
        ticket.setTicketDesc("Order Id " + order.getId() + " Is Not Prepared On Time. Raising A High Priority Ticket. Please Look Into The Matter.");
        ticket.setTicketCreationTime(Timestamp.from(Instant.now()));
        ticket.setDeliveryId(order.getId());
        ticket.setStatus(TicketStatusEnum.OPEN.getValue());
        ticket.setPriority(OrderPriorityEnum.HIGH.getName());
        return ticket;
    }
}

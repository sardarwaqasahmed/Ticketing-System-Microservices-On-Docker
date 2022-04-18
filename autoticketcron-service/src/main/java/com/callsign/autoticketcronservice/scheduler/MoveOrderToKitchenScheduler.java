package com.callsign.autoticketcronservice.scheduler;

import com.callsign.autoticketcronservice.feign.OrderDeliveryFeignClient;
import com.callsign.autoticketcronservice.model.OrderDeliveryDto;
import com.callsign.autoticketcronservice.model.OrderDeliveryStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.util.CollectionUtils.isEmpty;

/** This Background Job Will Fetch All Order That Are Being Placed By End User.
 *  This Scheduler Is Responsible For Moving Orders To The Kitchen. Status WIll Be Updated From 'Received' To 'Preparation'.
 *  Expected Delivery Time Is Also Being Updated On This Stage.
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Configuration
@EnableScheduling
@Slf4j
public class MoveOrderToKitchenScheduler {

    private OrderDeliveryFeignClient orderDeliveryFeignClient;
    private final Long AVERAGE_TIME_TO_REACH_DESTINATION = Long.valueOf(20);

    public MoveOrderToKitchenScheduler(OrderDeliveryFeignClient orderDeliveryFeignClient) {
        this.orderDeliveryFeignClient = orderDeliveryFeignClient;
    }

    @Scheduled(fixedDelay = 50000)
    public void moveOrderToKitchenScheduler() {
        log.info("MoveToKitchen Scheduler Started...");
        try {
            log.info("Fetch all those orders whose status is {} :", OrderDeliveryStatusEnum.RECEIVED.getValue());
            List<OrderDeliveryDto>  receivedOrderList = orderDeliveryFeignClient.search(OrderDeliveryStatusEnum.RECEIVED.getValue());
            log.info("Total Number Of Orders Fetched {} : ",receivedOrderList.size());
            markOrdersAsInPreparationPhase(receivedOrderList);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.info("MoveToKitchen Scheduler Ended...");
    }

    private void markOrdersAsInPreparationPhase(List<OrderDeliveryDto> receivedOrderList) {
        AtomicInteger counter = new AtomicInteger();
        if(!isEmpty(receivedOrderList)) {
            receivedOrderList.stream().forEach(order -> {
                // As POC I am adding 20 min time for reaching destination.
                Long timeToReachDestinationInMinutes = order.getFoodPreparationMeanTime() + AVERAGE_TIME_TO_REACH_DESTINATION;
                order.setDeliveryStatus(OrderDeliveryStatusEnum.PREPARATION.getName());
                order.setExpectedDeliveryTime(Timestamp.from(Instant.now().plusSeconds(timeToReachDestinationInMinutes*60)));
                ResponseEntity<OrderDeliveryDto> updatedOrder = orderDeliveryFeignClient.update(order);
                counter.getAndIncrement();
                log.info("Order {} Status Changed To {} :", order.getId(), OrderDeliveryStatusEnum.PREPARATION.getValue());
            });
        }

        log.info("Total Number of Orders moved to kitchen are {} :", counter);
    }

}

package com.callsign.autoticketcronservice.feign;

import com.callsign.autoticketcronservice.config.TicketFeignClientConfig;
import com.callsign.autoticketcronservice.model.TicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Waqas Ahmed
 * @date 4/17/2022
 */
@FeignClient(name = "ticketClient", url = "${ticket.srv.api.baseUrl}",  configuration = TicketFeignClientConfig.class)
public interface TicketFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<TicketDto> getAllTickets();

    @RequestMapping(method = RequestMethod.GET, value = "/search/${status}")
    List<TicketDto>  searchTicketByStatus(@PathVariable("status") String status);

    @RequestMapping(method = RequestMethod.GET, value = "/findByDeliveryId")
    List<TicketDto> findByDeliveryId(@RequestParam Long deliveryId);

    @RequestMapping(method = RequestMethod.POST, value = "/")
    ResponseEntity<TicketDto> add(@Valid @RequestBody TicketDto ticket);
}

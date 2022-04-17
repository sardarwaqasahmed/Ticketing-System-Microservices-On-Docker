package com.callsign.autoticketcronservice.feign;

import com.callsign.autoticketcronservice.config.TicketFeignClientConfig;
import com.callsign.autoticketcronservice.model.TicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}

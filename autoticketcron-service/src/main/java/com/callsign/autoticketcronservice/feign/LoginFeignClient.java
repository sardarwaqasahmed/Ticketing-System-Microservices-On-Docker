package com.callsign.autoticketcronservice.feign;

import com.callsign.autoticketcronservice.model.TicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Waqas Ahmed
 * @date 4/17/2022
 */
@FeignClient(name = "loginClient", url = "${login.srv.api.baseUrl}")
public interface LoginFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    String  login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password);
}

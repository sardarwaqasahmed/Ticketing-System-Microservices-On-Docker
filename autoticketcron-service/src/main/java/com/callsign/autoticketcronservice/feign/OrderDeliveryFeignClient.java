package com.callsign.autoticketcronservice.feign;

import com.callsign.autoticketcronservice.config.OrderDeliveryFeignClientConfig;
import com.callsign.autoticketcronservice.model.OrderDeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Waqas Ahmed
 * @date 4/17/2022
 */
@FeignClient(name = "orderDeliveryClient", url = "${order.srv.api.baseUrl}",  configuration = OrderDeliveryFeignClientConfig.class)
public interface OrderDeliveryFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<OrderDeliveryDto> getAllOrders();

    @RequestMapping(method = RequestMethod.GET, value = "/search/{deliveryStatus}")
    List<OrderDeliveryDto>  search(@PathVariable("deliveryStatus") String deliveryStatus);

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    List<OrderDeliveryDto>  findOrdersNotDelivered();



    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<OrderDeliveryDto> update(@RequestBody OrderDeliveryDto order);
}

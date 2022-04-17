package com.callsign.autoticketcronservice;

import com.callsign.autoticketcronservice.feign.LoginFeignClient;
import com.callsign.autoticketcronservice.feign.OrderDeliveryFeignClient;
import com.callsign.autoticketcronservice.feign.TicketFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 Author: waqas ahmed
 Date  : APR 17, 2022
 **/
@SpringBootApplication
@EnableFeignClients(clients = {
		LoginFeignClient.class,
		TicketFeignClient.class,
		OrderDeliveryFeignClient.class,
})
public class AutoTicketCronApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoTicketCronApiApplication.class, args);
	}

}

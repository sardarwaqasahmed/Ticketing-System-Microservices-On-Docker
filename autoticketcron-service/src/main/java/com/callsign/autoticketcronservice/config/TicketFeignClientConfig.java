package com.callsign.autoticketcronservice.config;

import com.callsign.autoticketcronservice.feign.LoginFeignClient;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author Waqas Ahmed
 * @date 4/17/2022
 */
public class TicketFeignClientConfig {

    @Autowired
    private LoginFeignClient loginFeignClient;

    @Autowired
    private AutoTicketCronServiceConfig serviceConfig;

    // Only accessible withing TicketFeignClient and not for other Spring context
    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String jwtToken = requestJWT();
                template.header("Authorization",
                        String.format("Bearer %s", jwtToken));
            }
        };
    }

    private String requestJWT() {
        return loginFeignClient.login(serviceConfig.getTicketSrvApiUsername(), serviceConfig.getTicketSrvApiPassword());
    }
}

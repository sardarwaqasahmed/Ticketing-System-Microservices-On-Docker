package com.callsign.autoticketcronservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Waqas Ahmed
 * @date 4/16/2022
 */
@RefreshScope
@Component
@Getter
@Setter
public class AutoTicketCronServiceConfig {

    private static final String PROFILE_LOCAL = "local";
    private static final String PROFILE_DEV = "dev";

    @Value("${spring.profile.active:default}")
    private String activeProfile;

    @Value("${environment}")
    private String environment;

    @Value("${login.srv.api.baseUrl}")
    private String loginApiBaseUrl;

    @Value("${ticket.srv.api.username}")
    private String ticketSrvApiUsername;

    @Value("${ticket.srv.api.password}")
    private String ticketSrvApiPassword;

    @Value("${order.srv.api.username}")
    private String orderSrvApiUsername;

    @Value("${order.srv.api.password}")
    private String orderSrvApiPassword;


    public boolean isLocalProfile() {
        return PROFILE_LOCAL.equalsIgnoreCase(activeProfile);
    }

    public boolean isDevProfile() {
        return PROFILE_DEV.equalsIgnoreCase(activeProfile);
    }

}

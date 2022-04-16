package com.callsign.loginservice.config;

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
public class LoginServiceConfig {

    private static final String PROFILE_LOCAL = "local";
    private static final String PROFILE_DEV = "dev";

    @Value("${spring.profile.active:default}")
    private String activeProfile;

    @Value("${environment}")
    private String environment;

    @Value("${jwt.secret}")
    private String jwtSecret;


    public boolean isLocalProfile() {
        return PROFILE_LOCAL.equalsIgnoreCase(activeProfile);
    }

    public boolean isDevProfile() {
        return PROFILE_DEV.equalsIgnoreCase(activeProfile);
    }

}

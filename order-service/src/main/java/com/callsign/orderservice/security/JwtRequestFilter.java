package com.callsign.orderservice.security;

import com.callsign.orderservice.entity.UserEntity;
import com.callsign.orderservice.service.UserService;
import com.callsign.orderservice.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Get authorization header and validate
        final String requestTokenHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(requestTokenHeader) || !requestTokenHeader.startsWith("Bearer ")) {
            log.error("Wrong Authorization Header");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // Get JWT and validate
        final String token = requestTokenHeader.split(" ")[1].trim();
        if (!jwtUtil.validateToken(token)) {
            log.error("Invalid JWT Token");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // Get User Identity and Set UserDetail in Spring Security Context
        UserEntity userEntity = userService.getUserEntiy(jwtUtil.getUsername(token));
        if (userEntity != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEntity, userEntity.getPassword(), new ArrayList<>());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            log.info("Authentication Done..");
        } else {
            log.error("Invalid User");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

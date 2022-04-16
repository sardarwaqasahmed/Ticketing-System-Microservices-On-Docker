package com.callsign.loginservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class JwtTokenMissingException extends ServletException {

    private static final long serialVersionUID = 1L;

    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}

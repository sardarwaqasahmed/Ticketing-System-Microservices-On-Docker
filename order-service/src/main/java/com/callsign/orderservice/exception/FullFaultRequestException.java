package com.callsign.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not a valid Request.")
public class FullFaultRequestException extends Exception {
    static final long serialVersionUID = -3387516993224229948L;


    public FullFaultRequestException(String message) {
        super(message);
    }
}

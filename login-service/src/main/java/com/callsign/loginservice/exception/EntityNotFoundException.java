package com.callsign.loginservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {
    static final long serialVersionUID = -3387516993334229948L;


    public EntityNotFoundException(String message) {
        super(message);
    }

}

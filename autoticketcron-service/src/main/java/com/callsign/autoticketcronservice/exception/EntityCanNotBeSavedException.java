package com.callsign.autoticketcronservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Entity can not be saved now.")
public class EntityCanNotBeSavedException extends Exception {
    static final long serialVersionUID = -3387516993724229948L;

    public EntityCanNotBeSavedException(String message) {
        super(message);
    }
}

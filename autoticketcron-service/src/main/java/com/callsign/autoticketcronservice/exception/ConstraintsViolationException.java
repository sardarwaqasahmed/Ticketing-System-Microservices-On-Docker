package com.callsign.autoticketcronservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated.")
public class ConstraintsViolationException extends Exception {

    static final long serialVersionUID = -3387516993224229948L;


    public ConstraintsViolationException(String message) {
        super(message);
    }

}

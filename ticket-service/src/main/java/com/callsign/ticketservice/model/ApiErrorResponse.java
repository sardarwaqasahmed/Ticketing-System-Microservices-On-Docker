package com.callsign.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ApiErrorResponse {

    private Date timestamp;
    private String status;
    private String code;
    private String message;
}

package com.callsign.autoticketcronservice.model;

import lombok.Getter;

/**
 * @author Waqas Ahmed
 * @date 4/18/2022
 */
@Getter
public enum TicketStatusEnum {
    OPEN("Open","open"),
    INPROGRESS("Inprogress","inprogress"),
    CLOSED("Closed,","closed,");

    private String name;
    private String value;

    private TicketStatusEnum(String name, String value) {
        this.name =  name;
        this.value = value;
    }
}

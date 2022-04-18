package com.callsign.autoticketcronservice.model;

import lombok.Getter;

/**
 * @author Waqas Ahmed
 * @date 4/17/2022
 */
@Getter
public enum OrderPriorityEnum {

    HIGH("High",1),
    MEDIUM("Medium",2),
    LOW("Low,",3);

    private String name;
    private int value;

    private OrderPriorityEnum(String name, int value) {
        this.name =  name;
        this.value = value;
    }
}

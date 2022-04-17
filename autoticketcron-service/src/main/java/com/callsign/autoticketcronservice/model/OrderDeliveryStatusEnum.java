package com.callsign.autoticketcronservice.model;

import lombok.Getter;

/**
 * @author Waqas Ahmed
 * @date 4/17/2022
 */
@Getter
public enum OrderDeliveryStatusEnum {

    RECEIVED("Received","received"),
    PREPARATION("Preparation","preparation"),
    PICKED("Picked,","picked,"),
    DELIVERED("Delivered)","delivered)");

    private String name;
    private String value;

    private OrderDeliveryStatusEnum(String name, String value) {
        this.name =  name;
        this.value = value;
    }
}

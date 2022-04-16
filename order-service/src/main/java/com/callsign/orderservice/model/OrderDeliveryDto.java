package com.callsign.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Waqas Ahmed
 * @date 4/16/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class OrderDeliveryDto {

    private Long id;

    @NotNull
    @NotBlank
    private String customerType;

    @NotNull
    @NotBlank
    private String deliveryStatus;

    @NotNull
    @NotBlank
    private Timestamp expectedDeliveryTime;

    @NotNull
    @NotBlank
    private int distanceFromDestination;

    @NotNull
    @NotBlank
    private int foodPreparationMeanTime;

    @NotNull
    @NotBlank
    private Timestamp timeToReachDestination;
}

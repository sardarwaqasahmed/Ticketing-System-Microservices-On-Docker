package com.callsign.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(
        name = "order_delivery"
)
/**
	Author: c_waqas.ahmad
	Date  : May 19, 2021
**/
public class OrderDeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersrv_sequence")
    @SequenceGenerator(name = "ordersrv_sequence", sequenceName = "ordersrv_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "customer_type",
            nullable = false)
    @NotNull(message = "customer_type can not be null!")
    private String customerType;

    @Column(name = "delivery_status",
            nullable = false)
    @NotNull(message = "delivery_status can not be null!")
    private String deliveryStatus;

    @Column(name = "order_creation_time")
    @NotNull(message = "order_creation_time can not be null!")
    private Timestamp orderCreationTime;

    @Column(name = "expected_delivery_time",
            nullable = false)
    @NotNull(message = "expected_delivery_time can not be null!")
    private Timestamp expectedDeliveryTime;

    @Column(name = "distance_from_destination",
            nullable = false)
    @NotNull(message = "distance_from_destination can not be null!")
    @Min(value = 5, message = "distance_from_destination should be positive value.")
    private int distanceFromDestination;

    
    @Column(name = "food_preparation_mean_time",
            nullable = false)
    private int foodPreparationMeanTime;

    @Column(name = "time_to_reach_destination",
            nullable = false)
    @NotNull(message = "time_to_reach_destination can not be null!")
    private Timestamp timeToReachDestination;

}

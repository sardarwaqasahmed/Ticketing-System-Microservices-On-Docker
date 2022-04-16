package com.callsign.ticketservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
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
        name = "ticket"
)
/**
	Author: c_waqas.ahmad
	Date  : May 19, 2021
**/
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketsrv_sequence")
    @SequenceGenerator(name = "ticketsrv_sequence", sequenceName = "ticketsrv_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "customer_id",
            nullable = false)
    @NotNull(message = "customer_id can not be null!")
    private Long customerId;

    @Column(name = "delivery_id",
            nullable = false)
    @NotNull(message = "delivery_id can not be null!")
    private Long deliveryId;

    @Column(name = "ticket_creation_time",
            nullable = false)
    @NotNull(message = "ticket_creation_time can not be null!")
    private Timestamp ticketCreationTime;
    
    @Column(name = "ticket_desc",
            nullable = false)
    @NotNull(message = "ticket_desc can not be null!")
    private String ticketDesc;

    @Column(name = "status",
            nullable = false)
    @NotNull(message = "status can not be null!")
    private String status;

    @Column(name = "priority",
            nullable = false)
    @NotNull(message = "priority can not be null!")
    private String priority;

    @Column(name = "ticket_close_time",
            nullable = false)
    @NotNull(message = "ticket_close_time can not be null!")
    private Timestamp ticketCloseTime;

}

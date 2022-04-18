package com.callsign.autoticketcronservice.model;

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
public class TicketDto {

    private Long id;

    @NotNull
    @NotBlank
    private Long customerId;

    @NotNull
    @NotBlank
    private Long deliveryId;

    @NotNull
    @NotBlank
    private Timestamp ticketCreationTime;

    @NotNull
    @NotBlank
    private String ticketDesc;

    @NotNull
    @NotBlank
    private String status;

    @NotNull
    @NotBlank
    private String priority;

    private Timestamp ticketCloseTime;
}

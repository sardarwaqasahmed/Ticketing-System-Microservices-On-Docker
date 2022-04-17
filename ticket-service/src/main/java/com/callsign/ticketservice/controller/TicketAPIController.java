package com.callsign.ticketservice.controller;

import com.callsign.ticketservice.entity.TicketEntity;
import com.callsign.ticketservice.exception.EntityCanNotBeSavedException;
import com.callsign.ticketservice.mapper.TicketAPIMapper;
import com.callsign.ticketservice.model.ApiErrorResponse;
import com.callsign.ticketservice.model.TicketDto;
import com.callsign.ticketservice.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TicketAPIController {

    private TicketService ticketDeliveryService;
    private final TicketAPIMapper ticketMapper;

    @GetMapping
    @Operation(summary = "Used to list all available tickets", security = @SecurityRequirement(name = "bearerAuth"))
    public @ResponseBody
    List<TicketDto> all() {
        return ticketMapper.entitesToDTOs(ticketDeliveryService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Used to get one ticket by its Id", security = @SecurityRequirement(name = "bearerAuth"))
    public @ResponseBody
    TicketDto get(@PathVariable Long id) throws EntityNotFoundException {
        return ticketMapper.entityToDto(ticketDeliveryService.getById(id));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Used to add new ticket", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<TicketDto> add(@Valid @RequestBody TicketDto ticket) throws EntityCanNotBeSavedException {
        ticketDeliveryService.add(ticketMapper.dtoToEntity(ticket));
        return new ResponseEntity<TicketDto>(HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Used to update an existing ticket", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<TicketDto> update(@Valid @RequestBody TicketDto ticket) throws EntityCanNotBeSavedException {
        ticketDeliveryService.update(ticketMapper.dtoToEntity(ticket));
        return new ResponseEntity<TicketDto>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Used to delete a ticket", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<TicketDto> delete(@PathVariable Long id) {
        ticketDeliveryService.delete(TicketEntity.builder().id(id).build());
        return new ResponseEntity<TicketDto>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public @ResponseBody
    @Operation(summary = "Used to search for ticket by priority, search is EXACT match. try to search for High", security = @SecurityRequirement(name = "bearerAuth"))
    List<TicketDto> search(@RequestParam String priority) {
        return ticketMapper.entitesToDTOs(ticketDeliveryService.searchByPriority(priority));
    }

    @GetMapping("/search/{status}")
    public @ResponseBody
    @Operation(summary = "Used to search for ticket by status, search is EXACT match. try to search for High", security = @SecurityRequirement(name = "bearerAuth"))
    List<TicketDto> searchByStatus(@PathVariable("status") String status) {
        return ticketMapper.entitesToDTOs(ticketDeliveryService.searchByStatus(status));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiErrorResponse handleEntityNotFoundException(final EntityNotFoundException ex) {
        return new ApiErrorResponse(new Date(),
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage());
    }

    @ExceptionHandler(EntityCanNotBeSavedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiErrorResponse handleEntityCanNotBeSavedException(final EntityCanNotBeSavedException ex) {
        return new ApiErrorResponse(new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage());
    }
}

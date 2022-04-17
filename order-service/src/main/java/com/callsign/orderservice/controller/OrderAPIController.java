package com.callsign.orderservice.controller;

import com.callsign.orderservice.entity.OrderDeliveryEntity;
import com.callsign.orderservice.exception.EntityCanNotBeSavedException;
import com.callsign.orderservice.mapper.OrderAPIMapper;
import com.callsign.orderservice.model.ApiErrorResponse;
import com.callsign.orderservice.model.OrderDeliveryDto;
import com.callsign.orderservice.service.OrderDeliveryService;
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
public class OrderAPIController {

    private OrderDeliveryService orderDeliveryService;
    private final OrderAPIMapper orderMapper;

    @GetMapping
    @Operation(summary = "Used to list all available Orders", security = @SecurityRequirement(name = "bearerAuth"))
    public @ResponseBody
    List<OrderDeliveryDto> all() {
        return orderMapper.entitesToDTOs(orderDeliveryService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Used to get one Order by its Id", security = @SecurityRequirement(name = "bearerAuth"))
    public @ResponseBody
    OrderDeliveryDto get(@PathVariable Long id) throws EntityNotFoundException {
        return orderMapper.entityToDto(orderDeliveryService.getById(id));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Client Side Will Used This API To Create New Order. Client can be Mobile,Web, or API. Initial Order Status Will Be Received.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<OrderDeliveryDto> add(@Valid @RequestBody OrderDeliveryDto order) throws EntityCanNotBeSavedException {
        orderDeliveryService.add(orderMapper.dtoToEntity(order));
        return new ResponseEntity<OrderDeliveryDto>(HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "API Client Will Use This API For Changing Order Status, Initial Call Will Change Status From 'Received' To 'Preparation'", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<OrderDeliveryDto> update(@Valid @RequestBody OrderDeliveryDto order) throws EntityCanNotBeSavedException {
        orderDeliveryService.update(orderMapper.dtoToEntity(order));
        return new ResponseEntity<OrderDeliveryDto>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Used to delete a order", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<OrderDeliveryDto> delete(@PathVariable Long id) {
        orderDeliveryService.delete(OrderDeliveryEntity.builder().id(id).build());
        return new ResponseEntity<OrderDeliveryDto>(HttpStatus.OK);
    }

    @GetMapping("/search/{deliveryStatus}")
    public @ResponseBody
    @Operation(summary = "Used to search for order by deliveryStatus, search is EXACT match. try to search for deliveryStatus Received", security = @SecurityRequirement(name = "bearerAuth"))
    List<OrderDeliveryDto> search(@PathVariable("deliveryStatus") String deliveryStatus) {
        return orderMapper.entitesToDTOs(orderDeliveryService.searchByDeliveryStatus(deliveryStatus));
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

package com.callsign.ticketservice.repository;

import com.callsign.ticketservice.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

    List<TicketEntity> findByPriorityIgnoreCase(String priority);
    List<TicketEntity> findByStatusIgnoreCase(String status);
    List<TicketEntity> findByDeliveryId(Long deliveryId);
}

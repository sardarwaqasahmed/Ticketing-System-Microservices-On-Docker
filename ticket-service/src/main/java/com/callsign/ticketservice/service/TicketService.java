package com.callsign.ticketservice.service;

import com.callsign.ticketservice.entity.TicketEntity;
import com.callsign.ticketservice.exception.EntityCanNotBeSavedException;
import com.callsign.ticketservice.repository.TicketRepository;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Service
@Slf4j
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    /**
     * get all available tickets in the store
     *
     * @return list of tickets
     */
    public List<TicketEntity> getAll() {
        List<TicketEntity> deliveryOrders = Lists.newArrayList(ticketRepository.findAll());
        log.info("number of fetched tickets are {}",deliveryOrders.size());
        return deliveryOrders;
    }

    /**
     * get one tickets by its id
     *
     * @param id
     * @return
     * @throws EntityNotFoundException
     */
    public TicketEntity getById(long id) throws EntityNotFoundException {
        Optional<TicketEntity> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent()) {
            log.info("Ticket Id {} retrieved successfully",id);
            return ticketOptional.get();
        } else {
            log.error("Ticket with id {} does not exist", id);
            throw new EntityNotFoundException("sorry, this Ticket does not exist.");
        }
    }

    public TicketEntity add(TicketEntity ticket) throws EntityCanNotBeSavedException {
        TicketEntity saveTicket = ticketRepository.save(ticket);
        if (saveTicket == null || saveTicket.getId() == null) {
            log.error("TicketEntity with id {} can not be saved", ticket.getId());
            throw new EntityCanNotBeSavedException("TicketEntity can not be saved.");
        } else {
            log.info("TicketEntity with id {} is saved successfully", ticket.getId());
            return saveTicket;
        }
    }

    public TicketEntity update(TicketEntity ticket) throws EntityCanNotBeSavedException {
        TicketEntity updatedTiket = ticketRepository.save(ticket);
        if (updatedTiket != null && updatedTiket.getId() != null) {
            log.info("TicketEntity with id {} is updated successfully", ticket.getId());
            return updatedTiket;
        } else {
            log.error("TicketEntity with id {} can not be updated", ticket.getId());
            throw new EntityCanNotBeSavedException("TicketEntity can not be updated.");
        }
    }

    public void delete(TicketEntity ticket) {
        ticketRepository.delete(ticket);
        log.info("TicketEntity Id {} is deleted successfully",ticket.getId());
    }

    public List<TicketEntity> searchByPriority(String priority) {
        List<TicketEntity> tickets = ticketRepository.findByPriorityIgnoreCase(priority);
        log.info("Number of tickets that match {}  is {}",priority,tickets.size());
        return tickets;
    }

    public List<TicketEntity> searchByStatus(String status) {
        List<TicketEntity> tickets = ticketRepository.findByStatusIgnoreCase(status);
        log.info("Number of tickets that match {}  is {}",status,tickets.size());
        return tickets;
    }
}

package com.callsign.ticketservice;

import com.callsign.ticketservice.mapper.TicketAPIMapper;
import com.callsign.ticketservice.model.TicketDto;
import com.callsign.ticketservice.service.TicketService;
import com.callsign.ticketservice.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class TicketControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TicketAPIMapper mapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtUtil jwtUtil;

    private String token = "Bearer ";

    @BeforeEach
    public void init() {
        token = token.concat(jwtUtil.generateJWTToken("ticketsrv"));
    }

    @Test
    public void testGetAllTicket() throws Exception {

        mvc.perform(get("/api/v1")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTicket() throws Exception {
        mvc.perform(get("/api/v1/3")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(notNullValue())))
                .andExpect(jsonPath("$.ticketDesc", is(notNullValue())))
                .andExpect(jsonPath("$.priority", is(notNullValue())));
    }

    @Test
    public void testFailedGetTicket() throws Exception {

        mvc.perform(get("/api/v1/1000")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddNewTicket() throws Exception {
        TicketDto request = TicketDto.builder()
                .id(Long.valueOf(1))
                .customerId(1l)
                .deliveryId(1l)
                .ticketCreationTime(Timestamp.from(Instant.now()))
                .ticketDesc("Delivery Id 1 for Customer ID is very late and didn't reach the destination on time. Raising a ticket.")
                .status("Open")
                .priority("High")
                .ticketCloseTime(Timestamp.from(Instant.now()))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateTicket() throws Exception {
        TicketDto request = TicketDto.builder()
                .id(Long.valueOf(1))
                .customerId(1l)
                .deliveryId(1l)
                .ticketCreationTime(Timestamp.from(Instant.now()))
                .ticketDesc("Delivery Id 1 for Customer ID is very late and didn't reach the destination on time. Raising a ticket.")
                .status("Close")
                .priority("High")
                .ticketCloseTime(Timestamp.from(Instant.now()))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.put("/api/v1/")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTicket() throws Exception {

        int numberBeforeDeletion = ticketService.getAll().size();

        mvc.perform(delete("/api/v1/1")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int numberAfterDeletion = ticketService.getAll().size();

        assertTrue(numberBeforeDeletion - numberAfterDeletion == 1);
    }

    @Test
    public void testSearch() throws Exception {
        mvc.perform(get("/api/v1/search?priority=High")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}
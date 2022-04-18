package com.callsign.orderservice;

import com.callsign.orderservice.mapper.OrderAPIMapper;
import com.callsign.orderservice.model.OrderDeliveryDto;
import com.callsign.orderservice.service.OrderDeliveryService;
import com.callsign.orderservice.util.JwtUtil;
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
public class OrderDeliveryControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderAPIMapper mapper;

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    @Autowired
    private JwtUtil jwtUtil;

    private String token = "Bearer ";

    @BeforeEach
    public void init() {
        token = token.concat(jwtUtil.generateJWTToken("admin"));
    }

    @Test
    public void testGetAllOrdersDelivery() throws Exception {

        mvc.perform(get("/api/v1")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderDelivery() throws Exception {
        mvc.perform(get("/api/v1/1")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerType", is(notNullValue())))
                .andExpect(jsonPath("$.deliveryStatus", is(notNullValue())))
                .andExpect(jsonPath("$.expectedDeliveryTime", is(notNullValue())));
    }

    @Test
    public void testFailedGetOrderDelivery() throws Exception {

        mvc.perform(get("/api/v1/1000")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddNewOrderDelivery() throws Exception {
        OrderDeliveryDto request = OrderDeliveryDto.builder()
                .customerType("VIP")
                .deliveryStatus("Received")
                .expectedDeliveryTime(Timestamp.from(Instant.now()))
                .timeToReachDestination(Timestamp.from(Instant.now()))
                .distanceFromDestination(5)
                .foodPreparationMeanTime(45l)
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
    public void testUpdateOrderDelivery() throws Exception {
        OrderDeliveryDto request = OrderDeliveryDto.builder()
                .id(Long.valueOf(1))
                .deliveryStatus("Preparation")
                .customerType("VIP")
                .expectedDeliveryTime(Timestamp.from(Instant.now()))
                .timeToReachDestination(Timestamp.from(Instant.now()))
                .distanceFromDestination(5)
                .foodPreparationMeanTime(45l)
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
    public void testDeleteOrderDelivery() throws Exception {

        int numberBeforeDeletion = orderDeliveryService.getAll().size();

        mvc.perform(delete("/api/v1/1")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int numberAfterDeletion = orderDeliveryService.getAll().size();

        assertTrue(numberBeforeDeletion - numberAfterDeletion == 1);
    }

    @Test
    public void testSearch() throws Exception {
        mvc.perform(get("/api/v1/search?deliveryStatus=Preparation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}
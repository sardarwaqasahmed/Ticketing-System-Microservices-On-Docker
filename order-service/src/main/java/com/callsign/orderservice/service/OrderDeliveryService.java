package com.callsign.orderservice.service;

import com.callsign.orderservice.entity.OrderDeliveryEntity;
import com.callsign.orderservice.exception.EntityCanNotBeSavedException;
import com.callsign.orderservice.model.OrderDeliveryStatusEnum;
import com.callsign.orderservice.repository.OrderDeliveryRepository;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Service
@Slf4j
@AllArgsConstructor
public class OrderDeliveryService {

    private final OrderDeliveryRepository orderDeliveryRepository;

    /**
     * get all available orders delivery in the store
     *
     * @return list of orders delivery
     */
    public List<OrderDeliveryEntity> getAll() {
        List<OrderDeliveryEntity> deliveryOrders = Lists.newArrayList(orderDeliveryRepository.findAll());
        log.info("number of fetched orders delivery are {}",deliveryOrders.size());
        return deliveryOrders;
    }

    /**
     * get one orders delivery by its id
     *
     * @param id
     * @return
     * @throws EntityNotFoundException
     */
    public OrderDeliveryEntity getById(long id) throws EntityNotFoundException {
        Optional<OrderDeliveryEntity> orderdeliveryOptional = orderDeliveryRepository.findById(id);
        if (orderdeliveryOptional.isPresent()) {
            log.info("OrderDelivery Id {} retrieved successfully",id);
            return orderdeliveryOptional.get();
        } else {
            log.error("OrderDelivery with id {} does not exist", id);
            throw new EntityNotFoundException("sorry, this OrderDelivery does not exist.");
        }
    }

    public OrderDeliveryEntity add(OrderDeliveryEntity orderDelivery) throws EntityCanNotBeSavedException {
        OrderDeliveryEntity saveOrderdelivery = orderDeliveryRepository.save(orderDelivery);
        if (saveOrderdelivery == null || saveOrderdelivery.getId() == null) {
            log.error("OrderDeliveryEntity with id {} can not be saved", orderDelivery.getId());
            throw new EntityCanNotBeSavedException("OrderDeliveryEntity can not be saved.");
        } else {
            log.info("OrderDeliveryEntity with id {} is saved successfully", orderDelivery.getId());
            return saveOrderdelivery;
        }
    }

    public OrderDeliveryEntity update(OrderDeliveryEntity orderDelivery) throws EntityCanNotBeSavedException {
        OrderDeliveryEntity updatedTiket = orderDeliveryRepository.save(orderDelivery);
        if (updatedTiket != null && updatedTiket.getId() != null) {
            log.info("OrderDeliveryEntity with id {} is updated successfully", orderDelivery.getId());
            return updatedTiket;
        } else {
            log.error("OrderDeliveryEntity with id {} can not be updated", orderDelivery.getId());
            throw new EntityCanNotBeSavedException("OrderDeliveryEntity can not be updated.");
        }
    }

    public void delete(OrderDeliveryEntity orderDelivery) {
        orderDeliveryRepository.delete(orderDelivery);
        log.info("OrderDeliveryEntity Id {} is deleted successfully",orderDelivery.getId());
    }

    public List<OrderDeliveryEntity> searchByDeliveryStatus(String deliveryStatus) {
        List<OrderDeliveryEntity> ordersDeliveryList = orderDeliveryRepository.findByDeliveryStatusIgnoreCase(deliveryStatus);
        log.info("Number of orders delivery that match {}  is {}",deliveryStatus,ordersDeliveryList.size());
        return ordersDeliveryList;
    }

    public List<OrderDeliveryEntity> findOrdersNotDelivered() {
        List<String> orderDeliveryStatus = new ArrayList<>();
        orderDeliveryStatus.add(OrderDeliveryStatusEnum.DELIVERED.getName());
        List<OrderDeliveryEntity> ordersDeliveryList = orderDeliveryRepository.findByDeliveryStatusNotIn(orderDeliveryStatus);
        log.info("Number of orders delivery that is not Delivered are {}", ordersDeliveryList.size());
        return ordersDeliveryList;
    }
}

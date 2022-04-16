package com.callsign.orderservice.service;

import com.callsign.orderservice.entity.OrderDeliveryEntity;
import com.callsign.orderservice.exception.EntityCanNotBeSavedException;
import com.callsign.orderservice.repository.OrderDeliveryRepository;
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

    public OrderDeliveryEntity add(OrderDeliveryEntity property) throws EntityCanNotBeSavedException {
        OrderDeliveryEntity saveOrderdelivery = orderDeliveryRepository.save(property);
        if (saveOrderdelivery == null || saveOrderdelivery.getId() == null) {
            log.error("OrderDeliveryEntity with id {} can not be saved", property.getId());
            throw new EntityCanNotBeSavedException("OrderDeliveryEntity can not be saved.");
        } else {
            log.info("OrderDeliveryEntity with id {} is saved successfully", property.getId());
            return saveOrderdelivery;
        }
    }

    public OrderDeliveryEntity update(OrderDeliveryEntity property) throws EntityCanNotBeSavedException {
        OrderDeliveryEntity updatedProperty = orderDeliveryRepository.save(property);
        if (updatedProperty != null && updatedProperty.getId() != null) {
            log.info("OrderDeliveryEntity with id {} is updated successfully", property.getId());
            return updatedProperty;
        } else {
            log.error("OrderDeliveryEntity with id {} can not be updated", property.getId());
            throw new EntityCanNotBeSavedException("OrderDeliveryEntity can not be updated.");
        }
    }

    public void delete(OrderDeliveryEntity property) {
        orderDeliveryRepository.delete(property);
        log.info("OrderDeliveryEntity Id {} is deleted successfully",property.getId());
    }

    public List<OrderDeliveryEntity> searchByDeliveryStatus(String deliveryStatus) {
        List<OrderDeliveryEntity> ordersDelivery = orderDeliveryRepository.findByDeliveryStatusIgnoreCase(deliveryStatus);
        log.info("Number of orders delivery that match {}  is {}",deliveryStatus,ordersDelivery.size());
        return ordersDelivery;
    }
}

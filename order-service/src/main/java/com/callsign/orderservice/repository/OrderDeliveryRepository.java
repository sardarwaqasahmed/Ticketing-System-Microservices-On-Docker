package com.callsign.orderservice.repository;

import com.callsign.orderservice.entity.OrderDeliveryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
public interface OrderDeliveryRepository extends CrudRepository<OrderDeliveryEntity, Long> {

    List<OrderDeliveryEntity> findByDeliveryStatusIgnoreCase(String deliveryStatus);
    List<OrderDeliveryEntity> findByDeliveryStatusNotIn(List<String> deliveryStatus);
}

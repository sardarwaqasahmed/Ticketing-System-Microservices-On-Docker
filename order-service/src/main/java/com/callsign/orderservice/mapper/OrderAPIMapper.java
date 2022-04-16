package com.callsign.orderservice.mapper;

import com.callsign.orderservice.entity.OrderDeliveryEntity;
import com.callsign.orderservice.model.OrderDeliveryDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Waqas Ahmed
 * @date 4/16/2022
 */
@Mapper
public interface OrderAPIMapper {


    OrderDeliveryDto entityToDto(OrderDeliveryEntity entity);
    OrderDeliveryEntity dtoToEntity(OrderDeliveryDto dto);

    List<OrderDeliveryDto> entitesToDTOs(List<OrderDeliveryEntity> entites);
}

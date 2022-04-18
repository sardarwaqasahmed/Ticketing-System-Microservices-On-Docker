package com.callsign.orderservice.mapper;

import com.callsign.orderservice.entity.OrderDeliveryEntity;
import com.callsign.orderservice.entity.OrderDeliveryEntity.OrderDeliveryEntityBuilder;
import com.callsign.orderservice.model.OrderDeliveryDto;
import com.callsign.orderservice.model.OrderDeliveryDto.OrderDeliveryDtoBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Component
public class OrderAPIMapperImpl implements OrderAPIMapper {

    @Override
    public OrderDeliveryDto entityToDto(OrderDeliveryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDeliveryDtoBuilder orderDeliveryDto = OrderDeliveryDto.builder();

        orderDeliveryDto.id( entity.getId() );
        orderDeliveryDto.customerType( entity.getCustomerType() );
        orderDeliveryDto.orderPriority( entity.getOrderPriority() );
        orderDeliveryDto.deliveryStatus( entity.getDeliveryStatus() );
        orderDeliveryDto.orderCreationTime(entity.getOrderCreationTime());
        orderDeliveryDto.expectedDeliveryTime( entity.getExpectedDeliveryTime() );
        orderDeliveryDto.distanceFromDestination( entity.getDistanceFromDestination() );
        orderDeliveryDto.foodPreparationMeanTime( entity.getFoodPreparationMeanTime() );
        orderDeliveryDto.timeToReachDestination( entity.getTimeToReachDestination() );

        return orderDeliveryDto.build();
    }

    @Override
    public OrderDeliveryEntity dtoToEntity(OrderDeliveryDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderDeliveryEntityBuilder orderDeliveryEntity = OrderDeliveryEntity.builder();

        orderDeliveryEntity.id( dto.getId() );
        orderDeliveryEntity.customerType( dto.getCustomerType() );
        orderDeliveryEntity.orderPriority( dto.getOrderPriority() );
        orderDeliveryEntity.deliveryStatus( dto.getDeliveryStatus() );
        orderDeliveryEntity.orderCreationTime(dto.getOrderCreationTime());
        orderDeliveryEntity.expectedDeliveryTime( dto.getExpectedDeliveryTime() );
        orderDeliveryEntity.distanceFromDestination( dto.getDistanceFromDestination() );
        orderDeliveryEntity.foodPreparationMeanTime( dto.getFoodPreparationMeanTime() );
        orderDeliveryEntity.timeToReachDestination( dto.getTimeToReachDestination() );

        return orderDeliveryEntity.build();
    }

    @Override
    public List<OrderDeliveryDto> entitesToDTOs(List<OrderDeliveryEntity> entites) {
        if ( entites == null ) {
            return null;
        }

        List<OrderDeliveryDto> list = new ArrayList<OrderDeliveryDto>( entites.size() );
        for ( OrderDeliveryEntity orderDeliveryEntity : entites ) {
            list.add( entityToDto( orderDeliveryEntity ) );
        }

        return list;
    }
}

package com.callsign.ticketservice.mapper;

import com.callsign.ticketservice.entity.TicketEntity;
import com.callsign.ticketservice.entity.TicketEntity.TicketEntityBuilder;
import com.callsign.ticketservice.model.TicketDto;
import com.callsign.ticketservice.model.TicketDto.TicketDtoBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Component
public class TicketAPIMapperImpl implements TicketAPIMapper {

    @Override
    public TicketDto entityToDto(TicketEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TicketDtoBuilder ticketDto = TicketDto.builder();

        ticketDto.id( entity.getId() );
        ticketDto.customerId( entity.getCustomerId() );
        ticketDto.deliveryId( entity.getDeliveryId() );
        ticketDto.ticketCreationTime( entity.getTicketCreationTime() );
        ticketDto.ticketDesc( entity.getTicketDesc() );
        ticketDto.status( entity.getStatus() );
        ticketDto.priority( entity.getPriority() );
        ticketDto.ticketCloseTime( entity.getTicketCloseTime() );

        return ticketDto.build();
    }

    @Override
    public TicketEntity dtoToEntity(TicketDto dto) {
        if ( dto == null ) {
            return null;
        }

        TicketEntityBuilder ticketEntity = TicketEntity.builder();

        ticketEntity.id( dto.getId() );
        ticketEntity.customerId( dto.getCustomerId() );
        ticketEntity.deliveryId( dto.getDeliveryId() );
        ticketEntity.ticketCreationTime( dto.getTicketCreationTime() );
        ticketEntity.ticketDesc( dto.getTicketDesc() );
        ticketEntity.status( dto.getStatus() );
        ticketEntity.priority( dto.getPriority() );
        ticketEntity.ticketCloseTime( dto.getTicketCloseTime() );

        return ticketEntity.build();
    }

    @Override
    public List<TicketDto> entitesToDTOs(List<TicketEntity> entites) {
        if ( entites == null ) {
            return null;
        }

        List<TicketDto> list = new ArrayList<TicketDto>( entites.size() );
        for ( TicketEntity ticketEntity : entites ) {
            list.add( entityToDto( ticketEntity ) );
        }

        return list;
    }
}

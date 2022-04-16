package com.callsign.ticketservice.mapper;

import com.callsign.ticketservice.entity.TicketEntity;
import com.callsign.ticketservice.model.TicketDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Waqas Ahmed
 * @date 4/16/2022
 */
//@Mapper
public interface TicketAPIMapper {


    TicketDto entityToDto(TicketEntity entity);
    TicketEntity dtoToEntity(TicketDto dto);

    List<TicketDto> entitesToDTOs(List<TicketEntity> entites);
}

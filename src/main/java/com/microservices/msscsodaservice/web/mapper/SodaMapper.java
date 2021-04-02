package com.microservices.msscsodaservice.web.mapper;

import com.microservices.msscsodaservice.domain.Soda;
import com.microservices.msscsodaservice.web.model.SodaDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface SodaMapper {

    SodaDto sodaToDto(Soda soda);
    Soda sodaDtoToSoda (SodaDto sodaDto);
}

package com.microservices.msscsodaservice.services;


import com.microservices.msscsodaservice.domain.Soda;
import com.microservices.msscsodaservice.web.mapper.SodaMapperImpl;
import com.microservices.msscsodaservice.web.model.SodaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SodaService{

    private final SodaMapperImpl sodaMapper;

    public SodaDto getDtoOfSoda(Soda soda){
        return sodaMapper.sodaToDto(soda);
    }

}

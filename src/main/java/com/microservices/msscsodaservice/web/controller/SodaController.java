package com.microservices.msscsodaservice.web.controller;

import com.microservices.msscsodaservice.web.mapper.SodaMapper;
import com.microservices.msscsodaservice.web.model.SodaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/soda")
@RestController
public class SodaController  {
    @GetMapping("/{sodaId}")
    public ResponseEntity getSodaById(@PathVariable UUID sodaId){
        //Todo impl
        return new ResponseEntity(SodaDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewSoda(@RequestBody @Valid SodaDto sodaDto){

        //Todo Impl
        return new ResponseEntity(SodaDto.builder().build(),HttpStatus.CREATED);
    }

    @PutMapping("/{sodaId}")
    public ResponseEntity updateSodaById (@PathVariable UUID sodaId, @RequestBody  @Valid  SodaDto sodaDto){

        //Todo Impl
        return new ResponseEntity(SodaDto.builder().build(),HttpStatus.NO_CONTENT);
    }
}

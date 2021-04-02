package com.microservices.msscsodaservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.msscsodaservice.web.model.SodaDto;
import com.microservices.msscsodaservice.web.model.SodaStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(SodaController.class)
class SodaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getSodaById() throws Exception {
        mockMvc.perform(get("/api/v1/soda/"+ UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewSoda() throws Exception {
        SodaDto sodaDto = getValidDto();
        String sodaDtoJson = objectMapper.writeValueAsString(sodaDto);
        mockMvc.perform(post("/api/v1/soda/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sodaDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateSodaById() throws Exception{
        SodaDto sodaDto = getValidDto();
        String sodaDtoJson = objectMapper.writeValueAsString(sodaDto);
        mockMvc.perform(put("/api/v1/soda/"+UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(sodaDtoJson))
                .andExpect(status().isNoContent());
    }

    SodaDto getValidDto (){

        return SodaDto.builder()
                .sodaName("Green Apple")
                .sodaStyle(SodaStyleEnum.MIRANDA)
                .price(new BigDecimal("3.59"))
                .upc(131234123L)
                .build();

    }
}
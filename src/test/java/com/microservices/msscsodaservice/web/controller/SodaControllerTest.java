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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(SodaController.class)
@ComponentScan(basePackages = "com.microservices.msscsodaservice.web.mapper")
class SodaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getSodaById() throws Exception {
        mockMvc.perform(get("/api/v1/soda/{sodaId}", UUID.randomUUID().toString())
                .param("iscold","yes") // just added to see how we can document query parameters but actually we don't have query params in our controller
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/soda",
                            pathParameters(
                             parameterWithName("sodaId").description("UUID of the desired soda to get.") //to document path parameters
                            ),
                            requestParameters(
                                    parameterWithName("iscold").description("the temperature of the soda") //to document Query parameters
                            ),
                            responseFields(
                                    fieldWithPath("id").description("The id of the soda."),
                                    fieldWithPath("version").description("The version of the returned soda."),
                                    fieldWithPath("createdDate").description("The timestamp at which the soda is saved."),
                                    fieldWithPath("lastModifiedDate").description("The last timestamp at which the soda data is updated."),
                                    fieldWithPath("sodaName").description("The name of the soda."),
                                    fieldWithPath("sodaStyle").description("The type of the soda (more details about the soda name)."),
                                    fieldWithPath("upc").description("The unique packaging code for each soda bottle."),
                                    fieldWithPath("price").description("The price of the soda bottle."),
                                    fieldWithPath("quantityOnHand").description("The quantity of this type of the soda bottle in the inventory.")
                                    )
                ));
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
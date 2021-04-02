package com.microservices.msscsodaservice.bootstrap;

import com.microservices.msscsodaservice.domain.Soda;
import com.microservices.msscsodaservice.repositories.SodaRepository;
import com.microservices.msscsodaservice.services.SodaService;
import com.microservices.msscsodaservice.web.model.SodaStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SodaLoader implements CommandLineRunner {

    private final SodaRepository sodaRepository;
    private final SodaService sodaService;



    @Override
    public void run(String... args) throws Exception {
        loadSodaObjects();
    }
    private void loadSodaObjects(){
        if (sodaRepository.count() == 0){
            sodaRepository.save(Soda.builder()
                                .sodaName("Green Apple")
                                .sodaStyle(SodaStyleEnum.MIRANDA)
                                .price(new BigDecimal("5.00"))
                                .quantity(12)
                                .upc(123L)
                                .minOnHand(123)
                                .build());

            sodaRepository.save(Soda.builder()
                    .sodaName("Pepsi Cola")
                    .sodaStyle(SodaStyleEnum.PEPSI)
                    .price(new BigDecimal("5.50"))
                    .quantity(12)
                    .upc(124L)
                    .minOnHand(123)
                    .build());

            System.out.println("Soda to SodaDto >> "
                    + sodaService.getDtoOfSoda(Soda.builder()
                    .id(UUID.randomUUID())
                    .sodaName("Green Apple")
                    .sodaStyle(SodaStyleEnum.MIRANDA)
                    .price(new BigDecimal("5.00"))
                    .build()));

        }

    }
}

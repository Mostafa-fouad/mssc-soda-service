package com.microservices.msscsodaservice.repositories;

import com.microservices.msscsodaservice.domain.Soda;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.UUID;

public interface SodaRepository extends PagingAndSortingRepository<Soda, UUID> {
}

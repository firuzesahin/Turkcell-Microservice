package com.kodlamaio.rentalservice.api.clients.car;


import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.enums.State;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "inventory-service", fallback = CarClientFallback.class)
public interface CarClient
{
    @GetMapping(value = "api/cars/check-car-available/{carId}")
    @Retry(name = "check-is-car-available")
    ClientResponse checkIfCarAvailable(@PathVariable UUID carId);

    @PutMapping("api/cars/{id}/change-state")
    void changeState(@PathVariable UUID id, @RequestBody State state);
}
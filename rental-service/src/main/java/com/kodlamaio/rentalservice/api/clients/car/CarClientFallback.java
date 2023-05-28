package com.kodlamaio.rentalservice.api.clients.car;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.enums.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CarClientFallback implements CarClient
{
    @Override
    public ClientResponse checkIfCarAvailable(UUID carId)
    {
        log.info("INVENTORY SERVICE IS DOWN!");
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }

    @Override
    public void changeState(UUID id, State state)
    {
        log.info("changeState INVENTORY SERVICE IS DOWN!");
        throw new RuntimeException("changeState INVENTORY SERVICE IS DOWN!");
    }
}
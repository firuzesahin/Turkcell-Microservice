package kodlama.io.maintenanceservice.api.clients;

import com.kodlamaio.commonpackage.utils.enums.State;
import kodlama.io.maintenanceservice.business.dto.responses.GetCarClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CarClientFallback implements CarClient
{
    @Override
    public GetCarClientResponse getById(UUID carId) {
        log.info("getById INVENTORY SERVICE IS DOWN!");
        throw new RuntimeException("getById INVENTORY SERVICE IS DOWN!");
    }

    @Override
    public void changeState(UUID id, State state) {
        log.info("changeState INVENTORY SERVICE IS DOWN!");
        throw new RuntimeException("changeState INVENTORY SERVICE IS DOWN!");
    }
}
package kodlama.io.maintenanceservice.api.clients;

import com.kodlamaio.commonpackage.utils.enums.State;
import jakarta.validation.Valid;
import kodlama.io.maintenanceservice.business.dto.responses.GetCarClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "inventory-service")
public interface CarClient
{
    @GetMapping("/api/cars/{carId}")
    GetCarClientResponse getById(@PathVariable UUID carId);

    @PutMapping("/{id}/change-state")
    void changeState(@PathVariable UUID id, @Valid @RequestBody State state);
}
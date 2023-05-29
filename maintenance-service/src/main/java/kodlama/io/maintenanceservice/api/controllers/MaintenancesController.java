package kodlama.io.maintenanceservice.api.controllers;


import kodlama.io.maintenanceservice.business.abstracts.MaintenanceService;
import kodlama.io.maintenanceservice.business.dto.requests.CreateMaintenanceRequest;
import kodlama.io.maintenanceservice.business.dto.requests.UpdateMaintenanceRequest;
import kodlama.io.maintenanceservice.business.dto.responses.CreateMaintenanceResponse;
import kodlama.io.maintenanceservice.business.dto.responses.GetAllMaintenancesResponse;
import kodlama.io.maintenanceservice.business.dto.responses.GetMaintenanceResponse;
import kodlama.io.maintenanceservice.business.dto.responses.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenancesController
{
    private final MaintenanceService service;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request) {
        return service.add(request);
    }

    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam UUID carId)
    {
        return service.returnCarFromMaintenance(carId);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable UUID id, @RequestBody UpdateMaintenanceRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    {
        service.delete(id);
    }
}
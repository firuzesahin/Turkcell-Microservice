package kodlama.io.maintenanceservice.repository;

import kodlama.io.maintenanceservice.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID>
{
    boolean existsByCarIdAndIsCompletedIsFalse(UUID carId);
    Maintenance findByCarIdAndIsCompletedIsFalse(UUID carId);
}
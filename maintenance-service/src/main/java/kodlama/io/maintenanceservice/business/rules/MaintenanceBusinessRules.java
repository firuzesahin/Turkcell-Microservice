package kodlama.io.maintenanceservice.business.rules;

import com.kodlamaio.commonpackage.utils.constants.Messages;
import com.kodlamaio.commonpackage.utils.enums.State;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import kodlama.io.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceBusinessRules
{
    private final MaintenanceRepository repository;

    public void checkIfMaintenanceExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Maintenance.NotExists);
        }
    }

    public void checkIfCarIsNotUnderMaintenance(UUID carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarNotExists);
        }
    }

    public void checkIfCarUnderMaintenance(UUID carId) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarExists);
        }
    }

    public void checkCarAvailabilityForMaintenance(State state)
    {
        if (state.equals(State.Rented)) {
            throw new BusinessException(Messages.Maintenance.CarIsRented);
        }
    }
}
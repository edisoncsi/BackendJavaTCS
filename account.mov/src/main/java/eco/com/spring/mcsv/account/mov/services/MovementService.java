package eco.com.spring.mcsv.account.mov.services;

import eco.com.spring.mcsv.account.mov.dtos.DepositWithdrawals;
import eco.com.spring.mcsv.account.mov.dtos.MovementDto;
import eco.com.spring.mcsv.account.mov.models.Movement;

import java.util.List;
import java.util.Optional;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

public interface MovementService {
    List<Movement> listMovements();
    
    Optional<Movement> getMovement(Long id);
    
    Optional<Movement> updateMovement(String id, MovementDto movement);

    List<Object[]> listMovements(DepositWithdrawals dto);
    
    void insertMovement(Movement movement);

    boolean getMovementById(Long id);

    void deleteMovement(Long id);
}

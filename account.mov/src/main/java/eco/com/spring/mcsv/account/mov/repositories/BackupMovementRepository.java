package eco.com.spring.mcsv.account.mov.repositories;

import eco.com.spring.mcsv.account.mov.models.BackupMovement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Repository
public interface BackupMovementRepository extends CrudRepository<BackupMovement, Long> {
}

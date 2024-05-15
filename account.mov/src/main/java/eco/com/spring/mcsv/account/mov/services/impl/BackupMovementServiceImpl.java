package eco.com.spring.mcsv.account.mov.services.impl;

import eco.com.spring.mcsv.account.mov.models.BackupMovement;
import eco.com.spring.mcsv.account.mov.repositories.BackupMovementRepository;
import eco.com.spring.mcsv.account.mov.services.BackupMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Service
public class BackupMovementServiceImpl implements BackupMovementService {

    @Autowired
    private BackupMovementRepository backupMovementRepository;
    @Override
    public List<BackupMovement> listBackupMovements() {
        return (List<BackupMovement>) backupMovementRepository.findAll();
    }

    @Override
    public void insertBackupMovement(BackupMovement backupMovement) {
        backupMovementRepository.save(backupMovement);
    }
}

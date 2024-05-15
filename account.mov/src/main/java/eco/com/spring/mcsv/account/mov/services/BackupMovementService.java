package eco.com.spring.mcsv.account.mov.services;

import eco.com.spring.mcsv.account.mov.models.BackupMovement;

import java.util.List;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

public interface BackupMovementService {

    List<BackupMovement> listBackupMovements();

    void insertBackupMovement(BackupMovement backupMovement);
}

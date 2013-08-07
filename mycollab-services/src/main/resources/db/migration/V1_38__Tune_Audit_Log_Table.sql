ALTER TABLE `m_audit_log` DROP COLUMN `lastUpdatedTime` , DROP COLUMN `createdTime` ;
ALTER TABLE `m_audit_log` ADD COLUMN `activityLogId` INT(11) NULL , 
  ADD CONSTRAINT `FK_m_audit_log_3`
  FOREIGN KEY (`activityLogId` )
  REFERENCES `s_activitystream` (`id` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `FK_m_audit_log_3_idx` (`activityLogId` ASC) ;
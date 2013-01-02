ALTER TABLE `m_audit_log` DROP COLUMN `refid` , CHANGE COLUMN `posteduser` `posteduser` VARCHAR(45) NULL  , ADD COLUMN `sAccountId` INT NOT NULL, ADD COLUMN `type` VARCHAR(45) NOT NULL, ADD COLUMN `typeid` INT NOT NULL, 
  ADD CONSTRAINT `FK_m_audit_log_1`
  FOREIGN KEY (`posteduser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE, 
  ADD CONSTRAINT `FK_m_audit_log_2`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_audit_log_1_idx` (`posteduser` ASC) 
, ADD INDEX `FK_m_audit_log_2_idx` (`sAccountId` ASC) ;
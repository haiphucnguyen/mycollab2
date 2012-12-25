ALTER TABLE `m_prj_risk` CHANGE COLUMN `status` `status` VARCHAR(45) NULL DEFAULT NULL, ADD COLUMN `sAccountId` INT NOT NULL, 
  ADD CONSTRAINT `FK_m_prj_risk1_4`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_risk1_4_idx` (`sAccountId` ASC) ;

ALTER TABLE `m_prj_problem` CHANGE COLUMN `status` `status` VARCHAR(45) NULL DEFAULT NULL, ADD COLUMN `sAccountId` INT NOT NULL, 
  ADD CONSTRAINT `FK_m_prj_risk_1`
  FOREIGN KEY (`raisedbyuser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE, 
  ADD CONSTRAINT `FK_m_prj_risk_2`
  FOREIGN KEY (`assigntouser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE, 
  ADD CONSTRAINT `FK_m_prj_risk_3`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_risk_1_idx` (`raisedbyuser` ASC) 
, ADD INDEX `FK_m_prj_risk_2_idx` (`assigntouser` ASC) 
, ADD INDEX `FK_m_prj_risk_3_idx` (`sAccountId` ASC) ;
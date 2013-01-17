ALTER TABLE `m_tracker_bug` ADD COLUMN `sAccountId` INT NOT NULL, 
  ADD CONSTRAINT `FK_m_tracker_bug_5`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_tracker_bug_5_idx` (`sAccountId` ASC) ;

ALTER TABLE `m_tracker_version` ADD COLUMN `sAccountId` INT NOT NULL , 
  ADD CONSTRAINT `FK_m_tracker_version_3`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_tracker_version_3_idx` (`sAccountId` ASC) ;

ALTER TABLE `m_tracker_component` ADD COLUMN `sAccountId` INT NOT NULL, 
  ADD CONSTRAINT `FK_m_tracker_component_4`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_tracker_component_4_idx` (`sAccountId` ASC) ;
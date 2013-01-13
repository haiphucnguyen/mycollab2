ALTER TABLE `m_prj_task` ADD COLUMN `sAccountId` INT NOT NULL, 
  ADD CONSTRAINT `FK_m_prj_task_5`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_task_5_idx` (`sAccountId` ASC) ;
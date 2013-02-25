ALTER TABLE `m_prj_task` ADD COLUMN `logBy` VARCHAR(45) NULL , 
  ADD CONSTRAINT `FK_m_prj_task_5`
  FOREIGN KEY (`logBy` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_task_5_idx` (`logBy` ASC) ;
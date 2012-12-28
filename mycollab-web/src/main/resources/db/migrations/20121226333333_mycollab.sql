ALTER TABLE `m_prj_task_list` ADD COLUMN `sAccountId` INT NULL, ADD COLUMN `milestoneId` INT NULL, 
ADD COLUMN `owner` VARCHAR(45) NULL, 
  ADD CONSTRAINT `PK_m_prj_task_list_2`
  FOREIGN KEY (`milestoneId` )
  REFERENCES `m_prj_milestone` (`id` )
  ON DELETE SET NULL
  ON UPDATE SET NULL, 
  ADD CONSTRAINT `PK_m_prj_task_list_3`
  FOREIGN KEY (`owner` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `PK_m_prj_task_list_2_idx` (`milestoneId` ASC) 
, ADD INDEX `PK_m_prj_task_list_3_idx` (`owner` ASC) ;
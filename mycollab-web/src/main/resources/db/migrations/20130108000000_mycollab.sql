ALTER TABLE `m_prj_task` DROP FOREIGN KEY `FK_m_prj_task_3` , DROP FOREIGN KEY `FK_m_prj_task_2` ;
ALTER TABLE `m_prj_task` DROP COLUMN `parenttask` , DROP COLUMN `predecessors` , ADD COLUMN `assignUser` VARCHAR(45) NULL  AFTER `lastUpdatedTime` , 
  ADD CONSTRAINT `FK_m_prj_task_3`
  FOREIGN KEY (`tasklistid` )
  REFERENCES `m_prj_task_list` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  ADD CONSTRAINT `FK_m_prj_task_4`
  FOREIGN KEY (`assignUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_task_4_idx` (`assignUser` ASC) 
, DROP INDEX `FK_m_prj_task_2` ;
ALTER TABLE `m_prj_task` ADD COLUMN `tasklistid` INT(11) UNSIGNED NULL, 
  ADD CONSTRAINT `FK_m_prj_task_3`
  FOREIGN KEY (`tasklistid` )
  REFERENCES `mycollab_live`.`m_prj_task_list` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_m_prj_task_3_idx` (`tasklistid` ASC) ;

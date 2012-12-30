ALTER TABLE `m_prj_task_list` CHANGE COLUMN `projectid` `projectid` INT(11) UNSIGNED NOT NULL  , 
  ADD CONSTRAINT `PK_m_prj_task_list_1`
  FOREIGN KEY (`projectid` )
  REFERENCES `m_prj_project` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `PK_m_prj_task_list_4_idx1` (`projectid` ASC) ;
drop table `m_prj_permission`;
ALTER TABLE `m_prj_role` ADD COLUMN `projectid` INT UNSIGNED NOT NULL, ADD COLUMN `permissionVal` TEXT NOT NULL, 
  ADD CONSTRAINT `FK_m_prj_role_2`
  FOREIGN KEY (`projectid` )
  REFERENCES `m_prj_project` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_role_2_idx` (`projectid` ASC) ;
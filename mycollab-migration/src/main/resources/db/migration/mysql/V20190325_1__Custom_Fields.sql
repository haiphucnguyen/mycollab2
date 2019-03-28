ALTER TABLE `m_tracker_bug`
DROP COLUMN `cus_dbl_03`,
DROP COLUMN `cus_dbl_02`,
DROP COLUMN `cus_dbl_01`,
DROP COLUMN `cus_time_04`,
DROP COLUMN `cus_time_03`,
DROP COLUMN `cus_time_02`,
DROP COLUMN `cus_time_01`,
DROP COLUMN `cus_str_05`,
DROP COLUMN `cus_str_04`,
DROP COLUMN `cus_str_03`,
DROP COLUMN `cus_str_02`,
DROP COLUMN `cus_str_01`,
DROP COLUMN `cus_int_10`,
DROP COLUMN `cus_int_09`,
DROP COLUMN `cus_int_08`,
DROP COLUMN `cus_int_07`,
DROP COLUMN `cus_int_06`,
DROP COLUMN `cus_int_05`,
DROP COLUMN `cus_int_04`,
DROP COLUMN `cus_int_03`,
DROP COLUMN `cus_int_02`,
DROP COLUMN `cus_int_01`;

ALTER TABLE `m_tracker_bug` RENAME TO  `m_prj_bug` ;
ALTER TABLE `m_prj_bug` 
DROP FOREIGN KEY `FK_m_tracker_bug_1`,
DROP FOREIGN KEY `FK_m_tracker_bug_2`,
DROP FOREIGN KEY `FK_m_tracker_bug_3`,
DROP FOREIGN KEY `FK_m_tracker_bug_4`,
DROP FOREIGN KEY `FK_m_tracker_bug_5`;
ALTER TABLE `m_prj_bug` 
;
ALTER TABLE `m_prj_bug` RENAME INDEX `FK_m_tracker_bug_5` TO `FK_m_prj_bug_5`;
ALTER TABLE `m_prj_bug` ALTER INDEX `FK_m_prj_bug_5` VISIBLE;
ALTER TABLE `m_prj_bug` 
ADD CONSTRAINT `FK_m_prj_bug_1`
  FOREIGN KEY (`createdUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_bug_2`
  FOREIGN KEY (`assignUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_bug_3`
  FOREIGN KEY (`projectId`)
  REFERENCES `m_prj_project` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_bug_4`
  FOREIGN KEY (`milestoneId`)
  REFERENCES `m_prj_milestone` (`id`)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_bug_5`
  FOREIGN KEY (`sAccountId`)
  REFERENCES `s_account` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `m_prj_bug` 
;
ALTER TABLE `m_prj_bug` RENAME INDEX `FK_m_tracker_bug_6` TO `FK_m_prj_bug_6`;
ALTER TABLE `m_prj_bug` ALTER INDEX `FK_m_prj_bug_6` VISIBLE;
ALTER TABLE `m_prj_bug` RENAME INDEX `FK_m_tracker_bug_4` TO `FK_m_prj_bug_4`;
ALTER TABLE `m_prj_bug` ALTER INDEX `FK_m_prj_bug_4` VISIBLE;
ALTER TABLE `m_prj_bug` RENAME INDEX `FK_m_tracker_bug_1` TO `FK_m_prj_bug_1`;
ALTER TABLE `m_prj_bug` ALTER INDEX `FK_m_prj_bug_1` VISIBLE;
ALTER TABLE `m_prj_bug` RENAME INDEX `FK_m_tracker_bug_2` TO `FK_m_prj_bug_2`;
ALTER TABLE `m_prj_bug` ALTER INDEX `FK_m_prj_bug_2` VISIBLE;  

ALTER TABLE `m_tracker_component`  RENAME TO  `m_prj_component` ;
ALTER TABLE `m_prj_component` 
DROP FOREIGN KEY `FK_m_tracker_component_1`,
DROP FOREIGN KEY `FK_m_tracker_component_2`,
DROP FOREIGN KEY `FK_m_tracker_component_3`,
DROP FOREIGN KEY `FK_m_tracker_component_4`;
ALTER TABLE `m_prj_component` 
;
ALTER TABLE `m_prj_component` RENAME INDEX `FK_m_tracker_component_1` TO `FK_m_prj_component_1`;
ALTER TABLE `m_prj_component` ALTER INDEX `FK_m_prj_component_1` VISIBLE;
ALTER TABLE `m_prj_component` RENAME INDEX `FK_m_tracker_component_4` TO `FK_m_prj_component_4`;
ALTER TABLE `m_prj_component` ALTER INDEX `FK_m_prj_component_4` VISIBLE;
ALTER TABLE `m_prj_component` RENAME INDEX `FK_m_tracker_component_2` TO `FK_m_prj_component_2`;
ALTER TABLE `m_prj_component` ALTER INDEX `FK_m_prj_component_2` VISIBLE;
ALTER TABLE `m_prj_component` RENAME INDEX `FK_m_tracker_component_3` TO `FK_m_prj_component_3`;
ALTER TABLE `m_prj_component` ALTER INDEX `FK_m_prj_component_3` VISIBLE;
ALTER TABLE `m_prj_component` 
ADD CONSTRAINT `FK_m_prj_component_1`
  FOREIGN KEY (`projectId`)
  REFERENCES `m_prj_project` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_component_2`
  FOREIGN KEY (`userlead`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_component_3`
  FOREIGN KEY (`createdUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_component_4`
  FOREIGN KEY (`sAccountId`)
  REFERENCES `s_account` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


ALTER TABLE `m_tracker_version` RENAME TO  `m_prj_version` ;
ALTER TABLE `m_prj_version` 
DROP FOREIGN KEY `FK_m_tracker_version_1`,
DROP FOREIGN KEY `FK_m_tracker_version_2`,
DROP FOREIGN KEY `FK_m_tracker_version_3`;
ALTER TABLE `m_prj_version` 
;
ALTER TABLE `m_prj_version` RENAME INDEX `FK_m_tracker_version_1` TO `FK_m_prj_version_1`;
ALTER TABLE `m_prj_version` ALTER INDEX `FK_m_prj_version_1` VISIBLE;
ALTER TABLE `m_prj_version` RENAME INDEX `FK_m_tracker_version_3` TO `FK_m_prj_version_3`;
ALTER TABLE `m_prj_version` ALTER INDEX `FK_m_prj_version_3` VISIBLE;
ALTER TABLE `m_prj_version` RENAME INDEX `FK_m_tracker_version_2` TO `FK_m_prj_version_2`;
ALTER TABLE `m_prj_version` ALTER INDEX `FK_m_prj_version_2` VISIBLE;
ALTER TABLE `m_prj_version` 
ADD CONSTRAINT `FK_m_prj_version_1`
  FOREIGN KEY (`projectId`)
  REFERENCES `m_prj_project` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_version_2`
  FOREIGN KEY (`createdUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_version_3`
  FOREIGN KEY (`sAccountId`)
  REFERENCES `s_account` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;





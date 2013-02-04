ALTER TABLE `m_tracker_bug` ADD COLUMN `milestoneId` INT NULL  AFTER `sAccountId` , 
  ADD CONSTRAINT `FK_m_tracker_bug_6`
  FOREIGN KEY (`milestoneId` )
  REFERENCES `m_prj_milestone` (`id` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `FK_m_tracker_bug_6_idx` (`milestoneId` ASC) ;
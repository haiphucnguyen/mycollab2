ALTER TABLE `m_prj_milestone` DROP FOREIGN KEY `PK_m_prj_milestone_1` ;
ALTER TABLE `m_prj_milestone` CHANGE COLUMN `owner` `owner` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL  , 
  ADD CONSTRAINT `PK_m_prj_milestone_1`
  FOREIGN KEY (`owner` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
  
drop table `m_pm_messages`;
drop table `m_pm_sent_messages`;

ALTER TABLE `m_prj_message` 
  ADD CONSTRAINT `FK_m_prj_message_1`
  FOREIGN KEY (`posteduser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_message_1_idx` (`posteduser` ASC) ;

ALTER TABLE `s_activitystream` ADD COLUMN `createdUserFullName` VARCHAR(100) NULL, 
  ADD CONSTRAINT `FK_m_crm_activitystream_2`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `FK_m_crm_activitystream_2_idx` (`createdUser` ASC) ;
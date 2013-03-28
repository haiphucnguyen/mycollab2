ALTER TABLE `m_tracker_bug` CHANGE COLUMN `summary` `summary` VARCHAR(4000) NOT NULL  , 
CHANGE COLUMN `detail` `detail` TEXT NULL DEFAULT NULL  , 
CHANGE COLUMN `environment` `environment` TEXT NULL DEFAULT NULL  , 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_tracker_component` CHANGE COLUMN `componentname` `componentname` VARCHAR(1000) NOT NULL  , 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_tracker_version` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  , 
CHANGE COLUMN `versionname` `versionname` VARCHAR(255) NOT NULL  ;

ALTER TABLE `m_crm_campaign` CHANGE COLUMN `campaignName` `campaignName` VARCHAR(255) NOT NULL  , 
CHANGE COLUMN `objective` `objective` TEXT NULL DEFAULT NULL  , 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_call` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  , 
CHANGE COLUMN `result` `result` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_account` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_case` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  , 
CHANGE COLUMN `resolution` `resolution` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_contact` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_lead` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_meeting` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_note` CHANGE COLUMN `note` `note` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_opportunity` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;

ALTER TABLE `m_crm_task` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;
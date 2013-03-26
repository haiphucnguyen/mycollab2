ALTER TABLE `m_prj_task` CHANGE COLUMN `notes` `notes` TEXT NULL DEFAULT NULL  ;
ALTER TABLE `m_prj_project` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;
ALTER TABLE `m_prj_problem` CHANGE COLUMN `description` `description` TEXT NULL  ;
ALTER TABLE `m_prj_risk` CHANGE COLUMN `description` `description` TEXT NULL  ;
ALTER TABLE `m_prj_milestone` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;
ALTER TABLE `m_prj_message` DROP COLUMN `isNews` , DROP COLUMN `messagehtml` , CHANGE COLUMN `message` `message` TEXT NULL DEFAULT NULL  ;
ALTER TABLE `m_prj_role` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;
ALTER TABLE `m_prj_task_list` CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL  ;
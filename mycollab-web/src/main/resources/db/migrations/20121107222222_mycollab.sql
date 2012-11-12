ALTER TABLE `m_tracker_bug` CHANGE COLUMN `status` `status` VARCHAR(45) NOT NULL;
ALTER TABLE `m_tracker_bug` CHANGE COLUMN `severity` `severity` VARCHAR(45) NULL DEFAULT NULL  ;
ALTER TABLE `m_tracker_bug` CHANGE COLUMN `priority` `priority` VARCHAR(45) NULL DEFAULT NULL  ;
ALTER TABLE `m_comment` 
CHANGE COLUMN `comment` `comment` TEXT CHARACTER SET 'utf8mb4' NULL DEFAULT NULL ;

ALTER TABLE `m_tracker_version` 
ADD COLUMN `status` VARCHAR(45) NULL;

ALTER TABLE `m_tracker_component` 
ADD COLUMN `status` VARCHAR(45) NULL;
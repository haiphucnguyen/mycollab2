ALTER TABLE `m_tracker_component` DROP COLUMN `createdDate` , 
ADD COLUMN `lastUpdatedTime` DATETIME NULL, ADD COLUMN `createdTime` DATETIME NULL;
ALTER TABLE `m_tracker_version` DROP COLUMN `createdDate` , 
ADD COLUMN `lastUpdatedTime` DATETIME NULL, ADD COLUMN `createdTime` DATETIME NULL;
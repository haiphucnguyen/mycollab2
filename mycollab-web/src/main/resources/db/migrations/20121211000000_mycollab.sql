ALTER TABLE `m_prj_task_list` ADD COLUMN `createdTime` DATETIME NULL, ADD COLUMN `lastUpdatedTime` DATETIME NULL;
ALTER TABLE `m_prj_task` ADD COLUMN `createdTime` DATETIME NULL, ADD COLUMN `lastUpdatedTime` DATETIME NULL;
ALTER TABLE `m_prj_project` ADD COLUMN `createdTime` DATETIME NULL, ADD COLUMN `lastUpdatedTime` DATETIME NULL;
ALTER TABLE `m_prj_news` ADD COLUMN `createdTime` DATETIME NULL, ADD COLUMN `lastUpdatedTime` DATETIME NULL;
ALTER TABLE `m_prj_milestone` ADD COLUMN `createdTime` DATETIME NULL, ADD COLUMN `lastUpdatedTime` DATETIME NULL;
ALTER TABLE `m_prj_message` ADD COLUMN `createdTime` DATETIME NULL, ADD COLUMN `lastUpdatedTime` DATETIME NULL;
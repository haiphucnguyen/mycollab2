ALTER TABLE `m_prj_message` ADD COLUMN `isStick` BOOLEAN NULL;
ALTER TABLE `m_crm_meeting` ADD COLUMN `isClosed` VARCHAR(45) NULL ;
ALTER TABLE `m_crm_call` ADD COLUMN `isClosed` BOOLEAN NULL;
ALTER TABLE `m_crm_task` ADD COLUMN `isClosed` BOOLEAN NULL;
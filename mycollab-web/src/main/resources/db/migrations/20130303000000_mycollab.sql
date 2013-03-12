ALTER TABLE `m_prj_milestone` DROP COLUMN `iscompleted` , ADD COLUMN `status` VARCHAR(45) NULL;
UPDATE m_prj_milestone SET status='Open';
ALTER TABLE `m_prj_milestone` CHANGE COLUMN `status` `status` VARCHAR(45) NOT NULL  ;


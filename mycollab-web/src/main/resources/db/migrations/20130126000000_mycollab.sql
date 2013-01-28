ALTER TABLE `m_prj_task_list` ADD COLUMN `status` VARCHAR(45) NULL;
UPDATE m_prj_task_list SET m_prj_task_list.status='Open' WHERE m_prj_task_list.id>0;
ALTER TABLE `m_prj_task_list` CHANGE COLUMN `status` `status` VARCHAR(45) NOT NULL  ;
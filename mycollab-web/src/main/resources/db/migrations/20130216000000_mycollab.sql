UPDATE `m_prj_task` SET percentagecomplete=0 WHERE percentagecomplete is null;
ALTER TABLE `m_prj_task` CHANGE COLUMN `percentagecomplete` `percentagecomplete` DOUBLE NOT NULL  ;
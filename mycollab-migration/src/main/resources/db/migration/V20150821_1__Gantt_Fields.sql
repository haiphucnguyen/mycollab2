ALTER TABLE `m_prj_task`
ADD COLUMN `ganttindex` INT(5) UNSIGNED NULL;
ALTER TABLE `m_prj_predecessor`
ADD COLUMN `lagDay` INT(3) NOT NULL DEFAULT 0;
DROP TABLE `m_prj_problem`;
ALTER TABLE `m_prj_risk`
ADD COLUMN `startDate` DATETIME NULL,
ADD COLUMN `endDate` DATETIME NULL;
ALTER TABLE `m_prj_project`
DROP COLUMN `actualEndDate`,
DROP COLUMN `actualStartDate`;
ALTER TABLE `m_prj_milestone`
DROP COLUMN `actualenddate`,
DROP COLUMN `actualstartdate`;
ALTER TABLE `m_prj_task`
DROP COLUMN `actualEndDate`,
DROP COLUMN `actualStartDate`;

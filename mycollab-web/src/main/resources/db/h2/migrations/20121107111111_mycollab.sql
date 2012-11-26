drop table if exists `m_prj_relation`;
drop table `m_prj_risk_source`;
ALTER TABLE `m_prj_problem` DROP COLUMN `problemsourceid` ;
drop table `m_prj_problem_source`;
ALTER TABLE `m_prj_problem` ADD COLUMN `source` VARCHAR(45) NULL ;
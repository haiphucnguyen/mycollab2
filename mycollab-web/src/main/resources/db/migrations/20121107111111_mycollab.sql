drop table if exists `m_prj_relation`;
ALTER TABLE `m_prj_risk` DROP FOREIGN KEY `FK_m_prj_risk_4` ;
ALTER TABLE `m_prj_risk` DROP COLUMN `risksourceid`, DROP INDEX `FK_m_prj_risk_4` ;
drop table `m_prj_risk_source`;
ALTER TABLE `m_prj_problem` DROP COLUMN `problemsourceid` ;
drop table `m_prj_problem_source`;
ALTER TABLE `m_prj_message` DROP FOREIGN KEY `FK_m_prj_message_1` ;
ALTER TABLE `m_prj_message` DROP COLUMN `categoryid` , ADD COLUMN `category` VARCHAR(45) NULL 
, DROP INDEX `FK_m_prj_message_1` ;
drop table `m_prj_message_category`;
ALTER TABLE `m_prj_risk` ADD COLUMN `source` VARCHAR(45) NULL ;
ALTER TABLE `m_prj_problem` ADD COLUMN `source` VARCHAR(45) NULL ;
CREATE TABLE `m_prj_standup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sAccountId` int(11) NOT NULL,
  `projectId` int(11) unsigned NOT NULL,
  `whatlastday` text,
  `whattoday` text,
  `whatproblem` text,
  `forday` datetime NOT NULL,
  `logBy` varchar(45) NOT NULL,
  `createTime` datetime NOT NULL,
  `lastUpdatedTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_standup_1_idx` (`sAccountId`),
  KEY `FK_m_prj_standup_2_idx` (`logBy`),
  KEY `FK_m_prj_standup_3_idx` (`projectId`),
  CONSTRAINT `FK_m_prj_standup_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_standup_2` FOREIGN KEY (`logBy`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_standup_3` FOREIGN KEY (`projectId`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8;

ALTER TABLE `m_prj_task` ADD COLUMN `investHours` DOUBLE NULL;
ALTER TABLE `m_tracker_bug` ADD COLUMN `investHours` DOUBLE NULL;


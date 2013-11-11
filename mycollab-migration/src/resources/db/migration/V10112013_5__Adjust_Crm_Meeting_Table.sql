ALTER TABLE `m_crm_meeting` 
CHANGE COLUMN `isRecurrence` `isRecurrence` BIT(1) NULL DEFAULT NULL ;

ALTER TABLE `m_crm_meeting` 
ADD COLUMN `isNotified` BIT(1) NULL,
ADD COLUMN `isNotifiedPrior` INT(11) NULL;

CREATE TABLE `m_crm_meeting_invitees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meetingId` int(11) NOT NULL,
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isModerator` bit(1) NOT NULL,
  `status` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `source` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_meeting_invitees_1_idx` (`meetingId`),
  CONSTRAINT `FK_m_crm_meeting_invitees_1` FOREIGN KEY (`meetingId`) REFERENCES `m_crm_meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

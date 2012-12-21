CREATE TABLE `m_crm_activitystream` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sAccountId` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_activitystream_1_idx` (`sAccountId`),
  CONSTRAINT `FK_m_crm_activitystream_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


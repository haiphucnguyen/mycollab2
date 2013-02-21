CREATE TABLE `s_relay_mail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bodyContent` text NOT NULL,
  `recipients` text NOT NULL,
  `subject` text NOT NULL,
  `sAccountId` int(11) NOT NULL,
  `fromName` varchar(255) NOT NULL,
  `fromEmail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_relay_mail_1_idx` (`sAccountId`),
  CONSTRAINT `FK_s_relay_mail_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ;


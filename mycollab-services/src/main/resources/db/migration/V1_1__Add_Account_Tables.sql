CREATE TABLE `s_user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `accountId` int(11) NOT NULL,
  `isAdmin` bit(1) NOT NULL,
  `isAccountOwner` bit(1) NOT NULL,
  `roleId` int(11) DEFAULT NULL,
  `registeredTime` datetime NOT NULL,
  `registerStatus` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastAccessedTime` datetime DEFAULT NULL,
  `registrationSource` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_user_account_1` (`accountId`),
  KEY `FK_s_user_account_3` (`roleId`),
  KEY `FK_s_user_account_2_idx` (`username`),
  CONSTRAINT `FK_s_user_account_1` FOREIGN KEY (`accountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_s_user_account_2` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_s_user_account_3` FOREIGN KEY (`roleId`) REFERENCES `s_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


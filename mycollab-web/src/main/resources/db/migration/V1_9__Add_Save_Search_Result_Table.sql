CREATE TABLE `s_save_search_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saveUser` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sAccountId` int(11) NOT NULL,
  `queryText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_save_search_result_1_idx` (`sAccountId`),
  KEY `FK_FK_s_save_search_result_2_idx` (`saveUser`),
  CONSTRAINT `FK_s_save_search_result_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FK_s_save_search_result_2` FOREIGN KEY (`saveUser`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `s_user_preference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `lastModuleVisit` varchar(45) DEFAULT NULL,
  `lastAccessedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_user_preference_1_idx` (`username`),
  CONSTRAINT `FK_s_user_preference_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
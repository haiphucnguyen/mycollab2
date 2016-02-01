CREATE TABLE `s_live_instances` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `appVersion` VARCHAR(45) NOT NULL,
  `javaVersion` VARCHAR(45) NOT NULL,
  `installedDate` DATETIME NOT NULL,
  `sysId` VARCHAR(100) NOT NULL,
  `sysProperties` VARCHAR(100) NOT NULL,
  `lastUpdatedDate` DATETIME NOT NULL,
  PRIMARY KEY (`id`));
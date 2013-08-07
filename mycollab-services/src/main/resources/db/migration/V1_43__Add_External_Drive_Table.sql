CREATE  TABLE `m_ecm_external_drive` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `storageName` VARCHAR(100) NOT NULL ,
  `owner` VARCHAR(45) NOT NULL ,
  `accessToken` VARCHAR(100) NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  `lastUpdatedTime` DATETIME NOT NULL ,
  `folderName` VARCHAR(256) NOT NULL ,
  PRIMARY KEY (`id`) );
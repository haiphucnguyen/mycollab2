CREATE  TABLE `m_prj_time` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NOT NULL ,
  `typeId` INT NOT NULL ,
  `createdUser` VARCHAR(45) NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  `lastUpdatedTime` DATETIME NOT NULL ,
  `timeValue` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`) );
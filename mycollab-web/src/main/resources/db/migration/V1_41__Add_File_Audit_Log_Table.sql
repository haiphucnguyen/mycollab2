CREATE  TABLE `m_ecm_activity_log` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `path` TEXT NOT NULL ,
  `createdUser` VARCHAR(45) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) );
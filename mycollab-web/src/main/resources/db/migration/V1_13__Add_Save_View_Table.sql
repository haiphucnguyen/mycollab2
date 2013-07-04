CREATE  TABLE `s_table_customize_view` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `createdUser` VARCHAR(45) NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  `viewId` VARCHAR(45) NOT NULL ,
  `viewInfo` TEXT NOT NULL ,
  PRIMARY KEY (`id`) );
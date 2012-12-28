CREATE  TABLE `s_user_preference` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NULL ,
  `lastModuleVisit` VARCHAR(45) NULL ,
  `lastAccessedTime` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_s_user_preference_1_idx` (`username` ASC) ,
  CONSTRAINT `FK_s_user_preference_1`
    FOREIGN KEY (`username` )
    REFERENCES `s_user` (`username` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
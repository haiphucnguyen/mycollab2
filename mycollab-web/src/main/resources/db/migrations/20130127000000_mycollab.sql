CREATE  TABLE `s_user_tracking` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `userAgent` TEXT NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  `sAccountId` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_s_user_tracking_1_idx` (`sAccountId` ASC) ,
  INDEX `FK_s_user_tracking_2_idx` (`username` ASC) ,
  CONSTRAINT `FK_s_user_tracking_1`
    FOREIGN KEY (`sAccountId` )
    REFERENCES `s_account` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_s_user_tracking_2`
    FOREIGN KEY (`username` )
    REFERENCES `s_user` (`username` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
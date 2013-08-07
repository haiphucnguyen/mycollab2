CREATE  TABLE `s_user_account_invitation` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `accountId` INT NOT NULL ,
  `invitationStatus` VARCHAR(45) NOT NULL ,
  `sentDate` DATETIME NULL ,
  `createdTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) );
  
ALTER TABLE `s_user_account_invitation` 
  ADD CONSTRAINT `FK_s_user_account_invitation_1`
  FOREIGN KEY (`username` )
  REFERENCES `s_user` (`username` )
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  ADD CONSTRAINT `FK_s_user_account_invitation_2`
  FOREIGN KEY (`accountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_s_user_account_invitation_1_idx` (`username` ASC) 
, ADD INDEX `FK_s_user_account_invitation_2_idx` (`accountId` ASC) ;
DELETE * FROM s_user_preference;
ALTER TABLE `s_user_preference` ADD COLUMN `sAccountId` INT(11) NOT NULL, 
  ADD CONSTRAINT `FK_s_user_preference_2`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_s_user_preference_2_idx` (`sAccountId` ASC) ;
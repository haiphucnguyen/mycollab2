ALTER TABLE `s_user` DROP FOREIGN KEY `FK_s_user_2` ;
ALTER TABLE `s_user` DROP COLUMN `countryid` , CHANGE COLUMN `timezoneid` `timezone` VARCHAR(45) NULL DEFAULT NULL  , ADD COLUMN `language` VARCHAR(45) NULL, ADD COLUMN `country` VARCHAR(45) NULL, ADD COLUMN `workPhone` VARCHAR(20) NULL, ADD COLUMN `homePhone` VARCHAR(20) NULL, ADD COLUMN `facebookAccount` VARCHAR(45) NULL, ADD COLUMN `twitterAccount` VARCHAR(45) NULL , ADD COLUMN `skypeContact` VARCHAR(45) NULL 
, DROP INDEX `FK_s_user_2` ;
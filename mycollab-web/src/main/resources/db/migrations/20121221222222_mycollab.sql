ALTER TABLE `s_activitystream` ADD COLUMN `module` VARCHAR(45) NULL;
ALTER TABLE `m_comment` DROP COLUMN `lastUpdatedTime` , DROP COLUMN `createdTime` , DROP COLUMN `commentid` , CHANGE COLUMN `owner` `createdUser` VARCHAR(45) NOT NULL  , CHANGE COLUMN `posteddate` `createdTime` DATETIME NOT NULL  , ADD COLUMN `type` VARCHAR(45) NULL, ADD COLUMN `typeId` VARCHAR(45) NULL, ADD COLUMN `sAccountId` INT NULL, 
  ADD CONSTRAINT `FK_m_comment_1`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_comment_1_idx` (`sAccountId` ASC) ;
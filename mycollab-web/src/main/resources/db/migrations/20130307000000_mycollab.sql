ALTER TABLE `m_prj_task` DROP COLUMN `investHours` , 
DROP COLUMN `ismilestone` , DROP COLUMN `actualCost` , 
DROP COLUMN `cost` , ADD COLUMN `originalEstimate` DOUBLE NULL , 
ADD COLUMN `remainEstimate` DOUBLE NULL;

CREATE  TABLE `m_prj_time_logging` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `projectId` INT UNSIGNED NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `typeid` INT NOT NULL ,
  `note` TEXT NULL ,
  `logValue` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_prj_time_logging_1_idx` (`projectId` ASC) ,
  CONSTRAINT `FK_m_prj_time_logging_1`
    FOREIGN KEY (`projectId` )
    REFERENCES `m_prj_project` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
ALTER TABLE `m_tracker_bug` CHANGE COLUMN `estimateTime` `estimateTime` DOUBLE NULL DEFAULT NULL  , 
CHANGE COLUMN `estimateRemainTime` `estimateRemainTime` DOUBLE NULL DEFAULT NULL  ;    
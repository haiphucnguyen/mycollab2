ALTER TABLE `s_role_permission` CHANGE COLUMN `pathid` `module` VARCHAR(45) NOT NULL  , 
CHANGE COLUMN `isAuthorz` `hasPermission` VARCHAR(45) NOT NULL, 
ADD COLUMN `type` VARCHAR(45) NOT NULL  AFTER `roleid` ;

CREATE  TABLE `s_user_permission` (
  `Id` INT NOT NULL AUTO_INCREMENT ,
  `module` VARCHAR(45) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `hasPermission` VARCHAR(45) NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Id`) ,
  INDEX `s_user_permission_idx` (`username` ASC) ,
  CONSTRAINT `s_user_permission`
    FOREIGN KEY (`username` )
    REFERENCES `s_user` (`username` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `s_user` ADD COLUMN `isAdmin` TINYINT NULL;

ALTER TABLE `m_hr_employee` DROP FOREIGN KEY `FK_m_hr_employee_1` ;
ALTER TABLE `m_hr_employee` DROP COLUMN `code` , DROP COLUMN `supervisorid` 
, DROP INDEX `FK922516433B88F77C` 
, DROP INDEX `Index_5` , RENAME TO  `s_user_information` ;
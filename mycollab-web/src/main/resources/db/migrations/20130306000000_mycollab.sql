drop table `s_title`;
CREATE  TABLE `s_account_settings` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `sAccountId` INT NOT NULL ,
  `logoPath` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_s_account_settings_1_idx` (`sAccountId` ASC) ,
  CONSTRAINT `FK_s_account_settings_1`
    FOREIGN KEY (`sAccountId` )
    REFERENCES `s_account` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
ALTER TABLE `s_account` ADD COLUMN `status` VARCHAR(45) NULL, ADD COLUMN `paymentMethod` VARCHAR(45) NULL;
ALTER TABLE `s_billing_plan` CHANGE COLUMN `billingType` `billingType` VARCHAR(45) NULL  , 
    CHANGE COLUMN `numUsers` `numUsers` INT(11) NULL  , 
    CHANGE COLUMN `volume` `volume` INT(11) NULL  , 
    CHANGE COLUMN `numProjects` `numProjects` INT(11) NULL  ;
ALTER TABLE `s_billing_plan` ADD COLUMN `pricing` DOUBLE NULL, 
ADD COLUMN `description` VARCHAR(1000) NULL;

UPDATE `s_billing_plan` SET billingType='Compact', numUsers=5, volume=5, numProjects=10, pricing=19.9 WHERE 1=1;

ALTER TABLE `s_billing_plan` CHANGE COLUMN `billingType` `billingType` VARCHAR(45) NOT NULL  , 
CHANGE COLUMN `numUsers` `numUsers` INT(11) NOT NULL  , 
CHANGE COLUMN `volume` `volume` INT(11) NOT NULL  , 
CHANGE COLUMN `numProjects` `numProjects` INT(11) NOT NULL  , 
CHANGE COLUMN `pricing` `pricing` DOUBLE NOT NULL  ;

ALTER TABLE `s_account` 
ADD COLUMN `pricing` DOUBLE NULL, 
ADD COLUMN `pricingEffectFrom` DATETIME NULL, 
ADD COLUMN `pricingEffectTo` DATETIME NULL;

UPDATE `s_account` SET createdTime='2013-01-01 00:00:00', status='Active', paymentMethod='Credit Card' WHERE 1=1;

ALTER TABLE `s_account` CHANGE COLUMN `createdTime` `createdTime` TIMESTAMP NOT NULL  , 
CHANGE COLUMN `status` `status` VARCHAR(45) NOT NULL  , 
CHANGE COLUMN `paymentMethod` `paymentMethod` VARCHAR(45) NOT NULL  ;

ALTER TABLE `s_account_settings` ADD COLUMN `defaultTimezone` VARCHAR(45) NULL;
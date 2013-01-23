ALTER TABLE `m_crm_contact` CHANGE COLUMN `primPostalCode` `primPostalCode` VARCHAR(45) NULL DEFAULT NULL  , 
CHANGE COLUMN `primCountry` `primCountry` VARCHAR(45) NULL DEFAULT NULL  , 
CHANGE COLUMN `otherPostalCode` `otherPostalCode` VARCHAR(45) NULL DEFAULT NULL  , 
CHANGE COLUMN `otherCountry` `otherCountry` VARCHAR(45) NULL DEFAULT NULL  ;
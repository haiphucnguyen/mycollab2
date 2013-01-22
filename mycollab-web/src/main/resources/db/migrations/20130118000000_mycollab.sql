ALTER TABLE `m_crm_account` DROP FOREIGN KEY `FK_m_crm_account_4` , DROP FOREIGN KEY `FK_m_crm_account_3` ;
ALTER TABLE `m_crm_account` DROP COLUMN `shippingCountryId` , DROP COLUMN `countryId` , ADD COLUMN `billingCountry` VARCHAR(45) NULL, ADD COLUMN `shippingCountry` VARCHAR(45) NULL
, DROP INDEX `FK_m_crm_account_4` 
, DROP INDEX `FK_m_crm_account_3` ;
CREATE  TABLE `s_account_currency` (
  `id` INT NOT NULL ,
  `currencyid` INT NOT NULL ,
  `accountid` INT NOT NULL ,
  PRIMARY KEY (`id`) );
  ALTER TABLE `s_currency` CHANGE COLUMN `id` `id` INT(10) NOT NULL AUTO_INCREMENT  ;
ALTER TABLE `m_crm_opportunity` CHANGE COLUMN `currencyid` `currencyid` INT(10) NULL DEFAULT NULL  , 
  ADD CONSTRAINT `FK_m_crm_opportunity_8`
  FOREIGN KEY (`currencyid` )
  REFERENCES `s_currency` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_crm_opportunity_8_idx` (`currencyid` ASC) ;  
drop table `m_crm_accounts_contacts`;
ALTER TABLE `m_crm_contact` ADD COLUMN `accountId` INT UNSIGNED NULL, 
  ADD CONSTRAINT `FK_m_crm_contact_9`
  FOREIGN KEY (`accountId` )
  REFERENCES `m_crm_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_crm_contact_9_idx` (`accountId` ASC) ;
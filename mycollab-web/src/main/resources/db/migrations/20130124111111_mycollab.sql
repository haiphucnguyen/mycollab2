ALTER TABLE `m_crm_note` DROP FOREIGN KEY `FK_m_crm_note_3` ;
ALTER TABLE `m_crm_note` 
  ADD CONSTRAINT `FK_m_crm_note_3`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_call` DROP FOREIGN KEY `FK_m_crm_call_1` ;
ALTER TABLE `m_crm_call` 
  ADD CONSTRAINT `FK_m_crm_call_1`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_campaign` DROP FOREIGN KEY `FK_m_crm_campaign_5` ;
ALTER TABLE `m_crm_campaign` 
  ADD CONSTRAINT `FK_m_crm_campaign_5`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_case` DROP FOREIGN KEY `FK_m_crm_case_2` ;
ALTER TABLE `m_crm_case` 
  ADD CONSTRAINT `FK_m_crm_case_2`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_contact` DROP FOREIGN KEY `FK_m_crm_contact_6` ;
ALTER TABLE `m_crm_contact` 
  ADD CONSTRAINT `FK_m_crm_contact_6`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_contract` DROP FOREIGN KEY `FK_m_crm_contract_5` ;
ALTER TABLE `m_crm_contract` 
  ADD CONSTRAINT `FK_m_crm_contract_5`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_customer` DROP FOREIGN KEY `FK_crm_customer_3` , DROP FOREIGN KEY `FK_crm_customer_2` , DROP FOREIGN KEY `FK_crm_customer_1` ;
ALTER TABLE `m_crm_customer` CHANGE COLUMN `title` `title` VARCHAR(45) NULL, CHANGE COLUMN `assignto` `assignUser` VARCHAR(45) NULL DEFAULT NULL  , 
  ADD CONSTRAINT `FK_crm_customer_3`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, DROP INDEX `FK_crm_customer_2` ;
ALTER TABLE `m_crm_lead` DROP FOREIGN KEY `FK_m_crm_lead_5` ;
ALTER TABLE `m_crm_lead` 
  ADD CONSTRAINT `FK_m_crm_lead_5`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_meeting` DROP FOREIGN KEY `FK_m_crm_meeting_1` ;
ALTER TABLE `m_crm_meeting` 
  ADD CONSTRAINT `FK_m_crm_meeting_1`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE `m_crm_opportunity` DROP FOREIGN KEY `FK_m_crm_opportunity_5` ;
ALTER TABLE `m_crm_opportunity` 
  ADD CONSTRAINT `FK_m_crm_opportunity_5`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_product` DROP FOREIGN KEY `FK_m_crm_product_4` ;
ALTER TABLE `m_crm_product` 
  ADD CONSTRAINT `FK_m_crm_product_4`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_crm_task` DROP FOREIGN KEY `FK_m_crm_task_4` ;
ALTER TABLE `m_crm_task` 
  ADD CONSTRAINT `FK_m_crm_task_4`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
ALTER TABLE `m_prj_member` ADD COLUMN `projectRoleId` INT NULL, ADD COLUMN `isAdmin` TINYINT(1) NULL;
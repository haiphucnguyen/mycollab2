ALTER TABLE `m_crm_contacts_opportunities` ADD COLUMN `decisionRole` VARCHAR(45) NULL;
ALTER TABLE `m_crm_accounts_leads` ADD COLUMN `isConvertRel` BIT(1) NULL;
CREATE TABLE `m_crm_contacts_leads` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contactId` INT(10) UNSIGNED NOT NULL,
  `leadId` INT(10) UNSIGNED NOT NULL,
  `isConvertRel` BIT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_m_crm_contacts_leads_1_idx` (`contactId` ASC),
  INDEX `FK_m_crm_contacts_leads_2_idx` (`leadId` ASC),
  CONSTRAINT `FK_m_crm_contacts_leads_1`
    FOREIGN KEY (`contactId`)
    REFERENCES `m_crm_contact` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contacts_leads_2`
    FOREIGN KEY (`leadId`)
    REFERENCES `m_crm_lead` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

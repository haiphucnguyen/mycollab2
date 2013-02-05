CREATE  TABLE `m_crm_contacts_opportunities` (
  `id` INT UNSIGNED NOT NULL ,
  `contactId` INT UNSIGNED NOT NULL ,
  `opportunityId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_contacts_opportunities_1_idx` (`contactId` ASC) ,
  INDEX `FK_m_crm_contacts_opportunities_2_idx` (`opportunityId` ASC) ,
  CONSTRAINT `FK_m_crm_contacts_opportunities_1`
    FOREIGN KEY (`contactId` )
    REFERENCES `m_crm_contact` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contacts_opportunities_2`
    FOREIGN KEY (`opportunityId` )
    REFERENCES `m_crm_opportunity` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
CREATE  TABLE `m_crm_campaigns_accounts` (
  `id` INT UNSIGNED NOT NULL ,
  `campaignId` INT UNSIGNED NOT NULL ,
  `accountId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_campaigns_accounts_1_idx` (`campaignId` ASC) ,
  INDEX `FK_m_crm_campaigns_accounts_2_idx` (`accountId` ASC) ,
  CONSTRAINT `FK_m_crm_campaigns_accounts_1`
    FOREIGN KEY (`campaignId` )
    REFERENCES `m_crm_campaign` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_campaigns_accounts_2`
    FOREIGN KEY (`accountId` )
    REFERENCES `m_crm_account` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
CREATE  TABLE `m_crm_campaigns_contacts` (
  `id` INT UNSIGNED NOT NULL ,
  `campaignId` INT UNSIGNED NOT NULL ,
  `contactId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_campaigns_contacts_1_idx` (`campaignId` ASC) ,
  INDEX `FK_m_crm_campaigns_contacts_2_idx` (`contactId` ASC) ,
  CONSTRAINT `FK_m_crm_campaigns_contacts_1`
    FOREIGN KEY (`campaignId` )
    REFERENCES `m_crm_campaign` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_campaigns_contacts_2`
    FOREIGN KEY (`contactId` )
    REFERENCES `m_crm_contact` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
CREATE  TABLE `m_crm_campaigns_leads` (
  `id` INT UNSIGNED NOT NULL ,
  `campaignId` INT UNSIGNED NOT NULL ,
  `leadId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_campaigns_leads_1_idx` (`campaignId` ASC) ,
  INDEX `FK_m_crm_campaigns_leads_2_idx` (`leadId` ASC) ,
  CONSTRAINT `FK_m_crm_campaigns_leads_1`
    FOREIGN KEY (`campaignId` )
    REFERENCES `m_crm_campaign` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_campaigns_leads_2`
    FOREIGN KEY (`leadId` )
    REFERENCES `m_crm_lead` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
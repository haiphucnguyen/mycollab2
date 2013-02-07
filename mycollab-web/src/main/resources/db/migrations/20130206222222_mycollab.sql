CREATE  TABLE `m_crm_opportunities_leads` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `opportunityId` INT UNSIGNED NOT NULL ,
  `leadId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_opportunities_leads_1_idx` (`opportunityId` ASC) ,
  INDEX `FK_m_crm_opportunities_leads_2_idx` (`leadId` ASC) ,
  CONSTRAINT `FK_m_crm_opportunities_leads_1`
    FOREIGN KEY (`opportunityId` )
    REFERENCES `m_crm_opportunity` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_opportunities_leads_2`
    FOREIGN KEY (`leadId` )
    REFERENCES `m_crm_lead` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
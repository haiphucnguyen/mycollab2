drop table `m_crm_accounts_opportunities`;
CREATE  TABLE `m_crm_accounts_leads` (
  `id` INT UNSIGNED NOT NULL ,
  `accountId` INT UNSIGNED NOT NULL ,
  `leadId` INT UNSIGNED NOT NULL ,
  `createTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_accounts_leads_1_idx` (`accountId` ASC) ,
  INDEX `FK_m_crm_accounts_leads_2_idx` (`leadId` ASC) ,
  CONSTRAINT `FK_m_crm_accounts_leads_1`
    FOREIGN KEY (`accountId` )
    REFERENCES `m_crm_account` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_accounts_leads_2`
    FOREIGN KEY (`leadId` )
    REFERENCES `m_crm_lead` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
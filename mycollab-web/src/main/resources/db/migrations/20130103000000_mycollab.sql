CREATE  TABLE `m_crm_accounts_contacts` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `accountId` INT UNSIGNED NOT NULL ,
  `contactId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_accounts_contacts_1_idx` (`accountId` ASC) ,
  INDEX `FK_m_crm_accounts_contacts_2_idx` (`contactId` ASC) ,
  CONSTRAINT `FK_m_crm_accounts_contacts_1`
    FOREIGN KEY (`accountId` )
    REFERENCES `m_crm_account` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_accounts_contacts_2`
    FOREIGN KEY (`contactId` )
    REFERENCES `m_crm_contact` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `m_crm_contact` DROP FOREIGN KEY `FK_m_crm_contact_1` ;
ALTER TABLE `m_crm_contact` DROP COLUMN `accountId` 
, DROP INDEX `FK_m_crm_contact_1` ;


CREATE  TABLE `m_crm_accounts_opportunities` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `accountId` INT UNSIGNED NOT NULL ,
  `opportunityId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_accounts_opportunities_1_idx` (`accountId` ASC) ,
  INDEX `FK_m_crm_accounts_opportunities_2_idx` (`opportunityId` ASC) ,
  CONSTRAINT `FK_m_crm_accounts_opportunities_1`
    FOREIGN KEY (`accountId` )
    REFERENCES `m_crm_account` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_accounts_opportunities_2`
    FOREIGN KEY (`opportunityId` )
    REFERENCES `m_crm_opportunity` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE  TABLE `m_crm_contacts_cases` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `contactId` INT UNSIGNED NOT NULL ,
  `caseId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_contacts_cases_1_idx` (`contactId` ASC) ,
  INDEX `FK_m_crm_contacts_cases_2_idx` (`caseId` ASC) ,
  CONSTRAINT `FK_m_crm_contacts_cases_1`
    FOREIGN KEY (`contactId` )
    REFERENCES `m_crm_contact` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contacts_cases_2`
    FOREIGN KEY (`caseId` )
    REFERENCES `m_crm_case` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
CREATE  TABLE `m_crm_opportunities_contacts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `opportunityId` INT UNSIGNED NOT NULL ,
  `contactId` INT UNSIGNED NOT NULL ,
  `createdTime` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_opportunities_contacts_1_idx` (`opportunityId` ASC) ,
  INDEX `FK_m_crm_opportunities_contacts_2_idx` (`contactId` ASC) ,
  CONSTRAINT `FK_m_crm_opportunities_contacts_1`
    FOREIGN KEY (`opportunityId` )
    REFERENCES `m_crm_opportunity` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_opportunities_contacts_2`
    FOREIGN KEY (`contactId` )
    REFERENCES `m_crm_contact` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);

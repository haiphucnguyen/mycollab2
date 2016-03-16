CREATE TABLE `m_prj_invoice` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `createdTime` DATETIME NOT NULL,
  `lastUpdatedTime` DATETIME NOT NULL,
  `createdUser` VARCHAR(45) NOT NULL,
  `assignUser` VARCHAR(45) NULL,
  `amount` DOUBLE NOT NULL,
  `currentId` INT(11) NULL,
  `clientId` INT(10) UNSIGNED NOT NULL,
  `contactUserFullName` VARCHAR(100) NOT NULL,
  `sAccountId` INT(11) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `note` VARCHAR(400) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_m_prj_invoice_1_idx` (`currentId` ASC),
  INDEX `FK_m_prj_invoice_2_idx` (`clientId` ASC),
  INDEX `FK_m_prj_invoice_3_idx` (`sAccountId` ASC),
  CONSTRAINT `FK_m_prj_invoice_1`
    FOREIGN KEY (`currentId`)
    REFERENCES `s_currency` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_invoice_2`
    FOREIGN KEY (`clientId`)
    REFERENCES `m_crm_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_invoice_3`
    FOREIGN KEY (`sAccountId`)
    REFERENCES `s_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `m_prj_invoice`
    ADD COLUMN `type` VARCHAR(45) NOT NULL;
ALTER TABLE `m_prj_invoice`
    ADD COLUMN `noId` VARCHAR(45);
CREATE  TABLE `m_crm_meeting` (
  `id` INT NOT NULL ,
  `subject` VARCHAR(1000) NULL ,
  `status` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  `typeid` INT UNSIGNED NULL ,
  `startDate` DATETIME NULL ,
  `endDate` DATETIME NULL ,
  `lastUpdatedTime` DATETIME NULL ,
  `createdTime` DATETIME NULL ,
  `createdUser` VARCHAR(45) NULL ,
  `sAccountId` INT NOT NULL ,
  `location` VARCHAR(100) NULL ,
  `description` VARCHAR(4000) NULL ,
  `isRecurrence` TINYINT NULL ,
  `recurrenceType` VARCHAR(45) NULL ,
  `recurrenceStartDate` DATETIME NULL ,
  `recurrenceEndDate` DATETIME NULL ,
  `contactType` VARCHAR(45) NULL ,
  `contactTypeId` INT UNSIGNED NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_meeting_1_idx` (`createdUser` ASC) ,
  INDEX `FK_m_crm_meeting_2_idx` (`sAccountId` ASC) ,
  CONSTRAINT `FK_m_crm_meeting_1`
    FOREIGN KEY (`createdUser` )
    REFERENCES `s_user` (`username` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_meeting_2`
    FOREIGN KEY (`sAccountId` )
    REFERENCES `s_account` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
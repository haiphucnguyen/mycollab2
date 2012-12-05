ALTER TABLE `m_crm_task` 
  ADD CONSTRAINT `FK_m_crm_task_1`
  FOREIGN KEY (`contactId` )
  REFERENCES `m_crm_contact` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_m_crm_task_1_idx` (`contactId` ASC) ;

ALTER TABLE `m_crm_task` DROP FOREIGN KEY `FK_m_crm_task_1` ;
ALTER TABLE `m_crm_task` 
  ADD CONSTRAINT `FK_m_crm_task_1`
  FOREIGN KEY (`contactId` )
  REFERENCES `m_crm_contact` (`id` )
  ON DELETE SET NULL
  ON UPDATE CASCADE;
  
CREATE  TABLE `m_crm_call` (
  `id` INT NOT NULL ,
  `subject` VARCHAR(1000) NOT NULL ,
  `startDate` DATETIME NULL ,
  `durationInSeconds` INT NULL ,
  `calltype` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  `typeid` INT NULL ,
  `lastUpdatedTime` DATETIME NULL ,
  `createdTime` VARCHAR(45) NULL ,
  `createdUser` VARCHAR(45) NULL ,
  `assignUser` VARCHAR(45) NULL ,
  `description` VARCHAR(4000) NULL ,
  `contactId` INT UNSIGNED NULL ,
  `sAccountId` INT NOT NULL ,
  `status` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_crm_call_1_idx` (`sAccountId` ASC) ,
  INDEX `FK_m_crm_call_2_idx` (`contactId` ASC) ,
  INDEX `FK_m_crm_call_3_idx` (`createdUser` ASC) ,
  INDEX `FK_m_crm_call_4_idx` (`assignUser` ASC) ,
  CONSTRAINT `FK_m_crm_call_1`
    FOREIGN KEY (`sAccountId` )
    REFERENCES `s_account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_call_2`
    FOREIGN KEY (`contactId` )
    REFERENCES `m_crm_contact` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_call_3`
    FOREIGN KEY (`createdUser` )
    REFERENCES `s_user` (`username` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_call_4`
    FOREIGN KEY (`assignUser` )
    REFERENCES `s_user` (`username` )
    ON DELETE SET NULL
    ON UPDATE CASCADE);  
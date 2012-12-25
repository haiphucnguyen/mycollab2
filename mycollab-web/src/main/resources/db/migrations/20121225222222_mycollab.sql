ALTER TABLE `m_prj_message` ADD COLUMN `sAccountId` INT NOT NULL, 
  ADD CONSTRAINT `FK_m_prj_message_3`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_m_prj_message_3_idx` (`sAccountId` ASC) ;
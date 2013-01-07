ALTER TABLE `m_crm_note` DROP FOREIGN KEY `FK_m_crm_note_2` , DROP FOREIGN KEY `FK_m_crm_note_1` ;
ALTER TABLE `m_crm_note` DROP COLUMN `attachmentpath` , DROP COLUMN `username` , DROP COLUMN `contactid` 
, DROP INDEX `FK_m_crm_note_2` 
, DROP INDEX `FK_m_crm_note_1` ;
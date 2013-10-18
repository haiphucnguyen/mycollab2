ALTER TABLE `m_form_section_field` 
ADD COLUMN `displayName` VARCHAR(100) NOT NULL AFTER `fieldIndex`,
ADD COLUMN `fieldFormat` VARCHAR(200) NOT NULL AFTER `displayName`;

ALTER TABLE `m_form_section_field` 
DROP FOREIGN KEY `FK_m_form_section_field_1`;
ALTER TABLE `m_form_section_field` 
DROP COLUMN `fieldId`,
ADD COLUMN `fieldname` VARCHAR(45) NOT NULL AFTER `fieldFormat`,
DROP INDEX `FK_m_form_section_field_1_idx` ;

DROP TABLE `m_custom_field_format`;
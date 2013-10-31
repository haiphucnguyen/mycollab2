ALTER TABLE `m_form_section_field` 
CHANGE COLUMN `fieldType` `fieldType` VARCHAR(1000) CHARACTER SET 'utf8mb4' NOT NULL ,
ADD COLUMN `isRequired` BIT(1) NOT NULL;
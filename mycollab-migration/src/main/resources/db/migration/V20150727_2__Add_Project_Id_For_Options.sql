ALTER TABLE `m_options` ADD COLUMN `extraId` INT(11) NULL;
ALTER TABLE `m_options` ADD COLUMN `isDefault` TINYINT(1) NOT NULL;
ALTER TABLE `m_options` ADD COLUMN `refOption` INT(11) NULL;

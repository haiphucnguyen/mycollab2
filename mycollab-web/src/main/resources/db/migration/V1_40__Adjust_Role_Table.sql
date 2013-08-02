ALTER TABLE `m_prj_role` ADD COLUMN `isSystemRole` BIT(1) NULL;
UPDATE `m_prj_role` SET isSystemRole=1

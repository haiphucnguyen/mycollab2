UPDATE `m_prj_member` SET isAdmin=0 WHERE isAdmin is NULL;
ALTER TABLE `m_prj_member` CHANGE COLUMN `isAdmin` `isAdmin` TINYINT(1) NOT NULL  ;
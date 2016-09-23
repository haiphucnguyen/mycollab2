ALTER TABLE `m_tracker_bug`
CHANGE COLUMN `summary` `name` VARCHAR(4000) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NOT NULL ;

ALTER TABLE `m_prj_task`
CHANGE COLUMN `taskname` `name` VARCHAR(400) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NOT NULL ;

ALTER TABLE `m_prj_risk`
CHANGE COLUMN `riskname` `name` VARCHAR(400) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NOT NULL ;

ALTER TABLE `m_prj_milestone` ADD COLUMN `priority` VARCHAR(45) NULL;

ALTER TABLE `m_tracker_bug`
DROP FOREIGN KEY `FK_m_tracker_bug_2`;
ALTER TABLE `m_tracker_bug`
CHANGE COLUMN `assignuser` `assignUser` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL ;
ALTER TABLE `m_tracker_bug`
ADD CONSTRAINT `FK_m_tracker_bug_2`
  FOREIGN KEY (`assignUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `m_prj_risk`
DROP FOREIGN KEY `FK_m_prj_risk1_3`;
ALTER TABLE `m_prj_risk`
CHANGE COLUMN `assigntouser` `assignUser` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL ;
ALTER TABLE `m_prj_risk`
ADD CONSTRAINT `FK_m_prj_risk1_3`
  FOREIGN KEY (`assignUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
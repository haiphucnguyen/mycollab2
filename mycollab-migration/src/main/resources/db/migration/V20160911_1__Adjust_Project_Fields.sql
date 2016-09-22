ALTER TABLE `m_tracker_bug`
CHANGE COLUMN `summary` `name` VARCHAR(4000) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NOT NULL ;

ALTER TABLE `m_prj_task`
CHANGE COLUMN `taskname` `name` VARCHAR(400) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NOT NULL ;

ALTER TABLE `m_prj_risk`
CHANGE COLUMN `riskname` `name` VARCHAR(400) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NOT NULL ;
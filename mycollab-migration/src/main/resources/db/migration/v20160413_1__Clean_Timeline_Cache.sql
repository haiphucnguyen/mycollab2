DELETE FROM `s_timeline_tracking_cache` WHERE id > 0;
ALTER TABLE `m_options` ADD COLUMN `isShow` BIT(1) NULL;
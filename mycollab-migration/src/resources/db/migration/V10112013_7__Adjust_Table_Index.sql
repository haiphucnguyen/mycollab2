ALTER TABLE `m_form_custom_field_value` 
ADD INDEX `INDEX_m_form_custom_field_value_1` USING BTREE (`module` ASC),
ADD INDEX `INDEX_m_form_custom_field_value_2` USING BTREE (`typeid` ASC);

ALTER TABLE `m_comment` 
ADD INDEX `INDEX_m_comment_2` USING BTREE (`extraTypeId` ASC),
ADD INDEX `INDEX_m_comment_3` (`typeId` ASC),
ADD INDEX `INDEX_m_comment_4` USING BTREE (`type` ASC),
ADD INDEX `FK_m_comment_2_idx` (`createdUser` ASC);
ALTER TABLE `m_comment` 
ADD CONSTRAINT `FK_m_comment_2`
  FOREIGN KEY (`createdUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `m_audit_log` 
ADD INDEX `INDEX_m_audit_log_4` USING BTREE (`type` ASC),
ADD INDEX `INDEX_m_audit_log_5` USING BTREE (`typeid` ASC),
ADD INDEX `INDEX_m_audit_log_6` USING BTREE (`module` ASC);

ALTER TABLE `m_tracker_bug_related_item` 
ADD INDEX `INDEX_m_tracker_bug_related_item_2` USING BTREE (`type` ASC),
ADD INDEX `INDEX_m_tracker_bug_related_item_3` USING BTREE (`typeid` ASC);

ALTER TABLE `s_activitystream` 
ADD INDEX `FK_m_crm_activitystream_3` USING BTREE (`module` ASC),
ADD INDEX `FK_m_crm_activitystream_4` USING BTREE (`type` ASC),
ADD INDEX `FK_m_crm_activitystream_5` USING BTREE (`typeId` ASC);
ALTER TABLE `m_audit_log`
DROP FOREIGN KEY `FK_m_audit_log_1`;

ALTER TABLE `m_audit_log`
CHANGE COLUMN `posteduser` `createdUser` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL ;

ALTER TABLE `s_activitystream`
CHANGE COLUMN `typeId` `typeId` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ;

ALTER TABLE `m_comment`
CHANGE COLUMN `typeId` `typeId` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL ;

ALTER TABLE `s_relay_email_notification`
CHANGE COLUMN `typeId` `typeId` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ;

ALTER TABLE `m_audit_log`
ADD CONSTRAINT `FK_m_audit_log_1`
  FOREIGN KEY (`createdUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE `s_account`
ADD INDEX `FK_s_account_1_idx` (`billingPlanId` ASC);

ALTER TABLE `s_account`
ADD CONSTRAINT `FK_s_account_1`
  FOREIGN KEY (`billingPlanId`)
  REFERENCES `s_billing_plan` (`id`)
  ON DELETE SET NULL
  ON UPDATE SET NULL;

ALTER TABLE `s_account`
CHANGE COLUMN `subdomain` `subDomain` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL ,
CHANGE COLUMN `sitename` `siteName` VARCHAR(255) NULL DEFAULT NULL ;

ALTER TABLE `m_monitor_item`
DROP FOREIGN KEY `FK_m_monitor_item_1`;
ALTER TABLE `m_monitor_item`
CHANGE COLUMN `user` `username` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ;
ALTER TABLE `m_monitor_item`
ADD CONSTRAINT `FK_m_monitor_item_1`
  FOREIGN KEY (`username`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `m_prj_risk`
CHANGE COLUMN `dateraised` `raisedDate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `percentagecomplete` `percentageComplete` DOUBLE NULL DEFAULT NULL ;

ALTER TABLE `m_prj_risk`
CHANGE COLUMN `probalitity` `probability` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL ;

ALTER TABLE `s_user` DROP COLUMN `middlename`;

ALTER TABLE `s_user`
CHANGE COLUMN `dateofbirth` `birthday` DATE NULL DEFAULT NULL ;

ALTER TABLE `m_prj_role_permission`
DROP FOREIGN KEY `FK_m_prj_role_permission_1`,
DROP FOREIGN KEY `FK_m_prj_role_permission_2`;
ALTER TABLE `m_prj_role_permission`
CHANGE COLUMN `roleid` `roleId` INT(11) UNSIGNED NOT NULL ,
CHANGE COLUMN `projectid` `projectId` INT(10) UNSIGNED NOT NULL ;
ALTER TABLE `m_prj_role_permission`
ADD CONSTRAINT `FK_m_prj_role_permission_1`
  FOREIGN KEY (`roleId`)
  REFERENCES `m_prj_role` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_m_prj_role_permission_2`
  FOREIGN KEY (`projectId`)
  REFERENCES `m_prj_project` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `m_prj_role`
CHANGE COLUMN `rolename` `roleName` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL ;


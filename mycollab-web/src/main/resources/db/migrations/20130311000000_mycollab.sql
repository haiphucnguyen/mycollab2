UPDATE `s_account` SET `billingPlanId`='1' WHERE 1=1;
ALTER TABLE `s_billing_plan` ADD COLUMN `hasBugEnable` TINYINT(1), ADD COLUMN `hasStandupMeetingEnable` TINYINT(1) NULL;
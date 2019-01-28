ALTER TABLE `s_billing_subscription`
ADD COLUMN `lastUpdatedTime` DATETIME NULL,
CHANGE COLUMN `createdTime` `createdTime` DATETIME NULL ;

ALTER TABLE `s_billing_subscription_history`
CHANGE COLUMN `createdTime` `createdTime` DATETIME NULL ;

ALTER TABLE `s_email_preference`
CHANGE COLUMN `createdTime` `createdTime` DATETIME NULL ;
ALTER TABLE `s_premium_users`
CHANGE COLUMN `id` `customerId` VARCHAR(50) NOT NULL ,
ADD COLUMN `lastCheckInstanceTime` DATETIME NOT NULL AFTER `isPhoneCall`,
ADD COLUMN `lastCheckId` VARCHAR(50) NOT NULL AFTER `lastCheckInstanceTime`,
ADD COLUMN `initialId` VARCHAR(50) NOT NULL AFTER `lastCheckId`,
ADD COLUMN `initialLastCheckTime` DATETIME NOT NULL AFTER `initialId`;

ALTER TABLE `s_premium_users` RENAME TO  `s_pro_edition_info` ;
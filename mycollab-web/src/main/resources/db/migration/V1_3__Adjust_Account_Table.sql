ALTER TABLE `s_user` DROP COLUMN `registerStatus`;
ALTER TABLE `s_user` DROP COLUMN `registrationSource`;
ALTER TABLE `s_user` DROP FOREIGN KEY `s_user_fk_1` , DROP FOREIGN KEY `FK_s_user_3` ;
ALTER TABLE `s_user` DROP COLUMN `roleid` , DROP COLUMN `isAdmin` , DROP COLUMN `accountId`, DROP INDEX `FK_s_user_3` , DROP INDEX `s_user_fk_1` ;
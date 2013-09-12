ALTER TABLE `s_user_account_invitation` ADD COLUMN `inviteUser` VARCHAR(45) NULL;
ALTER TABLE `s_user` DROP COLUMN `registerStatus` ;
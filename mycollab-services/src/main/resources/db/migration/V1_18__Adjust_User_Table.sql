SET SQL_SAFE_UPDATES=0;
UPDATE s_user SET registerStatus="Active" WHERE registerStatus IS NULL;
SET SQL_SAFE_UPDATES=1;
ALTER TABLE `s_user` CHANGE COLUMN `registerStatus` `registerStatus` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL  ;
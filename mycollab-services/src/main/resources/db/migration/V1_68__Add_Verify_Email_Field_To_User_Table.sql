ALTER TABLE `s_user` 
ADD COLUMN `isVerifiedEmail` BIT(1) NULL;

UPDATE s_user SET isVerifiedEmail = 1 WHERE s_user.username <> "";

ALTER TABLE `s_user` 
CHANGE COLUMN `isVerifiedEmail` `isVerifiedEmail` BIT(1) NOT NULL DEFAULT 1 ;
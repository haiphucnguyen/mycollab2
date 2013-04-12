UPDATE `s_user` SET email="hainguyen@esofthead" WHERE email IS NULL;
ALTER TABLE `s_user` CHANGE COLUMN `email` `email` VARCHAR(255) NOT NULL  ;
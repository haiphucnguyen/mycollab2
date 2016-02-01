CREATE TABLE `s_premium_users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `company` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `maxUsers` INT(6) NOT NULL,
  `registerDate` DATETIME NOT NULL,
  `expireDate` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `s_premium_users`
  ADD COLUMN `website` VARCHAR(255) NOT NULL,
  ADD COLUMN `isPhoneCall` TINYINT(1) NOT NULL;
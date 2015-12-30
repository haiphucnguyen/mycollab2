CREATE TABLE `s_customer_lead` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `company` VARCHAR(100) NOT NULL,
  `role` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `country` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));
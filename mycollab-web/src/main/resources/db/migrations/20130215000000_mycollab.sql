CREATE  TABLE `s_relay_email_notification` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `sAccountId` INT NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `typeid` INT UNSIGNED NOT NULL ,
  `action` VARCHAR(45) NOT NULL ,
  `changeBy` VARCHAR(45) NOT NULL ,
  `changeComment` TEXT NOT NULL ,
  PRIMARY KEY (`id`) );
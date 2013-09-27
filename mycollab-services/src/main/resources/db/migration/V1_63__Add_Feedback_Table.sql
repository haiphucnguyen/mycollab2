CREATE TABLE `s_customer_feedback` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sAccountId` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `reasonToLeave` TEXT NULL,
  `leaveType` INT NULL,
  `otherTool` VARCHAR(400) NULL,
  `reasonToBack` TEXT NULL,
  PRIMARY KEY (`id`));
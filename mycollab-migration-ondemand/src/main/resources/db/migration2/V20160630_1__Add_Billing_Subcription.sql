CREATE TABLE `s_billing_subscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(400) DEFAULT NULL,
  `email` varchar(400) NOT NULL,
  `billingId` int(11) NOT NULL,
  `name` varchar(400) NOT NULL,
  `subReference` varchar(400) NOT NULL,
  `accountId` int(11) NOT NULL,
  `createdTime` datetime NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_billing_subscription_1_idx` (`billingId`),
  KEY `FK_s_billing_subscription_2_idx` (`accountId`),
  CONSTRAINT `FK_s_billing_subscription_1` FOREIGN KEY (`billingId`) REFERENCES `s_billing_plan` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_s_billing_subscription_2` FOREIGN KEY (`accountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `s_billing_subscription_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orderId` VARCHAR(45) NOT NULL,
  `createdTime` DATETIME NOT NULL,
  `subscriptionId` INT(11) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ s_billing_subscription_history_1_idx` (`subscriptionId` ASC),
  CONSTRAINT `FK_ s_billing_subscription_history_1`
    FOREIGN KEY (`subscriptionId`)
    REFERENCES `s_billing_subscription` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

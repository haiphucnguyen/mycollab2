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

ALTER TABLE `s_billing_subscription`
ADD COLUMN `country` VARCHAR(400) NULL,
ADD COLUMN `city` VARCHAR(400) NULL,
ADD COLUMN `address` VARCHAR(400) NULL,
ADD COLUMN `state` VARCHAR(400) NULL,
ADD COLUMN `zipcode` VARCHAR(45) NULL,
ADD COLUMN `phone` VARCHAR(45) NULL,
ADD COLUMN `contactName` VARCHAR(400) NULL,
ADD COLUMN `subscriptionCustomerUrl` VARCHAR(400) NULL;

CREATE TABLE `s_billing_subscription_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orderId` VARCHAR(45) NOT NULL,
  `createdTime` DATETIME NOT NULL,
  `subscriptionId` INT(11) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `expiredDate` DATETIME NOT NULL,
  `totalPrice` DOUBLE NOT NULL,
  `productName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ s_billing_subscription_history_1_idx` (`subscriptionId` ASC),
  CONSTRAINT `FK_ s_billing_subscription_history_1`
    FOREIGN KEY (`subscriptionId`)
    REFERENCES `s_billing_subscription` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `s_pro_edition_info`
DROP COLUMN `originReference`,
DROP COLUMN `reference`,
ADD COLUMN `cost` DOUBLE NULL,
ADD COLUMN `orderId` VARCHAR(100) NOT NULL,
ADD COLUMN `country` VARCHAR(45) NULL,
ADD COLUMN `phone` VARCHAR(45) NOT NULL,
ADD COLUMN `address1` VARCHAR(400) NULL,
ADD COLUMN `address2` VARCHAR(400) NULL,
ADD COLUMN `city` VARCHAR(400) NULL;


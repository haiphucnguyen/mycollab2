DROP TABLE `s_pro_edition_info`;
CREATE TABLE `s_pro_edition_info` (
  `id` INT(11) UNSIGNED NOT NULL,
  `company` VARCHAR(400) NULL,
  `email` VARCHAR(400) NOT NULL,
  `internalProductName` VARCHAR(400) NULL,
  `name` VARCHAR(400) NULL,
  `quantity` INT(1) NULL,
  `reference` VARCHAR(400) NOT NULL,
  `issueDate` DATETIME NOT NULL,
  `originReference` VARCHAR(400) NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
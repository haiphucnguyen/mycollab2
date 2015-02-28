CREATE TABLE `s_favorite` (
  `id` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `typeid` VARCHAR(45) NOT NULL,
  `lastUpdatedTime` DATETIME NOT NULL,
  `createdTime` DATETIME NOT NULL,
  `extraTypeId` INT(11) NULL,
  `createdUser` VARCHAR(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sAccountId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_s_favorite_1_idx` (`createdUser` ASC),
  INDEX `FK_s_favorite_2_idx` (`sAccountId` ASC),
  CONSTRAINT `FK_s_favorite_1`
    FOREIGN KEY (`createdUser`)
    REFERENCES `s_user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_s_favorite_2`
    FOREIGN KEY (`sAccountId`)
    REFERENCES `s_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
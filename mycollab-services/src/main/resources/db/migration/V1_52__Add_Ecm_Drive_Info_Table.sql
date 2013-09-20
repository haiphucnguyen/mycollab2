CREATE TABLE `m_ecm_driveinfo` (
  `id` INT NOT NULL,
  `sAccountId` INT(11) NOT NULL,
  `docVolume` DOUBLE NULL,
  `textVolume` DOUBLE NULL,
  `binaryVolume` DOUBLE NULL,
  `audioVolume` DOUBLE NULL,
  `videoVolume` DOUBLE NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_m_ecm_driveinfo_1_idx` (`sAccountId` ASC),
  CONSTRAINT `FK_m_ecm_driveinfo_1`
    FOREIGN KEY (`sAccountId`)
    REFERENCES `s_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
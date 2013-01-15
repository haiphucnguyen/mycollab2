drop table `m_prj_news`;
ALTER TABLE `m_prj_message` ADD COLUMN `isNews` TINYINT(1) NULL;
CREATE  TABLE `m_prj_member` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `projectId` INT UNSIGNED NOT NULL ,
  `joinDate` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_prj_member_1_idx` (`username` ASC) ,
  INDEX `FK_m_prj_member_2_idx` (`projectId` ASC) ,
  CONSTRAINT `FK_m_prj_member_1`
    FOREIGN KEY (`username` )
    REFERENCES `s_user` (`username` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_member_2`
    FOREIGN KEY (`projectId` )
    REFERENCES `m_prj_project` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);

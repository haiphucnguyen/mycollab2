drop table `m_tracker_related_item`;
CREATE  TABLE `m_tracker_bug_related_item` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `bugid` INT UNSIGNED NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `typeid` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_ m_tracker_bug_related_item_1_idx` (`bugid` ASC) ,
  CONSTRAINT `FK_ m_tracker_bug_related_item_1`
    FOREIGN KEY (`bugid` )
    REFERENCES `m_tracker_bug` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
CREATE  TABLE `s_report_bug_issue` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `sAccountId` INT NULL ,
  `username` VARCHAR(45) NULL ,
  `userAgent` TEXT NULL ,
  `errorTrace` TEXT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_s_report_bug_issue_idx` (`sAccountId` ASC) ,
  INDEX `FK_s_report_bug_issue_2_idx` (`username` ASC) ,
  CONSTRAINT `FK_s_report_bug_issue_1`
    FOREIGN KEY (`sAccountId` )
    REFERENCES `s_account` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `FK_s_report_bug_issue_2`
    FOREIGN KEY (`username` )
    REFERENCES `s_user` (`username` )
    ON DELETE SET NULL
    ON UPDATE CASCADE);
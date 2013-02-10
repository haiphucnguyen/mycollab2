CREATE  TABLE `m_prj_role_permission` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `roleid` INT UNSIGNED NOT NULL ,
  `roleVal` TEXT NOT NULL ,
  `projectid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_m_prj_role_permission_1_idx` (`roleid` ASC) ,
  INDEX `FK_m_prj_role_permission_2_idx` (`projectid` ASC) ,
  CONSTRAINT `FK_m_prj_role_permission_1`
    FOREIGN KEY (`roleid` )
    REFERENCES `m_prj_role` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_role_permission_2`
    FOREIGN KEY (`projectid` )
    REFERENCES `m_prj_project` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
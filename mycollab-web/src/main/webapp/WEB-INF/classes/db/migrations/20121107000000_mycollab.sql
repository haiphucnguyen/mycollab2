CREATE TABLE `m_prj_task_list` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `projectid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PK_m_prj_task_list_1_idx` (`projectid`),
  CONSTRAINT `PK_m_prj_task_list_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`currencyid`) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE  TABLE `m_prj_milestone` (
  `id` INT NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `description` VARCHAR(4000) NULL ,
  `startdate` DATETIME NOT NULL ,
  `enddate` DATETIME NOT NULL ,
  `owner` VARCHAR(45) NOT NULL ,
  `flag` VARCHAR(45) NULL ,
  `projectid` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `PK_m_prj_milestone_1_idx` (`owner` ASC) ,
  INDEX `PK_m_prj_milestone_2_idx` (`projectid` ASC) ,
  CONSTRAINT `PK_m_prj_milestone_1`
    FOREIGN KEY (`owner` )
    REFERENCES `mycollab`.`s_user` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PK_m_prj_milestone_2`
    FOREIGN KEY (`projectid` )
    REFERENCES `mycollab`.`m_prj_project` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
ALTER TABLE `m_prj_task` ADD COLUMN `tasklistid` INT(11) UNSIGNED NULL, 
  ADD CONSTRAINT `FK_m_prj_task_3`
  FOREIGN KEY (`tasklistid` )
  REFERENCES `mycollab`.`m_prj_task_list` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_m_prj_task_3_idx` (`tasklistid` ASC) ;
    
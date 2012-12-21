CREATE TABLE `m_prj_task_list` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `projectid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PK_m_prj_task_list_1_idx` (`projectid`),
  CONSTRAINT `PK_m_prj_task_list_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`currencyid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `m_prj_milestone` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `startdate` datetime NOT NULL,
  `enddate` datetime NOT NULL,
  `owner` varchar(45) NOT NULL,
  `flag` varchar(45) DEFAULT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `iscompleted` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `PK_m_prj_milestone_1_idx` (`owner`),
  KEY `PK_m_prj_milestone_2_idx` (`projectid`),
  CONSTRAINT `PK_m_prj_milestone_1` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PK_m_prj_milestone_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


    
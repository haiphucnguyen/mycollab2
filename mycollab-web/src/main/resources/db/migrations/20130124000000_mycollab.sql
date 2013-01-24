UPDATE m_prj_project SET projectStatus='Open' WHERE m_prj_project.id > 0;
UPDATE m_prj_project SET shortname='Udf' WHERE m_prj_project.id > 0;

ALTER TABLE `m_prj_project` CHANGE COLUMN `shortname` `shortname` VARCHAR(45) NOT NULL  , 
CHANGE COLUMN `projectStatus` `projectStatus` VARCHAR(45) NOT NULL  ;

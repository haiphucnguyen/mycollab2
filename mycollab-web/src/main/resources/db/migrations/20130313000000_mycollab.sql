ALTER TABLE `m_prj_member` ADD COLUMN `status` VARCHAR(45) NULL ;
UPDATE `m_prj_member` SET status="Active" WHERE 1=1;
ALTER TABLE `m_prj_member` CHANGE COLUMN `status` `status` VARCHAR(45) NOT NULL  ;


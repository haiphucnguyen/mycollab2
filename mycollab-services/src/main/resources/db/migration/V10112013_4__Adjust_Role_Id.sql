ALTER TABLE `m_prj_role_permission` 
DROP FOREIGN KEY `FK_m_prj_role_permission_1`;
ALTER TABLE `m_prj_role_permission` 
CHANGE COLUMN `roleid` `roleid` INT(11) UNSIGNED NOT NULL ;
ALTER TABLE `m_prj_role_permission` 
ADD CONSTRAINT `FK_m_prj_role_permission_1`
  FOREIGN KEY (`roleid`)
  REFERENCES `m_prj_role` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `m_prj_role` 
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ;
drop table `s_roleuser`;
ALTER TABLE `s_user` ADD COLUMN `roleid` INT NULL, 
  ADD CONSTRAINT `FK_s_user_3`
  FOREIGN KEY (`roleid` )
  REFERENCES `s_roles` (`id` )
  ON DELETE SET NULL
  ON UPDATE CASCADE
, ADD INDEX `FK_s_user_3_idx` (`roleid` ASC) ;
ALTER TABLE `s_table_customize_view` ADD COLUMN `sAccountId` INT(11) NOT NULL , 
  ADD CONSTRAINT `FK_s_table_customize_view_1`
  FOREIGN KEY (`createdUser` )
  REFERENCES `s_user` (`username` )
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  ADD CONSTRAINT `FK_s_table_customize_view_2`
  FOREIGN KEY (`sAccountId` )
  REFERENCES `s_account` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE
, ADD INDEX `FK_s_table_customize_view_1_idx` (`createdUser` ASC) 
, ADD INDEX `FK_s_table_customize_view_2_idx` (`sAccountId` ASC) ;
ALTER TABLE `s_user_account_invitation` 
ADD INDEX `FK_s_user_account_invitation_3_idx` (`inviteUser` ASC);
ALTER TABLE `s_user_account_invitation` 
ADD CONSTRAINT `FK_s_user_account_invitation_3`
  FOREIGN KEY (`inviteUser`)
  REFERENCES `s_user` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

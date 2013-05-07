ALTER TABLE `s_account` ADD COLUMN `subdomain` VARCHAR(45) NULL 
, ADD UNIQUE INDEX `subdomain_UNIQUE` (`subdomain` ASC) ;

UPDATE `s_account` SET subdomain=id WHERE id > 0;
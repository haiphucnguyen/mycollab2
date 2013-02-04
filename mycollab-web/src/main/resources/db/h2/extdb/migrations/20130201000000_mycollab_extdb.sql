DROP TABLE IF EXISTS `ip_country`;

CREATE  TABLE `ip_country` (
  `fromip` BIGINT NOT NULL ,
  `toip` BIGINT NOT NULL ,
  `country_name` VARCHAR(100) NOT NULL ,
  `country_code` VARCHAR(5) NOT NULL ,
  PRIMARY KEY (`fromip`, `toip`) );
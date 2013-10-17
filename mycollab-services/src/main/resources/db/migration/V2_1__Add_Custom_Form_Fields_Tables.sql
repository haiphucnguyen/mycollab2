CREATE TABLE `m_custom_field_format` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `field_format` VARCHAR(200) NOT NULL,
  `field_name` VARCHAR(100) NOT NULL,
  `module` VARCHAR(45) NOT NULL,
  `sAccountId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_m_custom_field_format_1_idx` (`sAccountId` ASC),
  CONSTRAINT `FK_m_custom_field_format_1`
    FOREIGN KEY (`sAccountId`)
    REFERENCES `s_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `m_form_section` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `layoutIndex` INT NOT NULL,
  `module` VARCHAR(45) NOT NULL,
  `sAccountId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_m_form_section_1_idx` (`sAccountId` ASC),
  CONSTRAINT `FK_m_form_section_1`
    FOREIGN KEY (`sAccountId`)
    REFERENCES `s_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `m_form_section_layout` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `module` VARCHAR(45) NOT NULL,
  `sAccountId` INT NOT NULL,
  `sectionId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_m_form_section_layout_1_idx` (`sectionId` ASC),
  INDEX `FK_m_form_section_layout_2_idx` (`sAccountId` ASC),
  CONSTRAINT `FK_m_form_section_layout_1`
    FOREIGN KEY (`sectionId`)
    REFERENCES `m_form_section` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_form_section_layout_2`
    FOREIGN KEY (`sAccountId`)
    REFERENCES `s_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);  
    
CREATE TABLE `m_form_section_field` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fieldId` INT NOT NULL,
  `sectionId` INT NOT NULL,
  `isMandatory` BIT(1) NOT NULL,
  `fieldIndex` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_m_form_section_field_1_idx` (`fieldId` ASC),
  INDEX `FK_m_form_section_field_2_idx` (`sectionId` ASC),
  CONSTRAINT `FK_m_form_section_field_1`
    FOREIGN KEY (`fieldId`)
    REFERENCES `m_custom_field_format` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_m_form_section_field_2`
    FOREIGN KEY (`sectionId`)
    REFERENCES `m_form_section` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE); 
    
CREATE TABLE `m_form_custom_field_value` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `module` VARCHAR(45) NOT NULL,
  `typeid` INT NOT NULL,
  `number1` DOUBLE NULL,
  `number2` DOUBLE NULL,
  `number3` DOUBLE NULL,
  `number4` DOUBLE NULL,
  `number5` DOUBLE NULL,
  `int1` INT NULL,
  `int2` INT NULL,
  `int3` INT NULL,
  `int4` INT NULL,
  `int5` INT NULL,
  `date1` DATETIME NULL,
  `date2` DATETIME NULL,
  `date3` DATETIME NULL,
  `date4` DATETIME NULL,
  `date5` DATETIME NULL,
  `string1` TEXT NULL,
  `string2` TEXT NULL,
  `string3` TEXT NULL,
  `string4` TEXT NULL,
  `string5` TEXT NULL,
  `string6` TEXT NULL,
  `string7` TEXT NULL,
  `string8` TEXT NULL,
  `string9` TEXT NULL,
  `string10` TEXT NULL,
  PRIMARY KEY (`id`));    
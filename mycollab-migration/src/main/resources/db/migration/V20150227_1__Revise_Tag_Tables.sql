ALTER TABLE `s_tag_relationship`
DROP FOREIGN KEY `FK_s_tag_relationship_1`;
ALTER TABLE `s_tag_relationship`
CHANGE COLUMN `tagId` `name` VARCHAR(100) CHARACTER SET 'utf8mb4' NOT NULL ;

DROP TABLE `s_tag`;

ALTER TABLE `s_tag_relationship`
RENAME TO  `s_tag` ;

ALTER TABLE `s_tag`
CHANGE COLUMN `typerid` `typerid` VARCHAR(100) NOT NULL ;


ALTER TABLE `m_ecm_driveinfo` 
DROP COLUMN `imageVolume`,
DROP COLUMN `videoVolume`,
DROP COLUMN `audioVolume`,
DROP COLUMN `binaryVolume`,
DROP COLUMN `textVolume`,
DROP COLUMN `docVolume`,
ADD COLUMN `usedVolume` BIGINT NULL;
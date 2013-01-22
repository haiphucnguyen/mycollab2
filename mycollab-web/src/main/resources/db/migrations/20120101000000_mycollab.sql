-- MySQL dump 10.13  Distrib 5.5.24, for osx10.5 (i386)
--
-- Host: localhost    Database: mycollab
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `m_attachment`
--

DROP TABLE IF EXISTS `m_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_attachment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `documentpath` varchar(1000) NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_attachment`
--

LOCK TABLES `m_attachment` WRITE;
/*!40000 ALTER TABLE `m_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_audit_log`
--

DROP TABLE IF EXISTS `m_audit_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_audit_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `object_class` varchar(255) NOT NULL,
  `changeset` longtext NOT NULL,
  `posteddate` datetime NOT NULL,
  `posteduser` varchar(45) DEFAULT NULL,
  `createdTime` timestamp NULL DEFAULT NULL,
  `lastUpdatedTime` timestamp NULL DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `typeid` int(11) NOT NULL,
  `module` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_audit_log_1_idx` (`posteduser`),
  KEY `FK_m_audit_log_2_idx` (`sAccountId`),
  CONSTRAINT `FK_m_audit_log_1` FOREIGN KEY (`posteduser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_audit_log_2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_audit_log`
--

LOCK TABLES `m_audit_log` WRITE;
/*!40000 ALTER TABLE `m_audit_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_audit_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_colla_workgroup`
--

DROP TABLE IF EXISTS `m_colla_workgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_colla_workgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `isModerated` tinyint(1) DEFAULT NULL,
  `sAccountId` int(11) DEFAULT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  `accessMode` int(11) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_colla_workgroup_1` (`sAccountId`),
  CONSTRAINT `FK_m_colla_workgroup_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_colla_workgroup`
--

LOCK TABLES `m_colla_workgroup` WRITE;
/*!40000 ALTER TABLE `m_colla_workgroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_colla_workgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_colla_workgroup_comment`
--

DROP TABLE IF EXISTS `m_colla_workgroup_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_colla_workgroup_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(4000) DEFAULT NULL,
  `postedUser` varchar(45) DEFAULT NULL,
  `postedDate` datetime DEFAULT NULL,
  `messageid` int(11) DEFAULT NULL,
  `createdTime` timestamp NULL DEFAULT NULL,
  `lastUpdatedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_colla_workgroup_comment_1` (`messageid`),
  KEY `FK_m_colla_workgroup_comment_2` (`postedUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_colla_workgroup_comment`
--

LOCK TABLES `m_colla_workgroup_comment` WRITE;
/*!40000 ALTER TABLE `m_colla_workgroup_comment` DISABLE KEYS */;
INSERT INTO `m_colla_workgroup_comment` VALUES (1,'aaa','hainguyen',NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `m_colla_workgroup_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_colla_workgroup_message`
--

DROP TABLE IF EXISTS `m_colla_workgroup_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_colla_workgroup_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(4000) DEFAULT NULL,
  `postedUser` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `workgroupId` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `m_colla_workgroup_messagecol` varchar(400) DEFAULT NULL,
  `lastUpdatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `PK_m_colla_workgroup_message_1` (`workgroupId`),
  KEY `PK_m_colla_workgroup_message_2` (`postedUser`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_colla_workgroup_message`
--

LOCK TABLES `m_colla_workgroup_message` WRITE;
/*!40000 ALTER TABLE `m_colla_workgroup_message` DISABLE KEYS */;
INSERT INTO `m_colla_workgroup_message` VALUES (1,NULL,'hainguyen',NULL,1,NULL,NULL,NULL),(2,NULL,'linhduong',NULL,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `m_colla_workgroup_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_colla_workgroup_user`
--

DROP TABLE IF EXISTS `m_colla_workgroup_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_colla_workgroup_user` (
  `workgroup_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `joinedDate` datetime DEFAULT NULL,
  `isApproved` tinyint(1) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_colla_workgroup_user`
--

LOCK TABLES `m_colla_workgroup_user` WRITE;
/*!40000 ALTER TABLE `m_colla_workgroup_user` DISABLE KEYS */;
INSERT INTO `m_colla_workgroup_user` VALUES (1,'hainguyen',NULL,NULL,1,NULL,NULL,NULL),(1,'linhduong',NULL,NULL,2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `m_colla_workgroup_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_comment`
--

DROP TABLE IF EXISTS `m_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment` varchar(4000) DEFAULT NULL,
  `createdUser` varchar(45) NOT NULL,
  `createdTime` datetime NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `sAccountId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_comment_1_idx` (`sAccountId`),
  CONSTRAINT `FK_m_comment_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_comment`
--

LOCK TABLES `m_comment` WRITE;
/*!40000 ALTER TABLE `m_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_account`
--

DROP TABLE IF EXISTS `m_crm_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `accountName` varchar(255) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `phoneOffice` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `alternatePhone` varchar(45) DEFAULT NULL,
  `annualRevenue` varchar(45) DEFAULT NULL,
  `billingAddress` varchar(255) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `postalCode` varchar(45) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ownership` varchar(255) DEFAULT NULL,
  `shippingAddress` varchar(255) DEFAULT NULL,
  `shippingCity` varchar(100) DEFAULT NULL,
  `shippingPostalCode` varchar(45) DEFAULT NULL,
  `shippingState` varchar(45) DEFAULT NULL,
  `numemployees` int(10) unsigned DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL COMMENT '''Analyst'',''Competitor'',''Customer'',''Integrator'',''Investor'',''Partner'',''Press'',''Prospect,''Reseller'',''Other''',
  `industry` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `billingCountry` varchar(45) DEFAULT NULL,
  `shippingCountry` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_account_6` (`createdUser`),
  KEY `FK_m_crm_account_7` (`sAccountId`),
  KEY `FK_m_crm_account_8` (`assignUser`),
  CONSTRAINT `FK_m_crm_account_6` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_m_crm_account_7` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_account_8` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_account`
--

LOCK TABLES `m_crm_account` WRITE;
/*!40000 ALTER TABLE `m_crm_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_accounts_contacts`
--

DROP TABLE IF EXISTS `m_crm_accounts_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_accounts_contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(10) unsigned NOT NULL,
  `contactId` int(10) unsigned NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_accounts_contacts_1_idx` (`accountId`),
  KEY `FK_m_crm_accounts_contacts_2_idx` (`contactId`),
  CONSTRAINT `FK_m_crm_accounts_contacts_1` FOREIGN KEY (`accountId`) REFERENCES `m_crm_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_accounts_contacts_2` FOREIGN KEY (`contactId`) REFERENCES `m_crm_contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_accounts_contacts`
--

LOCK TABLES `m_crm_accounts_contacts` WRITE;
/*!40000 ALTER TABLE `m_crm_accounts_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_accounts_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_accounts_opportunities`
--

DROP TABLE IF EXISTS `m_crm_accounts_opportunities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_accounts_opportunities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(10) unsigned NOT NULL,
  `opportunityId` int(10) unsigned NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_accounts_opportunities_1_idx` (`accountId`),
  KEY `FK_m_crm_accounts_opportunities_2_idx` (`opportunityId`),
  CONSTRAINT `FK_m_crm_accounts_opportunities_1` FOREIGN KEY (`accountId`) REFERENCES `m_crm_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_accounts_opportunities_2` FOREIGN KEY (`opportunityId`) REFERENCES `m_crm_opportunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_accounts_opportunities`
--

LOCK TABLES `m_crm_accounts_opportunities` WRITE;
/*!40000 ALTER TABLE `m_crm_accounts_opportunities` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_accounts_opportunities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_call`
--

DROP TABLE IF EXISTS `m_crm_call`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_call` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(1000) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `durationInSeconds` int(11) DEFAULT NULL,
  `calltype` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `contactId` int(10) unsigned DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `purpose` varchar(45) DEFAULT NULL,
  `result` varchar(4000) DEFAULT NULL,
  `isClosed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_call_1_idx` (`sAccountId`),
  KEY `FK_m_crm_call_2_idx` (`contactId`),
  KEY `FK_m_crm_call_3_idx` (`createdUser`),
  KEY `FK_m_crm_call_4_idx` (`assignUser`),
  CONSTRAINT `FK_m_crm_call_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_call_2` FOREIGN KEY (`contactId`) REFERENCES `m_crm_contact` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_call_3` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_call_4` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_call`
--

LOCK TABLES `m_crm_call` WRITE;
/*!40000 ALTER TABLE `m_crm_call` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_call` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_campaign`
--

DROP TABLE IF EXISTS `m_crm_campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_campaign` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `campaignName` varchar(255) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `currencyId` int(11) DEFAULT NULL,
  `impressionnote` varchar(255) DEFAULT NULL,
  `budget` decimal(10,0) DEFAULT NULL,
  `actualCost` decimal(10,0) DEFAULT NULL,
  `expectedRevenue` decimal(10,0) DEFAULT NULL,
  `expectedCost` decimal(10,0) DEFAULT NULL,
  `objective` varchar(2000) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `impression` int(10) unsigned DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_campaign_2` (`currencyId`),
  KEY `FK_m_crm_campaign_5` (`createdUser`),
  KEY `FK_m_crm_campaign_6` (`sAccountId`),
  KEY `FK_m_crm_campaign_7` (`assignUser`),
  CONSTRAINT `FK_m_crm_campaign_2` FOREIGN KEY (`currencyId`) REFERENCES `s_currency` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_campaign_5` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_campaign_6` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_campaign_7` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_campaign`
--

LOCK TABLES `m_crm_campaign` WRITE;
/*!40000 ALTER TABLE `m_crm_campaign` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_case`
--

DROP TABLE IF EXISTS `m_crm_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_case` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `priority` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `subject` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `resolution` varchar(4000) DEFAULT NULL,
  `accountId` int(10) unsigned NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `origin` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_case_1` (`accountId`),
  KEY `FK_m_crm_case_2` (`createdUser`),
  KEY `FK_m_crm_case_3` (`sAccountId`),
  KEY `FK_m_crm_case_4` (`assignUser`),
  CONSTRAINT `FK_m_crm_case_1` FOREIGN KEY (`accountId`) REFERENCES `m_crm_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_case_2` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_case_3` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_case_4` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_case`
--

LOCK TABLES `m_crm_case` WRITE;
/*!40000 ALTER TABLE `m_crm_case` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_case` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_contact`
--

DROP TABLE IF EXISTS `m_crm_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_contact` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prefix` varchar(45) DEFAULT '',
  `firstname` varchar(45) DEFAULT '',
  `lastname` varchar(45) NOT NULL,
  `leadSource` varchar(45) DEFAULT NULL,
  `campaignId` int(10) unsigned DEFAULT NULL,
  `isCallable` tinyint(1) DEFAULT NULL,
  `officePhone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `homePhone` varchar(45) DEFAULT NULL,
  `otherPhone` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `assistant` varchar(100) DEFAULT NULL,
  `primAddress` varchar(255) DEFAULT NULL,
  `primCity` varchar(255) DEFAULT NULL,
  `primState` varchar(255) DEFAULT NULL,
  `primPostalCode` varchar(255) DEFAULT NULL,
  `primCountry` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `assistantPhone` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `otherAddress` varchar(255) DEFAULT NULL,
  `otherCity` varchar(255) DEFAULT NULL,
  `otherState` varchar(255) DEFAULT NULL,
  `otherPostalCode` varchar(255) DEFAULT NULL,
  `otherCountry` varchar(255) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_contact_3` (`campaignId`),
  KEY `FK_m_crm_contact_5` (`primCountry`),
  KEY `FK_m_crm_contact_6` (`createdUser`),
  KEY `FK_m_crm_contact_7` (`sAccountId`),
  KEY `FK_m_crm_contact_8` (`assignUser`),
  KEY `FK_m_crm_contact_2` (`leadSource`),
  CONSTRAINT `FK_m_crm_contact_3` FOREIGN KEY (`campaignId`) REFERENCES `m_crm_campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contact_6` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_contact_7` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contact_8` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_contact`
--

LOCK TABLES `m_crm_contact` WRITE;
/*!40000 ALTER TABLE `m_crm_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_contacts_cases`
--

DROP TABLE IF EXISTS `m_crm_contacts_cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_contacts_cases` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactId` int(10) unsigned NOT NULL,
  `caseId` int(10) unsigned NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_contacts_cases_1_idx` (`contactId`),
  KEY `FK_m_crm_contacts_cases_2_idx` (`caseId`),
  CONSTRAINT `FK_m_crm_contacts_cases_1` FOREIGN KEY (`contactId`) REFERENCES `m_crm_contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contacts_cases_2` FOREIGN KEY (`caseId`) REFERENCES `m_crm_case` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_contacts_cases`
--

LOCK TABLES `m_crm_contacts_cases` WRITE;
/*!40000 ALTER TABLE `m_crm_contacts_cases` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_contacts_cases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_contract`
--

DROP TABLE IF EXISTS `m_crm_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_contract` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contractname` varchar(255) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `accountid` int(10) unsigned DEFAULT NULL,
  `opportunityid` int(10) unsigned DEFAULT NULL,
  `currencyid` int(11) DEFAULT NULL,
  `customersigneddate` datetime DEFAULT NULL,
  `companysigneddate` datetime DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `contractvalue` decimal(10,0) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_contract_1` (`accountid`),
  KEY `FK_m_crm_contract_2` (`opportunityid`),
  KEY `FK_m_crm_contract_4` (`currencyid`),
  KEY `FK_m_crm_contract_5` (`createdUser`),
  KEY `FK_m_crm_contract_6` (`sAccountId`),
  KEY `FK_m_crm_contract_7` (`assignUser`),
  CONSTRAINT `FK_m_crm_contract_1` FOREIGN KEY (`accountid`) REFERENCES `m_crm_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contract_2` FOREIGN KEY (`opportunityid`) REFERENCES `m_crm_opportunity` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contract_4` FOREIGN KEY (`currencyid`) REFERENCES `s_currency` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contract_5` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_contract_6` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_contract_7` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_contract`
--

LOCK TABLES `m_crm_contract` WRITE;
/*!40000 ALTER TABLE `m_crm_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_customer`
--

DROP TABLE IF EXISTS `m_crm_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_customer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` int(10) unsigned DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) NOT NULL,
  `leadsource` int(10) unsigned DEFAULT NULL,
  `assignto` int(11) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `officePhone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `homePhone` varchar(45) DEFAULT NULL,
  `assisstant` varchar(80) DEFAULT NULL,
  `assisstantPhone` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_crm_customer_1` (`assignto`),
  KEY `FK_crm_customer_2` (`title`),
  KEY `FK_crm_customer_3` (`createdUser`),
  KEY `FK_crm_customer_4` (`sAccountId`),
  CONSTRAINT `FK_crm_customer_1` FOREIGN KEY (`assignto`) REFERENCES `m_hr_employee` (`id`),
  CONSTRAINT `FK_crm_customer_2` FOREIGN KEY (`title`) REFERENCES `s_title` (`id`),
  CONSTRAINT `FK_crm_customer_3` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_crm_customer_4` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_customer`
--

LOCK TABLES `m_crm_customer` WRITE;
/*!40000 ALTER TABLE `m_crm_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_lead`
--

DROP TABLE IF EXISTS `m_crm_lead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_lead` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `campaignId` int(10) unsigned DEFAULT NULL,
  `leadSourceDesc` varchar(255) DEFAULT NULL,
  `statusDesc` varchar(255) DEFAULT NULL,
  `referredBy` varchar(100) DEFAULT NULL,
  `prefixName` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) NOT NULL,
  `accountName` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `isCallable` tinyint(1) DEFAULT NULL,
  `officePhone` varchar(45) DEFAULT NULL,
  `homePhone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `otherPhone` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `primAddress` varchar(255) DEFAULT NULL,
  `primState` varchar(45) DEFAULT NULL,
  `primCity` varchar(45) DEFAULT NULL,
  `primPostalCode` varchar(45) DEFAULT NULL,
  `primCountry` varchar(45) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `otherAddress` varchar(255) DEFAULT NULL,
  `otherState` varchar(45) DEFAULT NULL,
  `otherCity` varchar(45) DEFAULT NULL,
  `otherPostalCode` varchar(45) DEFAULT NULL,
  `otherCountry` varchar(45) DEFAULT NULL,
  `industry` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `noEmployees` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_lead_2` (`campaignId`),
  KEY `FK_m_crm_lead_5` (`createdUser`),
  KEY `FK_m_crm_lead_6` (`sAccountId`),
  KEY `FK_m_crm_lead_7` (`assignUser`),
  CONSTRAINT `FK_m_crm_lead_2` FOREIGN KEY (`campaignId`) REFERENCES `m_crm_campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_lead_5` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_lead_6` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_lead_7` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_lead`
--

LOCK TABLES `m_crm_lead` WRITE;
/*!40000 ALTER TABLE `m_crm_lead` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_lead` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_meeting`
--

DROP TABLE IF EXISTS `m_crm_meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(1000) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeid` int(10) unsigned DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `isRecurrence` tinyint(4) DEFAULT NULL,
  `recurrenceType` varchar(45) DEFAULT NULL,
  `recurrenceStartDate` datetime DEFAULT NULL,
  `recurrenceEndDate` datetime DEFAULT NULL,
  `contactType` varchar(45) DEFAULT NULL,
  `contactTypeId` int(10) unsigned DEFAULT NULL,
  `isClosed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_meeting_1_idx` (`createdUser`),
  KEY `FK_m_crm_meeting_2_idx` (`sAccountId`),
  CONSTRAINT `FK_m_crm_meeting_1` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_meeting_2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_meeting`
--

LOCK TABLES `m_crm_meeting` WRITE;
/*!40000 ALTER TABLE `m_crm_meeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_note`
--

DROP TABLE IF EXISTS `m_crm_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_note` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `note` varchar(4000) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeid` int(10) unsigned DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_note_3` (`createdUser`),
  KEY `FK_m_crm_note_4` (`sAccountId`),
  CONSTRAINT `FK_m_crm_note_3` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_note_4` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_note`
--

LOCK TABLES `m_crm_note` WRITE;
/*!40000 ALTER TABLE `m_crm_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_opportunities_contacts`
--

DROP TABLE IF EXISTS `m_crm_opportunities_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_opportunities_contacts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `opportunityId` int(10) unsigned NOT NULL,
  `contactId` int(10) unsigned NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_opportunities_contacts_1_idx` (`opportunityId`),
  KEY `FK_m_crm_opportunities_contacts_2_idx` (`contactId`),
  CONSTRAINT `FK_m_crm_opportunities_contacts_1` FOREIGN KEY (`opportunityId`) REFERENCES `m_crm_opportunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_opportunities_contacts_2` FOREIGN KEY (`contactId`) REFERENCES `m_crm_contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_opportunities_contacts`
--

LOCK TABLES `m_crm_opportunities_contacts` WRITE;
/*!40000 ALTER TABLE `m_crm_opportunities_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_opportunities_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_opportunity`
--

DROP TABLE IF EXISTS `m_crm_opportunity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_opportunity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `opportunityName` varchar(255) NOT NULL,
  `currencyid` int(10) unsigned DEFAULT NULL,
  `accountid` int(10) unsigned NOT NULL,
  `amount` int(10) unsigned DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `expectedClosedDate` datetime DEFAULT NULL,
  `campaignid` int(10) unsigned DEFAULT NULL,
  `nextStep` varchar(255) DEFAULT NULL,
  `probability` int(10) unsigned DEFAULT NULL COMMENT 'limit from 0 to 100',
  `description` varchar(2000) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `opportunityType` varchar(45) DEFAULT NULL,
  `salesStage` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_opportunity_2` (`campaignid`),
  KEY `FK_m_crm_opportunity_5` (`createdUser`),
  KEY `FK_m_crm_opportunity_6` (`sAccountId`),
  KEY `FK_m_crm_opportunity_7` (`assignUser`),
  CONSTRAINT `FK_m_crm_opportunity_2` FOREIGN KEY (`campaignid`) REFERENCES `m_crm_campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_opportunity_5` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_opportunity_6` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_opportunity_7` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_opportunity`
--

LOCK TABLES `m_crm_opportunity` WRITE;
/*!40000 ALTER TABLE `m_crm_opportunity` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_opportunity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_product`
--

DROP TABLE IF EXISTS `m_crm_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `productname` varchar(255) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `accountid` int(10) unsigned DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `quantity` int(10) unsigned DEFAULT NULL,
  `serialnumber` varchar(45) DEFAULT NULL,
  `assessnumber` varchar(45) DEFAULT NULL,
  `contactid` int(10) unsigned DEFAULT NULL,
  `supportstartdate` datetime DEFAULT NULL,
  `supportenddate` datetime DEFAULT NULL,
  `salesdate` datetime DEFAULT NULL,
  `unitprice` double DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `producturl` varchar(255) DEFAULT NULL,
  `supportcontact` varchar(255) DEFAULT NULL,
  `supportterm` varchar(45) DEFAULT NULL,
  `supportdesc` varchar(100) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `listprice` double DEFAULT NULL,
  `groupid` int(10) unsigned DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `taxClass` varchar(45) DEFAULT NULL,
  `mftNumber` varchar(45) DEFAULT NULL,
  `vendorPartNumber` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_product_2` (`contactid`),
  KEY `FK_m_crm_product_1` (`accountid`),
  KEY `FK_m_crm_product_3` (`groupid`),
  KEY `FK_m_crm_product_4` (`createdUser`),
  KEY `FK_m_crm_product_5` (`sAccountId`),
  CONSTRAINT `FK_m_crm_product_1` FOREIGN KEY (`accountid`) REFERENCES `m_crm_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_product_2` FOREIGN KEY (`contactid`) REFERENCES `m_crm_contact` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_product_3` FOREIGN KEY (`groupid`) REFERENCES `m_crm_quote_group_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_product_4` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_product_5` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_product`
--

LOCK TABLES `m_crm_product` WRITE;
/*!40000 ALTER TABLE `m_crm_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_product_catalog`
--

DROP TABLE IF EXISTS `m_crm_product_catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_product_catalog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `taxclass` varchar(45) DEFAULT NULL,
  `mft_partnumber` varchar(45) DEFAULT NULL,
  `vendor_partnumber` varchar(45) DEFAULT NULL,
  `currencyid` int(11) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `listprice` double DEFAULT NULL,
  `discountprice` double DEFAULT NULL,
  `pricing_formula` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `date_available` datetime DEFAULT NULL,
  `availability` varchar(45) DEFAULT NULL,
  `quantity_in_stock` varchar(45) DEFAULT NULL,
  `support_name` varchar(100) DEFAULT NULL,
  `support_contact` varchar(100) DEFAULT NULL,
  `support_desc` varchar(100) DEFAULT NULL,
  `support_term` varchar(45) DEFAULT NULL,
  `productname` varchar(100) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_product_catalog_1` (`currencyid`),
  KEY `FK_m_crm_product_catalog_2` (`sAccountId`),
  CONSTRAINT `FK_m_crm_product_catalog_1` FOREIGN KEY (`currencyid`) REFERENCES `s_currency` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_product_catalog_2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_product_catalog`
--

LOCK TABLES `m_crm_product_catalog` WRITE;
/*!40000 ALTER TABLE `m_crm_product_catalog` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_product_catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_quote`
--

DROP TABLE IF EXISTS `m_crm_quote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_quote` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `quotestage` varchar(45) DEFAULT NULL,
  `opportunityid` int(10) unsigned DEFAULT NULL,
  `validuntil` datetime DEFAULT NULL,
  `shipping` varchar(255) DEFAULT NULL,
  `billaccount` int(10) unsigned NOT NULL,
  `billcontact` int(10) unsigned DEFAULT NULL,
  `shipaccount` int(10) unsigned DEFAULT NULL,
  `shipcontact` int(10) unsigned DEFAULT NULL,
  `billingaddress` varchar(255) NOT NULL,
  `billingcity` varchar(255) DEFAULT NULL,
  `billingstate` varchar(45) DEFAULT NULL,
  `billingpostalcode` varchar(45) DEFAULT NULL,
  `billingcountry` varchar(45) DEFAULT NULL,
  `shippingaddress` varchar(255) NOT NULL,
  `shippingcity` varchar(255) DEFAULT NULL,
  `shippingstate` varchar(45) DEFAULT NULL,
  `shippingpostalcode` varchar(45) DEFAULT NULL,
  `shippingcountry` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `paymentterm` varchar(45) DEFAULT NULL,
  `originalpodate` datetime DEFAULT NULL,
  `purchaseordernum` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_quote_2` (`opportunityid`),
  KEY `FK_m_crm_quote_4` (`shipaccount`),
  KEY `FK_m_crm_quote_3` (`billcontact`),
  KEY `FK_m_crm_quote_5` (`shipcontact`),
  KEY `FK_m_crm_quote_6` (`billaccount`),
  KEY `FK_m_crm_quote_7` (`createdUser`),
  KEY `FK_m_crm_quote_8` (`sAccountId`),
  KEY `FK_m_crm_quote_9` (`assignUser`),
  CONSTRAINT `FK_m_crm_quote_2` FOREIGN KEY (`opportunityid`) REFERENCES `m_crm_opportunity` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_quote_3` FOREIGN KEY (`billcontact`) REFERENCES `m_crm_contact` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_quote_4` FOREIGN KEY (`shipaccount`) REFERENCES `m_crm_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_quote_5` FOREIGN KEY (`shipcontact`) REFERENCES `m_crm_contact` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_quote_6` FOREIGN KEY (`billaccount`) REFERENCES `m_crm_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_quote_7` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_quote_8` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_quote_9` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_quote`
--

LOCK TABLES `m_crm_quote` WRITE;
/*!40000 ALTER TABLE `m_crm_quote` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_quote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_quote_group_product`
--

DROP TABLE IF EXISTS `m_crm_quote_group_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_quote_group_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `groupname` varchar(100) DEFAULT NULL,
  `groupstate` varchar(100) DEFAULT NULL,
  `tax` varchar(45) DEFAULT NULL,
  `shipping` varchar(45) DEFAULT NULL,
  `quoteid` int(10) unsigned NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_quote_group_product_2` (`quoteid`),
  CONSTRAINT `FK_m_crm_quote_group_product_1` FOREIGN KEY (`quoteid`) REFERENCES `m_crm_quote` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_quote_group_product_2` FOREIGN KEY (`quoteid`) REFERENCES `m_crm_quote` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_quote_group_product`
--

LOCK TABLES `m_crm_quote_group_product` WRITE;
/*!40000 ALTER TABLE `m_crm_quote_group_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_quote_group_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_target`
--

DROP TABLE IF EXISTS `m_crm_target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_target` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prefixname` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `accountname` varchar(255) DEFAULT NULL,
  `isCallable` tinyint(1) DEFAULT NULL,
  `officePhone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `homePhone` varchar(45) DEFAULT NULL,
  `otherPhone` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `assistant` varchar(255) DEFAULT NULL,
  `assistantPhone` varchar(45) DEFAULT NULL,
  `primaryAddress` varchar(255) DEFAULT NULL,
  `primaryCity` varchar(45) DEFAULT NULL,
  `primaryState` varchar(45) DEFAULT NULL,
  `primaryPostal` varchar(45) DEFAULT NULL,
  `primaryCountryId` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_target_2` (`primaryCountryId`),
  KEY `FK_m_crm_target_3` (`createdUser`),
  KEY `FK_m_crm_target_4` (`sAccountId`),
  KEY `FK_m_crm_target_5` (`assignUser`),
  CONSTRAINT `FK_m_crm_target_2` FOREIGN KEY (`primaryCountryId`) REFERENCES `s_country` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_target_3` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_target_4` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_target_5` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_target`
--

LOCK TABLES `m_crm_target` WRITE;
/*!40000 ALTER TABLE `m_crm_target` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_target` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_target_list`
--

DROP TABLE IF EXISTS `m_crm_target_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_target_list` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_target_list_2` (`createdUser`),
  KEY `FK_m_crm_target_list` (`sAccountId`),
  KEY `FK_m_crm_target_list_3` (`assignUser`),
  CONSTRAINT `FK_m_crm_target_list_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_target_list_2` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_target_list_3` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_target_list`
--

LOCK TABLES `m_crm_target_list` WRITE;
/*!40000 ALTER TABLE `m_crm_target_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_target_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_task`
--

DROP TABLE IF EXISTS `m_crm_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subject` varchar(1000) NOT NULL,
  `startdate` datetime DEFAULT NULL,
  `duedate` datetime DEFAULT NULL,
  `contactId` int(10) unsigned DEFAULT NULL,
  `typeid` int(10) unsigned DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL COMMENT 'One of values ''Planning'', ''Active'', \n''Inactive'', ''Completed'', ''In Queue'',''Sending''',
  `assignUser` varchar(45) DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL COMMENT 'one of values ''High'', ''Medium'', ''Low''',
  `type` varchar(45) DEFAULT NULL COMMENT '''Account'',''Campaign'',''Contact'',''Lead'',''Opportunity'',''Target'',''Quote'',''Product'',''Case''',
  `lastUpdatedTime` datetime DEFAULT NULL,
  `isClosed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_task_4` (`createdUser`),
  KEY `FK_m_crm_task_5` (`sAccountId`),
  KEY `FK_m_crm_task_6` (`assignUser`),
  KEY `FK_m_crm_task_1_idx` (`contactId`),
  CONSTRAINT `FK_m_crm_task_1` FOREIGN KEY (`contactId`) REFERENCES `m_crm_contact` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_task_4` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_task_5` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_task_6` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_task`
--

LOCK TABLES `m_crm_task` WRITE;
/*!40000 ALTER TABLE `m_crm_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_type_relationship`
--

DROP TABLE IF EXISTS `m_crm_type_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_type_relationship` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type1id` int(10) unsigned NOT NULL,
  `type2id` int(10) unsigned NOT NULL,
  `type` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_type_relationship`
--

LOCK TABLES `m_crm_type_relationship` WRITE;
/*!40000 ALTER TABLE `m_crm_type_relationship` DISABLE KEYS */;
INSERT INTO `m_crm_type_relationship` VALUES (1,1,1,2);
/*!40000 ALTER TABLE `m_crm_type_relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_ecm_transaction`
--

DROP TABLE IF EXISTS `m_ecm_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_transaction` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contentpath` varchar(400) NOT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `action` varchar(45) NOT NULL,
  `actiontime` datetime NOT NULL,
  `contenttype` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_ecm_transaction_1` (`owner`),
  CONSTRAINT `FK_m_ecm_transaction_1` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_transaction`
--

LOCK TABLES `m_ecm_transaction` WRITE;
/*!40000 ALTER TABLE `m_ecm_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_ecm_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_friend_friend`
--

DROP TABLE IF EXISTS `m_friend_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_friend_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `friendname` varchar(45) NOT NULL,
  `createdDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_friend_friend_1` (`username`),
  KEY `FK_m_friend_friend_2` (`friendname`),
  CONSTRAINT `FK_m_friend_friend_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_friend_friend_2` FOREIGN KEY (`friendname`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_friend_friend`
--

LOCK TABLES `m_friend_friend` WRITE;
/*!40000 ALTER TABLE `m_friend_friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_friend_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_friend_request`
--

DROP TABLE IF EXISTS `m_friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_friend_request` (
  `id` int(11) NOT NULL,
  `requestFrom` varchar(45) NOT NULL,
  `requestTo` varchar(45) NOT NULL,
  `requestDate` datetime NOT NULL,
  `message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_friend_request_1` (`requestFrom`),
  KEY `FK_m_friend_request_2` (`requestTo`),
  CONSTRAINT `FK_m_friend_request_1` FOREIGN KEY (`requestFrom`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_friend_request_2` FOREIGN KEY (`requestTo`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_friend_request`
--

LOCK TABLES `m_friend_request` WRITE;
/*!40000 ALTER TABLE `m_friend_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_group`
--

DROP TABLE IF EXISTS `m_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `owner` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `createddate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_group_1` (`owner`),
  CONSTRAINT `FK_m_group_1` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_group`
--

LOCK TABLES `m_group` WRITE;
/*!40000 ALTER TABLE `m_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_monitor_item`
--

DROP TABLE IF EXISTS `m_monitor_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_monitor_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `itemid` varchar(45) NOT NULL,
  `monitor_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_monitor_item_1` (`user`),
  CONSTRAINT `FK_m_monitor_item_1` FOREIGN KEY (`user`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_monitor_item`
--

LOCK TABLES `m_monitor_item` WRITE;
/*!40000 ALTER TABLE `m_monitor_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_monitor_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_pm_messages`
--

DROP TABLE IF EXISTS `m_pm_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_pm_messages` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sentFrom` varchar(45) NOT NULL,
  `sentTo` varchar(45) DEFAULT NULL,
  `isSent` tinyint(1) DEFAULT '0',
  `isRead` tinyint(1) DEFAULT '0',
  `sentDate` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` varchar(4000) NOT NULL,
  `isCopy` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_pm_messages`
--

LOCK TABLES `m_pm_messages` WRITE;
/*!40000 ALTER TABLE `m_pm_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_pm_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_pm_sent_messages`
--

DROP TABLE IF EXISTS `m_pm_sent_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_pm_sent_messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sentFrom` varchar(45) DEFAULT NULL,
  `sentTo` varchar(45) DEFAULT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `isRead` tinyint(1) DEFAULT NULL,
  `sentDate` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `body` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_pm_sent_messages`
--

LOCK TABLES `m_pm_sent_messages` WRITE;
/*!40000 ALTER TABLE `m_pm_sent_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_pm_sent_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_member`
--

DROP TABLE IF EXISTS `m_prj_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `projectId` int(10) unsigned NOT NULL,
  `joinDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_member_1_idx` (`username`),
  KEY `FK_m_prj_member_2_idx` (`projectId`),
  CONSTRAINT `FK_m_prj_member_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_member_2` FOREIGN KEY (`projectId`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_member`
--

LOCK TABLES `m_prj_member` WRITE;
/*!40000 ALTER TABLE `m_prj_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_message`
--

DROP TABLE IF EXISTS `m_prj_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) NOT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `messagehtml` varchar(4000) DEFAULT NULL,
  `posteddate` datetime NOT NULL,
  `posteduser` varchar(45) DEFAULT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `isStick` tinyint(1) DEFAULT NULL,
  `isNews` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_message_2` (`projectid`),
  KEY `FK_m_prj_message_3_idx` (`sAccountId`),
  CONSTRAINT `FK_m_prj_message_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_message_3` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_message`
--

LOCK TABLES `m_prj_message` WRITE;
/*!40000 ALTER TABLE `m_prj_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_milestone`
--

DROP TABLE IF EXISTS `m_prj_milestone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_milestone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `owner` varchar(45) NOT NULL,
  `flag` varchar(45) DEFAULT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `iscompleted` tinyint(1) DEFAULT '1',
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PK_m_prj_milestone_1_idx` (`owner`),
  KEY `PK_m_prj_milestone_2_idx` (`projectid`),
  CONSTRAINT `PK_m_prj_milestone_1` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PK_m_prj_milestone_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_milestone`
--

LOCK TABLES `m_prj_milestone` WRITE;
/*!40000 ALTER TABLE `m_prj_milestone` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_milestone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_monitor`
--

DROP TABLE IF EXISTS `m_prj_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_monitor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_monitor_1` (`projectid`),
  KEY `FK_m_prj_monitor_2` (`username`),
  CONSTRAINT `FK_m_prj_monitor_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_monitor_2` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_monitor`
--

LOCK TABLES `m_prj_monitor` WRITE;
/*!40000 ALTER TABLE `m_prj_monitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_monitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_problem`
--

DROP TABLE IF EXISTS `m_prj_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_problem` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `issuename` varchar(400) NOT NULL,
  `description` varchar(4000) NOT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `raisedbyuser` varchar(45) DEFAULT NULL,
  `assigntouser` varchar(45) DEFAULT NULL,
  `impact` varchar(45) DEFAULT NULL COMMENT 'from 1-5 (low - high)',
  `priority` varchar(45) DEFAULT NULL COMMENT 'from 1-5',
  `status` varchar(45) DEFAULT NULL COMMENT '0 (Open) and 1 (Closed)',
  `dateraised` datetime DEFAULT NULL,
  `datedue` datetime DEFAULT NULL,
  `actualstartdate` datetime DEFAULT NULL,
  `actualenddate` datetime DEFAULT NULL,
  `level` double unsigned DEFAULT NULL,
  `resolution` varchar(4000) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `problemsource` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_risk_1` (`projectid`),
  KEY `FK_m_prj_risk_2` (`raisedbyuser`),
  KEY `FK_m_prj_risk_1_idx` (`raisedbyuser`),
  KEY `FK_m_prj_risk_2_idx` (`assigntouser`),
  KEY `FK_m_prj_risk_3_idx` (`sAccountId`),
  CONSTRAINT `FK_m_prj_risk_1` FOREIGN KEY (`raisedbyuser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk_2` FOREIGN KEY (`assigntouser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk_3` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_problem`
--

LOCK TABLES `m_prj_problem` WRITE;
/*!40000 ALTER TABLE `m_prj_problem` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_project`
--

DROP TABLE IF EXISTS `m_prj_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `account` int(10) unsigned DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL,
  `shortname` varchar(45) DEFAULT NULL,
  `planStartDate` datetime DEFAULT NULL,
  `planEndDate` datetime DEFAULT NULL,
  `targetBudget` double DEFAULT NULL,
  `homePage` varchar(255) DEFAULT NULL,
  `actualBudget` double DEFAULT NULL,
  `projectType` varchar(45) DEFAULT NULL,
  `projectStatus` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `defaultBillingRate` double DEFAULT NULL,
  `actualStartDate` datetime DEFAULT NULL,
  `actualEndDate` datetime DEFAULT NULL,
  `defaultOvertimeBillingRate` double DEFAULT NULL,
  `currencyid` int(11) DEFAULT NULL,
  `progress` double DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_project_2` (`owner`),
  KEY `FK_m_project_project_1` (`account`),
  KEY `FK_m_prj_project_3` (`currencyid`),
  KEY `FK_m_prj_project_4` (`sAccountId`),
  CONSTRAINT `FK_m_prj_project_2` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_project_3` FOREIGN KEY (`currencyid`) REFERENCES `s_currency` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_project_4` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_project_project_1` FOREIGN KEY (`account`) REFERENCES `m_crm_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_project`
--

LOCK TABLES `m_prj_project` WRITE;
/*!40000 ALTER TABLE `m_prj_project` DISABLE KEYS */;
INSERT INTO `m_prj_project` VALUES (1,'a',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `m_prj_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_risk`
--

DROP TABLE IF EXISTS `m_prj_risk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_risk` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `riskname` varchar(400) NOT NULL,
  `description` varchar(4000) NOT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `raisedbyuser` varchar(45) DEFAULT NULL,
  `assigntouser` varchar(45) DEFAULT NULL,
  `consequence` varchar(45) DEFAULT NULL COMMENT 'from 1-5 (low - high)',
  `probalitity` varchar(45) DEFAULT NULL COMMENT 'from 1-5',
  `status` varchar(45) DEFAULT NULL COMMENT '0 (Open) and 1 (Closed)',
  `dateraised` datetime DEFAULT NULL,
  `datedue` datetime DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `resolution` varchar(4000) DEFAULT NULL,
  `level` double unsigned DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_risk1_1` (`projectid`),
  KEY `FK_m_prj_risk1_2` (`raisedbyuser`),
  KEY `FK_m_prj_risk1_3` (`assigntouser`),
  KEY `FK_m_prj_risk1_4_idx` (`sAccountId`),
  CONSTRAINT `FK_m_prj_risk1_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk1_2` FOREIGN KEY (`raisedbyuser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk1_3` FOREIGN KEY (`assigntouser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk1_4` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_risk`
--

LOCK TABLES `m_prj_risk` WRITE;
/*!40000 ALTER TABLE `m_prj_risk` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_risk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_role`
--

DROP TABLE IF EXISTS `m_prj_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `permissionVal` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_role_1` (`sAccountId`),
  KEY `FK_m_prj_role_2_idx` (`projectid`),
  CONSTRAINT `FK_m_prj_role_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_role_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_role`
--

LOCK TABLES `m_prj_role` WRITE;
/*!40000 ALTER TABLE `m_prj_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_task`
--

DROP TABLE IF EXISTS `m_prj_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `taskname` varchar(255) NOT NULL,
  `percentagecomplete` double DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `priority` int(10) unsigned DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `isestimated` tinyint(1) DEFAULT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `taskindex` int(10) unsigned DEFAULT NULL,
  `actualStartDate` datetime DEFAULT NULL,
  `actualEndDate` datetime DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `actualCost` double DEFAULT NULL,
  `ismilestone` tinyint(1) DEFAULT NULL,
  `tasklistid` int(11) unsigned DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_task_1` (`projectid`),
  KEY `FK_m_prj_task_3_idx` (`tasklistid`),
  KEY `FK_m_prj_task_3_idx1` (`assignUser`),
  KEY `FK_m_prj_task_4_idx` (`sAccountId`),
  CONSTRAINT `FK_m_prj_task_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_task_2` FOREIGN KEY (`tasklistid`) REFERENCES `m_prj_task_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_task_3` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_task_4` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_task`
--

LOCK TABLES `m_prj_task` WRITE;
/*!40000 ALTER TABLE `m_prj_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_task_list`
--

DROP TABLE IF EXISTS `m_prj_task_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_task_list` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `projectid` int(11) unsigned NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `sAccountId` int(11) DEFAULT NULL,
  `milestoneId` int(11) DEFAULT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `groupIndex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `PK_m_prj_task_list_1_idx` (`projectid`),
  KEY `PK_m_prj_task_list_2_idx` (`milestoneId`),
  KEY `PK_m_prj_task_list_3_idx` (`owner`),
  KEY `PK_m_prj_task_list_1_idx1` (`projectid`),
  CONSTRAINT `PK_m_prj_task_list_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PK_m_prj_task_list_2` FOREIGN KEY (`milestoneId`) REFERENCES `m_prj_milestone` (`id`) ON DELETE CASCADE ON UPDATE SET NULL,
  CONSTRAINT `PK_m_prj_task_list_3` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_task_list`
--

LOCK TABLES `m_prj_task_list` WRITE;
/*!40000 ALTER TABLE `m_prj_task_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_task_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_rss_agreegator`
--

DROP TABLE IF EXISTS `m_rss_agreegator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_rss_agreegator` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_module_agreegator_1` (`username`),
  CONSTRAINT `FK_m_module_agreegator_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_rss_agreegator`
--

LOCK TABLES `m_rss_agreegator` WRITE;
/*!40000 ALTER TABLE `m_rss_agreegator` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_rss_agreegator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_social_message`
--

DROP TABLE IF EXISTS `m_social_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_social_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) DEFAULT NULL,
  `postedDate` datetime DEFAULT NULL,
  `postedUser` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_social_message_1` (`postedUser`),
  CONSTRAINT `FK_m_social_message_1` FOREIGN KEY (`postedUser`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_social_message`
--

LOCK TABLES `m_social_message` WRITE;
/*!40000 ALTER TABLE `m_social_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_social_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_social_user_message`
--

DROP TABLE IF EXISTS `m_social_user_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_social_user_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `messages` text,
  PRIMARY KEY (`id`),
  KEY `FK_m_social_user_message_1` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_social_user_message`
--

LOCK TABLES `m_social_user_message` WRITE;
/*!40000 ALTER TABLE `m_social_user_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_social_user_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tag_relationship`
--

DROP TABLE IF EXISTS `m_tag_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tag_relationship` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contentpath` varchar(1000) NOT NULL,
  `tagtaxonomy` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_ecm_tag_relationship_1` (`tagtaxonomy`),
  CONSTRAINT `FK_m_ecm_tag_relationship_1` FOREIGN KEY (`tagtaxonomy`) REFERENCES `m_tag_taxonomy` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tag_relationship`
--

LOCK TABLES `m_tag_relationship` WRITE;
/*!40000 ALTER TABLE `m_tag_relationship` DISABLE KEYS */;
INSERT INTO `m_tag_relationship` VALUES (1,'content1',1),(2,'content1',2),(3,'content2',3),(4,'xyz',1);
/*!40000 ALTER TABLE `m_tag_relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tag_tag`
--

DROP TABLE IF EXISTS `m_tag_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tag_tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tag` varchar(45) NOT NULL,
  `taggroup` varchar(45) DEFAULT NULL,
  `slug` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tag_tag`
--

LOCK TABLES `m_tag_tag` WRITE;
/*!40000 ALTER TABLE `m_tag_tag` DISABLE KEYS */;
INSERT INTO `m_tag_tag` VALUES (1,'tag1',NULL,'tag1'),(2,'tag2',NULL,'tag2'),(3,'tag3',NULL,'tag3');
/*!40000 ALTER TABLE `m_tag_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tag_taxonomy`
--

DROP TABLE IF EXISTS `m_tag_taxonomy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tag_taxonomy` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tagid` int(10) unsigned NOT NULL,
  `parent` int(10) unsigned DEFAULT NULL,
  `count` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_ecm_tag_taxonomy_1` (`tagid`),
  CONSTRAINT `FK_m_ecm_tag_taxonomy_1` FOREIGN KEY (`tagid`) REFERENCES `m_tag_tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tag_taxonomy`
--

LOCK TABLES `m_tag_taxonomy` WRITE;
/*!40000 ALTER TABLE `m_tag_taxonomy` DISABLE KEYS */;
INSERT INTO `m_tag_taxonomy` VALUES (1,1,NULL,3),(2,2,NULL,4),(3,3,NULL,1);
/*!40000 ALTER TABLE `m_tag_taxonomy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_todo`
--

DROP TABLE IF EXISTS `m_todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_todo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `summary` varchar(255) NOT NULL,
  `statusId` int(10) unsigned NOT NULL COMMENT '0 is uncompleted; 1 is completed',
  `owner` varchar(45) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `duedate` datetime DEFAULT NULL,
  `sender` varchar(45) DEFAULT NULL,
  `categoryId` int(10) unsigned NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `reminder` int(10) unsigned DEFAULT NULL COMMENT 'Calculate by seconds',
  `percentageComplete` decimal(10,0) DEFAULT NULL,
  `completeDate` datetime DEFAULT NULL,
  `dismiss` tinyint(1) DEFAULT NULL,
  `enterdatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_todo_1` (`owner`),
  KEY `FK_m_todo_3` (`categoryId`),
  KEY `FK_m_todo_4` (`statusId`),
  KEY `FK_m_todo_2` (`sender`),
  CONSTRAINT `FK_m_todo_1` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_todo_2` FOREIGN KEY (`sender`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_todo_3` FOREIGN KEY (`categoryId`) REFERENCES `m_todo_category` (`id`),
  CONSTRAINT `FK_m_todo_4` FOREIGN KEY (`statusId`) REFERENCES `m_todo_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_todo`
--

LOCK TABLES `m_todo` WRITE;
/*!40000 ALTER TABLE `m_todo` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_todo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_todo_category`
--

DROP TABLE IF EXISTS `m_todo_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_todo_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_todo_category`
--

LOCK TABLES `m_todo_category` WRITE;
/*!40000 ALTER TABLE `m_todo_category` DISABLE KEYS */;
INSERT INTO `m_todo_category` VALUES (1,'xyz');
/*!40000 ALTER TABLE `m_todo_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_todo_status`
--

DROP TABLE IF EXISTS `m_todo_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_todo_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_todo_status`
--

LOCK TABLES `m_todo_status` WRITE;
/*!40000 ALTER TABLE `m_todo_status` DISABLE KEYS */;
INSERT INTO `m_todo_status` VALUES (1,'abc');
/*!40000 ALTER TABLE `m_todo_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_bug`
--

DROP TABLE IF EXISTS `m_tracker_bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_bug` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `summary` varchar(1000) NOT NULL,
  `detail` varchar(4000) DEFAULT NULL,
  `assignuser` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `logby` varchar(45) DEFAULT NULL,
  `severity` varchar(45) DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL,
  `lastUpdatedTime` datetime DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `duedate` datetime DEFAULT NULL,
  `environment` varchar(4000) DEFAULT NULL,
  `resolution` varchar(45) DEFAULT NULL,
  `cus_int_01` int(10) unsigned DEFAULT NULL,
  `cus_int_02` int(10) unsigned DEFAULT NULL,
  `cus_int_03` int(10) unsigned DEFAULT NULL,
  `cus_int_04` int(10) unsigned DEFAULT NULL,
  `cus_int_05` int(10) unsigned DEFAULT NULL,
  `cus_int_06` int(10) unsigned DEFAULT NULL,
  `cus_int_07` int(10) unsigned DEFAULT NULL,
  `cus_int_08` int(10) unsigned DEFAULT NULL,
  `cus_int_09` int(10) unsigned DEFAULT NULL,
  `cus_int_10` int(10) unsigned DEFAULT NULL,
  `cus_str_01` varchar(255) DEFAULT NULL,
  `cus_str_02` varchar(255) DEFAULT NULL,
  `cus_str_03` varchar(255) DEFAULT NULL,
  `cus_str_04` varchar(255) DEFAULT NULL,
  `cus_str_05` varchar(255) DEFAULT NULL,
  `cus_time_01` datetime DEFAULT NULL,
  `cus_time_02` datetime DEFAULT NULL,
  `cus_time_03` datetime DEFAULT NULL,
  `cus_time_04` datetime DEFAULT NULL,
  `cus_dbl_01` double DEFAULT NULL,
  `cus_dbl_02` double DEFAULT NULL,
  `cus_dbl_03` double DEFAULT NULL,
  `projectid` int(10) unsigned NOT NULL,
  `resolveddate` datetime DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `estimateTime` int(11) DEFAULT NULL,
  `estimateRemainTime` int(11) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_bug_1` (`assignuser`),
  KEY `FK_m_tracker_bug_2` (`logby`),
  KEY `FK_m_tracker_bug_4` (`projectid`),
  KEY `FK_m_tracker_bug_5_idx` (`sAccountId`),
  CONSTRAINT `FK_m_tracker_bug_5` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_bug_1` FOREIGN KEY (`assignuser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_bug_2` FOREIGN KEY (`logby`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_bug_4` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_bug`
--

LOCK TABLES `m_tracker_bug` WRITE;
/*!40000 ALTER TABLE `m_tracker_bug` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_tracker_bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_component`
--

DROP TABLE IF EXISTS `m_tracker_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_component` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `componentname` varchar(255) NOT NULL,
  `userlead` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_component_1` (`projectid`),
  KEY `FK_m_tracker_component_2` (`userlead`),
  KEY `FK_m_tracker_component_3` (`createdUser`),
  KEY `FK_m_tracker_component_4_idx` (`sAccountId`),
  CONSTRAINT `FK_m_tracker_component_4` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_component_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_component_2` FOREIGN KEY (`userlead`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_component_3` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_component`
--

LOCK TABLES `m_tracker_component` WRITE;
/*!40000 ALTER TABLE `m_tracker_component` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_tracker_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_metadata`
--

DROP TABLE IF EXISTS `m_tracker_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_metadata` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `xmlstring` longtext,
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_metadata_1` (`projectid`),
  CONSTRAINT `FK_m_tracker_metadata_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_metadata`
--

LOCK TABLES `m_tracker_metadata` WRITE;
/*!40000 ALTER TABLE `m_tracker_metadata` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_tracker_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_query`
--

DROP TABLE IF EXISTS `m_tracker_query`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_query` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `queryname` varchar(255) NOT NULL,
  `sharetype` int(10) unsigned NOT NULL COMMENT '0: private, 1: project, 2: public',
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `querytext` varchar(4000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `projectid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_query_1` (`owner`),
  KEY `FK_m_tracker_query_2` (`projectid`),
  CONSTRAINT `FK_m_tracker_query_1` FOREIGN KEY (`owner`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_query_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_query`
--

LOCK TABLES `m_tracker_query` WRITE;
/*!40000 ALTER TABLE `m_tracker_query` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_tracker_query` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_related_bug`
--

DROP TABLE IF EXISTS `m_tracker_related_bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_related_bug` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bugid` int(10) unsigned NOT NULL,
  `relatedid` int(10) unsigned NOT NULL,
  `relatetype` decimal(10,0) NOT NULL COMMENT '''0: is related to this, 1:isduplicate of this, 2:depends on this''',
  `comment` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_related_item_1` (`bugid`),
  KEY `FK_m_tracker_related_item_2` (`relatedid`),
  CONSTRAINT `FK_m_tracker_related_bug_1` FOREIGN KEY (`bugid`) REFERENCES `m_tracker_bug` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_related_bug_2` FOREIGN KEY (`relatedid`) REFERENCES `m_tracker_bug` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_related_bug`
--

LOCK TABLES `m_tracker_related_bug` WRITE;
/*!40000 ALTER TABLE `m_tracker_related_bug` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_tracker_related_bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_related_item`
--

DROP TABLE IF EXISTS `m_tracker_related_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_related_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `typeid` int(10) unsigned NOT NULL,
  `type` int(10) unsigned NOT NULL COMMENT '0:fixed version, 1:affected versions, 2:component',
  `refkey` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_related_item`
--

LOCK TABLES `m_tracker_related_item` WRITE;
/*!40000 ALTER TABLE `m_tracker_related_item` DISABLE KEYS */;
INSERT INTO `m_tracker_related_item` VALUES (1,0,1,''),(2,0,2,''),(3,1,2,''),(4,2,1,'');
/*!40000 ALTER TABLE `m_tracker_related_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_version`
--

DROP TABLE IF EXISTS `m_tracker_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_version` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `duedate` datetime DEFAULT NULL,
  `versionname` varchar(45) NOT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_version_1` (`projectid`),
  KEY `FK_m_tracker_version_2` (`createdUser`),
  KEY `FK_m_tracker_version_3_idx` (`sAccountId`),
  CONSTRAINT `FK_m_tracker_version_3` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_version_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_version_2` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_version`
--

LOCK TABLES `m_tracker_version` WRITE;
/*!40000 ALTER TABLE `m_tracker_version` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_tracker_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_account`
--

DROP TABLE IF EXISTS `s_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdTime` timestamp NULL DEFAULT NULL,
  `billingPlanId` int(11) DEFAULT NULL,
  `accountName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_account_fk_1` (`billingPlanId`),
  CONSTRAINT `s_account_fk_1` FOREIGN KEY (`billingPlanId`) REFERENCES `s_billing_plan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_account`
--

LOCK TABLES `s_account` WRITE;
/*!40000 ALTER TABLE `s_account` DISABLE KEYS */;
INSERT INTO `s_account` VALUES (1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `s_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_activitystream`
--

DROP TABLE IF EXISTS `s_activitystream`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_activitystream` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sAccountId` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `module` varchar(45) DEFAULT NULL,
  `nameField` text,
  `extraTypeId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_activitystream_1_idx` (`sAccountId`),
  CONSTRAINT `FK_m_crm_activitystream_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_activitystream`
--

LOCK TABLES `s_activitystream` WRITE;
/*!40000 ALTER TABLE `s_activitystream` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_activitystream` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_billing_plan`
--

DROP TABLE IF EXISTS `s_billing_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_billing_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billingType` varchar(45) DEFAULT NULL,
  `numUsers` int(11) DEFAULT NULL,
  `volume` int(11) DEFAULT NULL,
  `numProjects` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_billing_plan`
--

LOCK TABLES `s_billing_plan` WRITE;
/*!40000 ALTER TABLE `s_billing_plan` DISABLE KEYS */;
INSERT INTO `s_billing_plan` VALUES (1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `s_billing_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_country`
--

DROP TABLE IF EXISTS `s_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `countryname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_country`
--

LOCK TABLES `s_country` WRITE;
/*!40000 ALTER TABLE `s_country` DISABLE KEYS */;
INSERT INTO `s_country` VALUES (1,'Viet Nam');
/*!40000 ALTER TABLE `s_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_currency`
--

DROP TABLE IF EXISTS `s_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `isocode` varchar(45) NOT NULL,
  `symbol` varchar(45) NOT NULL DEFAULT '$',
  `conversionrate` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_currency`
--

LOCK TABLES `s_currency` WRITE;
/*!40000 ALTER TABLE `s_currency` DISABLE KEYS */;
INSERT INTO `s_currency` VALUES (1,'VND','1','a',NULL);
/*!40000 ALTER TABLE `s_currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_role_permission`
--

DROP TABLE IF EXISTS `s_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_role_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `roleVal` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_role_permission_1` (`roleid`),
  CONSTRAINT `FK_role_permission_1` FOREIGN KEY (`roleid`) REFERENCES `s_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_role_permission`
--

LOCK TABLES `s_role_permission` WRITE;
/*!40000 ALTER TABLE `s_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_roles`
--

DROP TABLE IF EXISTS `s_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_roles` (
  `rolename` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK_s_roles_1` (`sAccountId`),
  CONSTRAINT `FK_s_roles_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_roles`
--

LOCK TABLES `s_roles` WRITE;
/*!40000 ALTER TABLE `s_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_title`
--

DROP TABLE IF EXISTS `s_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_title` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_title`
--

LOCK TABLES `s_title` WRITE;
/*!40000 ALTER TABLE `s_title` DISABLE KEYS */;
INSERT INTO `s_title` VALUES (1,'Mr'),(2,'Mrs'),(3,'Ms'),(4,'Dr'),(5,'Prof');
/*!40000 ALTER TABLE `s_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_user`
--

DROP TABLE IF EXISTS `s_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_user` (
  `username` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `middlename` varchar(45) DEFAULT '',
  `lastname` varchar(45) NOT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `dateofbirth` datetime DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `registeredTime` datetime DEFAULT NULL COMMENT 'has value follows format MM/dd/YYYY hh:ss',
  `lastAccessedTime` datetime DEFAULT NULL COMMENT 'has value follows format MM/dd/YYYY hh:ss',
  `accountId` int(11) NOT NULL,
  `countryid` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `timezoneid` varchar(255) DEFAULT NULL,
  `registrationSource` varchar(100) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  `registerStatus` varchar(45) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`),
  KEY `s_user_fk_1` (`accountId`),
  KEY `FK_s_user_2` (`countryid`),
  KEY `FK_s_user_3_idx` (`roleid`),
  CONSTRAINT `FK_s_user_3` FOREIGN KEY (`roleid`) REFERENCES `s_roles` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_s_user_2` FOREIGN KEY (`countryid`) REFERENCES `s_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `s_user_fk_1` FOREIGN KEY (`accountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user`
--

LOCK TABLES `s_user` WRITE;
/*!40000 ALTER TABLE `s_user` DISABLE KEYS */;
INSERT INTO `s_user` VALUES ('user1','a1',NULL,'b1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user2','a2',NULL,'b2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `s_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_user_information`
--

DROP TABLE IF EXISTS `s_user_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_hr_employee_2` (`username`),
  KEY `FK_m_hr_employee_3` (`sAccountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user_information`
--

LOCK TABLES `s_user_information` WRITE;
/*!40000 ALTER TABLE `s_user_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_user_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_user_permission`
--

DROP TABLE IF EXISTS `s_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_user_permission` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `hasPermission` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `s_user_permission_idx` (`username`),
  CONSTRAINT `s_user_permission` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user_permission`
--

LOCK TABLES `s_user_permission` WRITE;
/*!40000 ALTER TABLE `s_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_user_preference`
--

DROP TABLE IF EXISTS `s_user_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_user_preference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `lastModuleVisit` varchar(45) DEFAULT NULL,
  `lastAccessedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_user_preference_1_idx` (`username`),
  CONSTRAINT `FK_s_user_preference_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user_preference`
--

LOCK TABLES `s_user_preference` WRITE;
/*!40000 ALTER TABLE `s_user_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_user_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schema_version`
--

DROP TABLE IF EXISTS `schema_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schema_version` (
  `version` varchar(32) NOT NULL,
  `applied_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `duration` int(11) NOT NULL,
  UNIQUE KEY `version` (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schema_version`
--

LOCK TABLES `schema_version` WRITE;
/*!40000 ALTER TABLE `schema_version` DISABLE KEYS */;
INSERT INTO `schema_version` VALUES ('20121102000000','2012-12-07 07:11:13',12350);
/*!40000 ALTER TABLE `schema_version` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-22 20:43:18

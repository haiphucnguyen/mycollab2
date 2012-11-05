CREATE DATABASE  IF NOT EXISTS `mycollab` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mycollab`;
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
  `status` tinyint(1) DEFAULT NULL COMMENT '0 (Open) and 1 (Closed)',
  `dateraised` datetime DEFAULT NULL,
  `datedue` datetime DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `resolution` varchar(4000) DEFAULT NULL,
  `level` int(10) unsigned DEFAULT NULL,
  `risksourceid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_risk1_1` (`projectid`),
  KEY `FK_m_prj_risk_4` (`risksourceid`),
  KEY `FK_m_prj_risk1_2` (`raisedbyuser`),
  KEY `FK_m_prj_risk1_3` (`assigntouser`),
  CONSTRAINT `FK_m_prj_risk1_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk1_2` FOREIGN KEY (`raisedbyuser`) REFERENCES `engroup`.`s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk1_3` FOREIGN KEY (`assigntouser`) REFERENCES `engroup`.`s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk_4` FOREIGN KEY (`risksourceid`) REFERENCES `m_prj_risk_source` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
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
-- Table structure for table `m_crm_accounttype`
--

DROP TABLE IF EXISTS `m_crm_accounttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_accounttype` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_accounttype_2_idx` (`sAccountId`),
  CONSTRAINT `FK_m_crm_accounttype_2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_accounttype`
--

LOCK TABLES `m_crm_accounttype` WRITE;
/*!40000 ALTER TABLE `m_crm_accounttype` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_accounttype` ENABLE KEYS */;
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
-- Table structure for table `chat_con_binval`
--

DROP TABLE IF EXISTS `chat_con_binval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_con_binval` (
  `BINVAL_ID` varchar(64) NOT NULL,
  `BINVAL_DATA` longblob NOT NULL,
  UNIQUE KEY `chat_con_binval_IDX` (`BINVAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_con_binval`
--

LOCK TABLES `chat_con_binval` WRITE;
/*!40000 ALTER TABLE `chat_con_binval` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_con_binval` ENABLE KEYS */;
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
  `owner` varchar(45) NOT NULL,
  `posteddate` datetime NOT NULL,
  `commentid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_comment`
--

LOCK TABLES `m_comment` WRITE;
/*!40000 ALTER TABLE `m_comment` DISABLE KEYS */;
INSERT INTO `m_comment` VALUES (1,NULL,'hainguyen','2011-01-01 00:00:00','comment-crm-1'),(5,'aaa','hainguyen','2012-02-20 07:41:38','crm-2');
/*!40000 ALTER TABLE `m_comment` ENABLE KEYS */;
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
-- Table structure for table `m_hr_employee_family`
--

DROP TABLE IF EXISTS `m_hr_employee_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_family` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `relationship` varchar(45) NOT NULL COMMENT '0: wife, 1: husband, 2: child, 3:others',
  `dob` varchar(45) DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hr_employee_family_1` (`employeeid`),
  CONSTRAINT `FK_hr_employee_family_1` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_family`
--

LOCK TABLES `m_hr_employee_family` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_family` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_division`
--

DROP TABLE IF EXISTS `m_hr_division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_division` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(4000) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `division` varchar(255) NOT NULL,
  `companyid` int(11) NOT NULL,
  `divisionparentid` int(11) DEFAULT NULL,
  `divisiontypeid` int(11) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `divisionheadid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60BF6042C86EE491` (`companyid`),
  KEY `FK60BF6042B281B3E3` (`divisiontypeid`),
  KEY `FK60BF6042B76561AD` (`divisionparentid`),
  KEY `FK60BF604224D3B4D2` (`companyid`),
  KEY `FK60BF6042D389E442` (`divisiontypeid`),
  KEY `FK60BF6042E79A998C` (`divisionparentid`),
  KEY `FK_m_hr_division_4` (`divisionheadid`),
  CONSTRAINT `FK60BF604224D3B4D2` FOREIGN KEY (`companyid`) REFERENCES `m_hr_company` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK60BF6042B281B3E3` FOREIGN KEY (`divisiontypeid`) REFERENCES `m_hr_divisiontype` (`id`),
  CONSTRAINT `FK60BF6042E79A998C` FOREIGN KEY (`divisionparentid`) REFERENCES `m_hr_division` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_division_4` FOREIGN KEY (`divisionheadid`) REFERENCES `m_hr_employee` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_division`
--

LOCK TABLES `m_hr_division` WRITE;
/*!40000 ALTER TABLE `m_hr_division` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_view`
--

DROP TABLE IF EXISTS `s_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_view` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `viewname` varchar(100) NOT NULL,
  `title` varchar(45) NOT NULL,
  `uri` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '0:inactive; 1:active',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_view`
--

LOCK TABLES `s_view` WRITE;
/*!40000 ALTER TABLE `s_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_view` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
-- Table structure for table `m_prj_task_resource`
--

DROP TABLE IF EXISTS `m_prj_task_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_task_resource` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `taskid` int(10) unsigned NOT NULL,
  `resourceid` int(10) unsigned NOT NULL,
  `unit` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_task_resource_1` (`resourceid`),
  KEY `FK_m_prj_task_resource_2` (`taskid`),
  CONSTRAINT `FK_m_prj_task_resource_1` FOREIGN KEY (`resourceid`) REFERENCES `m_prj_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_task_resource_2` FOREIGN KEY (`taskid`) REFERENCES `m_prj_task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_task_resource`
--

LOCK TABLES `m_prj_task_resource` WRITE;
/*!40000 ALTER TABLE `m_prj_task_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_task_resource` ENABLE KEYS */;
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
  `status` tinyint(1) DEFAULT NULL COMMENT '0 (Open) and 1 (Closed)',
  `dateraised` datetime DEFAULT NULL,
  `datedue` datetime DEFAULT NULL,
  `actualstartdate` datetime DEFAULT NULL,
  `actualenddate` datetime DEFAULT NULL,
  `level` int(10) unsigned DEFAULT NULL,
  `resolution` varchar(4000) DEFAULT NULL,
  `problemsourceid` int(10) unsigned DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_risk_1` (`projectid`),
  KEY `FK_m_prj_risk_2` (`raisedbyuser`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_problem`
--

LOCK TABLES `m_prj_problem` WRITE;
/*!40000 ALTER TABLE `m_prj_problem` DISABLE KEYS */;
INSERT INTO `m_prj_problem` VALUES (1,'a','aaa',1,'user2','user1',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(2,'b','aaa',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'c','aaa',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
  `priority` int(10) unsigned DEFAULT NULL,
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
INSERT INTO `m_prj_project` VALUES (1,'a',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `m_prj_project` ENABLE KEYS */;
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
-- Table structure for table `m_hr_licenses`
--

DROP TABLE IF EXISTS `m_hr_licenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_licenses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(2000) DEFAULT NULL,
  `license` varchar(255) NOT NULL,
  `code` varchar(45) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_hr_licenses_1` (`sAccountId`),
  CONSTRAINT `FK_m_hr_licenses_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_licenses`
--

LOCK TABLES `m_hr_licenses` WRITE;
/*!40000 ALTER TABLE `m_hr_licenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_licenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_ecm_ver_bundle`
--

DROP TABLE IF EXISTS `m_ecm_ver_bundle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_ver_bundle` (
  `NODE_ID` varbinary(16) NOT NULL,
  `BUNDLE_DATA` longblob NOT NULL,
  UNIQUE KEY `m_ecm_ver_bundle_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_ver_bundle`
--

LOCK TABLES `m_ecm_ver_bundle` WRITE;
/*!40000 ALTER TABLE `m_ecm_ver_bundle` DISABLE KEYS */;
INSERT INTO `m_ecm_ver_bundle` VALUES ('\0ïÇšNI†ŒáRqx3g','\0\0\0\0\0ÏEfRK\nµtm]Ù-^N\0\0ÿÿÿÿÿÿÿÿ\0=R@\rÀOOµ®›£“­Ø\0\0\0\0$292c9885-4aa4-4e10-a305-dccceff70257\0\0\0\0'),('¸%eÉE§>¸1Zp6','\0\0\0\0\0	e–¤5kyF#éâƒãoš\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\0À½yå+¾B°-ïÄkâ£Ş\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.094+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡ÿÿÿÿ6\"	oŸEã«¯-ÌÏ‘ŸS\0\0\0\0content\0\0\0\0'),('ìÖm›1H\"“ŠIJU$L/','\0\0\0\0\0¤Ë\"¬bëCé¬-ìVM)H\0\0ÿÿÿÿÿÿÿÿ\00}¿¥+Cëƒ	@zÕC\0\0\0\0$765f9367-ad40-4605-88d3-b361c5ddba12\0\0\0\0'),('èWñ“M—£	.SÏr‚d','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0@ÇµÔÒKÖ˜#OlÉ3\0\0\0\076\0\0\0\0'),('âò²<B¦ØTœò…7','\0\0\0\0\0E¾—HieAŞ‹`Í…Ó9¦\0\0ÿÿÿÿÿÿÿÿ\0+·ê+5%D ƒ•GC0é\0\0\0\0$ef98f3bc-1c43-4a56-a2a6-09ef72ccebe3\0\0\0\0'),('Òßİ¾ÜLt‚\'DÅi2','\0\0\0\0\0+·ê+5%D ƒ•GC0é\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('$‘Fz¹@ ³\0óPB=','\0\0\0\0\0GèvLL¶ö$©W‘İ\0\0ÿÿÿÿÿÿÿÿ\0Í\nCÛ9AÈ»¸å«0×Ã\0\0\0\0$3a6c1237-0a3a-4b42-b34c-f0054368352b\0\0\0\0'),('rªŞ¢DI™È“^š','\0\0\0\0\0•ˆ?C@J¥Pÿ&à§È\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('P1‘ÜN]‹	§üAëİı','\0\0\0\0\0	EºÚäkùA)¥ÿ8FÕ²™J\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\nplain/text\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.128+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('ÏP9ï]Mé¼?~¶÷A','\0\0\0\0\0	Õ-C›Oî¶Õ9ÕHz!\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$6057a90a-b6a9-42a3-94e2-66bdb15e4580\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('¦ÁH@ßO,è$ï·Æ','\0\0\0\0\0•ˆ?C@J¥Pÿ&à§È\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:09.274+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ5CcôÊC=O?téì—\0\0\0\0\nfrozenNode\0\0\0\0'),('	[UşK/‡”¥9Ç-Ó','\0\0\0\0\0	‚ŞIYİ­H&©(D‹H/\'Ã\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content 2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0plain/x\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.247+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('	µ=ä÷\nN‘-Y/[;Œ','\0\0\0\0\0EÑEäMöµW‹UR)\0\0ÿÿÿÿÿÿÿÿ\0İ†.)¯B’ZV&5”\0\0\0\064\0\0\0\0'),('6ë:Î?BO›DÍ»[ğc?','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0­úy¸şJy“ë`…\ZÔê\0\0\0\07c\0\0\0\0'),('G,‘\nD@Ç•‡gŒèX-','\0\0\0\0\0DjôÂNE‰n8óıãÇĞ\0\0ÿÿÿÿÿÿÿÿ\0·yÈ„ˆFÑ¿¨ü–7ú	\0\0\0\0$a5a24541-8265-4837-87a3-bb44653ee66b\0\0\0\0'),('{ç#2Mù–}~\n[¤„z','\0\0\0\0\0	”ZÔVVÆFï–U£G.¾Êí\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content 2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0plain/x\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.235+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('\rÖÏ# äAÒ)HZñj¢','\0\0\0\0\0:Ñ3·òG‡ôb¦ŞC\n\r\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('7I G¢=IÀÂOÂ','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0e–¤5kyF#éâƒãoš\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.200+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0NÂb\nƒO²Š)2/5 ÿÿÿÿBÛÓ°N?¶\ró¢hÎ×\0\0\0\0\nfrozenNode\0\0\0'),('NÂb\nƒO²Š)2/5 ','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\07I G¢=IÀÂOÂ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.272+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0%f+9?®O»¨—ÆğÙÿÿÿÿ‚ŞIYİ­H&©(D‹H/\'Ã\0\0\0\0\nfrozenNode\0\0\0'),('•ˆ?C@J¥Pÿ&à§È','\0\0\0\0\0rÍëi<ÆE©›Àÿk¦#Ÿ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$11f843f6-fb4e-47e5-b428-67322fe3c4c3ÿÿÿÿrªŞ¢DI™È“^š\0\0\0\0\rversionLabels¦ÁH@ßO,è$ï·Æ\0\0\0\0rootVersion\0\0\0\0'),('Md‡¿£Mœ”Z	ƒø','\0\0\0\0\0	0d-sæİA5¶}öÙ´V¡\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\nplain/text\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.518+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('$Z/ï#Nµ®¥ŒãŠÀº','\0\0\0\0\0¸ƒ<¸I@NÂ¥)f9Æ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:56.346+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ£=ŞfO\Z“¸çoDt¶¦\0\0\0\0\nfrozenNode\0\0\0\0'),('S…™çDÿ¶™-ÿaGÉ','\0\0\0\0\0	Í ÚDS£E\0‚VôPÒi¸\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$a6b230c8-a475-4433-a335-560ca312afdf\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),(';®DñšÒË1ö5ÒŠ','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0¼¿\'ù¾1Kç·Ú?ød4Ñ\0\0\0\0f8\0\0\0\0'),('§¬M\nMl¨Ší\Z9¨iH','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0DjôÂNE‰n8óıãÇĞ\0\0\0\0a2\0\0\0\0'),('\Zòï¨û.AÅ½(œœ?','\0\0\0\0\0¯^š-¢J‡µ^…ŸÔ\0y\0\0ÿÿÿÿÿÿÿÿ\0kÈ`­æINˆ—loj?4X\0\0\0\013\0\0\0\0'),('+a”‹ÅJİ¥È¬‰\Zš','\0\0\0\0\0X©ÀÎI#K2°É ç!2È÷\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$43fbcada-8280-4343-be8f-6545c2388065ÿÿÿÿÙ±İN?­^Œí9\r˜d\0\0\0\0\rversionLabelsæÈyLZ’’‹ŸÇÆ˜§\0\0\0\0rootVersion\0\0\0\0'),('Í\nCÛ9AÈ»¸å«0×Ã','\0\0\0\0\0$‘Fz¹@ ³\0óPB=\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$3a6c1237-0a3a-4b42-b34c-f0054368352bÿÿÿÿsxÚGÉAj‚W«š?!ù\0\0\0\0\rversionLabels|·±DqAº¦²ËšãÑ\0\0\0\0rootVersion\0\0\0\0'),('Í?’ÿNpq$Xİ‡úú','\0\0\0\0\0‚/¤µJJˆà“`¾VİV\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:08.406+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ?~k}êAm,¦¡FåBW\0\0\0\0\nfrozenNode\0\0\0\0'),('W3ÜHªI´¶ìuğèS','\0\0\0\0\0uÇ¨AØ¼H\\¨¶\\©>–Ts\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:56.447+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ”FRÜ©1E^²°W…V¬ˆ\0\0\0\0\nfrozenNode\0\0\0\0'),('Ùšø÷Iq¸®”ñÔ|{','\0\0\0\0\0}³ª9»eOË»nØl0wöø\0\0ÿÿÿÿÿÿÿÿ\0ÁeÔºŸCP¨|ûU·F/N\0\0\0\0$340ef2fa-6b36-4429-ac47-5fb3352af8a6\0\0\0\0'),('!èc$E²AmœFì\'üSÃ','\0\0\0\0\0`±.pÔJ¿>ÜUì_îû\0\0ÿÿÿÿÿÿÿÿ\0èD¯ñ @Ï£®[{?ˆn\0\0\0\0$a6b230c8-a475-4433-a335-560ca312afdf\0\0\0\0'),('$iaL‰Näƒ<ñ{Øô\n','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0®ƒæøS–EÔ”ƒÆ£Cüæ\0\0\0\017\0\0\0\0'),('%f+9?®O»¨—ÆğÙ','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0NÂb\nƒO²Š)2/5 \0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.153+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0šªfÔ‘@áš+(pë)ÀÿÿÿÿEºÚäkùA)¥ÿ8FÕ²™J\0\0\0\0\nfrozenNode\0\0\0'),('+·ê+5%D ƒ•GC0é','\0\0\0\0\0âò²<B¦ØTœò…7\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$ef98f3bc-1c43-4a56-a2a6-09ef72ccebe3ÿÿÿÿÒßİ¾ÜLt‚\'DÅi2\0\0\0\0\rversionLabels…v‰ıÊÒI¦‡vâ·i¬R,\0\0\0\0rootVersion\0\0\0\0'),('0d-sæİA5¶}öÙ´V¡','\0\0\0\0\0	“3Ú[xA*¥´˜˜Œ,‘]\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\0O]ÎóŠEğ“\'ÁÖ]X„|\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.537+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0AAAAÿÿÿÿMd‡¿£Mœ”Z	ƒø\0\0\0\0content\0\0\0\0'),('0}¿¥+Cëƒ	@zÕC','\0\0\0\0\0ìÖm›1H\"“ŠIJU$L/\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$765f9367-ad40-4605-88d3-b361c5ddba12ÿÿÿÿóİ‡âıAÔÛ£•=/?\0\0\0\0\rversionLabelsè(µÿ4@J1@Vnã&\0\0\0\0rootVersion\0\0\0\0'),('4Îau–BOä¼ØT£ÀõJ','\0\0\0\0\0´eÓ²4F®6nipºPv\0\0ÿÿÿÿÿÿÿÿ\0uÇ¨AØ¼H\\¨¶\\©>–Ts\0\0\0\0$8ef5cace-7ee1-4f7e-9d4f-674590d851ea\0\0\0\0'),('5CcôÊC=O?téì—','\0\0\0\0\0	¦ÁH@ßO,è$ï·Æ\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$11f843f6-fb4e-47e5-b428-67322fe3c4c3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('6†ÈSõI÷°–h¹[1','\0\0\0\0\0	ìå*Y¥HE‘<Áìà©\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$5e177071-30ac-467e-b40c-a59877ef28eb\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('6\"	oŸEã«¯-ÌÏ‘ŸS','\0\0\0\0\0	¸%eÉE§>¸1Zp6\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\nplain/text\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.088+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('7¶ZwÜ\"Oç•\nˆPŞß5','\0\0\0\0\0r´k7ëAhÅè3ô8\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$2c226142-7844-48f2-8064-aaba5d22b43dÿÿÿÿO6:›[@¦®-/tpö59\0\0\0\0\rversionLabelsÂÔvŒÔ¾@šŠ:K\nò‚æ\"\0\0\0\0rootVersion\0\0\0\0'),(':Ñ3·òG‡ôb¦ŞC\n\r','\0\0\0\0\0‡áîoïCE–«¢5!\"É\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$6057a90a-b6a9-42a3-94e2-66bdb15e4580ÿÿÿÿ\rÖÏ# äAÒ)HZñj¢\0\0\0\0\rversionLabelsÕ-C›Oî¶Õ9ÕHz!\0\0\0\0rootVersion\0\0\0\0'),(';x®ÈNK¶üß²G','\0\0\0\0\0·yÈ„ˆFÑ¿¨ü–7ú	\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:09.268+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿğa}°š\0Fm½~Ñü³ş\0\0\0\0\nfrozenNode\0\0\0\0'),(';¦ówûCm”¸BË4Æf','\0\0\0\0\0	À½yå+¾B°-ïÄkâ£Ş\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\0“3Ú[xA*¥´˜˜Œ,‘]\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.589+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0AAAAÿÿÿÿôãIûbO?¸Ì×¢Œ´¼\0\0\0\0content\0\0\0\0'),('=M—ÔìQEƒŒ·[Ú¯¥d','\0\0\0\0\0	è(µÿ4@J1@Vnã&\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$765f9367-ad40-4605-88d3-b361c5ddba12\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('=R@\rÀOOµ®›£“­Ø','\0\0\0\0\0\0ïÇšNI†ŒáRqx3g\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$292c9885-4aa4-4e10-a305-dccceff70257ÿÿÿÿD§}ùNØL$‰&YG}nM\0\0\0\0\rversionLabelsşô‰2ÈNcÂ[†.+\0\0\0\0rootVersion\0\0\0\0'),('?~k}êAm,¦¡FåBW','\0\0\0\0\0	Í?’ÿNpq$Xİ‡úú\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$2d8b14fa-9738-4f88-a2e8-f0434dcc8725\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('@ÇµÔÒKÖ˜#OlÉ3','\0\0\0\0\0èWñ“M—£	.SÏr‚d\0\0ÿÿÿÿÿÿÿÿ\0“HéCé‘§°>GBú\0\0\0\01f\0\0\0\0'),('Aü\'¼iPB`…^û@Âœ×','\0\0\0\0\0’,D×ÆDŠ‘â¿6^D\0\0ÿÿÿÿÿÿÿÿ\0_0½ÌHONB·wÒˆAhA\0\0\0\044\0\0\0\0'),('BÛÓ°N?¶\ró¢hÎ×','\0\0\0\0\0	7I G¢=IÀÂOÂ\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\0e–¤5kyF#éâƒãoš\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.186+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0AAAAÿÿÿÿı+›cßI<¼†M\nÑ\rJÌ\0\0\0\0content\0\0\0\0'),('DjôÂNE‰n8óıãÇĞ','\0\0\0\0\0§¬M\nMl¨Ší\Z9¨iH\0\0ÿÿÿÿÿÿÿÿ\0G,‘\nD@Ç•‡gŒèX-\0\0\0\045\0\0\0\0'),('D§}ùNØL$‰&YG}nM','\0\0\0\0\0=R@\rÀOOµ®›£“­Ø\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('D¶I{S4J_’(¤uv/X¹','\0\0\0\0\0‚/¤µJJˆà“`¾VİV\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('DÎ5g’ZOÃ¿®i<Lec','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0æi£–ÉLBVµ*ƒÛô–W\0\0\0\022\0\0\0\0'),('EÑEäMöµW‹UR)','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0	µ=ä÷\nN‘-Y/[;Œ\0\0\0\0b2\0\0\0\0'),('EºÚäkùA)¥ÿ8FÕ²™J','\0\0\0\0\0	%f+9?®O»¨—ÆğÙ\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\0NÂb\nƒO²Š)2/5 \0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.134+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡ÿÿÿÿP1‘ÜN]‹	§üAëİı\0\0\0\0content\0\0\0\0'),('E¾—HieAŞ‹`Í…Ó9¦','\0\0\0\0\0mjèŒ8Fº¤ÉîV\n­¢Ş\0\0ÿÿÿÿÿÿÿÿ\0âò²<B¦ØTœò…7\0\0\0\0f3\0\0\0\0'),('GèvLL¶ö$©W‘İ','\0\0\0\0\0Ü@ÿÌPC€C•¥65 ï\0\0ÿÿÿÿÿÿÿÿ\0$‘Fz¹@ ³\0óPB=\0\0\0\012\0\0\0\0'),('H™4nÆIª‚‚\'hïY','\0\0\0\0\0	şô‰2ÈNcÂ[†.+\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$292c9885-4aa4-4e10-a305-dccceff70257\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('H°“Î2î@eƒ\Z2x©I»','\0\0\0\0\0	šªfÔ‘@áš+(pë)À\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\0%f+9?®O»¨—ÆğÙ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.203+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0AAAAÿÿÿÿ´ıã”FÏ‰ä†c‚S»Ì\0\0\0\0content\0\0\0\0'),('K–Ì;IÛKì°%óCGRá•','\0\0\0\0\0œÈéc¾§@V§¢{ÍÑÑwr\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('O6:›[@¦®-/tpö59','\0\0\0\0\07¶ZwÜ\"Oç•\nˆPŞß5\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('O]ÎóŠEğ“\'ÁÖ]X„|','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.491+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0“3Ú[xA*¥´˜˜Œ,‘]ÿÿÿÿ]¤\\Ç(F–½W3peå\0\0\0\0\nfrozenNode\0\0\0'),('W’5G°J­ŠhJ\n¥','\0\0\0\0\0·yÈ„ˆFÑ¿¨ü–7ú	\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('WË ú>¥OwÄ ’KC1','\0\0\0\0\0Í¼7E²ıC¸£®GZ”ª\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$cd026ae8-f24c-4bcb-aa1b-558ba635b11cÿÿÿÿÿâÛ}:¯A“šÆ„­Júz\0\0\0\0\rversionLabels±Evf³+FÏœ\\GíêLóß\0\0\0\0rootVersion\0\0\0\0'),('X©ÀÎI#K2°É ç!2È÷','\0\0\0\0\0i£r¯E¦‹£ôô\n§\0\0ÿÿÿÿÿÿÿÿ\0+a”‹ÅJİ¥È¬‰\Zš\0\0\0\0$43fbcada-8280-4343-be8f-6545c2388065\0\0\0\0'),(']mäÆ9ĞG—“M”\\àü','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0i£r¯E¦‹£ôô\n§\0\0\0\0fb\0\0\0\0'),(']¤\\Ç(F–½W3peå','\0\0\0\0\0	O]ÎóŠEğ“\'ÁÖ]X„|\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('^by±ïºOé¢}\\–v¯Gß','\0\0\0\0\0dk:Ğ`âE»«yYYip\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:56.455+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿš4&Éîæ@¹ÃªI‚ïşÒ\0\0\0\0\nfrozenNode\0\0\0\0'),('_0½ÌHONB·wÒˆAhA','\0\0\0\0\0Aü\'¼iPB`…^û@Âœ×\0\0ÿÿÿÿÿÿÿÿ\0³ã“Q¥KØªV,gz‘\0\0\0\0$f2ed4478-8705-4273-99ab-dc02378f4257\0\0\0\0'),('`±.pÔJ¿>ÜUì_îû','\0\0\0\0\0íäMîøDµ˜L÷d}b9\0\0ÿÿÿÿÿÿÿÿ\0!èc$E²AmœFì\'üSÃ\0\0\0\030\0\0\0\0'),('cZ©,²?MX„Ÿ|£Y1û','\0\0\0\0\0	p:çÏŠ’DÈª‰ñ¬*Vz\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$ae9c13a0-5714-475b-8585-1c244638225a\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('dk:Ğ`âE»«yYYip','\0\0\0\0\0ç°‚N/ŠIcGí£P•Š™\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$f150391d-facf-41e8-8a01-e43138badbfaÿÿÿÿÅ¹èıá­CZ†öü–ãwÎU\0\0\0\0\rversionLabels^by±ïºOé¢}\\–v¯Gß\0\0\0\0rootVersion\0\0\0\0'),('e–¤5kyF#éâƒãoš','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0À½yå+¾B°-ïÄkâ£Ş\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.116+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\07I G¢=IÀÂOÂÿÿÿÿ¸%eÉE§>¸1Zp6\0\0\0\0\nfrozenNode\0\0\0'),('eÄçI¿¸Çƒa’','\0\0\0\0\0	…v‰ıÊÒI¦‡vâ·i¬R,\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$ef98f3bc-1c43-4a56-a2a6-09ef72ccebe3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('gRÅ^’×At²§³S,ÃwP','\0\0\0\0\0¦¶5ìêÀCªW2<aÂGr\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$c8b442dd-2e26-4cec-9cf0-07ff8f77bcd7ÿÿÿÿÍkiù9tObª†`é¶¸¶\0\0\0\0\rversionLabels±3.!ªA2‘$\n¹×èŞ\0\0\0\0\0rootVersion\0\0\0\0'),('gÛ—Êb‚OmÕ|ìØú\Z','\0\0\0\0\0 ~ü	*Lª—PÀ\"&å•\0\0ÿÿÿÿÿÿÿÿ\0‡áîoïCE–«¢5!\"É\0\0\0\0a9\0\0\0\0'),('hô‹/m@±s…€\0c','\0\0\0\0\0ÁeÔºŸCP¨|ûU·F/N\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('i¡_ê\"NX‹ßØú„¼','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0òŞÃL`OÈO•Æÿ(\0\0\0\08b\0\0\0\0'),('i£r¯E¦‹£ôô\n§','\0\0\0\0\0]mäÆ9ĞG—“M”\\àü\0\0ÿÿÿÿÿÿÿÿ\0X©ÀÎI#K2°É ç!2È÷\0\0\0\0ca\0\0\0\0'),('mjèŒ8Fº¤ÉîV\n­¢Ş','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0E¾—HieAŞ‹`Í…Ó9¦\0\0\0\098\0\0\0\0'),('m:ıÌïşNü–Â*‚6#b','\0\0\0\0\0­úy¸şJy“ë`…\ZÔê\0\0ÿÿÿÿÿÿÿÿ\0¸ƒ<¸I@NÂ¥)f9Æ\0\0\0\0$5d7c6f5c-77d7-4bed-82e1-5e312ae5fdd3\0\0\0\0'),('náÇ°é0E²¬†Nc{g','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0è[|ÙËªJ¹¸Q‰›@\0\0\0\0b4\0\0\0\0'),('p:çÏŠ’DÈª‰ñ¬*Vz','\0\0\0\0\0¦=ÇêFa«ÖLrì=È\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:11.103+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿcZ©,²?MX„Ÿ|£Y1û\0\0\0\0\nfrozenNode\0\0\0\0'),('r´k7ëAhÅè3ô8','\0\0\0\0\0æi£–ÉLBVµ*ƒÛô–W\0\0ÿÿÿÿÿÿÿÿ\07¶ZwÜ\"Oç•\nˆPŞß5\0\0\0\0$2c226142-7844-48f2-8064-aaba5d22b43d\0\0\0\0'),('rÍëi<ÆE©›Àÿk¦#Ÿ','\0\0\0\0\0¼¿\'ù¾1Kç·Ú?ød4Ñ\0\0ÿÿÿÿÿÿÿÿ\0•ˆ?C@J¥Pÿ&à§È\0\0\0\0$11f843f6-fb4e-47e5-b428-67322fe3c4c3\0\0\0\0'),('sxÚGÉAj‚W«š?!ù','\0\0\0\0\0Í\nCÛ9AÈ»¸å«0×Ã\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('tÁÜ™ŸI\\­ü09×ÛÎ','\0\0\0\0\0èD¯ñ @Ï£®[{?ˆn\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('té}®K¯¦”í<Œ¯1ö','\0\0\0\0\0Ã\\å«Ğ\nNÏwj\n9j£Í\0\0ÿÿÿÿÿÿÿÿ\0ç°‚N/ŠIcGí£P•Š™\0\0\0\039\0\0\0\0'),('uÇ¨AØ¼H\\¨¶\\©>–Ts','\0\0\0\0\04Îau–BOä¼ØT£ÀõJ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$8ef5cace-7ee1-4f7e-9d4f-674590d851eaÿÿÿÿÓ]*´KÀD}¸UğHD$£\0\0\0\0\rversionLabelsW3ÜHªI´¶ìuğèS\0\0\0\0rootVersion\0\0\0\0'),('z9ÜXÃF®ã´ğÄõëÉ','\0\0\0\0\0	æÈyLZ’’‹ŸÇÆ˜§\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$43fbcada-8280-4343-be8f-6545c2388065\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('|·±DqAº¦²ËšãÑ','\0\0\0\0\0Í\nCÛ9AÈ»¸å«0×Ã\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:16.220+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿÍsrM*KL†L:Õ÷ÿ¯ù\0\0\0\0\nfrozenNode\0\0\0\0'),('}³ª9»eOË»nØl0wöø','\0\0\0\0\0ñolÍ“\ZF	—Ø‘@bõ\0\0ÿÿÿÿÿÿÿÿ\0Ùšø÷Iq¸®”ñÔ|{\0\0\0\0f2\0\0\0\0'),('íäMîøDµ˜L÷d}b9','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0`±.pÔJ¿>ÜUì_îû\0\0\0\0b2\0\0\0\0'),('‚/¤µJJˆà“`¾VİV','\0\0\0\0\0ŒC>€NEäµ;à”\ZÃ±\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$2d8b14fa-9738-4f88-a2e8-f0434dcc8725ÿÿÿÿD¶I{S4J_’(¤uv/X¹\0\0\0\0\rversionLabelsÍ?’ÿNpq$Xİ‡úú\0\0\0\0rootVersion\0\0\0\0'),('‚ŞIYİ­H&©(D‹H/\'Ã','\0\0\0\0\0	NÂb\nƒO²Š)2/5 \0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\07I G¢=IÀÂOÂ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.248+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0AAAAÿÿÿÿ	[UşK/‡”¥9Ç-Ó\0\0\0\0content\0\0\0\0'),('…v‰ıÊÒI¦‡vâ·i¬R,','\0\0\0\0\0+·ê+5%D ƒ•GC0é\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:56.451+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿeÄçI¿¸Çƒa’\0\0\0\0\nfrozenNode\0\0\0\0'),('‡áîoïCE–«¢5!\"É','\0\0\0\0\0gÛ—Êb‚OmÕ|ìØú\Z\0\0ÿÿÿÿÿÿÿÿ\0:Ñ3·òG‡ôb¦ŞC\n\r\0\0\0\0$6057a90a-b6a9-42a3-94e2-66bdb15e4580\0\0\0\0'),('‰o\'İ¼H«›€œøhŠÔ','\0\0\0\0\0³ã“Q¥KØªV,gz‘\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('Š²!$™ÁLW›uÇª-59','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0Å[ÑòD†­¿„>ögxî\0\0\0\002\0\0\0\0'),('ŒC>€NEäµ;à”\ZÃ±','\0\0\0\0\0òŞÃL`OÈO•Æÿ(\0\0ÿÿÿÿÿÿÿÿ\0‚/¤µJJˆà“`¾VİV\0\0\0\0$2d8b14fa-9738-4f88-a2e8-f0434dcc8725\0\0\0\0'),('±3.!ªA2‘$\n¹×èŞ\0','\0\0\0\0\0gRÅ^’×At²§³S,ÃwP\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:51.241+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿş×! ·ÉBüµgfQ\0:ä\0\0\0\0\nfrozenNode\0\0\0\0'),('‘_‚—IÇ”l^;^D','\0\0\0\0\0ÁeÔºŸCP¨|ûU·F/N\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:45.456+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ½9T¢Ec²¹Ï|úa\0\0\0\0\nfrozenNode\0\0\0\0'),('’,D×ÆDŠ‘â¿6^D','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0Aü\'¼iPB`…^û@Âœ×\0\0\0\0ed\0\0\0\0'),('“HéCé‘§°>GBú','\0\0\0\0\0@ÇµÔÒKÖ˜#OlÉ3\0\0ÿÿÿÿÿÿÿÿ\0¢ã&K©·IÆ–×]÷ùoĞ×\0\0\0\0$2f761fd2-6b84-453e-b7d3-ab328c9abaa9\0\0\0\0'),('“3Ú[xA*¥´˜˜Œ,‘]','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0O]ÎóŠEğ“\'ÁÖ]X„|\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.553+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0À½yå+¾B°-ïÄkâ£Şÿÿÿÿ0d-sæİA5¶}öÙ´V¡\0\0\0\0\nfrozenNode\0\0\0'),('”FRÜ©1E^²°W…V¬ˆ','\0\0\0\0\0	W3ÜHªI´¶ìuğèS\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$8ef5cace-7ee1-4f7e-9d4f-674590d851ea\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('”ZÔVVÆFï–U£G.¾Êí','\0\0\0\0\0	¶È Ã^H”Iš+>sæ\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0\0\0\0\0\0	\0\0\0\0\0šªfÔ‘@áš+(pë)À\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.236+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0file\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0AAAAÿÿÿÿ{ç#2Mù–}~\n[¤„z\0\0\0\0content\0\0\0\0'),('š4&Éîæ@¹ÃªI‚ïşÒ','\0\0\0\0\0	^by±ïºOé¢}\\–v¯Gß\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$f150391d-facf-41e8-8a01-e43138badbfa\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('šªfÔ‘@áš+(pë)À','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0%f+9?®O»¨—ÆğÙ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.216+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0¶È Ã^H”Iš+>sæÿÿÿÿH°“Î2î@eƒ\Z2x©I»\0\0\0\0\nfrozenNode\0\0\0'),('œÈ;fÓEï•\\GæÔ¦','\0\0\0\0\0	ÂÔvŒÔ¾@šŠ:K\nò‚æ\"\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$2c226142-7844-48f2-8064-aaba5d22b43d\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('œÈéc¾§@V§¢{ÍÑÑwr','\0\0\0\0\0¤%ÊÒ}Ic”TMJ,ô\\\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$5e177071-30ac-467e-b40c-a59877ef28ebÿÿÿÿK–Ì;IÛKì°%óCGRá•\0\0\0\0\rversionLabelsìå*Y¥HE‘<Áìà©\0\0\0\0rootVersion\0\0\0\0'),('kÈ`­æINˆ—loj?4X','\0\0\0\0\0\Zòï¨û.AÅ½(œœ?\0\0ÿÿÿÿÿÿÿÿ\0¦=ÇêFa«ÖLrì=È\0\0\0\0$ae9c13a0-5714-475b-8585-1c244638225a\0\0\0\0'),('ÏKƒÜB+‚EÊ‘','\0\0\0\0\0¢ã&K©·IÆ–×]÷ùoĞ×\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:43:56.780+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿËA÷ñk’J]½ãßÛß-UW\0\0\0\0\nfrozenNode\0\0\0\0'),(' ~ü	*Lª—PÀ\"&å•','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0gÛ—Êb‚OmÕ|ìØú\Z\0\0\0\057\0\0\0\0'),(' ½%2k¶FPÄ‡—Dàô','\0\0\0\0\0	±Evf³+FÏœ\\GíêLóß\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$cd026ae8-f24c-4bcb-aa1b-558ba635b11c\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('¢ã&K©·IÆ–×]÷ùoĞ×','\0\0\0\0\0“HéCé‘§°>GBú\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$2f761fd2-6b84-453e-b7d3-ab328c9abaa9ÿÿÿÿÂ·9­=Nw¬Şùgå å\0\0\0\0\rversionLabelsÏKƒÜB+‚EÊ‘\0\0\0\0rootVersion\0\0\0\0'),('£=ŞfO\Z“¸çoDt¶¦','\0\0\0\0\0	$Z/ï#Nµ®¥ŒãŠÀº\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$5d7c6f5c-77d7-4bed-82e1-5e312ae5fdd3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('¤%ÊÒ}Ic”TMJ,ô\\','\0\0\0\0\0®ƒæøS–EÔ”ƒÆ£Cüæ\0\0ÿÿÿÿÿÿÿÿ\0œÈéc¾§@V§¢{ÍÑÑwr\0\0\0\0$5e177071-30ac-467e-b40c-a59877ef28eb\0\0\0\0'),('¤Ë\"¬bëCé¬-ìVM)H','\0\0\0\0\0·l¦[.%@}e*ŞIÕAF\0\0ÿÿÿÿÿÿÿÿ\0ìÖm›1H\"“ŠIJU$L/\0\0\0\093\0\0\0\0'),('¦=ÇêFa«ÖLrì=È','\0\0\0\0\0kÈ`­æINˆ—loj?4X\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$ae9c13a0-5714-475b-8585-1c244638225aÿÿÿÿüf<BJ@„®ê—¡1Šñ3\0\0\0\0\rversionLabelsp:çÏŠ’DÈª‰ñ¬*Vz\0\0\0\0rootVersion\0\0\0\0'),('¦¶5ìêÀCªW2<aÂGr','\0\0\0\0\0è[|ÙËªJ¹¸Q‰›@\0\0ÿÿÿÿÿÿÿÿ\0gRÅ^’×At²§³S,ÃwP\0\0\0\0$c8b442dd-2e26-4cec-9cf0-07ff8f77bcd7\0\0\0\0'),('­úy¸şJy“ë`…\ZÔê','\0\0\0\0\06ë:Î?BO›DÍ»[ğc?\0\0ÿÿÿÿÿÿÿÿ\0m:ıÌïşNü–Â*‚6#b\0\0\0\06f\0\0\0\0'),('®ƒæøS–EÔ”ƒÆ£Cüæ','\0\0\0\0\0$iaL‰Näƒ<ñ{Øô\n\0\0ÿÿÿÿÿÿÿÿ\0¤%ÊÒ}Ic”TMJ,ô\\\0\0\0\070\0\0\0\0'),('¯^š-¢J‡µ^…ŸÔ\0y','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0\Zòï¨û.AÅ½(œœ?\0\0\0\09c\0\0\0\0'),('±Evf³+FÏœ\\GíêLóß','\0\0\0\0\0WË ú>¥OwÄ ’KC1\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:08.236+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ ½%2k¶FPÄ‡—Dàô\0\0\0\0\nfrozenNode\0\0\0\0'),('³ã“Q¥KØªV,gz‘','\0\0\0\0\0_0½ÌHONB·wÒˆAhA\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$f2ed4478-8705-4273-99ab-dc02378f4257ÿÿÿÿ‰o\'İ¼H«›€œøhŠÔ\0\0\0\0\rversionLabelsÿ‹\"‰„Ká®ú[ \0\0\0\0rootVersion\0\0\0\0'),('³}ÆZ¦Nå@åK¥C','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0ÏEfRK\nµtm]Ù-^N\0\0\0\02c\0\0\0\0'),('´eÓ²4F®6nipºPv','\0\0\0\0\0í/°BCK é\"Œ\0\0ÿÿÿÿÿÿÿÿ\04Îau–BOä¼ØT£ÀõJ\0\0\0\0ca\0\0\0\0'),('´ıã”FÏ‰ä†c‚S»Ì','\0\0\0\0\0	H°“Î2î@eƒ\Z2x©I»\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\nplain/text\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.185+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('¶È Ã^H”Iš+>sæ','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0šªfÔ‘@áš+(pë)À\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.261+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ”ZÔVVÆFï–U£G.¾Êí\0\0\0\0\nfrozenNode\0\0\0\0'),('·l¦[.%@}e*ŞIÕAF','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0¤Ë\"¬bëCé¬-ìVM)H\0\0\0\05f\0\0\0\0'),('·yÈ„ˆFÑ¿¨ü–7ú	','\0\0\0\0\0G,‘\nD@Ç•‡gŒèX-\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$a5a24541-8265-4837-87a3-bb44653ee66bÿÿÿÿW’5G°J­ŠhJ\n¥\0\0\0\0\rversionLabels;x®ÈNK¶üß²G\0\0\0\0rootVersion\0\0\0\0'),('¸ƒ<¸I@NÂ¥)f9Æ','\0\0\0\0\0m:ıÌïşNü–Â*‚6#b\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$5d7c6f5c-77d7-4bed-82e1-5e312ae5fdd3ÿÿÿÿÎ9§šÎA‰‹Ø3›ôPše\0\0\0\0\rversionLabels$Z/ï#Nµ®¥ŒãŠÀº\0\0\0\0rootVersion\0\0\0\0'),('¼¿\'ù¾1Kç·Ú?ød4Ñ','\0\0\0\0\0;®DñšÒË1ö5ÒŠ\0\0ÿÿÿÿÿÿÿÿ\0rÍëi<ÆE©›Àÿk¦#Ÿ\0\0\0\043\0\0\0\0'),('½9T¢Ec²¹Ï|úa','\0\0\0\0\0	‘_‚—IÇ”l^;^D\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$340ef2fa-6b36-4429-ac47-5fb3352af8a6\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('À½yå+¾B°-ïÄkâ£Ş','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0“3Ú[xA*¥´˜˜Œ,‘]\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.617+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0e–¤5kyF#éâƒãošÿÿÿÿ;¦ówûCm”¸BË4Æf\0\0\0\0\nfrozenNode\0\0\0'),('ÁeÔºŸCP¨|ûU·F/N','\0\0\0\0\0Ùšø÷Iq¸®”ñÔ|{\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$340ef2fa-6b36-4429-ac47-5fb3352af8a6ÿÿÿÿhô‹/m@±s…€\0c\0\0\0\0\rversionLabels‘_‚—IÇ”l^;^D\0\0\0\0rootVersion\0\0\0\0'),('Â·9­=Nw¬Şùgå å','\0\0\0\0\0¢ã&K©·IÆ–×]÷ùoĞ×\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('ÂÔvŒÔ¾@šŠ:K\nò‚æ\"','\0\0\0\0\07¶ZwÜ\"Oç•\nˆPŞß5\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:43:57.923+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿœÈ;fÓEï•\\GæÔ¦\0\0\0\0\nfrozenNode\0\0\0\0'),('Ã\\å«Ğ\nNÏwj\n9j£Í','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0té}®K¯¦”í<Œ¯1ö\0\0\0\050\0\0\0\0'),('Å[ÑòD†­¿„>ögxî','\0\0\0\0\0Š²!$™ÁLW›uÇª-59\0\0ÿÿÿÿÿÿÿÿ\0Í¼7E²ıC¸£®GZ”ª\0\0\0\06a\0\0\0\0'),('Å¹èıá­CZ†öü–ãwÎU','\0\0\0\0\0dk:Ğ`âE»«yYYip\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('ËA÷ñk’J]½ãßÛß-UW','\0\0\0\0\0	ÏKƒÜB+‚EÊ‘\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$2f761fd2-6b84-453e-b7d3-ab328c9abaa9\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('Íkiù9tObª†`é¶¸¶','\0\0\0\0\0gRÅ^’×At²§³S,ÃwP\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('ÍsrM*KL†L:Õ÷ÿ¯ù','\0\0\0\0\0	|·±DqAº¦²ËšãÑ\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$3a6c1237-0a3a-4b42-b34c-f0054368352b\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('Í ÚDS£E\0‚VôPÒi¸','\0\0\0\0\0èD¯ñ @Ï£®[{?ˆn\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.861+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿS…™çDÿ¶™-ÿaGÉ\0\0\0\0\nfrozenNode\0\0\0\0'),('Í¼7E²ıC¸£®GZ”ª','\0\0\0\0\0Å[ÑòD†­¿„>ögxî\0\0ÿÿÿÿÿÿÿÿ\0WË ú>¥OwÄ ’KC1\0\0\0\0$cd026ae8-f24c-4bcb-aa1b-558ba635b11c\0\0\0\0'),('Î9§šÎA‰‹Ø3›ôPše','\0\0\0\0\0¸ƒ<¸I@NÂ¥)f9Æ\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('ÏEfRK\nµtm]Ù-^N','\0\0\0\0\0³}ÆZ¦Nå@åK¥C\0\0ÿÿÿÿÿÿÿÿ\0\0ïÇšNI†ŒáRqx3g\0\0\0\098\0\0\0\0'),('Ó]*´KÀD}¸UğHD$£','\0\0\0\0\0uÇ¨AØ¼H\\¨¶\\©>–Ts\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('Õ-C›Oî¶Õ9ÕHz!','\0\0\0\0\0:Ñ3·òG‡ôb¦ŞC\n\r\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:09.259+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿÏP9ï]Mé¼?~¶÷A\0\0\0\0\nfrozenNode\0\0\0\0'),('Ù±İN?­^Œí9\r˜d','\0\0\0\0\0+a”‹ÅJİ¥È¬‰\Zš\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('Ü@ÿÌPC€C•¥65 ï','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0GèvLL¶ö$©W‘İ\0\0\0\06c\0\0\0\0'),('İ†.)¯B’ZV&5”','\0\0\0\0\0	µ=ä÷\nN‘-Y/[;Œ\0\0ÿÿÿÿÿÿÿÿ\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226\0\0\0\0'),('Ş­¾ïúÎº¾Êşº¾Êşº¾','\0\0\0\0\0Ş­¾ïÊşº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0 ~ü	*Lª—PÀ\"&å•\0\0\0\060§¬M\nMl¨Ší\Z9¨iH\0\0\0\0a5;®DñšÒË1ö5ÒŠ\0\0\0\011EÑEäMöµW‹UR)\0\0\0\012íäMîøDµ˜L÷d}b9\0\0\0\0a6¯^š-¢J‡µ^…ŸÔ\0y\0\0\0\0aeÜ@ÿÌPC€C•¥65 ï\0\0\0\03a]mäÆ9ĞG—“M”\\àü\0\0\0\043èWñ“M—£	.SÏr‚d\0\0\0\02fDÎ5g’ZOÃ¿®i<Lec\0\0\0\02c’,D×ÆDŠ‘â¿6^D\0\0\0\0f2Š²!$™ÁLW›uÇª-59\0\0\0\0cd$iaL‰Näƒ<ñ{Øô\n\0\0\0\05e·l¦[.%@}e*ŞIÕAF\0\0\0\076i¡_ê\"NX‹ßØú„¼\0\0\0\02dñolÍ“\ZF	—Ø‘@bõ\0\0\0\034³}ÆZ¦Nå@åK¥C\0\0\0\029náÇ°é0E²¬†Nc{g\0\0\0\0c86ë:Î?BO›DÍ»[ğc?\0\0\0\05dí/°BCK é\"Œ\0\0\0\08emjèŒ8Fº¤ÉîV\n­¢Ş\0\0\0\0efÃ\\å«Ğ\nNÏwj\n9j£Í\0\0\0\0f1\0\0\0'),('æÈyLZ’’‹ŸÇÆ˜§','\0\0\0\0\0+a”‹ÅJİ¥È¬‰\Zš\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:21.317+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿz9ÜXÃF®ã´ğÄõëÉ\0\0\0\0\nfrozenNode\0\0\0\0'),('æi£–ÉLBVµ*ƒÛô–W','\0\0\0\0\0DÎ5g’ZOÃ¿®i<Lec\0\0ÿÿÿÿÿÿÿÿ\0r´k7ëAhÅè3ô8\0\0\0\061\0\0\0\0'),('ç°‚N/ŠIcGí£P•Š™','\0\0\0\0\0té}®K¯¦”í<Œ¯1ö\0\0ÿÿÿÿÿÿÿÿ\0dk:Ğ`âE»«yYYip\0\0\0\0$f150391d-facf-41e8-8a01-e43138badbfa\0\0\0\0'),('è(µÿ4@J1@Vnã&','\0\0\0\0\00}¿¥+Cëƒ	@zÕC\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:08.401+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ=M—ÔìQEƒŒ·[Ú¯¥d\0\0\0\0\nfrozenNode\0\0\0\0'),('èD¯ñ @Ï£®[{?ˆn','\0\0\0\0\0!èc$E²AmœFì\'üSÃ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$a6b230c8-a475-4433-a335-560ca312afdfÿÿÿÿtÁÜ™ŸI\\­ü09×ÛÎ\0\0\0\0\rversionLabelsÍ ÚDS£E\0‚VôPÒi¸\0\0\0\0rootVersion\0\0\0\0'),('è[|ÙËªJ¹¸Q‰›@','\0\0\0\0\0náÇ°é0E²¬†Nc{g\0\0ÿÿÿÿÿÿÿÿ\0¦¶5ìêÀCªW2<aÂGr\0\0\0\042\0\0\0\0'),('êWqú=LMN‹MCâpd×','\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('ìå*Y¥HE‘<Áìà©','\0\0\0\0\0œÈéc¾§@V§¢{ÍÑÑwr\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:08.396+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿ6†ÈSõI÷°–h¹[1\0\0\0\0\nfrozenNode\0\0\0\0'),('í/°BCK é\"Œ','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0´eÓ²4F®6nipºPv\0\0\0\0f5\0\0\0\0'),('îFwpO¬A8‰\"Äµİ×˜','\0\0\0\0\0	ÿ‹\"‰„Ká®ú[ \0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$f2ed4478-8705-4273-99ab-dc02378f4257\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('ğa}°š\0Fm½~Ñü³ş','\0\0\0\0\0	;x®ÈNK¶üß²G\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$a5a24541-8265-4837-87a3-bb44653ee66b\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('ñolÍ“\ZF	—Ø‘@bõ','\0\0\0\0\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0}³ª9»eOË»nØl0wöø\0\0\0\00e\0\0\0\0'),('òŞÃL`OÈO•Æÿ(','\0\0\0\0\0i¡_ê\"NX‹ßØú„¼\0\0ÿÿÿÿÿÿÿÿ\0ŒC>€NEäµ;à”\ZÃ±\0\0\0\014\0\0\0\0'),('óİ‡âıAÔÛ£•=/?','\0\0\0\0\00}¿¥+Cëƒ	@zÕC\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('ôãIûbO?¸Ì×¢Œ´¼','\0\0\0\0\0	;¦ówûCm”¸BË4Æf\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content 2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0plain/x\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.588+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('÷V6Ù´$L{½üÖ€4+Ú¡','\0\0\0\0\0İ†.)¯B’ZV&5”\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$12b264c8-fdd9-4f83-990a-280441fae226ÿÿÿÿêWqú=LMN‹MCâpd×\0\0\0\0\rversionLabelsO]ÎóŠEğ“\'ÁÖ]X„|\0\0\0\0rootVersion“3Ú[xA*¥´˜˜Œ,‘]\0\0\0\01.0À½yå+¾B°-ïÄkâ£Ş\0\0\0\01.1e–¤5kyF#éâƒãoš\0\0\0\01.27I G¢=IÀÂOÂ\0\0\0\01.3NÂb\nƒO²Š)2/5 \0\0\0\01.4%f+9?®O»¨—ÆğÙ\0\0\0\01.5šªfÔ‘@áš+(pë)À\0\0\0\01.6¶È Ã^H”Iš+>sæ\0\0\0\01.7\0\0\0'),('üf<BJ@„®ê—¡1Šñ3','\0\0\0\0\0¦=ÇêFa«ÖLrì=È\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0'),('ı+›cßI<¼†M\nÑ\rJÌ','\0\0\0\0\0	BÛÓ°N?¶\ró¢hÎ×\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$df79bfa1-6d28-4da4-9c04-60daa5df4f52\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0resource\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\nplain/text\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:09.168+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\rdiscriminator\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('ş×! ·ÉBüµgfQ\0:ä','\0\0\0\0\0	±3.!ªA2‘$\n¹×èŞ\0\0\0ÿÿÿÿ\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0$c8b442dd-2e26-4cec-9cf0-07ff8f77bcd7\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0fileÿÿÿÿ\0\0\0\0'),('şô‰2ÈNcÂ[†.+','\0\0\0\0\0=R@\rÀOOµ®›£“­Ø\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:46.109+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿH™4nÆIª‚‚\'hïY\0\0\0\0\nfrozenNode\0\0\0\0'),('ÿ‹\"‰„Ká®ú[ ','\0\0\0\0\0³ã“Q¥KØªV,gz‘\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-08T07:44:03.093+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0ÿÿÿÿîFwpO¬A8‰\"Äµİ×˜\0\0\0\0\nfrozenNode\0\0\0\0'),('ÿâÛ}:¯A“šÆ„­Júz','\0\0\0\0\0WË ú>¥OwÄ ’KC1\0\0ÿÿÿÿÿÿÿÿ\0\0\0\0\0');
/*!40000 ALTER TABLE `m_ecm_ver_bundle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_holiday`
--

DROP TABLE IF EXISTS `m_hr_holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_holiday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `holDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `holName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `m_hr_holiday_pk_1` (`sAccountId`),
  CONSTRAINT `m_hr_holiday_pk_1` FOREIGN KEY (`sAccountId`) REFERENCES `engroup`.`s_account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_holiday`
--

LOCK TABLES `m_hr_holiday` WRITE;
/*!40000 ALTER TABLE `m_hr_holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_opportunity_type`
--

DROP TABLE IF EXISTS `m_crm_opportunity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_opportunity_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_opportunity_type`
--

LOCK TABLES `m_crm_opportunity_type` WRITE;
/*!40000 ALTER TABLE `m_crm_opportunity_type` DISABLE KEYS */;
INSERT INTO `m_crm_opportunity_type` VALUES (1,'Existing Business'),(2,'New Business');
/*!40000 ALTER TABLE `m_crm_opportunity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_jobtitle`
--

DROP TABLE IF EXISTS `m_hr_jobtitle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_jobtitle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(2000) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `code` varchar(100) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_hr_jobtitle_1` (`sAccountId`),
  CONSTRAINT `FK_m_hr_jobtitle_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_jobtitle`
--

LOCK TABLES `m_hr_jobtitle` WRITE;
/*!40000 ALTER TABLE `m_hr_jobtitle` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_jobtitle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee_leave`
--

DROP TABLE IF EXISTS `m_hr_employee_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromdate` datetime NOT NULL,
  `todate` datetime NOT NULL,
  `leavetypeid` int(11) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `reasonToLeave` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEBFBC9BB306E7131` (`leavetypeid`),
  KEY `FKEBFBC9BB6E03532` (`leavetypeid`),
  KEY `FKEBFBC9BBD6C89963` (`username`),
  KEY `FKEBFBC9BB6FDD142` (`username`),
  CONSTRAINT `FK_m_hr_employee_leave_1` FOREIGN KEY (`username`) REFERENCES `engroup`.`s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_employee_leave_2` FOREIGN KEY (`leavetypeid`) REFERENCES `m_hr_leave_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_leave`
--

LOCK TABLES `m_hr_employee_leave` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_leave` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_leave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee_personal`
--

DROP TABLE IF EXISTS `m_hr_employee_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_personal` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ssn` varchar(45) DEFAULT NULL,
  `sin` varchar(45) DEFAULT NULL,
  `countryid` int(11) DEFAULT NULL,
  `gender` int(10) unsigned DEFAULT NULL COMMENT '0  represents for woman and 1 represents for man',
  `maritalstatus` int(10) unsigned DEFAULT NULL COMMENT '0:Unmarried; 1:Married; 2:Divorced;3:Others',
  `birthday` datetime DEFAULT NULL,
  `employeeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hr_employee_personal_2` (`countryid`),
  KEY `FK_hr_employee_personal_3` (`employeeid`),
  CONSTRAINT `FK_hr_employee_personal_1` FOREIGN KEY (`countryid`) REFERENCES `s_country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_hr_employee_personal_3` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_personal`
--

LOCK TABLES `m_hr_employee_personal` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_personal` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_personal` ENABLE KEYS */;
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
INSERT INTO `s_account` VALUES (1,NULL,NULL);
/*!40000 ALTER TABLE `s_account` ENABLE KEYS */;
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
  `accountId` int(10) unsigned DEFAULT NULL,
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `otherAddress` varchar(255) DEFAULT NULL,
  `otherCity` varchar(255) DEFAULT NULL,
  `otherState` varchar(255) DEFAULT NULL,
  `otherPostalCode` varchar(255) DEFAULT NULL,
  `otherCountry` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_contact_1` (`accountId`),
  KEY `FK_m_crm_contact_3` (`campaignId`),
  KEY `FK_m_crm_contact_5` (`primCountry`),
  KEY `FK_m_crm_contact_6` (`createdUser`),
  KEY `FK_m_crm_contact_7` (`sAccountId`),
  KEY `FK_m_crm_contact_8` (`assignUser`),
  KEY `FK_m_crm_contact_2` (`leadSource`),
  CONSTRAINT `FK_m_crm_contact_1` FOREIGN KEY (`accountId`) REFERENCES `m_crm_account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
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
-- Table structure for table `m_hr_employee_work_experience`
--

DROP TABLE IF EXISTS `m_hr_employee_work_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_work_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(45) DEFAULT NULL,
  `employer` varchar(45) DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `jobtitle` varchar(45) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7056095CD6C89963` (`employeeid`),
  KEY `FK7056095C6FDD142` (`employeeid`),
  CONSTRAINT `FK7056095C6FDD142` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_work_experience`
--

LOCK TABLES `m_hr_employee_work_experience` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_work_experience` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_work_experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_ecm_con_binval`
--

DROP TABLE IF EXISTS `m_ecm_con_binval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_con_binval` (
  `BINVAL_ID` varchar(64) NOT NULL,
  `BINVAL_DATA` longblob NOT NULL,
  UNIQUE KEY `m_ecm_con_binval_IDX` (`BINVAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_con_binval`
--

LOCK TABLES `m_ecm_con_binval` WRITE;
/*!40000 ALTER TABLE `m_ecm_con_binval` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_ecm_con_binval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_change_log`
--

DROP TABLE IF EXISTS `m_prj_change_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_change_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `posteddate` datetime DEFAULT NULL,
  `posteduser` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL COMMENT 'could be the value ''message'', ''resource'',''risk'',''problem'',''task''',
  `sourceid` int(11) DEFAULT NULL,
  `logAction` varchar(45) DEFAULT NULL COMMENT 'action could be one of values ''Add'',''Update'',''Remove''',
  `sourceDesc` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_change_log_1` (`projectid`),
  KEY `FK_m_prj_change_log_2` (`posteduser`),
  CONSTRAINT `FK_m_prj_change_log_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_change_log_2` FOREIGN KEY (`posteduser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_change_log`
--

LOCK TABLES `m_prj_change_log` WRITE;
/*!40000 ALTER TABLE `m_prj_change_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_change_log` ENABLE KEYS */;
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
  `posteduser` varchar(45) NOT NULL,
  `refid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
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
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_role_1` (`sAccountId`),
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_colla_workgroup_user`
--

LOCK TABLES `m_colla_workgroup_user` WRITE;
/*!40000 ALTER TABLE `m_colla_workgroup_user` DISABLE KEYS */;
INSERT INTO `m_colla_workgroup_user` VALUES (1,'hainguyen',NULL,NULL,1,NULL),(1,'linhduong',NULL,NULL,2,NULL);
/*!40000 ALTER TABLE `m_colla_workgroup_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_history`
--

DROP TABLE IF EXISTS `m_tracker_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `summary` varchar(1000) DEFAULT NULL,
  `detail` varchar(4000) DEFAULT NULL,
  `assignuser` varchar(45) DEFAULT NULL,
  `posteddate` datetime DEFAULT NULL,
  `logby` varchar(45) DEFAULT NULL,
  `severity` int(10) unsigned DEFAULT NULL,
  `priority` int(10) unsigned DEFAULT NULL,
  `status` int(10) unsigned DEFAULT NULL,
  `environment` varchar(4000) DEFAULT NULL,
  `affectedversions` varchar(400) DEFAULT NULL,
  `fixonversions` varchar(400) DEFAULT NULL,
  `resolution` int(10) unsigned DEFAULT NULL,
  `relatedbug` int(10) unsigned NOT NULL,
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
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_history_1` (`relatedbug`),
  CONSTRAINT `FK_m_tracker_history_1` FOREIGN KEY (`relatedbug`) REFERENCES `m_tracker_bug` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_history`
--

LOCK TABLES `m_tracker_history` WRITE;
/*!40000 ALTER TABLE `m_tracker_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_tracker_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee_licenses`
--

DROP TABLE IF EXISTS `m_hr_employee_licenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_licenses` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `licenseid` int(11) NOT NULL,
  `employeeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hr_employee_licenses_1` (`licenseid`),
  KEY `FK_hr_employee_licenses_2` (`employeeid`),
  CONSTRAINT `FK_hr_employee_licenses_1` FOREIGN KEY (`licenseid`) REFERENCES `m_hr_licenses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_hr_employee_licenses_2` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_licenses`
--

LOCK TABLES `m_hr_employee_licenses` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_licenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_licenses` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_target_list_2` (`createdUser`),
  KEY `FK_m_crm_target_list` (`sAccountId`),
  KEY `FK_m_crm_target_list_3` (`assignUser`),
  CONSTRAINT `FK_m_crm_target_list_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_target_list_2` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_m_crm_target_list_3` FOREIGN KEY (`assignUser`) REFERENCES `engroup`.`s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
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
-- Table structure for table `m_prj_message`
--

DROP TABLE IF EXISTS `m_prj_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `categoryid` int(10) unsigned DEFAULT NULL,
  `title` varchar(1000) NOT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `messagehtml` varchar(4000) DEFAULT NULL,
  `posteddate` datetime NOT NULL,
  `posteduser` varchar(45) DEFAULT NULL,
  `projectid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_message_1` (`categoryid`),
  KEY `FK_m_prj_message_2` (`projectid`),
  CONSTRAINT `FK_m_prj_message_1` FOREIGN KEY (`categoryid`) REFERENCES `m_prj_message_category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_message_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
-- Table structure for table `s_chat_server`
--

DROP TABLE IF EXISTS `s_chat_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_chat_server` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `host` varchar(255) NOT NULL,
  `port` int(10) unsigned NOT NULL,
  `account_suffix` varchar(45) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_chat_server`
--

LOCK TABLES `s_chat_server` WRITE;
/*!40000 ALTER TABLE `s_chat_server` DISABLE KEYS */;
INSERT INTO `s_chat_server` VALUES (1,'gtalk','talk.google.com',5222,'@gmail.com');
/*!40000 ALTER TABLE `s_chat_server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_ecm_ver_binval`
--

DROP TABLE IF EXISTS `m_ecm_ver_binval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_ver_binval` (
  `BINVAL_ID` varchar(64) NOT NULL,
  `BINVAL_DATA` longblob NOT NULL,
  UNIQUE KEY `m_ecm_ver_binval_IDX` (`BINVAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_ver_binval`
--

LOCK TABLES `m_ecm_ver_binval` WRITE;
/*!40000 ALTER TABLE `m_ecm_ver_binval` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_ecm_ver_binval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_company`
--

DROP TABLE IF EXISTS `m_hr_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `comments` varchar(4000) DEFAULT NULL,
  `companyname` varchar(45) NOT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `taxid` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `countryid` int(11) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98646481B8FF853` (`countryid`),
  KEY `FK986464871461B5C` (`countryid`),
  KEY `FK_m_hr_company_2` (`sAccountId`),
  CONSTRAINT `FK986464871461B5C` FOREIGN KEY (`countryid`) REFERENCES `s_country` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_company_2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_company`
--

LOCK TABLES `m_hr_company` WRITE;
/*!40000 ALTER TABLE `m_hr_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_company` ENABLE KEYS */;
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
INSERT INTO `m_colla_workgroup_comment` VALUES (1,'aaa','hainguyen',NULL,1);
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
  `postedDate` datetime DEFAULT NULL,
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
-- Table structure for table `m_hr_skill`
--

DROP TABLE IF EXISTS `m_hr_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(2000) DEFAULT NULL,
  `skill` varchar(255) NOT NULL,
  `code` varchar(45) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_hr_skill_1` (`sAccountId`),
  CONSTRAINT `FK_m_hr_skill_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_skill`
--

LOCK TABLES `m_hr_skill` WRITE;
/*!40000 ALTER TABLE `m_hr_skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_skill` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `opportunityType` varchar(45) DEFAULT NULL,
  `salesStage` varchar(45) DEFAULT NULL,
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
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
-- Table structure for table `m_prj_relation`
--

DROP TABLE IF EXISTS `m_prj_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `taskid` int(10) unsigned NOT NULL,
  `relatedtaskid` int(10) unsigned NOT NULL,
  `type` int(10) unsigned NOT NULL,
  `duration` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_relation_2` (`taskid`),
  KEY `FK_m_prj_relation_3` (`relatedtaskid`),
  KEY `FK_m_prj_relation_1` (`projectid`),
  CONSTRAINT `FK_m_prj_relation_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_relation_2` FOREIGN KEY (`taskid`) REFERENCES `m_prj_task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_relation_3` FOREIGN KEY (`relatedtaskid`) REFERENCES `m_prj_task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_relation`
--

LOCK TABLES `m_prj_relation` WRITE;
/*!40000 ALTER TABLE `m_prj_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_relation` ENABLE KEYS */;
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
  `taskindex` int(10) unsigned NOT NULL,
  `predecessors` varchar(255) DEFAULT NULL,
  `parenttask` int(10) unsigned DEFAULT NULL,
  `actualStartDate` datetime DEFAULT NULL,
  `actualEndDate` datetime DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `actualCost` double DEFAULT NULL,
  `ismilestone` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_task_2` (`parenttask`),
  KEY `FK_m_prj_task_1` (`projectid`),
  CONSTRAINT `FK_m_prj_task_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_task_2` FOREIGN KEY (`parenttask`) REFERENCES `m_prj_task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
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
-- Table structure for table `m_hr_employee_skill`
--

DROP TABLE IF EXISTS `m_hr_employee_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(45) DEFAULT NULL,
  `yearsofexperience` int(11) DEFAULT NULL,
  `skillid` int(11) NOT NULL,
  `employeeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEC614555D6C89963` (`employeeid`),
  KEY `FKEC614555ACEC98D1` (`skillid`),
  KEY `FKEC6145556FDD142` (`employeeid`),
  KEY `FKEC6145555D1A8552` (`skillid`),
  CONSTRAINT `FKEC6145555D1A8552` FOREIGN KEY (`skillid`) REFERENCES `m_hr_skill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKEC6145556FDD142` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_skill`
--

LOCK TABLES `m_hr_employee_skill` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_skill` ENABLE KEYS */;
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
-- Table structure for table `m_hr_employee_job`
--

DROP TABLE IF EXISTS `m_hr_employee_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `joineddate` datetime DEFAULT NULL,
  `jobtitleid` int(11) DEFAULT NULL,
  `employeeid` int(11) NOT NULL,
  `employmentstatusid` int(11) DEFAULT NULL,
  `divisionid` int(11) DEFAULT NULL,
  `contract_startdate` datetime NOT NULL,
  `contract_enddate` datetime NOT NULL,
  `comments` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jobtitleid` (`jobtitleid`),
  UNIQUE KEY `jobtitleid_2` (`jobtitleid`),
  KEY `FK318707C1D6C89963` (`employeeid`),
  KEY `FK318707C172DE643` (`employmentstatusid`),
  KEY `FK318707C165C6D723` (`jobtitleid`),
  KEY `FK318707C1FCC16143` (`divisionid`),
  KEY `FK318707C16FDD142` (`employeeid`),
  KEY `FK318707C1DD8CF22` (`employmentstatusid`),
  KEY `FK318707C195FC0F02` (`jobtitleid`),
  KEY `FK318707C12CF69922` (`divisionid`),
  CONSTRAINT `FK318707C12CF69922` FOREIGN KEY (`divisionid`) REFERENCES `m_hr_division` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK318707C165C6D723` FOREIGN KEY (`jobtitleid`) REFERENCES `m_hr_jobtitle` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK318707C16FDD142` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK318707C172DE643` FOREIGN KEY (`employmentstatusid`) REFERENCES `m_hr_employment_status` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_job`
--

LOCK TABLES `m_hr_employee_job` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_message_category`
--

DROP TABLE IF EXISTS `m_prj_message_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_message_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_message_category`
--

LOCK TABLES `m_prj_message_category` WRITE;
/*!40000 ALTER TABLE `m_prj_message_category` DISABLE KEYS */;
INSERT INTO `m_prj_message_category` VALUES (1,'No category'),(2,'Assets'),(3,'Code'),(4,'Copy writting'),(5,'Design'),(6,'Miscellaneous'),(7,'Transcript');
/*!40000 ALTER TABLE `m_prj_message_category` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_version_1` (`projectid`),
  KEY `FK_m_tracker_version_2` (`createdUser`),
  CONSTRAINT `FK_m_tracker_version_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_tracker_version_2` FOREIGN KEY (`createdUser`) REFERENCES `engroup`.`s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
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
-- Table structure for table `m_prj_problem_source`
--

DROP TABLE IF EXISTS `m_prj_problem_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_problem_source` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sourcename` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_problem_source`
--

LOCK TABLES `m_prj_problem_source` WRITE;
/*!40000 ALTER TABLE `m_prj_problem_source` DISABLE KEYS */;
INSERT INTO `m_prj_problem_source` VALUES (1,'source',NULL);
/*!40000 ALTER TABLE `m_prj_problem_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_divisiontype`
--

DROP TABLE IF EXISTS `m_hr_divisiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_divisiontype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_divisiontype`
--

LOCK TABLES `m_hr_divisiontype` WRITE;
/*!40000 ALTER TABLE `m_hr_divisiontype` DISABLE KEYS */;
INSERT INTO `m_hr_divisiontype` VALUES (1,'AAA'),(2,'BBB');
/*!40000 ALTER TABLE `m_hr_divisiontype` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_component_1` (`projectid`),
  KEY `FK_m_tracker_component_2` (`userlead`),
  KEY `FK_m_tracker_component_3` (`createdUser`),
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
-- Table structure for table `m_ecm_ver_names`
--

DROP TABLE IF EXISTS `m_ecm_ver_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_ver_names` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_ver_names`
--

LOCK TABLES `m_ecm_ver_names` WRITE;
/*!40000 ALTER TABLE `m_ecm_ver_names` DISABLE KEYS */;
INSERT INTO `m_ecm_ver_names` VALUES (1,'versionStorage'),(2,'version'),(3,'predecessors'),(4,'created'),(5,'successors'),(6,'versionHistory'),(7,'versionableUuid'),(8,'versionLabels'),(9,'frozenNode'),(10,'frozenUuid'),(11,'frozenPrimaryType'),(12,'modified'),(13,'documentStatus'),(14,'data'),(15,'mimeType'),(16,'lastModified'),(17,'frozenMixinTypes'),(18,'ocm_classname');
/*!40000 ALTER TABLE `m_ecm_ver_names` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
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
-- Table structure for table `m_hr_employee_language`
--

DROP TABLE IF EXISTS `m_hr_employee_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_language` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employeeid` int(11) NOT NULL,
  `languageid` int(11) NOT NULL,
  `levelid` int(10) unsigned NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_hr_employee_language_2` (`employeeid`),
  KEY `FK_m_hr_employee_language_1` (`levelid`),
  KEY `FK_m_hr_employee_language_3` (`languageid`),
  CONSTRAINT `FK_m_hr_employee_language_1` FOREIGN KEY (`levelid`) REFERENCES `m_hr_language_level` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_m_hr_employee_language_2` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_employee_language_3` FOREIGN KEY (`languageid`) REFERENCES `m_hr_language` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_language`
--

LOCK TABLES `m_hr_employee_language` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_role_permission`
--

DROP TABLE IF EXISTS `s_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_role_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pathid` varchar(255) NOT NULL,
  `isAuthorz` tinyint(1) NOT NULL COMMENT '0: prohibit, 1:read, 2:write',
  `roleid` int(11) NOT NULL,
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
-- Table structure for table `m_hr_language_level`
--

DROP TABLE IF EXISTS `m_hr_language_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_language_level` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `level` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_language_level`
--

LOCK TABLES `m_hr_language_level` WRITE;
/*!40000 ALTER TABLE `m_hr_language_level` DISABLE KEYS */;
INSERT INTO `m_hr_language_level` VALUES (1,'Expert');
/*!40000 ALTER TABLE `m_hr_language_level` ENABLE KEYS */;
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
  `countryId` int(11) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ownership` varchar(255) DEFAULT NULL,
  `shippingAddress` varchar(255) DEFAULT NULL,
  `shippingCity` varchar(100) DEFAULT NULL,
  `shippingPostalCode` varchar(45) DEFAULT NULL,
  `shippingCountryId` int(11) DEFAULT NULL,
  `shippingState` varchar(45) DEFAULT NULL,
  `numemployees` int(10) unsigned DEFAULT NULL,
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL COMMENT '''Analyst'',''Competitor'',''Customer'',''Integrator'',''Investor'',''Partner'',''Press'',''Prospect,''Reseller'',''Other''',
  `industry` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_account_3` (`countryId`),
  KEY `FK_m_crm_account_4` (`shippingCountryId`),
  KEY `FK_m_crm_account_6` (`createdUser`),
  KEY `FK_m_crm_account_7` (`sAccountId`),
  KEY `FK_m_crm_account_8` (`assignUser`),
  CONSTRAINT `FK_m_crm_account_3` FOREIGN KEY (`countryId`) REFERENCES `s_country` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_account_4` FOREIGN KEY (`shippingCountryId`) REFERENCES `s_country` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
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
-- Table structure for table `chat_con_names`
--

DROP TABLE IF EXISTS `chat_con_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_con_names` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_con_names`
--

LOCK TABLES `chat_con_names` WRITE;
/*!40000 ALTER TABLE `chat_con_names` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_con_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_pay_grades`
--

DROP TABLE IF EXISTS `m_hr_pay_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_pay_grades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maximumsalary` double DEFAULT NULL,
  `minimumsalary` double DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `currencyid` int(11) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK12599948CD254961` (`currencyid`),
  KEY `FK125999482E338778` (`currencyid`),
  KEY `FK_m_hr_pay_grades_2` (`sAccountId`),
  CONSTRAINT `FK125999482E338778` FOREIGN KEY (`currencyid`) REFERENCES `s_currency` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_pay_grades_2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_pay_grades`
--

LOCK TABLES `m_hr_pay_grades` WRITE;
/*!40000 ALTER TABLE `m_hr_pay_grades` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_pay_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_company_policy`
--

DROP TABLE IF EXISTS `m_hr_company_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_company_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `details` varchar(4000) DEFAULT NULL,
  `applyTo` varchar(1000) DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  `lastVisionDate` timestamp NULL DEFAULT NULL,
  `policyDocPath` varchar(4000) NOT NULL,
  `sAccountId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `m_hr_company_policy_fk1` (`createdUser`),
  KEY `m_hr_company_policy_fk2` (`sAccountId`),
  CONSTRAINT `m_hr_company_policy_fk1` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `m_hr_company_policy_fk2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_company_policy`
--

LOCK TABLES `m_hr_company_policy` WRITE;
/*!40000 ALTER TABLE `m_hr_company_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_company_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_roleuser`
--

DROP TABLE IF EXISTS `s_roleuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_roleuser` (
  `username` varchar(45) NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_roleuser_1` (`username`),
  KEY `FK_s_roleuser_2` (`roleid`),
  CONSTRAINT `FK_s_roleuser_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_s_roleuser_2` FOREIGN KEY (`roleid`) REFERENCES `s_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_roleuser`
--

LOCK TABLES `s_roleuser` WRITE;
/*!40000 ALTER TABLE `s_roleuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_roleuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_ecm_ver_refs`
--

DROP TABLE IF EXISTS `m_ecm_ver_refs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_ver_refs` (
  `NODE_ID` varbinary(16) NOT NULL,
  `REFS_DATA` longblob NOT NULL,
  UNIQUE KEY `m_ecm_ver_refs_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_ver_refs`
--

LOCK TABLES `m_ecm_ver_refs` WRITE;
/*!40000 ALTER TABLE `m_ecm_ver_refs` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_ecm_ver_refs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_language`
--

DROP TABLE IF EXISTS `m_hr_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(100) NOT NULL,
  `code` varchar(45) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_hr_language_1` (`sAccountId`),
  CONSTRAINT `FK_m_hr_language_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_language`
--

LOCK TABLES `m_hr_language` WRITE;
/*!40000 ALTER TABLE `m_hr_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee_payment`
--

DROP TABLE IF EXISTS `m_hr_employee_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_payment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employeeid` int(11) NOT NULL,
  `paygradeid` int(11) NOT NULL,
  `salary` double NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_hr_employee_payment_1` (`employeeid`),
  KEY `FK_m_hr_employee_payment_2` (`paygradeid`),
  CONSTRAINT `FK_m_hr_employee_payment_1` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_employee_payment_2` FOREIGN KEY (`paygradeid`) REFERENCES `m_hr_pay_grades` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_payment`
--

LOCK TABLES `m_hr_employee_payment` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee_emergency_contact`
--

DROP TABLE IF EXISTS `m_hr_employee_emergency_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_emergency_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hometelephone` varchar(45) DEFAULT NULL,
  `mobiletelephone` varchar(45) DEFAULT NULL,
  `relname` varchar(255) NOT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `worktelephone` varchar(45) DEFAULT NULL,
  `employeeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCF938476D6C89963` (`employeeid`),
  KEY `FKCF9384766FDD142` (`employeeid`),
  CONSTRAINT `FKCF9384766FDD142` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_emergency_contact`
--

LOCK TABLES `m_hr_employee_emergency_contact` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_emergency_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_emergency_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee_immigration`
--

DROP TABLE IF EXISTS `m_hr_employee_immigration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_immigration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `expireddate` datetime DEFAULT NULL,
  `issuesdate` datetime DEFAULT NULL,
  `no` varchar(45) DEFAULT NULL,
  `type` int(10) unsigned DEFAULT NULL COMMENT '0:visa, 1:passport',
  `citizenship` int(11) DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEEB3D72ED6C89963` (`employeeid`),
  KEY `FKEEB3D72E443402C6` (`citizenship`),
  KEY `FKEEB3D72E6FDD142` (`employeeid`),
  KEY `FKEEB3D72E99EA25CF` (`citizenship`),
  CONSTRAINT `FKEEB3D72E99EA25CF` FOREIGN KEY (`citizenship`) REFERENCES `s_country` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKEEB3D72ED6C89963` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_immigration`
--

LOCK TABLES `m_hr_employee_immigration` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_immigration` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_immigration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee_contact`
--

DROP TABLE IF EXISTS `m_hr_employee_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT NULL,
  `hometelephone` varchar(45) DEFAULT NULL,
  `otheremail` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `street1` varchar(45) DEFAULT NULL,
  `street2` varchar(45) DEFAULT NULL,
  `workemail` varchar(45) DEFAULT NULL,
  `worktelephone` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `employeeid` int(11) NOT NULL,
  `countryid` int(11) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employeeid` (`employeeid`),
  UNIQUE KEY `employeeid_2` (`employeeid`),
  KEY `FK11DEEE24D6C89963` (`employeeid`),
  KEY `FK11DEEE241B8FF853` (`countryid`),
  KEY `FK11DEEE246FDD142` (`employeeid`),
  KEY `FK11DEEE2471461B5C` (`countryid`),
  CONSTRAINT `FK11DEEE2471461B5C` FOREIGN KEY (`countryid`) REFERENCES `s_country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_hr_employee_contact_2` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_contact`
--

LOCK TABLES `m_hr_employee_contact` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_leave_type`
--

DROP TABLE IF EXISTS `m_hr_leave_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_leave_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(4000) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `maximumLeave` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_leave_type`
--

LOCK TABLES `m_hr_leave_type` WRITE;
/*!40000 ALTER TABLE `m_hr_leave_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_leave_type` ENABLE KEYS */;
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
-- Table structure for table `m_prj_risk_source`
--

DROP TABLE IF EXISTS `m_prj_risk_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_risk_source` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_risk_source`
--

LOCK TABLES `m_prj_risk_source` WRITE;
/*!40000 ALTER TABLE `m_prj_risk_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_risk_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_tracker_related_item`
--

DROP TABLE IF EXISTS `m_tracker_related_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_tracker_related_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(10) unsigned NOT NULL COMMENT '0:fixed version, 1:affected versions, 2:component',
  `relateitemid` int(10) unsigned NOT NULL,
  `refitemkey` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_tracker_related_item`
--

LOCK TABLES `m_tracker_related_item` WRITE;
/*!40000 ALTER TABLE `m_tracker_related_item` DISABLE KEYS */;
INSERT INTO `m_tracker_related_item` VALUES (1,0,1,'bug-1'),(2,0,2,'bug-1'),(3,1,2,'bug-1'),(4,2,1,'bug-1');
/*!40000 ALTER TABLE `m_tracker_related_item` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL COMMENT 'One of values ''Planning'', ''Active'', \n''Inactive'', ''Completed'', ''In Queue'',''Sending''',
  `assignUser` varchar(45) DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL COMMENT 'one of values ''High'', ''Medium'', ''Low''',
  `type` varchar(45) DEFAULT NULL COMMENT '''Account'',''Campaign'',''Contact'',''Lead'',''Opportunity'',''Target'',''Quote'',''Product'',''Case''',
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_task_4` (`createdUser`),
  KEY `FK_m_crm_task_5` (`sAccountId`),
  KEY `FK_m_crm_task_6` (`assignUser`),
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
-- Table structure for table `m_crm_industry`
--

DROP TABLE IF EXISTS `m_crm_industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_industry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_industry`
--

LOCK TABLES `m_crm_industry` WRITE;
/*!40000 ALTER TABLE `m_crm_industry` DISABLE KEYS */;
INSERT INTO `m_crm_industry` VALUES (1,'a');
/*!40000 ALTER TABLE `m_crm_industry` ENABLE KEYS */;
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
-- Table structure for table `chat_ver_names`
--

DROP TABLE IF EXISTS `chat_ver_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_ver_names` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_ver_names`
--

LOCK TABLES `chat_ver_names` WRITE;
/*!40000 ALTER TABLE `chat_ver_names` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_ver_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_module`
--

DROP TABLE IF EXISTS `s_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_module` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `modulename` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `uri` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '0 is enable and 1 is disable module',
  `title` varchar(255) NOT NULL,
  `sortindex` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_module`
--

LOCK TABLES `s_module` WRITE;
/*!40000 ALTER TABLE `s_module` DISABLE KEYS */;
INSERT INTO `s_module` VALUES (1,'Human Resource','Human Resource Module','Hr.swf',1,'Human Resource',0),(2,'Customer Management','Customer Management','Crm.swf',1,'Customer Management',1),(3,'Document Management','Document Management','Ecm.swf',1,'Document',5),(4,'Project Management','Project Management','Project.swf',1,'Project Management',6);
/*!40000 ALTER TABLE `s_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_colla_workgroup`
--

DROP TABLE IF EXISTS `m_colla_workgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_colla_workgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `isModerated` tinyint(1) DEFAULT NULL,
  `sAccountId` int(11) DEFAULT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  `accessMode` int(11) DEFAULT NULL,
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
INSERT INTO `schema_version` VALUES ('20100704000000','2011-04-24 13:46:13',3089),('20100705000000','2011-04-24 13:46:17',124),('20100902000000','2011-04-24 13:46:17',94),('20101024000000','2011-04-24 13:46:17',1934),('20101203000000','2011-04-24 13:46:19',1935),('20110421000000','2011-04-24 13:46:21',62);
/*!40000 ALTER TABLE `schema_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_task_status`
--

DROP TABLE IF EXISTS `m_crm_task_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_task_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_task_status`
--

LOCK TABLES `m_crm_task_status` WRITE;
/*!40000 ALTER TABLE `m_crm_task_status` DISABLE KEYS */;
INSERT INTO `m_crm_task_status` VALUES (1,'In Progress');
/*!40000 ALTER TABLE `m_crm_task_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_education`
--

DROP TABLE IF EXISTS `m_hr_education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_education` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(255) NOT NULL,
  `durationinmonth` int(11) DEFAULT NULL,
  `institute` varchar(255) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hr_education_1` (`sAccountId`),
  CONSTRAINT `FK_hr_education_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_education`
--

LOCK TABLES `m_hr_education` WRITE;
/*!40000 ALTER TABLE `m_hr_education` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_location`
--

DROP TABLE IF EXISTS `m_hr_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  `comments` varchar(2000) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `locName` varchar(100) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `countryid` int(11) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBC51CF4A1B8FF853` (`countryid`),
  KEY `FKBC51CF4A71461B5C` (`countryid`),
  KEY `FK_m_hr_location_2` (`sAccountId`),
  CONSTRAINT `FKBC51CF4A1B8FF853` FOREIGN KEY (`countryid`) REFERENCES `s_country` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_m_hr_location_2` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_location`
--

LOCK TABLES `m_hr_location` WRITE;
/*!40000 ALTER TABLE `m_hr_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_location` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
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
-- Table structure for table `s_user_module_settings`
--

DROP TABLE IF EXISTS `s_user_module_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_user_module_settings` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  `moduleid` int(10) unsigned NOT NULL,
  `active` tinyint(1) DEFAULT NULL COMMENT '0: module will be started when application start, 1: module is only be loaded when request',
  PRIMARY KEY (`id`),
  KEY `FK_ows_user_module_settings_2` (`moduleid`),
  KEY `FK_ows_user_module_settings_1` (`rolename`),
  CONSTRAINT `FK_ows_user_module_settings_2` FOREIGN KEY (`moduleid`) REFERENCES `s_module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user_module_settings`
--

LOCK TABLES `s_user_module_settings` WRITE;
/*!40000 ALTER TABLE `s_user_module_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_user_module_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_note`
--

DROP TABLE IF EXISTS `m_crm_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_note` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contactid` int(10) unsigned DEFAULT NULL,
  `subject` varchar(255) NOT NULL,
  `note` varchar(4000) DEFAULT NULL,
  `type` int(10) unsigned DEFAULT NULL,
  `typeid` int(10) unsigned DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `attachmentpath` varchar(1000) DEFAULT NULL,
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_crm_note_1` (`username`),
  KEY `FK_m_crm_note_2` (`contactid`),
  KEY `FK_m_crm_note_3` (`createdUser`),
  KEY `FK_m_crm_note_4` (`sAccountId`),
  CONSTRAINT `FK_m_crm_note_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_crm_note_2` FOREIGN KEY (`contactid`) REFERENCES `m_crm_contact` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
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
-- Table structure for table `chat_ver_bundle`
--

DROP TABLE IF EXISTS `chat_ver_bundle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_ver_bundle` (
  `NODE_ID` varbinary(16) NOT NULL,
  `BUNDLE_DATA` longblob NOT NULL,
  UNIQUE KEY `chat_ver_bundle_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_ver_bundle`
--

LOCK TABLES `chat_ver_bundle` WRITE;
/*!40000 ALTER TABLE `chat_ver_bundle` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_ver_bundle` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
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
  `posteddate` datetime DEFAULT NULL,
  `logby` varchar(45) DEFAULT NULL,
  `severity` int(10) unsigned DEFAULT NULL,
  `priority` int(10) unsigned DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `status` int(10) unsigned NOT NULL,
  `duedate` datetime DEFAULT NULL,
  `environment` varchar(4000) DEFAULT NULL,
  `resolution` int(10) unsigned DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  KEY `FK_m_tracker_bug_1` (`assignuser`),
  KEY `FK_m_tracker_bug_2` (`logby`),
  KEY `FK_m_tracker_bug_4` (`projectid`),
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
  `displayname` varchar(100) DEFAULT NULL,
  `registeredTime` datetime DEFAULT NULL COMMENT 'has value follows format MM/dd/YYYY hh:ss',
  `active` tinyint(1) DEFAULT '0',
  `lastAccessedTime` datetime DEFAULT NULL COMMENT 'has value follows format MM/dd/YYYY hh:ss',
  `accountId` int(11) NOT NULL,
  `countryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`),
  KEY `s_user_fk_1` (`accountId`),
  KEY `FK_s_user_2` (`countryid`),
  CONSTRAINT `FK_s_user_2` FOREIGN KEY (`countryid`) REFERENCES `s_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `s_user_fk_1` FOREIGN KEY (`accountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user`
--

LOCK TABLES `s_user` WRITE;
/*!40000 ALTER TABLE `s_user` DISABLE KEYS */;
INSERT INTO `s_user` VALUES ('user1','a1',NULL,'b1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),('user2','a2',NULL,'b2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `s_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_source_type`
--

DROP TABLE IF EXISTS `m_crm_source_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_source_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_source_type`
--

LOCK TABLES `m_crm_source_type` WRITE;
/*!40000 ALTER TABLE `m_crm_source_type` DISABLE KEYS */;
INSERT INTO `m_crm_source_type` VALUES (1,'Cold Call'),(2,'Existing Customer'),(3,'Self Generated'),(4,'Partner'),(5,'Web Site'),(6,'World of Mouth');
/*!40000 ALTER TABLE `m_crm_source_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employee`
--

DROP TABLE IF EXISTS `m_hr_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supervisorid` int(11) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_hr_employee_2` (`username`),
  UNIQUE KEY `Index_5` (`code`),
  KEY `FK922516433B88F77C` (`supervisorid`),
  KEY `FK_m_hr_employee_3` (`sAccountId`),
  CONSTRAINT `FK_m_hr_employee_1` FOREIGN KEY (`supervisorid`) REFERENCES `m_hr_employee` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_employee_2` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_employee_3` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee`
--

LOCK TABLES `m_hr_employee` WRITE;
/*!40000 ALTER TABLE `m_hr_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_ecm_con_refs`
--

DROP TABLE IF EXISTS `m_ecm_con_refs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_con_refs` (
  `NODE_ID` varbinary(16) NOT NULL,
  `REFS_DATA` longblob NOT NULL,
  UNIQUE KEY `m_ecm_con_refs_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_con_refs`
--

LOCK TABLES `m_ecm_con_refs` WRITE;
/*!40000 ALTER TABLE `m_ecm_con_refs` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_ecm_con_refs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_con_refs`
--

DROP TABLE IF EXISTS `chat_con_refs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_con_refs` (
  `NODE_ID` varbinary(16) NOT NULL,
  `REFS_DATA` longblob NOT NULL,
  UNIQUE KEY `chat_con_refs_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_con_refs`
--

LOCK TABLES `chat_con_refs` WRITE;
/*!40000 ALTER TABLE `chat_con_refs` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_con_refs` ENABLE KEYS */;
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
-- Table structure for table `m_crm_sales_stage`
--

DROP TABLE IF EXISTS `m_crm_sales_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_sales_stage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_sales_stage`
--

LOCK TABLES `m_crm_sales_stage` WRITE;
/*!40000 ALTER TABLE `m_crm_sales_stage` DISABLE KEYS */;
INSERT INTO `m_crm_sales_stage` VALUES (1,'a');
/*!40000 ALTER TABLE `m_crm_sales_stage` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_social_user_message`
--

LOCK TABLES `m_social_user_message` WRITE;
/*!40000 ALTER TABLE `m_social_user_message` DISABLE KEYS */;
INSERT INTO `m_social_user_message` VALUES (1,'baohan','{\"messageList\":{\"messages\":[{\"@class\":\"tree-set\",\"comparator\":{\"@class\":\"com.engroup.module.social.domain.SocialMessageList$MessageComparator\"},\"message\":[{\"message\":\"It is an interesting topic !!!\",\"posteddate\":\"2011-06-02 10:39:12.709 ICT\",\"posteduser\":\"hainguyen\"},{\"message\":\"It is an interesting topic !!!\",\"posteddate\":\"2011-06-02 10:41:34.279 ICT\",\"posteduser\":\"hainguyen\"},{\"message\":\"It is an interesting topic !!!\",\"posteddate\":\"2011-06-02 10:41:50.390 ICT\",\"posteduser\":\"hainguyen\"}]}]}}'),(2,'linhduong','{\"messageList\":{\"messages\":[{\"@class\":\"tree-set\",\"comparator\":{\"@class\":\"com.engroup.module.social.domain.SocialMessageList$MessageComparator\"},\"message\":[{\"message\":\"It is an interesting topic !!!\",\"posteddate\":\"2011-06-02 10:39:12.709 ICT\",\"posteduser\":\"hainguyen\"},{\"message\":\"It is an interesting topic !!!\",\"posteddate\":\"2011-06-02 10:41:34.279 ICT\",\"posteduser\":\"hainguyen\"},{\"message\":\"It is an interesting topic !!!\",\"posteddate\":\"2011-06-02 10:41:50.390 ICT\",\"posteduser\":\"hainguyen\"}]}]}}');
/*!40000 ALTER TABLE `m_social_user_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_con_bundle`
--

DROP TABLE IF EXISTS `chat_con_bundle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_con_bundle` (
  `NODE_ID` varbinary(16) NOT NULL,
  `BUNDLE_DATA` longblob NOT NULL,
  UNIQUE KEY `chat_con_bundle_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_con_bundle`
--

LOCK TABLES `chat_con_bundle` WRITE;
/*!40000 ALTER TABLE `chat_con_bundle` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_con_bundle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_job_categories`
--

DROP TABLE IF EXISTS `m_hr_job_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_job_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `paygradeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK12FA3B3C7056ADC` (`paygradeid`),
  KEY `FK12FA3B39D772EDD` (`paygradeid`),
  CONSTRAINT `FK12FA3B39D772EDD` FOREIGN KEY (`paygradeid`) REFERENCES `m_hr_pay_grades` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_job_categories`
--

LOCK TABLES `m_hr_job_categories` WRITE;
/*!40000 ALTER TABLE `m_hr_job_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_job_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_hr_employment_status`
--

DROP TABLE IF EXISTS `m_hr_employment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employment_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(2000) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `code` varchar(45) NOT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_hr_employment_status_1` (`sAccountId`),
  CONSTRAINT `FK_m_hr_employment_status_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employment_status`
--

LOCK TABLES `m_hr_employment_status` WRITE;
/*!40000 ALTER TABLE `m_hr_employment_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employment_status` ENABLE KEYS */;
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
-- Table structure for table `m_ecm_con_bundle`
--

DROP TABLE IF EXISTS `m_ecm_con_bundle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_con_bundle` (
  `NODE_ID` varbinary(16) NOT NULL,
  `BUNDLE_DATA` longblob NOT NULL,
  UNIQUE KEY `m_ecm_con_bundle_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_con_bundle`
--

LOCK TABLES `m_ecm_con_bundle` WRITE;
/*!40000 ALTER TABLE `m_ecm_con_bundle` DISABLE KEYS */;
INSERT INTO `m_ecm_con_bundle` VALUES ('(ÊÕ1Mã»#ü¦¡K½×','\0\0\0\0\0\Z09™@qBÿ£¥?ïwÖã\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.357+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.374+07:00ÿÿÿÿ\0\0\0\0\0'),(':LÄK‚Gö½¾*5êşúb','\0\0\0\0\0sDÏªHß¼]˜ÖU-½\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0admin\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.UserPermissionÿÿÿÿ\0\0\0\0\0'),('²dÈıÙOƒ™\n(Aúâ&','\0\0\0\0\0Êşº¾Êşº¾Êşº¾Êşº¾\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.236+07:00\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0÷V6Ù´$L{½üÖ€4+Ú¡\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0AAAA\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-09-05T23:11:10.484+07:00\0\0\0\0\0\0\0\0	\0\0\0\0\0\0¶È Ã^H”Iš+>sæ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÿÿÿßy¿¡m(M¤œ`Ú¥ßOR\0\0\0\0content\0\0\0'),('•R93ãN±‡\0ÃÇ—¤%','\0\0\0\0\0şq7)ÄM†ÒNÓĞ{|ƒ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('%S\0!E©üÏ‘\rp9€','\0\0\0\0\0\Z09™@qBÿ£¥?ïwÖã\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.225+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.300+07:00ÿÿÿÿ\0\0\0\0\0'),('\Z09™@qBÿ£¥?ïwÖã','\0\0\0\0\0¾Ğ‰N¥½¼YìzUi\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.296+07:00ÿÿÿÿ\0%S\0!E©üÏ‘\rp9€\0\0\0\0	bookmarks>W\'*ŒMx•Œœ	9_\0\0\0\0projects(ÊÕ1Mã»#ü¦¡K½×\0\0\0\0	user_iconÃŠÿË\\&KA›^¡dÃ—Á\0\0\0\0attachments\0\0\0'),('\ZS’êøFÀ‘w¹ø`æ)Ê','\0\0\0\0\0×ı	Á@NEš	·áxúCQ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('\ZºpäƒOM¦ˆ¾t.','\0\0\0\0\0æìÄ›€LúŸ‡è)‚Oº°\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.929+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.982+07:00ÿÿÿÿ\0\0\0\0\0'),('#-ª½·Lô‹‘R‰^İš','\0\0\0\0\0.¦†37O0¯_`owzÛ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('$±«‹\ZdKH•A#û=ƒÆ','\0\0\0\0\0ïü»íËLİ¥Î°¯\rè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.439+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.501+07:00ÿÿÿÿ\0\0\0\0\0'),('*8éK†Jâ¼³$ò-ı¤','\0\0\0\0\0æìÄ›€LúŸ‡è)‚Oº°\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.677+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.752+07:00ÿÿÿÿ\0\0\0\0\0'),('.&²œ\'B³ñ—İŒ2BZ','\0\0\0\0\0õİ!ƒÅNp³ w\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.267+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.330+07:00ÿÿÿÿ\0›+<ÒÆêGàº-cËĞ‘˜ó\0\0\0\0permissions\0\0\0\0'),('.¦†37O0¯_`owzÛ','\0\0\0\0\0tÆ[UĞoOÆƒ½&K¥şh\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.604+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.644+07:00ÿÿÿÿ\0#-ª½·Lô‹‘R‰^İš\0\0\0\0permissions\0\0\0\0'),(';Y»¹rNµ¥@tşví¿å','\0\0\0\0\0ïü»íËLİ¥Î°¯\rè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.361+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.408+07:00ÿÿÿÿ\0\0\0\0\0'),('<€b®+„F¸£ışuĞÂç','\0\0\0\0\0æìÄ›€LúŸ‡è)‚Oº°\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.777+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.827+07:00ÿÿÿÿ\0\0\0\0\0'),('E¤ËJ«LC´3~¥Ğ^','\0\0\0\0\0öÈ™ş¿@Õ 9³«SFè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.688+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0admin\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.751+07:00ÿÿÿÿ\0\\–|Ô¨³@G„³#]Øsk\0\0\0\0permissions\0\0\0\0'),('T«/À*wJ¶É÷è¾R','\0\0\0\0\0ïü»íËLİ¥Î°¯\rè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.610+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.657+07:00ÿÿÿÿ\0\0\0\0\0'),('\\–|Ô¨³@G„³#]Øsk','\0\0\0\0\0E¤ËJ«LC´3~¥Ğ^\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0admin\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.UserPermissionÿÿÿÿ\0\0\0\0\0'),('j¿¦ÂŠüN‚qeß#pP','\0\0\0\0\0µáXı›lKR·¸ÀÀ¢¤yè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('sDÏªHß¼]˜ÖU-½','\0\0\0\0\0öü«ê×ÍJ	Œª§‰Q6\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:08.011+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0admin\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:08.073+07:00ÿÿÿÿ\0:LÄK‚Gö½¾*5êşúb\0\0\0\0permissions\0\0\0\0'),('tÆ[UĞoOÆƒ½&K¥şh','\0\0\0\0\0Êşº¾Êşº¾Êşº¾Êşº¾\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:06.640+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:06.855+07:00ÿÿÿÿ\0«±åCŠëJHàĞhÓÓ\0\0\0\0permissionswLIP ©Eƒ…]µş{m\0\0\0\0content.¦†37O0¯_`owzÛ\0\0\0\0users\0\0\0'),('wLIP ©Eƒ…]µş{m','\0\0\0\0\0tÆ[UĞoOÆƒ½&K¥şh\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.153+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.568+07:00ÿÿÿÿ\0}VVÜÁ@™’ÁÂàÏë”\0\0\0\0permissionsæìÄ›€LúŸ‡è)‚Oº°\0\0\0\0systemöü«ê×ÍJ	Œª§‰Q6\0\0\0\0users\0\0\0'),('}VVÜÁ@™’ÁÂàÏë”','\0\0\0\0\0wLIP ©Eƒ…]µş{m\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('>W\'*ŒMx•Œœ	9_','\0\0\0\0\0\Z09™@qBÿ£¥?ïwÖã\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.318+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.335+07:00ÿÿÿÿ\0\0\0\0\0'),('›+<ÒÆêGàº-cËĞ‘˜ó','\0\0\0\0\0.&²œ\'B³ñ—İŒ2BZ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('§zNóƒgCÒBYö‚','\0\0\0\0\0¾Ğ‰N¥½¼YìzUi\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.456+07:00ÿÿÿÿ\0ßıÛÁGq…ş™UúÈ\0\0\0\0admin\0\0\0\0'),('«±åCŠëJHàĞhÓÓ','\0\0\0\0\0tÆ[UĞoOÆƒ½&K¥şh\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('µáXı›lKR·¸ÀÀ¢¤yè','\0\0\0\0\0õİ!ƒÅNp³ w\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.064+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.236+07:00ÿÿÿÿ\0j¿¦ÂŠüN‚qeß#pP\0\0\0\0permissionsïü»íËLİ¥Î°¯\rè\0\0\0\0systemöÈ™ş¿@Õ 9³«SFè\0\0\0\0users\0\0\0'),('¾Ğ‰N¥½¼YìzUi','\0\0\0\0\0×ı	Á@NEš	·áxúCQ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:02.805+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.114+07:00ÿÿÿÿ\0àF’·ï”M«¾•ËDÑÉÕ\0\0\0\0\0permissions\Z09™@qBÿ£¥?ïwÖã\0\0\0\0system§zNóƒgCÒBYö‚\0\0\0\0users\0\0\0'),('ÃŠÿË\\&KA›^¡dÃ—Á','\0\0\0\0\0\Z09™@qBÿ£¥?ïwÖã\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.392+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.414+07:00ÿÿÿÿ\0\0\0\0\0'),('ÃÊ”’zLÜ›¥à¡uÚ','\0\0\0\0\0ïü»íËLİ¥Î°¯\rè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.532+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.579+07:00ÿÿÿÿ\0\0\0\0\0'),('ÇÌòş´GH4™ß}Ë:ô¼X','\0\0\0\0\0æìÄ›€LúŸ‡è)‚Oº°\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.851+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.905+07:00ÿÿÿÿ\0\0\0\0\0'),('Êşº¾Êşº¾Êşº¾Êşº¾','\0\0\0\0\0\0\0\0ÿÿÿÿÿÿÿÿ\0Ş­¾ïÊşº¾Êşº¾Êşº¾\0\0\0\0systemõİ!ƒÅNp³ w\0\0\0\01×ı	Á@NEš	·áxúCQ\0\0\0\02tÆ[UĞoOÆƒ½&K¥şh\0\0\0\03²dÈıÙOƒ™\n(Aúâ&\0\0\0\0A\0\0\"\0'),('ÎN©öw\rFŞ¬›2/æ_','\0\0\0\0\0õİ!ƒÅNp³ w\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('×ı	Á@NEš	·áxúCQ','\0\0\0\0\0Êşº¾Êşº¾Êşº¾Êşº¾\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:36:59.928+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:00.612+07:00ÿÿÿÿ\0\ZS’êøFÀ‘w¹ø`æ)Ê\0\0\0\0permissions¾Ğ‰N¥½¼YìzUi\0\0\0\0contentşq7)ÄM†ÒNÓĞ{|ƒ\0\0\0\0users\0\0\0'),('Ş­¾ïÊşº¾Êşº¾Êşº¾','\0\0\0\0\0Êşº¾Êşº¾Êşº¾Êşº¾\0\0ÿÿÿÿÿÿÿÿ\0Ş­¾ïúÎº¾Êşº¾Êşº¾\0\0\0\0versionStorageŞ­¾ïÊşÊşÊşº¾Êşº¾\0\0\0\0	nodeTypes\0\0\0\0'),('ßıÛÁGq…ş™UúÈ','\0\0\0\0\0§zNóƒgCÒBYö‚\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.435+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0admin\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.460+07:00ÿÿÿÿ\0ôœe{C–¿ p‚%ˆÏ1\0\0\0\0permissions\0\0\0\0'),('ßy¿¡m(M¤œ`Ú¥ßOR','\0\0\0\0\0²dÈıÙOƒ™\n(Aúâ&\0\0\0\0\0	\0\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0this is the content 2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0plain/x\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02012-02-21T00:14:57.235+07:00\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0&com.engroup.module.ecm.domain.Resourceÿÿÿÿ\0\0\0\0'),('àF’·ï”M«¾•ËDÑÉÕ\0','\0\0\0\0\0¾Ğ‰N¥½¼YìzUi\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.RolePermission\0\0\0\0\0\0\n\0\0\0\0\0\0\0\0\0\0\0\0registered_userÿÿÿÿ\0\0\0\0\0'),('æìÄ›€LúŸ‡è)‚Oº°','\0\0\0\0\0wLIP ©Eƒ…]µş{m\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:07.746+07:00ÿÿÿÿ\0*8éK†Jâ¼³$ò-ı¤\0\0\0\0	bookmarks<€b®+„F¸£ışuĞÂç\0\0\0\0projectsÇÌòş´GH4™ß}Ë:ô¼X\0\0\0\0	user_icon\ZºpäƒOM¦ˆ¾t.\0\0\0\0attachments\0\0\0'),('ïü»íËLİ¥Î°¯\rè','\0\0\0\0\0µáXı›lKR·¸ÀÀ¢¤yè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.408+07:00ÿÿÿÿ\0;Y»¹rNµ¥@tşví¿å\0\0\0\0	bookmarks$±«‹\ZdKH•A#û=ƒÆ\0\0\0\0projectsÃÊ”’zLÜ›¥à¡uÚ\0\0\0\0	user_iconT«/À*wJ¶É÷è¾R\0\0\0\0attachments\0\0\0'),('ôœe{C–¿ p‚%ˆÏ1','\0\0\0\0\0ßıÛÁGq…ş™UúÈ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\r\0\0\0\0\0\0\0\0\0\0\0\0admin\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\0\0\0\0\0\0\0\0\0\0\0\0,com.engroup.module.ecm.domain.UserPermissionÿÿÿÿ\0\0\0\0\0'),('õİ!ƒÅNp³ w','\0\0\0\0\0Êşº¾Êşº¾Êşº¾Êşº¾\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:25.815+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:25.908+07:00ÿÿÿÿ\0ÎN©öw\rFŞ¬›2/æ_\0\0\0\0permissionsµáXı›lKR·¸ÀÀ¢¤yè\0\0\0\0content.&²œ\'B³ñ—İŒ2BZ\0\0\0\0users\0\0\0'),('öÈ™ş¿@Õ 9³«SFè','\0\0\0\0\0µáXı›lKR·¸ÀÀ¢¤yè\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-24T20:46:26.751+07:00ÿÿÿÿ\0E¤ËJ«LC´3~¥Ğ^\0\0\0\0admin\0\0\0\0'),('öü«ê×ÍJ	Œª§‰Q6','\0\0\0\0\0wLIP ©Eƒ…]µş{m\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T11:47:08.066+07:00ÿÿÿÿ\0sDÏªHß¼]˜ÖU-½\0\0\0\0admin\0\0\0\0'),('şq7)ÄM†ÒNÓĞ{|ƒ','\0\0\0\0\0×ı	Á@NEš	·áxúCQ\0\0ÿÿÿÿ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.145+07:00\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\02011-04-25T08:37:05.201+07:00ÿÿÿÿ\0•R93ãN±‡\0ÃÇ—¤%\0\0\0\0permissions\0\0\0\0');
/*!40000 ALTER TABLE `m_ecm_con_bundle` ENABLE KEYS */;
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
-- Table structure for table `m_attachment`
--

DROP TABLE IF EXISTS `m_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_attachment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `attachmentid` varchar(45) NOT NULL,
  `documentpath` varchar(1000) NOT NULL,
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
-- Table structure for table `m_prj_permission`
--

DROP TABLE IF EXISTS `m_prj_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `pathid` varchar(45) NOT NULL,
  `canAccess` tinyint(1) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_permission_1` (`projectid`),
  KEY `FK_m_prj_permission_2` (`username`),
  CONSTRAINT `FK_m_prj_permission_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_permission_2` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_permission`
--

LOCK TABLES `m_prj_permission` WRITE;
/*!40000 ALTER TABLE `m_prj_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_ver_binval`
--

DROP TABLE IF EXISTS `chat_ver_binval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_ver_binval` (
  `BINVAL_ID` varchar(64) NOT NULL,
  `BINVAL_DATA` longblob NOT NULL,
  UNIQUE KEY `chat_ver_binval_IDX` (`BINVAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_ver_binval`
--

LOCK TABLES `chat_ver_binval` WRITE;
/*!40000 ALTER TABLE `chat_ver_binval` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_ver_binval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_prj_news`
--

DROP TABLE IF EXISTS `m_prj_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_news` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectid` int(10) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `htmldescription` varchar(4000) DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL,
  `posteduser` varchar(45) DEFAULT NULL,
  `posteddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_news_1` (`projectid`),
  CONSTRAINT `FK_m_prj_news_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_news`
--

LOCK TABLES `m_prj_news` WRITE;
/*!40000 ALTER TABLE `m_prj_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_crm_task_type`
--

DROP TABLE IF EXISTS `m_crm_task_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_crm_task_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_crm_task_type`
--

LOCK TABLES `m_crm_task_type` WRITE;
/*!40000 ALTER TABLE `m_crm_task_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_crm_task_type` ENABLE KEYS */;
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
-- Table structure for table `m_hr_employee_education`
--

DROP TABLE IF EXISTS `m_hr_employee_education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_education` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `educationid` int(11) NOT NULL,
  `employeeid` int(11) NOT NULL,
  `startdate` datetime NOT NULL,
  `enddate` datetime NOT NULL,
  `score` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hr_employee_education_1` (`educationid`),
  KEY `FK_hr_employee_education_2` (`employeeid`),
  CONSTRAINT `FK_hr_employee_education_1` FOREIGN KEY (`educationid`) REFERENCES `m_hr_education` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_hr_employee_education_2` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_education`
--

LOCK TABLES `m_hr_employee_education` WRITE;
/*!40000 ALTER TABLE `m_hr_employee_education` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_hr_employee_education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_user_view_settings`
--

DROP TABLE IF EXISTS `s_user_view_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_user_view_settings` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL COMMENT '0: inactive; 1:active',
  `viewid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s_user_view_settings_1` (`viewid`),
  KEY `FK_s_user_view_settings_2` (`username`),
  CONSTRAINT `FK_s_user_view_settings_1` FOREIGN KEY (`viewid`) REFERENCES `s_view` (`id`),
  CONSTRAINT `FK_s_user_view_settings_2` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user_view_settings`
--

LOCK TABLES `s_user_view_settings` WRITE;
/*!40000 ALTER TABLE `s_user_view_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_user_view_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_ver_refs`
--

DROP TABLE IF EXISTS `chat_ver_refs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_ver_refs` (
  `NODE_ID` varbinary(16) NOT NULL,
  `REFS_DATA` longblob NOT NULL,
  UNIQUE KEY `chat_ver_refs_IDX` (`NODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_ver_refs`
--

LOCK TABLES `chat_ver_refs` WRITE;
/*!40000 ALTER TABLE `chat_ver_refs` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_ver_refs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_ecm_con_names`
--

DROP TABLE IF EXISTS `m_ecm_con_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_ecm_con_names` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_ecm_con_names`
--

LOCK TABLES `m_ecm_con_names` WRITE;
/*!40000 ALTER TABLE `m_ecm_con_names` DISABLE KEYS */;
INSERT INTO `m_ecm_con_names` VALUES (1,'root'),(2,'system'),(3,'folder'),(4,'modified'),(5,'created'),(6,'rolePermission'),(7,'inherit'),(8,'flag'),(9,'ocm_classname'),(10,'rolename'),(11,'owner'),(12,'userPermission'),(13,'username'),(14,'file'),(15,'predecessors'),(16,'versionHistory'),(17,'baseVersion'),(18,'isCheckedOut'),(19,'resource'),(20,'discriminator'),(21,'data'),(22,'mimeType'),(23,'lastModified'),(24,'documentStatus'),(25,'description');
/*!40000 ALTER TABLE `m_ecm_con_names` ENABLE KEYS */;
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
-- Table structure for table `s_user_preferences`
--

DROP TABLE IF EXISTS `s_user_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_user_preferences` (
  `username` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_password` varchar(255) DEFAULT NULL,
  `chat_account` varchar(45) DEFAULT NULL,
  `chat_password` varchar(45) DEFAULT NULL,
  `chat_server_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_s_user_preferences_2` (`chat_server_id`),
  CONSTRAINT `FK_s_user_preferences_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_s_user_preferences_2` FOREIGN KEY (`chat_server_id`) REFERENCES `s_chat_server` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_user_preferences`
--

LOCK TABLES `s_user_preferences` WRITE;
/*!40000 ALTER TABLE `s_user_preferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_user_preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_marital_status`
--

DROP TABLE IF EXISTS `s_marital_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_marital_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_marital_status`
--

LOCK TABLES `s_marital_status` WRITE;
/*!40000 ALTER TABLE `s_marital_status` DISABLE KEYS */;
INSERT INTO `s_marital_status` VALUES (1,'Unmarried'),(2,'Married'),(3,'Divorced'),(4,'Others');
/*!40000 ALTER TABLE `s_marital_status` ENABLE KEYS */;
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
  `createdTime` timestamp NULL DEFAULT NULL,
  `createdUser` varchar(45) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  `assignUser` varchar(45) DEFAULT NULL,
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
  CONSTRAINT `FK_m_crm_quote_9` FOREIGN KEY (`assignUser`) REFERENCES `engroup`.`s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
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
-- Table structure for table `m_prj_resource`
--

DROP TABLE IF EXISTS `m_prj_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_resource` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `resourcename` varchar(255) NOT NULL,
  `bookingtype` varchar(45) NOT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `username` varchar(45) DEFAULT '0',
  `projectid` int(10) unsigned NOT NULL DEFAULT '0',
  `allocation` int(10) unsigned DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `workingtimerate` double DEFAULT NULL,
  `overtimerate` double DEFAULT NULL,
  `roleid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_resource_2` (`projectid`),
  KEY `FK_m_prj_resource_3` (`roleid`),
  KEY `FK_m_prj_resource_1` (`username`),
  CONSTRAINT `FK_m_prj_resource_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_resource_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_resource_3` FOREIGN KEY (`roleid`) REFERENCES `m_prj_role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_prj_resource`
--

LOCK TABLES `m_prj_resource` WRITE;
/*!40000 ALTER TABLE `m_prj_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_prj_resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-05 13:57:59

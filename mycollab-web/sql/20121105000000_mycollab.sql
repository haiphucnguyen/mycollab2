--CREATE DATABASE  IF NOT EXISTS `mycollab_live` /*!40100 DEFAULT CHARACTER SET utf8 */;
--USE `mycollab_live`;
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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;


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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_billing_plan`
--

/*!40000 ALTER TABLE `s_billing_plan` DISABLE KEYS */;
INSERT INTO `s_billing_plan` VALUES (1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `s_billing_plan` ENABLE KEYS */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_account`
--


/*!40000 ALTER TABLE `s_account` DISABLE KEYS */;
INSERT INTO `s_account` VALUES (1,NULL,NULL);
/*!40000 ALTER TABLE `s_account` ENABLE KEYS */;

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
  `registeredTime` datetime DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  `lastAccessedTime` datetime DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  `countryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`),
  KEY `s_user_fk_1` (`accountId`),
  KEY `FK_s_user_2` (`countryid`),
  CONSTRAINT `FK_s_user_2` FOREIGN KEY (`countryid`) REFERENCES `s_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `s_user_fk_1` FOREIGN KEY (`accountId`) REFERENCES `s_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;



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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `m_hr_employee_family`
--

DROP TABLE IF EXISTS `m_hr_employee_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_employee_family` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `relationship` varchar(45) NOT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hr_employee_family_1` (`employeeid`),
  CONSTRAINT `FK_hr_employee_family_1` FOREIGN KEY (`employeeid`) REFERENCES `m_hr_employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `createdTime` timestamp NULL DEFAULT NULL,
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;



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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `m_hr_holiday`
--

DROP TABLE IF EXISTS `m_hr_holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_hr_holiday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `holDate` timestamp NOT NULL DEFAULT NULL,
  `holName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `m_hr_holiday_pk_1` (`sAccountId`),
  CONSTRAINT `m_hr_holiday_pk_1` FOREIGN KEY (`sAccountId`) REFERENCES `s_account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `FK_m_hr_employee_leave_1` FOREIGN KEY (`username`) REFERENCES `s_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_hr_employee_leave_2` FOREIGN KEY (`leavetypeid`) REFERENCES `m_hr_leave_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_hr_employee_leave`

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;



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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `FK_m_crm_target_list_3` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `m_prj_message`
--

DROP TABLE IF EXISTS `m_prj_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_prj_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(45) unsigned DEFAULT NULL,
  `title` varchar(1000) NOT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `messagehtml` varchar(4000) DEFAULT NULL,
  `posteddate` datetime NOT NULL,
  `posteduser` varchar(45) DEFAULT NULL,
  `projectid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_message_2` (`projectid`),
  CONSTRAINT `FK_m_prj_message_2` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;


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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `FK_m_crm_quote_9` FOREIGN KEY (`assignUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;



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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_title`
--

/*!40000 ALTER TABLE `s_title` DISABLE KEYS */;
INSERT INTO `s_title` VALUES (1,'Mr'),(2,'Mrs'),(3,'Ms'),(4,'Dr'),(5,'Prof');
/*!40000 ALTER TABLE `s_title` ENABLE KEYS */;


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;



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
  CONSTRAINT `FK_m_tracker_version_2` FOREIGN KEY (`createdUser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;





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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `m_ecm_ver_names`
--


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;




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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;



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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schema_version`
--

DROP TABLE IF EXISTS `schema_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schema_version` (
  `version` varchar(32) NOT NULL,
  `applied_on` timestamp NOT NULL DEFAULT NULL,
  `duration` int(11) NOT NULL,
  UNIQUE KEY `version` (`version`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;





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
);
/*!40101 SET character_set_client = @saved_cs_client */;


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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;



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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;



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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `consequence` varchar(45) DEFAULT NULL,
  `probalitity` varchar(45) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `dateraised` datetime DEFAULT NULL,
  `datedue` datetime DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `resolution` varchar(4000) DEFAULT NULL,
  `level` int(10) unsigned DEFAULT NULL,
  `risksource` varchar(45) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m_prj_risk1_1` (`projectid`),
  KEY `FK_m_prj_risk1_2` (`raisedbyuser`),
  KEY `FK_m_prj_risk1_3` (`assigntouser`),
  CONSTRAINT `FK_m_prj_risk1_1` FOREIGN KEY (`projectid`) REFERENCES `m_prj_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk1_2` FOREIGN KEY (`raisedbyuser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_m_prj_risk1_3` FOREIGN KEY (`assigntouser`) REFERENCES `s_user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-05 13:57:59

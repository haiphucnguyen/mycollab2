CREATE TABLE `m_attachment` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(45) NOT NULL,
    `documentpath` VARCHAR(1000) NOT NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `typeid` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_audit_log` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `object_class` VARCHAR(255) NOT NULL,
    `changeset` LONGTEXT NOT NULL,
    `posteddate` DATETIME NOT NULL,
    `posteduser` VARCHAR(45) NULL,
    `createdTime` TIMESTAMP NULL,
    `lastUpdatedTime` TIMESTAMP NULL,
    `sAccountId` INT NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `typeid` INT NOT NULL,
    `module` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_colla_workgroup` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `owner` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `isModerated` BIT NULL,
    `sAccountId` INT NULL,
    `groupname` VARCHAR(255) NULL,
    `accessMode` INT NULL,
    `createdTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_colla_workgroup_comment` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `comment` VARCHAR(4000) NULL,
    `postedUser` VARCHAR(45) NULL,
    `postedDate` DATETIME NULL,
    `messageid` INT NULL,
    `createdTime` TIMESTAMP NULL,
    `lastUpdatedTime` TIMESTAMP NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_colla_workgroup_message` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `message` VARCHAR(4000) NULL,
    `postedUser` VARCHAR(45) NULL,
    `createdTime` DATETIME NULL,
    `workgroupId` INT NULL,
    `title` VARCHAR(45) NULL,
    `m_colla_workgroup_messagecol` VARCHAR(400) NULL,
    `lastUpdatedDate` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_colla_workgroup_user` (
    `workgroup_id` INT NOT NULL,
    `username` VARCHAR(45) NOT NULL,
    `joinedDate` DATETIME NULL,
    `isApproved` BIT NULL,
    `id` INT NOT NULL AUTO_INCREMENT,
    `role` INT NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_comment` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `comment` VARCHAR(4000) NULL,
    `createdUser` VARCHAR(45) NOT NULL,
    `createdTime` DATETIME NOT NULL,
    `type` VARCHAR(45) NULL,
    `typeId` INT NULL,
    `sAccountId` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_account` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `accountName` VARCHAR(255) NOT NULL,
    `website` VARCHAR(255) NULL,
    `phoneOffice` VARCHAR(45) NULL,
    `fax` VARCHAR(45) NULL,
    `alternatePhone` VARCHAR(45) NULL,
    `annualRevenue` VARCHAR(45) NULL,
    `billingAddress` VARCHAR(255) NULL,
    `city` VARCHAR(100) NULL,
    `postalCode` VARCHAR(45) NULL,
    `description` VARCHAR(2000) NULL,
    `state` VARCHAR(45) NULL,
    `email` VARCHAR(45) NULL,
    `ownership` VARCHAR(255) NULL,
    `shippingAddress` VARCHAR(255) NULL,
    `shippingCity` VARCHAR(100) NULL,
    `shippingPostalCode` VARCHAR(45) NULL,
    `shippingState` VARCHAR(45) NULL,
    `numemployees` INT UNSIGNED NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `type` VARCHAR(45) NULL,
    `industry` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    `billingCountry` VARCHAR(45) NULL,
    `shippingCountry` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_accounts_contacts` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `accountId` INT UNSIGNED NOT NULL,
    `contactId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_accounts_leads` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `accountId` INT UNSIGNED NOT NULL,
    `leadId` INT UNSIGNED NOT NULL,
    `createTime` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_call` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `subject` VARCHAR(1000) NOT NULL,
    `startDate` DATETIME NULL,
    `durationInSeconds` INT NULL,
    `calltype` VARCHAR(45) NULL,
    `type` VARCHAR(45) NULL,
    `typeid` INT NULL,
    `lastUpdatedTime` DATETIME NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `assignUser` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `contactId` INT UNSIGNED NULL,
    `sAccountId` INT NOT NULL,
    `status` VARCHAR(45) NULL,
    `purpose` VARCHAR(45) NULL,
    `result` VARCHAR(4000) NULL,
    `isClosed` BIT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_campaign` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `campaignName` VARCHAR(255) NOT NULL,
    `startDate` DATETIME NULL,
    `endDate` DATETIME NULL,
    `currencyId` INT NULL,
    `impressionnote` VARCHAR(255) NULL,
    `budget` DECIMAL(10,0) NULL,
    `actualCost` DECIMAL(10,0) NULL,
    `expectedRevenue` DECIMAL(10,0) NULL,
    `expectedCost` DECIMAL(10,0) NULL,
    `objective` VARCHAR(2000) NULL,
    `description` VARCHAR(2000) NULL,
    `impression` INT UNSIGNED NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `status` VARCHAR(45) NULL,
    `type` VARCHAR(45) NULL,
    `assignUser` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_campaigns_accounts` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `campaignId` INT UNSIGNED NOT NULL,
    `accountId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_campaigns_contacts` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `campaignId` INT UNSIGNED NOT NULL,
    `contactId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_campaigns_leads` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `campaignId` INT UNSIGNED NOT NULL,
    `leadId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_case` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `priority` VARCHAR(45) NULL,
    `status` VARCHAR(45) NULL,
    `type` VARCHAR(45) NULL,
    `subject` VARCHAR(255) NOT NULL,
    `description` VARCHAR(4000) NULL,
    `resolution` VARCHAR(4000) NULL,
    `accountId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    `reason` VARCHAR(45) NULL,
    `origin` VARCHAR(45) NULL,
    `email` VARCHAR(45) NULL,
    `phonenumber` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_contact` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `prefix` VARCHAR(45) NULL  DEFAULT '',
    `firstname` VARCHAR(45) NULL  DEFAULT '',
    `lastname` VARCHAR(45) NOT NULL,
    `leadSource` VARCHAR(45) NULL,
    `campaignId` INT UNSIGNED NULL,
    `isCallable` BIT NULL,
    `officePhone` VARCHAR(45) NULL,
    `mobile` VARCHAR(45) NULL,
    `homePhone` VARCHAR(45) NULL,
    `otherPhone` VARCHAR(45) NULL,
    `fax` VARCHAR(45) NULL,
    `birthday` DATE NULL,
    `assistant` VARCHAR(100) NULL,
    `primAddress` VARCHAR(255) NULL,
    `primCity` VARCHAR(255) NULL,
    `primState` VARCHAR(255) NULL,
    `primPostalCode` VARCHAR(45) NULL,
    `primCountry` VARCHAR(45) NULL,
    `description` VARCHAR(2000) NULL,
    `title` VARCHAR(100) NULL,
    `assistantPhone` VARCHAR(45) NULL,
    `email` VARCHAR(255) NULL,
    `department` VARCHAR(255) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `otherAddress` VARCHAR(255) NULL,
    `otherCity` VARCHAR(255) NULL,
    `otherState` VARCHAR(255) NULL,
    `otherPostalCode` VARCHAR(45) NULL,
    `otherCountry` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_contacts_cases` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `contactId` INT UNSIGNED NOT NULL,
    `caseId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_contacts_opportunities` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contactId` INT UNSIGNED NOT NULL,
    `opportunityId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_contract` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contractname` VARCHAR(255) NOT NULL,
    `status` VARCHAR(45) NULL,
    `code` VARCHAR(45) NULL,
    `accountid` INT UNSIGNED NULL,
    `opportunityid` INT UNSIGNED NULL,
    `currencyid` INT NULL,
    `customersigneddate` DATETIME NULL,
    `companysigneddate` DATETIME NULL,
    `type` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `startdate` DATETIME NULL,
    `enddate` DATETIME NULL,
    `contractvalue` DECIMAL(10,0) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_customer` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NULL,
    `firstname` VARCHAR(45) NULL,
    `lastname` VARCHAR(45) NOT NULL,
    `leadsource` INT UNSIGNED NULL,
    `assignUser` VARCHAR(45) NULL,
    `department` VARCHAR(255) NULL,
    `officePhone` VARCHAR(45) NULL,
    `mobile` VARCHAR(45) NULL,
    `homePhone` VARCHAR(45) NULL,
    `assisstant` VARCHAR(80) NULL,
    `assisstantPhone` VARCHAR(45) NULL,
    `birthday` DATE NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_industry` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_lead` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `campaignId` INT UNSIGNED NULL,
    `leadSourceDesc` VARCHAR(255) NULL,
    `statusDesc` VARCHAR(255) NULL,
    `referredBy` VARCHAR(100) NULL,
    `prefixName` VARCHAR(45) NULL,
    `firstname` VARCHAR(45) NULL,
    `lastname` VARCHAR(45) NOT NULL,
    `accountName` VARCHAR(100) NULL,
    `title` VARCHAR(100) NULL,
    `department` VARCHAR(100) NULL,
    `isCallable` BIT NULL,
    `officePhone` VARCHAR(45) NULL,
    `homePhone` VARCHAR(45) NULL,
    `mobile` VARCHAR(45) NULL,
    `otherPhone` VARCHAR(45) NULL,
    `fax` VARCHAR(45) NULL,
    `primAddress` VARCHAR(255) NULL,
    `primState` VARCHAR(45) NULL,
    `primCity` VARCHAR(45) NULL,
    `primPostalCode` VARCHAR(45) NULL,
    `primCountry` VARCHAR(45) NULL,
    `description` VARCHAR(2000) NULL,
    `email` VARCHAR(255) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `status` VARCHAR(45) NULL,
    `source` VARCHAR(45) NULL,
    `website` VARCHAR(255) NULL,
    `otherAddress` VARCHAR(255) NULL,
    `otherState` VARCHAR(45) NULL,
    `otherCity` VARCHAR(45) NULL,
    `otherPostalCode` VARCHAR(45) NULL,
    `otherCountry` VARCHAR(45) NULL,
    `industry` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    `noEmployees` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_meeting` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `subject` VARCHAR(1000) NULL,
    `status` VARCHAR(45) NULL,
    `type` VARCHAR(45) NULL,
    `typeid` INT UNSIGNED NULL,
    `startDate` DATETIME NULL,
    `endDate` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `location` VARCHAR(100) NULL,
    `description` VARCHAR(4000) NULL,
    `isRecurrence` TINYINT NULL,
    `recurrenceType` VARCHAR(45) NULL,
    `recurrenceStartDate` DATETIME NULL,
    `recurrenceEndDate` DATETIME NULL,
    `contactType` VARCHAR(45) NULL,
    `contactTypeId` INT UNSIGNED NULL,
    `isClosed` BIT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_note` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `subject` VARCHAR(255) NOT NULL,
    `note` VARCHAR(4000) NULL,
    `type` VARCHAR(45) NULL,
    `typeid` INT UNSIGNED NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_opportunities_contacts` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `opportunityId` INT UNSIGNED NOT NULL,
    `contactId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_opportunities_leads` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `opportunityId` INT UNSIGNED NOT NULL,
    `leadId` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_opportunity` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `opportunityName` VARCHAR(255) NOT NULL,
    `currencyid` INT UNSIGNED NULL,
    `accountid` INT UNSIGNED NOT NULL,
    `amount` INT UNSIGNED NULL,
    `type` VARCHAR(45) NULL,
    `source` VARCHAR(45) NULL,
    `expectedClosedDate` DATETIME NULL,
    `campaignid` INT UNSIGNED NULL,
    `nextStep` VARCHAR(255) NULL,
    `probability` INT UNSIGNED NULL,
    `description` VARCHAR(2000) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `opportunityType` VARCHAR(45) NULL,
    `salesStage` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_opportunity_type` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_product` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `productname` VARCHAR(255) NOT NULL,
    `status` VARCHAR(45) NULL,
    `accountid` INT UNSIGNED NULL,
    `website` VARCHAR(255) NULL,
    `quantity` INT UNSIGNED NULL,
    `serialnumber` VARCHAR(45) NULL,
    `assessnumber` VARCHAR(45) NULL,
    `contactid` INT UNSIGNED NULL,
    `supportstartdate` DATETIME NULL,
    `supportenddate` DATETIME NULL,
    `salesdate` DATETIME NULL,
    `unitprice` DOUBLE NULL,
    `description` VARCHAR(4000) NULL,
    `producturl` VARCHAR(255) NULL,
    `supportcontact` VARCHAR(255) NULL,
    `supportterm` VARCHAR(45) NULL,
    `supportdesc` VARCHAR(100) NULL,
    `cost` DOUBLE NULL,
    `listprice` DOUBLE NULL,
    `groupid` INT UNSIGNED NULL,
    `tax` DOUBLE NULL,
    `taxClass` VARCHAR(45) NULL,
    `mftNumber` VARCHAR(45) NULL,
    `vendorPartNumber` VARCHAR(45) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_product_catalog` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `url` VARCHAR(255) NULL,
    `taxclass` VARCHAR(45) NULL,
    `mft_partnumber` VARCHAR(45) NULL,
    `vendor_partnumber` VARCHAR(45) NULL,
    `currencyid` INT NULL,
    `cost` DOUBLE NULL,
    `listprice` DOUBLE NULL,
    `discountprice` DOUBLE NULL,
    `pricing_formula` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `date_available` DATETIME NULL,
    `availability` VARCHAR(45) NULL,
    `quantity_in_stock` VARCHAR(45) NULL,
    `support_name` VARCHAR(100) NULL,
    `support_contact` VARCHAR(100) NULL,
    `support_desc` VARCHAR(100) NULL,
    `support_term` VARCHAR(45) NULL,
    `productname` VARCHAR(100) NULL,
    `sAccountId` INT NOT NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_quote` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `subject` VARCHAR(255) NOT NULL,
    `quotestage` VARCHAR(45) NULL,
    `opportunityid` INT UNSIGNED NULL,
    `validuntil` DATETIME NULL,
    `shipping` VARCHAR(255) NULL,
    `billaccount` INT UNSIGNED NOT NULL,
    `billcontact` INT UNSIGNED NULL,
    `shipaccount` INT UNSIGNED NULL,
    `shipcontact` INT UNSIGNED NULL,
    `billingaddress` VARCHAR(255) NOT NULL,
    `billingcity` VARCHAR(255) NULL,
    `billingstate` VARCHAR(45) NULL,
    `billingpostalcode` VARCHAR(45) NULL,
    `billingcountry` VARCHAR(45) NULL,
    `shippingaddress` VARCHAR(255) NOT NULL,
    `shippingcity` VARCHAR(255) NULL,
    `shippingstate` VARCHAR(45) NULL,
    `shippingpostalcode` VARCHAR(45) NULL,
    `shippingcountry` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `paymentterm` VARCHAR(45) NULL,
    `originalpodate` DATETIME NULL,
    `purchaseordernum` VARCHAR(45) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_quote_group_product` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `groupname` VARCHAR(100) NULL,
    `groupstate` VARCHAR(100) NULL,
    `tax` VARCHAR(45) NULL,
    `shipping` VARCHAR(45) NULL,
    `quoteid` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_sales_stage` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_source_type` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_target` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `prefixname` VARCHAR(45) NULL,
    `firstname` VARCHAR(45) NULL,
    `lastname` VARCHAR(45) NOT NULL,
    `title` VARCHAR(255) NULL,
    `department` VARCHAR(255) NULL,
    `birthday` DATE NULL,
    `accountname` VARCHAR(255) NULL,
    `isCallable` BIT NULL,
    `officePhone` VARCHAR(45) NULL,
    `mobile` VARCHAR(45) NULL,
    `homePhone` VARCHAR(45) NULL,
    `otherPhone` VARCHAR(45) NULL,
    `fax` VARCHAR(45) NULL,
    `assistant` VARCHAR(255) NULL,
    `assistantPhone` VARCHAR(45) NULL,
    `primaryAddress` VARCHAR(255) NULL,
    `primaryCity` VARCHAR(45) NULL,
    `primaryState` VARCHAR(45) NULL,
    `primaryPostal` VARCHAR(45) NULL,
    `primaryCountryId` INT NULL,
    `description` VARCHAR(255) NULL,
    `email` VARCHAR(255) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_target_list` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `type` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `assignUser` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_task` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `subject` VARCHAR(1000) NOT NULL,
    `startdate` DATETIME NULL,
    `duedate` DATETIME NULL,
    `contactId` INT UNSIGNED NULL,
    `typeid` INT UNSIGNED NULL,
    `description` VARCHAR(4000) NULL,
    `createdTime` DATETIME NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `status` VARCHAR(45) NULL,
    `assignUser` VARCHAR(45) NULL,
    `priority` VARCHAR(45) NULL,
    `type` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    `isClosed` BIT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_task_status` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_crm_task_type` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_ecm_transaction` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contentpath` VARCHAR(400) NOT NULL,
    `owner` VARCHAR(45) NULL,
    `action` VARCHAR(45) NOT NULL,
    `actiontime` DATETIME NOT NULL,
    `contenttype` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_friend_friend` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `friendname` VARCHAR(45) NOT NULL,
    `createdDate` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_friend_request` (
    `id` INT NOT NULL,
    `requestFrom` VARCHAR(45) NOT NULL,
    `requestTo` VARCHAR(45) NOT NULL,
    `requestDate` DATETIME NOT NULL,
    `message` VARCHAR(1000) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_group` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `owner` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `createddate` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_company` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `address1` VARCHAR(255) NULL,
    `address2` VARCHAR(255) NULL,
    `city` VARCHAR(100) NULL,
    `comments` VARCHAR(4000) NULL,
    `companyname` VARCHAR(45) NOT NULL,
    `fax` VARCHAR(45) NULL,
    `phone` VARCHAR(45) NULL,
    `state` VARCHAR(100) NULL,
    `taxid` VARCHAR(45) NULL,
    `zipcode` VARCHAR(45) NULL,
    `countryid` INT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_company_policy` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `details` VARCHAR(4000) NULL,
    `applyTo` VARCHAR(1000) NULL,
    `createdUser` VARCHAR(45) NULL,
    `status` VARCHAR(100) NOT NULL,
    `lastVisionDate` TIMESTAMP NULL,
    `policyDocPath` VARCHAR(4000) NOT NULL,
    `sAccountId` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_division` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(4000) NULL,
    `location` VARCHAR(255) NULL,
    `division` VARCHAR(255) NOT NULL,
    `companyid` INT NOT NULL,
    `divisionparentid` INT NULL,
    `divisiontypeid` INT NULL,
    `telephone` VARCHAR(45) NULL,
    `fax` VARCHAR(45) NULL,
    `divisionheadid` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_divisiontype` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_education` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `course` VARCHAR(255) NOT NULL,
    `durationinmonth` INT NULL,
    `institute` VARCHAR(255) NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_contact` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `city` VARCHAR(45) NULL,
    `hometelephone` VARCHAR(45) NULL,
    `otheremail` VARCHAR(45) NULL,
    `state` VARCHAR(45) NULL,
    `street1` VARCHAR(45) NULL,
    `street2` VARCHAR(45) NULL,
    `workemail` VARCHAR(45) NULL,
    `worktelephone` VARCHAR(45) NULL,
    `zipcode` VARCHAR(45) NULL,
    `employeeid` INT NOT NULL,
    `countryid` INT NULL,
    `mobile` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_education` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `educationid` INT NOT NULL,
    `employeeid` INT NOT NULL,
    `startdate` DATETIME NOT NULL,
    `enddate` DATETIME NOT NULL,
    `score` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_emergency_contact` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `hometelephone` VARCHAR(45) NULL,
    `mobiletelephone` VARCHAR(45) NULL,
    `relname` VARCHAR(255) NOT NULL,
    `relationship` VARCHAR(255) NULL,
    `worktelephone` VARCHAR(45) NULL,
    `employeeid` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_family` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `relationship` VARCHAR(45) NOT NULL,
    `dob` VARCHAR(45) NULL,
    `employeeid` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_immigration` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `comments` VARCHAR(255) NULL,
    `expireddate` DATETIME NULL,
    `issuesdate` DATETIME NULL,
    `no` VARCHAR(45) NULL,
    `type` INT UNSIGNED NULL,
    `citizenship` INT NULL,
    `employeeid` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_job` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `joineddate` DATETIME NULL,
    `jobtitleid` INT NULL,
    `employeeid` INT NOT NULL,
    `employmentstatusid` INT NULL,
    `divisionid` INT NULL,
    `contract_startdate` DATETIME NOT NULL,
    `contract_enddate` DATETIME NOT NULL,
    `comments` VARCHAR(2000) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_language` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `employeeid` INT NOT NULL,
    `languageid` INT NOT NULL,
    `levelid` INT UNSIGNED NOT NULL,
    `comment` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_leave` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `fromdate` DATETIME NOT NULL,
    `todate` DATETIME NOT NULL,
    `leavetypeid` INT NULL,
    `username` VARCHAR(45) NOT NULL,
    `reasonToLeave` VARCHAR(4000) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_licenses` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `startDate` DATETIME NULL,
    `endDate` DATETIME NULL,
    `licenseid` INT NOT NULL,
    `employeeid` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_payment` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `employeeid` INT NOT NULL,
    `paygradeid` INT NOT NULL,
    `salary` DOUBLE NOT NULL,
    `comment` VARCHAR(255) NULL,
    `startdate` DATETIME NULL,
    `enddate` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_personal` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `ssn` VARCHAR(45) NULL,
    `sin` VARCHAR(45) NULL,
    `countryid` INT NULL,
    `gender` INT UNSIGNED NULL,
    `maritalstatus` INT UNSIGNED NULL,
    `birthday` DATETIME NULL,
    `employeeid` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_skill` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `comments` VARCHAR(45) NULL,
    `yearsofexperience` INT NULL,
    `skillid` INT NOT NULL,
    `employeeid` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employee_work_experience` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `comments` VARCHAR(45) NULL,
    `employer` VARCHAR(45) NULL,
    `enddate` DATETIME NULL,
    `jobtitle` VARCHAR(45) NULL,
    `startdate` DATETIME NULL,
    `employeeid` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_employment_status` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(2000) NULL,
    `status` VARCHAR(255) NOT NULL,
    `code` VARCHAR(45) NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_holiday` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `holDate` TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
    `holName` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_job_categories` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `paygradeid` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_jobtitle` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(2000) NULL,
    `title` VARCHAR(255) NOT NULL,
    `code` VARCHAR(100) NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_language` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `language` VARCHAR(100) NOT NULL,
    `code` VARCHAR(45) NOT NULL,
    `description` VARCHAR(2000) NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_language_level` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `level` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_leave_type` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(4000) NULL,
    `name` VARCHAR(45) NOT NULL,
    `maximumLeave` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_licenses` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(2000) NULL,
    `license` VARCHAR(255) NOT NULL,
    `code` VARCHAR(45) NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_location` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `address` VARCHAR(45) NOT NULL,
    `city` VARCHAR(45) NULL,
    `comments` VARCHAR(2000) NULL,
    `fax` VARCHAR(45) NULL,
    `locName` VARCHAR(100) NOT NULL,
    `phone` VARCHAR(45) NULL,
    `state` VARCHAR(45) NULL,
    `zipcode` VARCHAR(45) NULL,
    `countryid` INT NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_pay_grades` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `maximumsalary` DOUBLE NULL,
    `minimumsalary` DOUBLE NULL,
    `code` VARCHAR(255) NOT NULL,
    `currencyid` INT NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_hr_skill` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(2000) NULL,
    `skill` VARCHAR(255) NOT NULL,
    `code` VARCHAR(45) NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_monitor_item` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user` VARCHAR(45) NOT NULL,
    `monitor_date` DATETIME NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `typeid` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_pm_messages` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `sentFrom` VARCHAR(45) NOT NULL,
    `sentTo` VARCHAR(45) NULL,
    `isSent` BIT NULL  DEFAULT 0,
    `isRead` BIT NULL  DEFAULT 0,
    `sentDate` DATETIME NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `body` VARCHAR(4000) NOT NULL,
    `isCopy` BIT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_pm_sent_messages` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `sentFrom` VARCHAR(45) NULL,
    `sentTo` VARCHAR(45) NULL,
    `owner` VARCHAR(45) NULL,
    `isRead` BIT NULL,
    `sentDate` DATETIME NULL,
    `title` VARCHAR(255) NULL,
    `body` VARCHAR(4000) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_member` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `projectId` INT UNSIGNED NOT NULL,
    `joinDate` DATETIME NOT NULL,
    `projectRoleId` INT NULL,
    `isAdmin` BIT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_message` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(1000) NOT NULL,
    `message` TEXT NULL,
    `posteddate` DATETIME NOT NULL,
    `posteduser` VARCHAR(45) NULL,
    `projectid` INT UNSIGNED NOT NULL,
    `category` VARCHAR(45) NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `sAccountId` INT NOT NULL,
    `isStick` BIT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_milestone` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT NULL,
    `startdate` DATETIME NULL,
    `enddate` DATETIME NULL,
    `owner` VARCHAR(45) NOT NULL,
    `flag` VARCHAR(45) NULL,
    `projectid` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `sAccountId` INT NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_problem` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `issuename` VARCHAR(400) NOT NULL,
    `description` TEXT NULL,
    `projectid` INT UNSIGNED NOT NULL,
    `raisedbyuser` VARCHAR(45) NULL,
    `assigntouser` VARCHAR(45) NULL,
    `impact` VARCHAR(45) NULL,
    `priority` VARCHAR(45) NULL,
    `status` VARCHAR(45) NULL,
    `dateraised` DATETIME NULL,
    `datedue` DATETIME NULL,
    `actualstartdate` DATETIME NULL,
    `actualenddate` DATETIME NULL,
    `level` DOUBLE UNSIGNED NULL,
    `resolution` VARCHAR(4000) NULL,
    `state` VARCHAR(45) NULL,
    `problemsource` VARCHAR(45) NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `type` VARCHAR(45) NULL,
    `typeid` INT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_project` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `owner` VARCHAR(45) NULL,
    `account` INT UNSIGNED NULL,
    `priority` VARCHAR(45) NULL,
    `shortname` VARCHAR(45) NOT NULL,
    `planStartDate` DATETIME NULL,
    `planEndDate` DATETIME NULL,
    `targetBudget` DOUBLE NULL,
    `homePage` VARCHAR(255) NULL,
    `actualBudget` DOUBLE NULL,
    `projectType` VARCHAR(45) NULL,
    `projectStatus` VARCHAR(45) NOT NULL,
    `description` TEXT NULL,
    `defaultBillingRate` DOUBLE NULL,
    `actualStartDate` DATETIME NULL,
    `actualEndDate` DATETIME NULL,
    `defaultOvertimeBillingRate` DOUBLE NULL,
    `currencyid` INT NULL,
    `progress` DOUBLE NULL,
    `sAccountId` INT NOT NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_resource` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `resourcename` VARCHAR(255) NOT NULL,
    `bookingtype` VARCHAR(45) NOT NULL,
    `notes` VARCHAR(1000) NULL,
    `username` VARCHAR(45) NULL  DEFAULT '0',
    `projectid` INT UNSIGNED NOT NULL  DEFAULT 0,
    `allocation` INT UNSIGNED NULL,
    `startdate` DATETIME NULL,
    `enddate` DATETIME NULL,
    `workingtimerate` DOUBLE NULL,
    `overtimerate` DOUBLE NULL,
    `roleid` INT UNSIGNED NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_risk` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `riskname` VARCHAR(400) NOT NULL,
    `description` TEXT NULL,
    `projectid` INT UNSIGNED NOT NULL,
    `raisedbyuser` VARCHAR(45) NULL,
    `assigntouser` VARCHAR(45) NULL,
    `consequence` VARCHAR(45) NULL,
    `probalitity` VARCHAR(45) NULL,
    `status` VARCHAR(45) NULL,
    `dateraised` DATETIME NULL,
    `datedue` DATETIME NULL,
    `response` VARCHAR(255) NULL,
    `resolution` VARCHAR(4000) NULL,
    `level` DOUBLE UNSIGNED NULL,
    `source` VARCHAR(45) NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `type` VARCHAR(45) NULL,
    `typeid` INT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_role` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `rolename` VARCHAR(255) NOT NULL,
    `description` TEXT NULL,
    `sAccountId` INT NOT NULL,
    `projectid` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_role_permission` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `roleid` INT UNSIGNED NOT NULL,
    `roleVal` TEXT NOT NULL,
    `projectid` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_standup` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `sAccountId` INT NOT NULL,
    `projectId` INT UNSIGNED NOT NULL,
    `whatlastday` TEXT NULL,
    `whattoday` TEXT NULL,
    `whatproblem` TEXT NULL,
    `forday` DATETIME NOT NULL,
    `logBy` VARCHAR(45) NOT NULL,
    `createdTime` DATETIME NOT NULL,
    `lastUpdatedTime` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_task` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `taskname` VARCHAR(255) NOT NULL,
    `percentagecomplete` DOUBLE NOT NULL,
    `startdate` DATETIME NULL,
    `enddate` DATETIME NULL,
    `priority` VARCHAR(45) NULL,
    `duration` DOUBLE NULL,
    `isestimated` BIT NULL,
    `projectid` INT UNSIGNED NOT NULL,
    `deadline` DATETIME NULL,
    `notes` TEXT NULL,
    `taskindex` INT UNSIGNED NULL,
    `actualStartDate` DATETIME NULL,
    `actualEndDate` DATETIME NULL,
    `tasklistid` INT UNSIGNED NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `assignUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `status` VARCHAR(45) NULL,
    `logBy` VARCHAR(45) NULL,
    `taskkey` INT NULL,
    `originalEstimate` DOUBLE NULL,
    `remainEstimate` DOUBLE NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_task_list` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT NULL,
    `projectid` INT UNSIGNED NOT NULL,
    `createdTime` DATETIME NULL,
    `lastUpdatedTime` DATETIME NULL,
    `sAccountId` INT NULL,
    `milestoneId` INT NULL,
    `owner` VARCHAR(45) NULL,
    `groupIndex` INT NULL,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_task_resource` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `taskid` INT UNSIGNED NOT NULL,
    `resourceid` INT UNSIGNED NOT NULL,
    `unit` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_prj_time_logging` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `projectId` INT UNSIGNED NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `typeid` INT NOT NULL,
    `note` TEXT NULL,
    `logValue` DOUBLE NOT NULL,
    `loguser` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_rss_agreegator` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `uri` VARCHAR(255) NOT NULL,
    `description` VARCHAR(2000) NULL,
    `username` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_social_message` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `message` VARCHAR(1000) NULL,
    `postedDate` DATETIME NULL,
    `postedUser` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_social_user_message` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user` VARCHAR(45) NOT NULL,
    `messages` TEXT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tag_relationship` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contentpath` VARCHAR(1000) NOT NULL,
    `tagtaxonomy` INT UNSIGNED NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tag_tag` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `tag` VARCHAR(45) NOT NULL,
    `taggroup` VARCHAR(45) NULL,
    `slug` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tag_taxonomy` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `tagid` INT UNSIGNED NOT NULL,
    `parent` INT UNSIGNED NULL,
    `count` INT UNSIGNED NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_todo` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `summary` VARCHAR(255) NOT NULL,
    `statusId` INT UNSIGNED NOT NULL,
    `owner` VARCHAR(45) NOT NULL,
    `description` VARCHAR(2000) NULL,
    `startdate` DATETIME NULL,
    `duedate` DATETIME NULL,
    `sender` VARCHAR(45) NULL,
    `categoryId` INT UNSIGNED NOT NULL,
    `location` VARCHAR(255) NULL,
    `reminder` INT UNSIGNED NULL,
    `percentageComplete` DECIMAL(10,0) NULL,
    `completeDate` DATETIME NULL,
    `dismiss` BIT NULL,
    `enterdatetime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_todo_category` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_todo_status` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_bug` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `summary` VARCHAR(1000) NOT NULL,
    `detail` VARCHAR(4000) NULL,
    `assignuser` VARCHAR(45) NULL,
    `createdTime` DATETIME NULL,
    `logby` VARCHAR(45) NULL,
    `severity` VARCHAR(45) NULL,
    `priority` VARCHAR(45) NULL,
    `lastUpdatedTime` DATETIME NULL,
    `status` VARCHAR(45) NOT NULL,
    `duedate` DATETIME NULL,
    `environment` VARCHAR(4000) NULL,
    `resolution` VARCHAR(45) NULL,
    `cus_int_01` INT UNSIGNED NULL,
    `cus_int_02` INT UNSIGNED NULL,
    `cus_int_03` INT UNSIGNED NULL,
    `cus_int_04` INT UNSIGNED NULL,
    `cus_int_05` INT UNSIGNED NULL,
    `cus_int_06` INT UNSIGNED NULL,
    `cus_int_07` INT UNSIGNED NULL,
    `cus_int_08` INT UNSIGNED NULL,
    `cus_int_09` INT UNSIGNED NULL,
    `cus_int_10` INT UNSIGNED NULL,
    `cus_str_01` VARCHAR(255) NULL,
    `cus_str_02` VARCHAR(255) NULL,
    `cus_str_03` VARCHAR(255) NULL,
    `cus_str_04` VARCHAR(255) NULL,
    `cus_str_05` VARCHAR(255) NULL,
    `cus_time_01` DATETIME NULL,
    `cus_time_02` DATETIME NULL,
    `cus_time_03` DATETIME NULL,
    `cus_time_04` DATETIME NULL,
    `cus_dbl_01` DOUBLE NULL,
    `cus_dbl_02` DOUBLE NULL,
    `cus_dbl_03` DOUBLE NULL,
    `projectid` INT UNSIGNED NOT NULL,
    `resolveddate` DATETIME NULL,
    `description` VARCHAR(4000) NULL,
    `estimateTime` DOUBLE NULL,
    `estimateRemainTime` DOUBLE NULL,
    `sAccountId` INT NOT NULL,
    `milestoneId` INT NULL,
    `bugkey` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_bug_related_item` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `bugid` INT UNSIGNED NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `typeid` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_component` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `projectid` INT UNSIGNED NOT NULL,
    `componentname` VARCHAR(255) NOT NULL,
    `userlead` VARCHAR(45) NULL,
    `description` VARCHAR(4000) NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `lastUpdatedTime` DATETIME NULL,
    `createdTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_history` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `summary` VARCHAR(1000) NULL,
    `detail` VARCHAR(4000) NULL,
    `assignuser` VARCHAR(45) NULL,
    `posteddate` DATETIME NULL,
    `logby` VARCHAR(45) NULL,
    `severity` INT UNSIGNED NULL,
    `priority` INT UNSIGNED NULL,
    `status` INT UNSIGNED NULL,
    `environment` VARCHAR(4000) NULL,
    `affectedversions` VARCHAR(400) NULL,
    `fixonversions` VARCHAR(400) NULL,
    `resolution` INT UNSIGNED NULL,
    `relatedbug` INT UNSIGNED NOT NULL,
    `cus_int_01` INT UNSIGNED NULL,
    `cus_int_02` INT UNSIGNED NULL,
    `cus_int_03` INT UNSIGNED NULL,
    `cus_int_04` INT UNSIGNED NULL,
    `cus_int_05` INT UNSIGNED NULL,
    `cus_int_06` INT UNSIGNED NULL,
    `cus_int_07` INT UNSIGNED NULL,
    `cus_int_08` INT UNSIGNED NULL,
    `cus_int_09` INT UNSIGNED NULL,
    `cus_int_10` INT UNSIGNED NULL,
    `cus_str_01` VARCHAR(255) NULL,
    `cus_str_02` VARCHAR(255) NULL,
    `cus_str_03` VARCHAR(255) NULL,
    `cus_str_04` VARCHAR(255) NULL,
    `cus_str_05` VARCHAR(255) NULL,
    `cus_time_01` DATETIME NULL,
    `cus_time_02` DATETIME NULL,
    `cus_time_03` DATETIME NULL,
    `cus_time_04` DATETIME NULL,
    `cus_dbl_01` DOUBLE NULL,
    `cus_dbl_02` DOUBLE NULL,
    `cus_dbl_03` DOUBLE NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_metadata` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `projectid` INT UNSIGNED NOT NULL,
    `xmlstring` LONGTEXT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_query` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `queryname` VARCHAR(255) NOT NULL,
    `sharetype` INT UNSIGNED NOT NULL,
    `createddate` DATETIME NULL,
    `updateddate` DATETIME NULL,
    `owner` VARCHAR(45) NULL,
    `querytext` VARCHAR(4000) NULL,
    `description` VARCHAR(1000) NULL,
    `projectid` INT UNSIGNED NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_related_bug` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `bugid` INT UNSIGNED NOT NULL,
    `relatedid` INT UNSIGNED NOT NULL,
    `relatetype` DECIMAL(10,0) NOT NULL,
    `comment` VARCHAR(4000) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `m_tracker_version` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `projectid` INT UNSIGNED NOT NULL,
    `description` VARCHAR(4000) NULL,
    `duedate` DATETIME NULL,
    `versionname` VARCHAR(45) NOT NULL,
    `createdUser` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `lastUpdatedTime` DATETIME NULL,
    `createdTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_account` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `createdTime` TIMESTAMP NULL,
    `billingPlanId` INT NULL,
    `accountName` VARCHAR(100) NULL,
    `status` VARCHAR(45) NULL,
    `paymentMethod` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_account_settings` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `sAccountId` INT NOT NULL,
    `logoPath` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_activitystream` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `sAccountId` INT NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `typeId` INT NOT NULL,
    `createdTime` DATETIME NULL,
    `action` VARCHAR(45) NULL,
    `createdUser` VARCHAR(45) NULL,
    `module` VARCHAR(45) NULL,
    `nameField` TEXT NULL,
    `extraTypeId` INT UNSIGNED NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_billing_plan` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `billingType` VARCHAR(45) NULL,
    `numUsers` INT NULL,
    `volume` INT NULL,
    `numProjects` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_chat_server` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(255) NOT NULL,
    `host` VARCHAR(255) NOT NULL,
    `port` INT UNSIGNED NOT NULL,
    `account_suffix` VARCHAR(45) NULL  DEFAULT '',
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_country` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `countryname` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_currency` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `isocode` VARCHAR(45) NOT NULL,
    `symbol` VARCHAR(45) NOT NULL  DEFAULT '$',
    `conversionrate` DOUBLE NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_marital_status` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_module` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `modulename` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `uri` VARCHAR(255) NOT NULL,
    `status` BIT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `sortindex` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_relay_email_notification` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `sAccountId` INT NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `typeid` INT UNSIGNED NOT NULL,
    `action` VARCHAR(45) NOT NULL,
    `changeBy` VARCHAR(45) NOT NULL,
    `changeComment` TEXT NULL,
    `extraTypeId` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_relay_mail` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `bodyContent` TEXT NOT NULL,
    `recipients` TEXT NOT NULL,
    `subject` TEXT NOT NULL,
    `sAccountId` INT NOT NULL,
    `fromName` VARCHAR(255) NOT NULL,
    `fromEmail` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_report_bug_issue` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `sAccountId` INT NULL,
    `username` VARCHAR(45) NULL,
    `userAgent` TEXT NULL,
    `errorTrace` TEXT NOT NULL,
    `ipaddress` VARCHAR(15) NULL,
    `country_code` VARCHAR(5) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_role_permission` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `roleid` INT NOT NULL,
    `roleVal` TEXT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_roles` (
    `rolename` VARCHAR(45) NOT NULL,
    `description` VARCHAR(45) NULL,
    `sAccountId` INT NOT NULL,
    `id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_user` (
    `username` VARCHAR(45) NOT NULL,
    `firstname` VARCHAR(45) NOT NULL,
    `middlename` VARCHAR(45) NULL  DEFAULT '',
    `lastname` VARCHAR(45) NOT NULL,
    `nickname` VARCHAR(45) NULL,
    `dateofbirth` DATETIME NULL,
    `password` TEXT NULL,
    `email` VARCHAR(255) NULL,
    `website` VARCHAR(255) NULL,
    `registeredTime` DATETIME NULL,
    `lastAccessedTime` DATETIME NULL,
    `accountId` INT NOT NULL,
    `company` VARCHAR(255) NULL,
    `timezone` VARCHAR(45) NULL,
    `registrationSource` VARCHAR(100) NULL,
    `isAdmin` BIT NULL,
    `registerStatus` VARCHAR(45) NULL,
    `roleid` INT NULL,
    `language` VARCHAR(45) NULL,
    `country` VARCHAR(45) NULL,
    `workPhone` VARCHAR(20) NULL,
    `homePhone` VARCHAR(20) NULL,
    `facebookAccount` VARCHAR(45) NULL,
    `twitterAccount` VARCHAR(45) NULL,
    `skypeContact` VARCHAR(45) NULL,
    PRIMARY KEY (`username`)
);

CREATE TABLE `s_user_information` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_user_permission` (
    `Id` INT NOT NULL AUTO_INCREMENT,
    `module` VARCHAR(45) NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `hasPermission` VARCHAR(45) NOT NULL,
    `username` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`Id`)
);

CREATE TABLE `s_user_preference` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NULL,
    `lastModuleVisit` VARCHAR(45) NULL,
    `lastAccessedTime` DATETIME NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `s_user_tracking` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `userAgent` TEXT NOT NULL,
    `createdTime` DATETIME NOT NULL,
    `sAccountId` INT NOT NULL,
    PRIMARY KEY (`id`)
);


-- dump data -------------------------------------------




-- Generate script insert data for m_attachment -----------------------------------
ALTER TABLE m_attachment ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (1, 'crm-note', 'crm-note/2/default_user_avatar_48_48.png', '2013-01-22 21:51:00.0', '2013-01-22 21:51:00.0', 2);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (2, 'crm-note', 'crm-note/2/da1589d79676690909670cfe5be92b02.PDF', '2013-01-22 21:51:00.0', '2013-01-22 21:51:00.0', 2);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (3, 'common-comment', 'common-comment/1/exportservice.rar', '2013-01-22 21:51:16.0', '2013-01-22 21:51:16.0', 1);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (4, 'common-comment', 'common-comment/2/administration-1.0.zip', '2013-01-22 21:51:32.0', '2013-01-22 21:51:32.0', 2);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (5, 'project-message', 'project-message/1/da1589d79676690909670cfe5be92b02.PDF', '2013-01-22 22:54:38.0', '2013-01-22 22:54:38.0', 1);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (6, 'common-comment', 'common-comment/7/default_user_avatar_48_48.png', '2013-01-22 22:55:05.0', '2013-01-22 22:55:05.0', 7);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (7, 'common-comment', 'common-comment/9/default_user_avatar_48_48.png', '2013-01-22 23:13:23.0', '2013-01-22 23:13:23.0', 9);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (8, 'common-comment', 'common-comment/10/1449393683ProgrammingAmazonEC2.epub', '2013-01-22 23:14:55.0', '2013-01-22 23:14:55.0', 10);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (10, 'project-bug', 'project-bug/1/da1589d79676690909670cfe5be92b02.PDF', '2013-01-23 01:10:00.0', '2013-01-23 01:10:00.0', 1);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (11, 'common-comment', 'common-comment/11/Bac Kim Thang - Xuan Mai.mp3', '2013-01-23 01:10:28.0', '2013-01-23 01:10:28.0', 11);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (13, 'project-bug', 'project-bug/1/1430238011.pdf', '2013-01-23 09:09:42.0', '2013-01-23 09:09:42.0', 1);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (14, 'common-comment', 'common-comment/13/breadcrumb-1.6.0.zip', '2013-01-23 10:49:59.0', '2013-01-23 10:49:59.0', 13);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (15, 'project-message', 'project-message/2/administration-1.0.zip', '2013-01-23 10:51:01.0', '2013-01-23 10:51:01.0', 2);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (16, 'project-message', 'project-message/2/da1589d79676690909670cfe5be92b02.PDF', '2013-01-23 10:51:01.0', '2013-01-23 10:51:01.0', 2);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (17, 'project-task', 'project-task/1/da1589d79676690909670cfe5be92b02.PDF', '2013-01-23 21:40:02.0', '2013-01-23 21:40:02.0', 1);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (19, 'common-comment', 'common-comment/17/breadcrumb-1.6.0.zip', '2013-01-23 22:12:18.0', '2013-01-23 22:12:18.0', 17);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (20, 'common-comment', 'common-comment/30/mycollab_test.h2.db', '2013-01-30 14:23:13.0', '2013-01-30 14:23:13.0', 30);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (21, 'project-bug', 'project-bug/18/Screen Shot 2013-02-06 at 4.18.11 PM.png', '2013-02-17 22:40:35.0', '2013-02-17 22:40:35.0', 18);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (22, 'common-comment', 'common-comment/45/Screen Shot 2013-02-14 at 5.43.45 PM.png', '2013-02-18 11:06:04.0', '2013-02-18 11:06:04.0', 45);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (23, 'project-bug', 'project-bug/36/image_tour2.jpg', '2013-02-28 18:26:53.0', '2013-02-28 18:26:53.0', 36);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (24, 'project-bug', 'project-bug/36/default_user_avatar_100_100.png', '2013-02-28 23:23:08.0', '2013-02-28 23:23:08.0', 36);
INSERT INTO m_attachment(id, type, documentpath, createdTime, lastUpdatedTime, typeid) VALUES (25, 'project-bug', 'project-bug/44/IMG_4751.JPG', '2013-03-26 11:14:20.0', '2013-03-26 11:14:20.0', 44);
ALTER TABLE m_attachment ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_colla_workgroup_comment -----------------------------------
ALTER TABLE m_colla_workgroup_comment ALTER COLUMN id INT ;
INSERT INTO m_colla_workgroup_comment(id, comment, postedUser, postedDate, messageid, createdTime, lastUpdatedTime) VALUES (1, 'aaa', 'hainguyen', NULL, 1, NULL, NULL);
ALTER TABLE m_colla_workgroup_comment ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_colla_workgroup_message -----------------------------------
ALTER TABLE m_colla_workgroup_message ALTER COLUMN id INT ;
INSERT INTO m_colla_workgroup_message(id, message, postedUser, createdTime, workgroupId, title, m_colla_workgroup_messagecol, lastUpdatedDate) VALUES (1, NULL, 'hainguyen', NULL, 1, NULL, NULL, NULL);
INSERT INTO m_colla_workgroup_message(id, message, postedUser, createdTime, workgroupId, title, m_colla_workgroup_messagecol, lastUpdatedDate) VALUES (2, NULL, 'linhduong', NULL, 1, NULL, NULL, NULL);
ALTER TABLE m_colla_workgroup_message ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_colla_workgroup_user -----------------------------------
ALTER TABLE m_colla_workgroup_user ALTER COLUMN id INT ;
INSERT INTO m_colla_workgroup_user(workgroup_id, username, joinedDate, isApproved, id, role, createdTime, lastUpdatedTime) VALUES (1, 'hainguyen', NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO m_colla_workgroup_user(workgroup_id, username, joinedDate, isApproved, id, role, createdTime, lastUpdatedTime) VALUES (1, 'linhduong', NULL, NULL, 2, NULL, NULL, NULL);
ALTER TABLE m_colla_workgroup_user ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_crm_industry -----------------------------------
ALTER TABLE m_crm_industry ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_industry(id, name) VALUES (1, 'a');
ALTER TABLE m_crm_industry ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_opportunity_type -----------------------------------
ALTER TABLE m_crm_opportunity_type ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_opportunity_type(id, name) VALUES (1, 'Existing Business');
INSERT INTO m_crm_opportunity_type(id, name) VALUES (2, 'New Business');
ALTER TABLE m_crm_opportunity_type ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_sales_stage -----------------------------------
ALTER TABLE m_crm_sales_stage ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_sales_stage(id, name) VALUES (1, 'a');
ALTER TABLE m_crm_sales_stage ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_source_type -----------------------------------
ALTER TABLE m_crm_source_type ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_source_type(id, name) VALUES (1, 'Cold Call');
INSERT INTO m_crm_source_type(id, name) VALUES (2, 'Existing Customer');
INSERT INTO m_crm_source_type(id, name) VALUES (3, 'Self Generated');
INSERT INTO m_crm_source_type(id, name) VALUES (4, 'Partner');
INSERT INTO m_crm_source_type(id, name) VALUES (5, 'Web Site');
INSERT INTO m_crm_source_type(id, name) VALUES (6, 'World of Mouth');
ALTER TABLE m_crm_source_type ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_task_status -----------------------------------
ALTER TABLE m_crm_task_status ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_task_status(id, status) VALUES (1, 'In Progress');
ALTER TABLE m_crm_task_status ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_hr_divisiontype -----------------------------------
ALTER TABLE m_hr_divisiontype ALTER COLUMN id INT ;
INSERT INTO m_hr_divisiontype(id, name) VALUES (1, 'AAA');
INSERT INTO m_hr_divisiontype(id, name) VALUES (2, 'BBB');
ALTER TABLE m_hr_divisiontype ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_hr_language_level -----------------------------------
ALTER TABLE m_hr_language_level ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_hr_language_level(id, level) VALUES (1, 'Expert');
ALTER TABLE m_hr_language_level ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_tag_tag -----------------------------------
ALTER TABLE m_tag_tag ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_tag_tag(id, tag, taggroup, slug) VALUES (1, 'tag1', NULL, 'tag1');
INSERT INTO m_tag_tag(id, tag, taggroup, slug) VALUES (2, 'tag2', NULL, 'tag2');
INSERT INTO m_tag_tag(id, tag, taggroup, slug) VALUES (3, 'tag3', NULL, 'tag3');
ALTER TABLE m_tag_tag ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_tag_taxonomy -----------------------------------
ALTER TABLE m_tag_taxonomy ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_tag_taxonomy(id, tagid, parent, count) VALUES (1, 1, NULL, 3);
INSERT INTO m_tag_taxonomy(id, tagid, parent, count) VALUES (2, 2, NULL, 4);
INSERT INTO m_tag_taxonomy(id, tagid, parent, count) VALUES (3, 3, NULL, 1);
ALTER TABLE m_tag_taxonomy ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_todo_category -----------------------------------
ALTER TABLE m_todo_category ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_todo_category(id, name) VALUES (1, 'xyz');
ALTER TABLE m_todo_category ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_todo_status -----------------------------------
ALTER TABLE m_todo_status ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_todo_status(id, name) VALUES (1, 'abc');
ALTER TABLE m_todo_status ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for s_billing_plan -----------------------------------
ALTER TABLE s_billing_plan ALTER COLUMN id INT ;
INSERT INTO s_billing_plan(id, billingType, numUsers, volume, numProjects) VALUES (1, NULL, NULL, NULL, NULL);
ALTER TABLE s_billing_plan ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for s_chat_server -----------------------------------
ALTER TABLE s_chat_server ALTER COLUMN id INT UNSIGNED ;
INSERT INTO s_chat_server(id, code, host, port, account_suffix) VALUES (1, 'gtalk', 'talk.google.com', 5222, '@gmail.com');
ALTER TABLE s_chat_server ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for s_country -----------------------------------
ALTER TABLE s_country ALTER COLUMN id INT ;
INSERT INTO s_country(id, countryname) VALUES (1, 'Viet Nam');
ALTER TABLE s_country ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for s_currency -----------------------------------
ALTER TABLE s_currency ALTER COLUMN id INT ;
INSERT INTO s_currency(id, name, isocode, symbol, conversionrate) VALUES (1, 'VND', '1', 'a', NULL);
ALTER TABLE s_currency ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for s_marital_status -----------------------------------
ALTER TABLE s_marital_status ALTER COLUMN id INT ;
INSERT INTO s_marital_status(id, name) VALUES (1, 'Unmarried');
INSERT INTO s_marital_status(id, name) VALUES (2, 'Married');
INSERT INTO s_marital_status(id, name) VALUES (3, 'Divorced');
INSERT INTO s_marital_status(id, name) VALUES (4, 'Others');
ALTER TABLE s_marital_status ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for s_module -----------------------------------
ALTER TABLE s_module ALTER COLUMN id INT UNSIGNED ;
INSERT INTO s_module(id, modulename, description, uri, status, title, sortindex) VALUES (1, 'Human Resource', 'Human Resource Module', 'Hr.swf', 1, 'Human Resource', 0);
INSERT INTO s_module(id, modulename, description, uri, status, title, sortindex) VALUES (2, 'Customer Management', 'Customer Management', 'Crm.swf', 1, 'Customer Management', 1);
INSERT INTO s_module(id, modulename, description, uri, status, title, sortindex) VALUES (3, 'Document Management', 'Document Management', 'Ecm.swf', 1, 'Document', 5);
INSERT INTO s_module(id, modulename, description, uri, status, title, sortindex) VALUES (4, 'Project Management', 'Project Management', 'Project.swf', 1, 'Project Management', 6);
ALTER TABLE s_module ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;





-- Generate script insert data for m_crm_campaign -----------------------------------
ALTER TABLE m_crm_campaign ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_campaign(id, campaignName, startDate, endDate, currencyId, impressionnote, budget, actualCost, expectedRevenue, expectedCost, objective, description, impression, createdTime, createdUser, sAccountId, status, type, assignUser, lastUpdatedTime) VALUES (1, 'gewgwe ded', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-01-22 23:07:10.0', NULL, 1, 'Complete', 'Webinar', NULL, '2013-01-22 23:07:10.0');
INSERT INTO m_crm_campaign(id, campaignName, startDate, endDate, currencyId, impressionnote, budget, actualCost, expectedRevenue, expectedCost, objective, description, impression, createdTime, createdUser, sAccountId, status, type, assignUser, lastUpdatedTime) VALUES (2, 'fewfew', '2013-01-29 11:33:13.0', '2013-02-03 11:33:13.0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-04 11:33:43.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 11:33:43.0');
INSERT INTO m_crm_campaign(id, campaignName, startDate, endDate, currencyId, impressionnote, budget, actualCost, expectedRevenue, expectedCost, objective, description, impression, createdTime, createdUser, sAccountId, status, type, assignUser, lastUpdatedTime) VALUES (3, 'ggewgew e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-04 11:45:10.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 11:45:10.0');
INSERT INTO m_crm_campaign(id, campaignName, startDate, endDate, currencyId, impressionnote, budget, actualCost, expectedRevenue, expectedCost, objective, description, impression, createdTime, createdUser, sAccountId, status, type, assignUser, lastUpdatedTime) VALUES (4, 'csacsac s', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-06 15:46:01.0', NULL, 1, NULL, NULL, NULL, '2013-02-06 15:46:01.0');
ALTER TABLE m_crm_campaign ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_campaigns_accounts -----------------------------------
ALTER TABLE m_crm_campaigns_accounts ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_campaigns_accounts(id, campaignId, accountId, createdTime) VALUES (1, 3, 7, '2013-02-05 21:20:42.0');
INSERT INTO m_crm_campaigns_accounts(id, campaignId, accountId, createdTime) VALUES (3, 3, 5, '2013-02-05 21:47:35.0');
INSERT INTO m_crm_campaigns_accounts(id, campaignId, accountId, createdTime) VALUES (4, 3, 2, '2013-02-05 22:04:06.0');
INSERT INTO m_crm_campaigns_accounts(id, campaignId, accountId, createdTime) VALUES (5, 3, 4, '2013-02-05 22:04:06.0');
ALTER TABLE m_crm_campaigns_accounts ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_campaigns_leads -----------------------------------
ALTER TABLE m_crm_campaigns_leads ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_campaigns_leads(id, campaignId, leadId, createdTime) VALUES (1, 3, 4, '2013-02-06 10:14:06.0');
INSERT INTO m_crm_campaigns_leads(id, campaignId, leadId, createdTime) VALUES (3, 3, 2, '2013-02-06 10:29:45.0');
INSERT INTO m_crm_campaigns_leads(id, campaignId, leadId, createdTime) VALUES (4, 3, 3, '2013-02-06 10:29:45.0');
INSERT INTO m_crm_campaigns_leads(id, campaignId, leadId, createdTime) VALUES (5, 2, 3, '2013-02-06 14:37:25.0');
INSERT INTO m_crm_campaigns_leads(id, campaignId, leadId, createdTime) VALUES (6, 2, 2, '2013-02-06 14:37:25.0');
INSERT INTO m_crm_campaigns_leads(id, campaignId, leadId, createdTime) VALUES (7, 2, 4, '2013-02-06 14:37:25.0');
INSERT INTO m_crm_campaigns_leads(id, campaignId, leadId, createdTime) VALUES (8, 4, 4, '2013-02-06 15:46:01.0');
ALTER TABLE m_crm_campaigns_leads ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_contact -----------------------------------
ALTER TABLE m_crm_contact ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_contact(id, prefix, firstname, lastname, leadSource, campaignId, isCallable, officePhone, mobile, homePhone, otherPhone, fax, birthday, assistant, primAddress, primCity, primState, primPostalCode, primCountry, description, title, assistantPhone, email, department, createdTime, createdUser, sAccountId, assignUser, otherAddress, otherCity, otherState, otherPostalCode, otherCountry, lastUpdatedTime) VALUES (6, NULL, 'gewgwe', 'eewg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 13:17:06.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 13:17:06.0');
ALTER TABLE m_crm_contact ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_contacts_cases -----------------------------------
ALTER TABLE m_crm_contacts_cases ALTER COLUMN id INT ;
INSERT INTO m_crm_contacts_cases(id, contactId, caseId, createdTime) VALUES (3, 6, 2, '2013-02-07 13:17:06.0');
ALTER TABLE m_crm_contacts_cases ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_crm_lead -----------------------------------
ALTER TABLE m_crm_lead ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_lead(id, campaignId, leadSourceDesc, statusDesc, referredBy, prefixName, firstname, lastname, accountName, title, department, isCallable, officePhone, homePhone, mobile, otherPhone, fax, primAddress, primState, primCity, primPostalCode, primCountry, description, email, createdTime, createdUser, sAccountId, assignUser, status, source, website, otherAddress, otherState, otherCity, otherPostalCode, otherCountry, industry, lastUpdatedTime, noEmployees) VALUES (2, NULL, NULL, NULL, NULL, 'Mrs.', 'fefewfew', 'wdwqdwq w', 'fefewgew', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 10:04:16.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 13:59:59.0', NULL);
INSERT INTO m_crm_lead(id, campaignId, leadSourceDesc, statusDesc, referredBy, prefixName, firstname, lastname, accountName, title, department, isCallable, officePhone, homePhone, mobile, otherPhone, fax, primAddress, primState, primCity, primPostalCode, primCountry, description, email, createdTime, createdUser, sAccountId, assignUser, status, source, website, otherAddress, otherState, otherCity, otherPostalCode, otherCountry, industry, lastUpdatedTime, noEmployees) VALUES (3, NULL, NULL, NULL, NULL, 'Mr.', 'ewgewg', 'gg e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-06 09:49:36.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-06 09:49:36.0', NULL);
INSERT INTO m_crm_lead(id, campaignId, leadSourceDesc, statusDesc, referredBy, prefixName, firstname, lastname, accountName, title, department, isCallable, officePhone, homePhone, mobile, otherPhone, fax, primAddress, primState, primCity, primPostalCode, primCountry, description, email, createdTime, createdUser, sAccountId, assignUser, status, source, website, otherAddress, otherState, otherCity, otherPostalCode, otherCountry, industry, lastUpdatedTime, noEmployees) VALUES (4, NULL, NULL, NULL, NULL, NULL, 'rehre', 'hreher', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-06 10:14:06.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-06 10:14:06.0', NULL);
INSERT INTO m_crm_lead(id, campaignId, leadSourceDesc, statusDesc, referredBy, prefixName, firstname, lastname, accountName, title, department, isCallable, officePhone, homePhone, mobile, otherPhone, fax, primAddress, primState, primCity, primPostalCode, primCountry, description, email, createdTime, createdUser, sAccountId, assignUser, status, source, website, otherAddress, otherState, otherCity, otherPostalCode, otherCountry, industry, lastUpdatedTime, noEmployees) VALUES (5, NULL, NULL, NULL, NULL, NULL, 'afew', 'gewg e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:02:07.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:02:07.0', NULL);
INSERT INTO m_crm_lead(id, campaignId, leadSourceDesc, statusDesc, referredBy, prefixName, firstname, lastname, accountName, title, department, isCallable, officePhone, homePhone, mobile, otherPhone, fax, primAddress, primState, primCity, primPostalCode, primCountry, description, email, createdTime, createdUser, sAccountId, assignUser, status, source, website, otherAddress, otherState, otherCity, otherPostalCode, otherCountry, industry, lastUpdatedTime, noEmployees) VALUES (6, NULL, NULL, NULL, NULL, NULL, 'fe e', 'ewfef', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:13:40.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:13:44.0', NULL);
INSERT INTO m_crm_lead(id, campaignId, leadSourceDesc, statusDesc, referredBy, prefixName, firstname, lastname, accountName, title, department, isCallable, officePhone, homePhone, mobile, otherPhone, fax, primAddress, primState, primCity, primPostalCode, primCountry, description, email, createdTime, createdUser, sAccountId, assignUser, status, source, website, otherAddress, otherState, otherCity, otherPostalCode, otherCountry, industry, lastUpdatedTime, noEmployees) VALUES (7, NULL, NULL, NULL, NULL, NULL, 'fewfew', 'fewf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:14:19.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:14:19.0', NULL);
INSERT INTO m_crm_lead(id, campaignId, leadSourceDesc, statusDesc, referredBy, prefixName, firstname, lastname, accountName, title, department, isCallable, officePhone, homePhone, mobile, otherPhone, fax, primAddress, primState, primCity, primPostalCode, primCountry, description, email, createdTime, createdUser, sAccountId, assignUser, status, source, website, otherAddress, otherState, otherCity, otherPostalCode, otherCountry, industry, lastUpdatedTime, noEmployees) VALUES (8, NULL, NULL, NULL, NULL, NULL, 'ss', 'sss', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:49:17.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-07 10:49:17.0', NULL);
ALTER TABLE m_crm_lead ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_opportunities_leads -----------------------------------
ALTER TABLE m_crm_opportunities_leads ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_opportunities_leads(id, opportunityId, leadId, createdTime) VALUES (2, 9, 7, '2013-02-07 10:36:41.0');
INSERT INTO m_crm_opportunities_leads(id, opportunityId, leadId, createdTime) VALUES (3, 9, 5, '2013-02-07 10:49:01.0');
INSERT INTO m_crm_opportunities_leads(id, opportunityId, leadId, createdTime) VALUES (4, 9, 8, '2013-02-07 10:49:17.0');
ALTER TABLE m_crm_opportunities_leads ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_opportunity -----------------------------------
ALTER TABLE m_crm_opportunity ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (1, 'gegewg', NULL, 1, NULL, NULL, 'Partner', NULL, NULL, NULL, NULL, NULL, '2013-01-22 23:07:37.0', NULL, 1, NULL, 'New Business', 'Need Analysis', '2013-01-22 23:07:37.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (2, 'fefe e', NULL, 3, NULL, NULL, NULL, '2013-02-11 21:10:35.0', NULL, NULL, NULL, NULL, '2013-02-03 21:10:47.0', NULL, 1, NULL, NULL, NULL, '2013-02-03 21:10:47.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (3, 'ffew e', NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-04 21:11:53.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 21:11:53.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (4, 'rre r', NULL, 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-04 21:12:10.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 21:12:10.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (5, 'opp', NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 15:47:21.0', NULL, 1, NULL, NULL, NULL, '2013-02-05 15:47:21.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (6, 'cscsac', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 15:52:18.0', NULL, 1, NULL, NULL, NULL, '2013-02-05 15:52:18.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (7, 'fefewf', NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 16:11:44.0', NULL, 1, NULL, NULL, NULL, '2013-02-05 16:11:44.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (8, 'fefe ee', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 16:25:48.0', NULL, 1, NULL, NULL, NULL, '2013-02-05 16:25:48.0');
INSERT INTO m_crm_opportunity(id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, campaignid, nextStep, probability, description, createdTime, createdUser, sAccountId, assignUser, opportunityType, salesStage, lastUpdatedTime) VALUES (9, 'xxx', NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 16:25:59.0', NULL, 1, NULL, NULL, NULL, '2013-02-05 16:25:59.0');
ALTER TABLE m_crm_opportunity ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_prj_project -----------------------------------
ALTER TABLE m_prj_project ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_prj_project(id, name, owner, account, priority, shortname, planStartDate, planEndDate, targetBudget, homePage, actualBudget, projectType, projectStatus, description, defaultBillingRate, actualStartDate, actualEndDate, defaultOvertimeBillingRate, currencyid, progress, sAccountId, createdTime, lastUpdatedTime) VALUES (1, 'a', NULL, NULL, NULL, 'Udf', NULL, NULL, NULL, NULL, NULL, NULL, 'Open', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_prj_project(id, name, owner, account, priority, shortname, planStartDate, planEndDate, targetBudget, homePage, actualBudget, projectType, projectStatus, description, defaultBillingRate, actualStartDate, actualEndDate, defaultOvertimeBillingRate, currencyid, progress, sAccountId, createdTime, lastUpdatedTime) VALUES (2, 'MyCollab', NULL, NULL, NULL, 'MCB', NULL, NULL, NULL, 'http://www.mycollab.com', NULL, NULL, 'Open', 'This is the page of mycollab project<br>', NULL, '2013-01-01 16:40:58.0', '2013-03-28 10:51:46.0', NULL, NULL, NULL, 1, '2013-01-24 16:41:37.0', '2013-03-15 10:51:53.0');
INSERT INTO m_prj_project(id, name, owner, account, priority, shortname, planStartDate, planEndDate, targetBudget, homePage, actualBudget, projectType, projectStatus, description, defaultBillingRate, actualStartDate, actualEndDate, defaultOvertimeBillingRate, currencyid, progress, sAccountId, createdTime, lastUpdatedTime) VALUES (3, 'TEST', NULL, NULL, NULL, 'tes', NULL, NULL, NULL, NULL, NULL, NULL, 'Open', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2013-01-24 16:45:50.0', '2013-01-24 16:45:50.0');
INSERT INTO m_prj_project(id, name, owner, account, priority, shortname, planStartDate, planEndDate, targetBudget, homePage, actualBudget, projectType, projectStatus, description, defaultBillingRate, actualStartDate, actualEndDate, defaultOvertimeBillingRate, currencyid, progress, sAccountId, createdTime, lastUpdatedTime) VALUES (4, 'dwqdwqfwq wfwfwqf', NULL, NULL, NULL, 'aaa', NULL, NULL, NULL, NULL, NULL, NULL, 'Open', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2013-01-25 14:01:18.0', '2013-01-25 14:01:18.0');
INSERT INTO m_prj_project(id, name, owner, account, priority, shortname, planStartDate, planEndDate, targetBudget, homePage, actualBudget, projectType, projectStatus, description, defaultBillingRate, actualStartDate, actualEndDate, defaultOvertimeBillingRate, currencyid, progress, sAccountId, createdTime, lastUpdatedTime) VALUES (5, 'test', NULL, NULL, NULL, 'aaa', NULL, NULL, NULL, NULL, NULL, NULL, 'Open', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2013-02-22 16:25:55.0', '2013-02-22 16:25:55.0');
INSERT INTO m_prj_project(id, name, owner, account, priority, shortname, planStartDate, planEndDate, targetBudget, homePage, actualBudget, projectType, projectStatus, description, defaultBillingRate, actualStartDate, actualEndDate, defaultOvertimeBillingRate, currencyid, progress, sAccountId, createdTime, lastUpdatedTime) VALUES (6, 'test', NULL, NULL, NULL, 'aaa', NULL, NULL, NULL, NULL, NULL, NULL, 'Open', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2013-02-27 09:49:37.0', '2013-02-27 09:49:37.0');
INSERT INTO m_prj_project(id, name, owner, account, priority, shortname, planStartDate, planEndDate, targetBudget, homePage, actualBudget, projectType, projectStatus, description, defaultBillingRate, actualStartDate, actualEndDate, defaultOvertimeBillingRate, currencyid, progress, sAccountId, createdTime, lastUpdatedTime) VALUES (7, 'test1', NULL, NULL, NULL, 'aaa', NULL, NULL, NULL, NULL, NULL, NULL, 'Open', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2013-02-27 09:49:54.0', '2013-02-27 09:49:54.0');
ALTER TABLE m_prj_project ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_prj_risk -----------------------------------
ALTER TABLE m_prj_risk ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (1, 'fwqfv qwfqw qwqwfewwe qfefewf eeee vefewgrehgre ee vrebreherhrehre ewggewgw eee', 'dwdqw', 1, 'user2', 'user2', 'Critical', 'Unlikely', NULL, NULL, '2013-01-15 23:14:18.0', 'fefewfw', NULL, 4, NULL, '2013-01-22 23:14:42.0', '2013-02-03 21:36:05.0', NULL, NULL, 1);
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (2, 'risk', 'ewgew ee<br>', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2013-01-24 11:44:28.0', '2013-01-24 11:44:28.0', NULL, NULL, 1);
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (3, 'gwgre', 'grgreg<br>', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2013-02-04 09:04:45.0', '2013-02-04 09:04:45.0', NULL, NULL, 1);
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (4, 'fefewf', 'fefew efewfew<br>', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2013-02-04 09:04:54.0', '2013-02-04 09:04:54.0', NULL, NULL, 1);
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (5, 'cscsac', 'fewgew e<br>', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2013-02-04 09:05:00.0', '2013-02-04 09:05:00.0', NULL, NULL, 1);
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (6, 'fefe e', 'fefewfew', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2013-02-04 09:05:09.0', '2013-02-04 09:05:09.0', NULL, NULL, 1);
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (7, 'ggre', '<b>fwfwqf</b>ggrg fefew<br><br>ewgewg<br><br>', 1, 'a@a.com', 'hainguyen@esofthead.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2013-02-04 09:06:12.0', '2013-02-22 13:23:16.0', NULL, NULL, 1);
INSERT INTO m_prj_risk(id, riskname, description, projectid, raisedbyuser, assigntouser, consequence, probalitity, status, dateraised, datedue, response, resolution, level, source, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (8, 'fewfe', 'ewgew', 6, 'hainguyen@esofthead.com', NULL, 'Marginal', 'Possible', 'Open', NULL, NULL, NULL, NULL, 3, NULL, '2013-03-20 23:09:23.0', '2013-03-20 23:09:23.0', NULL, NULL, 1);
ALTER TABLE m_prj_risk ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_prj_role -----------------------------------
ALTER TABLE m_prj_role ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_prj_role(id, rolename, description, sAccountId, projectid) VALUES (5, 'Software Developer', NULL, 1, 1);
INSERT INTO m_prj_role(id, rolename, description, sAccountId, projectid) VALUES (6, 'test', NULL, 1, 2);
INSERT INTO m_prj_role(id, rolename, description, sAccountId, projectid) VALUES (7, 'Default', NULL, 1, 6);
ALTER TABLE m_prj_role ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_prj_role_permission -----------------------------------
ALTER TABLE m_prj_role_permission ALTER COLUMN id INT ;
INSERT INTO m_prj_role_permission(id, roleid, roleVal, projectid) VALUES (4, 5, '<?xml version=""1.0"" ?><com.esofthead.mycollab.common.domain.PermissionMap serialization=""custom""><unserializable-parents></unserializable-parents><map><default><loadFactor>0.75</loadFactor><threshold>12</threshold></default><int>16</int><int>10</int><string>User</string><int>1</int><string>Risk</string><int>1</int><string>Milestone</string><int>1</int><string>Problem</string><int>1</int><string>Message</string><int>2</int><string>Task</string><int>2</int><string>Role</string><int>1</int><string>Component</string><int>2</int><string>Bug</string><int>2</int><string>Version</string><int>1</int></map></com.esofthead.mycollab.common.domain.PermissionMap>', 1);
INSERT INTO m_prj_role_permission(id, roleid, roleVal, projectid) VALUES (5, 6, '<?xml version=""1.0"" ?><com.esofthead.mycollab.common.domain.PermissionMap serialization=""custom""><unserializable-parents></unserializable-parents><map><default><loadFactor>0.75</loadFactor><threshold>12</threshold></default><int>16</int><int>10</int><string>User</string><int>0</int><string>Risk</string><int>0</int><string>Milestone</string><int>0</int><string>Problem</string><int>0</int><string>Message</string><int>0</int><string>Task</string><int>0</int><string>Role</string><int>0</int><string>Component</string><int>0</int><string>Bug</string><int>0</int><string>Version</string><int>0</int></map></com.esofthead.mycollab.common.domain.PermissionMap>', 2);
INSERT INTO m_prj_role_permission(id, roleid, roleVal, projectid) VALUES (6, 7, '<?xml version=""1.0"" ?><com.esofthead.mycollab.common.domain.PermissionMap serialization=""custom""><unserializable-parents></unserializable-parents><map><default><loadFactor>0.75</loadFactor><threshold>12</threshold></default><int>16</int><int>10</int><string>User</string><int>1</int><string>Risk</string><int>1</int><string>Milestone</string><int>1</int><string>Problem</string><int>1</int><string>Message</string><int>1</int><string>Task</string><int>1</int><string>Role</string><int>1</int><string>Component</string><int>1</int><string>Bug</string><int>1</int><string>Version</string><int>1</int></map></com.esofthead.mycollab.common.domain.PermissionMap>', 6);
ALTER TABLE m_prj_role_permission ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_prj_standup -----------------------------------
ALTER TABLE m_prj_standup ALTER COLUMN id INT ;
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (1, 1, 1, 'dwqd<br>', 'dwq w<br>', 'dwdwq', '2013-02-16 22:52:44.0', 'hainguyen@esofthead.com', '2013-02-16 22:52:44.0', '2013-02-16 22:52:44.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (2, 1, 1, 'vdvdv fewf<br>', 'fefew wqfqwf<br>', 'gewgew e<br>', '2013-02-19 13:32:57.0', 'hainguyen@esofthead.com', '2013-02-19 13:32:57.0', '2013-02-19 13:32:57.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (3, 1, 1, 'asfwf fgewgew<br>egewgewgew<b>gewgewgweg<br><u>gewgew</u><br></b><br>', 'gewg <sub>gewgewg<sup>gewgewgew</sup></sub><b>gewgewgewgewgew ewgewg egewgwegffewg eewgewgw gwgewgew gwg</b><br>', 'ewgewgewg eee<u>gewgewg</u><br>', '2013-02-21 11:50:06.0', 'hainguyen@esofthead.com', '2013-02-21 11:50:06.0', '2013-02-21 11:50:06.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (4, 1, 1, 'cewvew qfewf<br>', 'fewfew efew<br>', 'vdvcdv fewfew<br>', '2013-02-25 22:43:52.0', 'hainguyen@esofthead.com', '2013-02-25 22:43:52.0', '2013-02-25 22:43:52.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (5, 1, 2, 'gegew<br>', 'ewtw', 'bewgewgb e3<br>', '2013-03-12 12:11:51.0', 'hainguyen@esofthead.com', '2013-03-12 12:11:51.0', '2013-03-12 12:11:51.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (6, 1, 2, 'assa<br>', 'fwf', 'fwqf', '2013-03-13 15:59:44.0', 'hainguyen@esofthead.com', '2013-03-13 15:59:44.0', '2013-03-13 15:59:44.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (7, 1, 1, 'wqfwqf<br>', 'fwqfw', 'wfwqf', '2013-03-18 13:04:05.0', 'hainguyen@esofthead.com', '2013-03-18 13:04:05.0', '2013-03-18 13:04:05.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (8, 1, 1, 'fef<br>', 'ewgew', 'gwgw', '2013-03-21 08:33:16.0', 'hainguyen@esofthead.com', '2013-03-21 08:33:16.0', '2013-03-21 08:33:16.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (9, 1, 2, 'fef<br>', 'fefe', 'feewg', '2013-03-22 22:01:55.0', 'hainguyen@esofthead.com', '2013-03-22 22:01:55.0', '2013-03-22 22:01:55.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (10, 1, 2, 'dd<br>', NULL, NULL, '2013-03-22 22:02:02.0', 'hainguyen@esofthead.com', '2013-03-22 22:02:02.0', '2013-03-22 22:02:02.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (11, 1, 1, 'fewfew<br>', 'fefew', 'gewgew', '2013-03-25 09:56:02.0', 'hainguyen@esofthead.com', '2013-03-25 09:56:02.0', '2013-03-25 09:56:02.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (12, 1, 6, 'fewfew<br>', 'fewfew', 'gewgew', '2013-03-25 10:00:34.0', 'hainguyen@esofthead.com', '2013-03-25 10:00:34.0', '2013-03-25 10:00:34.0');
INSERT INTO m_prj_standup(id, sAccountId, projectId, whatlastday, whattoday, whatproblem, forday, logBy, createdTime, lastUpdatedTime) VALUES (13, 1, 2, 'efewg<br>', 'egew', 'gewg e<br>', '2013-03-25 11:20:23.0', 'hainguyen@esofthead.com', '2013-03-25 11:20:23.0', '2013-03-25 11:20:23.0');
ALTER TABLE m_prj_standup ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_prj_task -----------------------------------
ALTER TABLE m_prj_task ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (1, 'ffewfew', 50, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 2, '2013-01-23 21:40:02.0', '2013-01-24 20:59:26.0', NULL, 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (2, 'fewfefew ewgwgew', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 2, '2013-01-24 13:27:41.0', '2013-02-13 06:10:03.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (3, 'test', 10, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 1, '2013-01-24 21:00:03.0', '2013-01-24 21:00:03.0', 'hainguyen@esofthead.com', 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (4, 'ss ss', 70, '2013-01-07 21:02:00.0', NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 6, '2013-01-24 21:02:10.0', '2013-01-24 21:02:29.0', 'hainguyen@esofthead.com', 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (5, 'egwgrg', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-01-24 21:03:09.0', '2013-02-04 18:06:16.0', 'a@a.com', 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (6, 'gewgewgew', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-01-30 11:23:07.0', '2013-02-03 12:56:08.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (7, 'csacas s', 100, '2013-02-04 10:53:15.0', '2013-02-28 10:53:15.0', NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-03 13:31:24.0', '2013-02-04 17:56:59.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (8, 'cascas w', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-03 13:31:53.0', '2013-02-04 17:57:03.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (9, 'www', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-03 13:32:28.0', '2013-02-04 18:02:52.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (10, 'aaa', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-03 13:33:10.0', '2013-02-12 17:55:41.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (11, 'gewgewg', 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 9, '2013-02-04 18:07:48.0', '2013-02-04 18:07:48.0', NULL, 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (12, 'gewgewg', 100, '2013-02-11 14:50:59.0', '2013-02-18 14:50:59.0', 'Urgent', NULL, NULL, 1, '2013-02-19 14:50:59.0', '', NULL, NULL, NULL, 9, '2013-02-06 14:51:10.0', '2013-02-13 10:44:25.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (13, 'egwegew r', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-07 15:34:05.0', '2013-02-25 21:55:07.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (14, 'ee', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 9, '2013-02-07 15:34:36.0', '2013-02-13 06:08:32.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (15, 'xxx', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-07 15:38:10.0', '2013-02-13 10:44:20.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (16, 'yyy', 100, NULL, NULL, 'Hight', NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-07 15:38:28.0', '2013-02-13 06:08:18.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (17, 'grgrg r', 100, NULL, NULL, 'High', NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-08 10:33:04.0', '2013-02-13 06:10:12.0', 'b@esofthead.com', 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (18, 'sss', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-08 10:37:51.0', '2013-02-13 06:08:23.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (19, 'yyy', 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 9, '2013-02-08 10:38:01.0', '2013-02-08 10:38:01.0', NULL, 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (20, 'qqq', 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-08 11:04:07.0', '2013-02-08 11:04:07.0', NULL, 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (21, 'ewgewgewg', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-08 11:06:07.0', '2013-02-25 20:44:47.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (22, 'xxxx', 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 9, '2013-02-08 11:06:14.0', '2013-02-08 11:06:14.0', NULL, 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (23, 'test', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-21 09:40:49.0', '2013-02-25 20:44:58.0', 'c@a.com', 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (24, 'test 1', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-21 17:16:09.0', '2013-02-25 20:44:48.0', 'b@esofthead.com', 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (25, 'test 2', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-21 17:19:47.0', '2013-02-25 20:44:56.0', 'c@a.com', 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (26, 'fefew', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-21 17:21:40.0', '2013-02-25 20:44:55.0', 'c@a.com', 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (27, 'ss', 20, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 2, '2013-02-21 22:40:01.0', '2013-02-21 22:40:01.0', NULL, 1, 'Open', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (29, 'aaa', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-21 23:57:50.0', '2013-02-25 20:44:54.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (30, 'www', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-22 00:02:01.0', '2013-02-25 20:44:53.0', NULL, 1, 'Closed', NULL, NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (31, 'ewee', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 20:35:49.0', '2013-02-25 20:44:51.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (32, 'grgreg', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 20:40:19.0', '2013-02-25 20:44:50.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (33, 'gewgw', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 20:45:05.0', '2013-02-25 21:55:09.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (34, 'grgrg r', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 21:00:20.0', '2013-03-18 10:44:28.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (35, 'fafa s', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 21:07:53.0', '2013-02-25 21:55:10.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (36, 'dds', 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 21:41:16.0', '2013-02-25 21:41:16.0', 'hainguyen@esofthead.com', 1, 'Open', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (37, 'rey5y', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 21:48:43.0', '2013-02-25 21:55:11.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (38, 'rree', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 21:51:22.0', '2013-02-25 23:08:12.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (39, 'ykytkt', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 21:55:24.0', '2013-02-25 22:17:01.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (40, 'ssafsa', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 22:00:59.0', '2013-02-25 22:16:59.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (41, 'xxxx', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 22:06:08.0', '2013-02-25 22:16:58.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (42, 'zzz', 100, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 22:17:09.0', '2013-02-25 23:08:10.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (43, 'yyyy', 100, '2013-02-25 22:22:35.0', NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 22:22:45.0', '2013-02-25 23:08:09.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (44, 'fewfew', 0, '2013-02-26 22:28:08.0', NULL, 'Low', NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 22:28:22.0', '2013-03-18 11:35:10.0', 'hainguyen@esofthead.com', 1, 'Open', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (45, 'egewg', 0, NULL, '2013-02-25 23:08:13.0', NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-25 23:08:23.0', '2013-03-18 10:30:01.0', 'hainguyen@esofthead.com', 1, 'Pending', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (46, 'fefewf efew', 100, '2013-02-26 08:34:48.0', '2013-02-26 08:34:48.0', 'Low', NULL, NULL, 1, '2013-02-26 08:34:48.0', '', NULL, '2013-02-26 08:34:48.0', '2013-02-26 08:34:48.0', 7, '2013-02-26 08:35:12.0', '2013-03-18 11:04:18.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (47, 'afewfewg', 0, '2013-02-26 10:11:39.0', '2013-02-26 10:11:39.0', NULL, NULL, NULL, 1, '2013-02-26 10:11:39.0', '', NULL, NULL, NULL, 7, '2013-02-26 10:11:53.0', '2013-03-18 11:03:53.0', 'x@a.com', 1, 'Pending', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (48, 'test accc', 0, '2013-02-26 13:05:28.0', '2013-02-26 13:05:28.0', NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-26 13:05:43.0', '2013-02-26 13:05:43.0', 'hainguyen@esofthead.com', 1, 'Open', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (49, 'egg egewg', 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-02-26 13:24:21.0', '2013-03-18 10:24:08.0', 'hainguyen@esofthead.com', 1, 'Pending', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (50, 'fefe e', 100, NULL, NULL, NULL, NULL, NULL, 6, NULL, '', NULL, NULL, NULL, 10, '2013-02-27 11:34:37.0', '2013-03-08 09:07:47.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (51, 'egewgew', 0, NULL, NULL, NULL, NULL, NULL, 6, NULL, '', NULL, NULL, NULL, 10, '2013-02-27 11:43:06.0', '2013-02-27 11:43:06.0', 'hainguyen@esofthead.com', 1, 'Open', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (52, 'ewgewg', 0, NULL, NULL, NULL, NULL, NULL, 6, NULL, '', NULL, NULL, NULL, 10, '2013-02-27 14:32:59.0', '2013-02-27 14:32:59.0', 'hainguyen@esofthead.com', 1, 'Open', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (53, 'ewgewg', 0, NULL, NULL, NULL, NULL, NULL, 6, NULL, '', NULL, NULL, NULL, 10, '2013-02-27 14:56:11.0', '2013-02-27 14:56:11.0', 'hainguyen@esofthead.com', 1, 'Open', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (54, 'fewgew', 100, NULL, NULL, 'Medium', NULL, NULL, 6, NULL, '', NULL, NULL, NULL, 10, '2013-02-27 16:44:20.0', '2013-03-08 08:51:27.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (55, 'ewgew wgewg', 100, NULL, NULL, 'Medium', NULL, NULL, 6, NULL, '', NULL, NULL, NULL, 10, '2013-02-28 14:12:59.0', '2013-03-08 08:49:43.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (56, 'example', 100, NULL, NULL, 'Medium', NULL, NULL, 6, NULL, '', NULL, NULL, NULL, 10, '2013-03-07 13:29:53.0', '2013-03-08 08:45:58.0', 'hainguyen@esofthead.com', 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (57, 'aaa', 0, '2013-03-11 09:41:45.0', '2013-03-12 09:41:45.0', 'Medium', NULL, NULL, 2, '2013-03-27 11:03:03.0', '', NULL, NULL, NULL, 11, '2013-03-11 09:41:56.0', '2013-03-18 10:20:56.0', NULL, 1, 'Pending', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (59, 'efegfew e', 100, '2013-03-28 09:42:14.0', '2013-03-30 09:42:14.0', 'Medium', NULL, NULL, 2, NULL, '', NULL, NULL, NULL, 11, '2013-03-11 09:42:26.0', '2013-03-14 14:20:39.0', NULL, 1, 'Closed', 'hainguyen@esofthead.com', NULL, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (61, 'fewfew e', 0, NULL, NULL, 'Medium', NULL, NULL, 2, NULL, '', NULL, NULL, NULL, 12, '2013-03-14 13:53:28.0', '2013-03-18 10:42:32.0', 'hainguyen@esofthead.com', 1, 'Open', 'hainguyen@esofthead.com', 1, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (62, 'a', 0, NULL, NULL, 'Medium', NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-03-17 07:29:03.0', '2013-03-18 10:23:54.0', NULL, 1, 'Pending', 'hainguyen@esofthead.com', 1, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (63, 'fff', 0, NULL, NULL, 'Medium', NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-03-18 11:37:05.0', '2013-03-18 11:37:05.0', NULL, 1, 'Open', 'hainguyen@esofthead.com', 2, NULL, NULL);
INSERT INTO m_prj_task(id, taskname, percentagecomplete, startdate, enddate, priority, duration, isestimated, projectid, deadline, notes, taskindex, actualStartDate, actualEndDate, tasklistid, createdTime, lastUpdatedTime, assignUser, sAccountId, status, logBy, taskkey, originalEstimate, remainEstimate) VALUES (64, 'sss', 0, NULL, NULL, 'Medium', NULL, NULL, 1, NULL, '', NULL, NULL, NULL, 7, '2013-03-18 20:59:46.0', '2013-03-18 20:59:46.0', NULL, 1, 'Open', 'hainguyen@esofthead.com', 3, NULL, NULL);
ALTER TABLE m_prj_task ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_prj_task_list -----------------------------------
ALTER TABLE m_prj_task_list ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (1, 'dwwq', 'fqwfqw', 1, '2013-01-23 01:01:26.0', '2013-01-28 09:39:09.0', 1, NULL, 'hainguyen@esofthead.com', 5, 'Closed');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (2, 'fefew', 'ewgwe', 1, '2013-01-23 08:32:13.0', '2013-01-23 08:32:13.0', 1, NULL, NULL, 7, 'Open');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (3, 'task group 1', 'ggregr \ngegewg e\ngewg', 1, '2013-01-24 21:00:43.0', '2013-01-28 09:44:49.0', 1, 1, 'hainguyen@esofthead.com', 4, 'Closed');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (4, 'ffew', 'gwegew \nfegewg\ngeg', 1, '2013-01-24 21:01:09.0', '2013-01-28 09:44:41.0', 1, 1, 'hainguyen@esofthead.com', 3, 'Closed');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (5, 'gewg', 'gewg rkk', 1, '2013-01-24 21:01:21.0', '2013-01-28 09:33:47.0', 1, NULL, 'hainguyen@esofthead.com', 3, 'Closed');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (6, 'egeg', 'gewg ee', 1, '2013-01-24 21:01:53.0', '2013-01-28 09:23:18.0', 1, NULL, 'a@a.com', 2, 'Closed');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (7, 'ww ee', 'wgreg e', 1, '2013-01-24 21:02:53.0', '2013-02-21 22:40:44.0', 1, 2, 'hainguyen@esofthead.com', 0, 'Open');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (8, 'grgre', 'greg rgre', 1, '2013-01-25 09:33:13.0', '2013-01-28 09:22:13.0', 1, NULL, NULL, 8, 'Closed');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (9, 'ewgewg', 'gewg ewgewg', 1, '2013-02-04 18:07:40.0', '2013-02-04 18:07:40.0', 1, 1, 'b@esofthead.com', 1, 'Open');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (10, 'dvds', 'vdsvds', 6, '2013-02-27 11:34:30.0', '2013-03-20 14:15:31.0', 1, 3, 'hainguyen@esofthead.com', NULL, 'Open');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (11, 'fewf', 'fewfew', 2, '2013-03-09 00:34:40.0', '2013-03-13 02:14:50.0', 1, 5, NULL, NULL, 'Open');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (12, 'ggre', 'grgre', 2, '2013-03-13 16:58:27.0', '2013-03-13 16:58:27.0', 1, 6, NULL, NULL, 'Open');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (13, 'ccc', NULL, 1, '2013-03-17 07:31:22.0', '2013-03-17 07:37:27.0', 1, NULL, NULL, NULL, 'Closed');
INSERT INTO m_prj_task_list(id, name, description, projectid, createdTime, lastUpdatedTime, sAccountId, milestoneId, owner, groupIndex, status) VALUES (14, 'ewgw', NULL, 1, '2013-03-20 21:19:34.0', '2013-03-20 21:19:48.0', 1, 1, NULL, NULL, 'Open');
ALTER TABLE m_prj_task_list ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_tag_relationship -----------------------------------
ALTER TABLE m_tag_relationship ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_tag_relationship(id, contentpath, tagtaxonomy) VALUES (1, 'content1', 1);
INSERT INTO m_tag_relationship(id, contentpath, tagtaxonomy) VALUES (2, 'content1', 2);
INSERT INTO m_tag_relationship(id, contentpath, tagtaxonomy) VALUES (3, 'content2', 3);
INSERT INTO m_tag_relationship(id, contentpath, tagtaxonomy) VALUES (4, 'xyz', 1);
ALTER TABLE m_tag_relationship ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_tracker_bug -----------------------------------
ALTER TABLE m_tracker_bug ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (1, 'ceew  e ee', NULL, 'hainguyen@esofthead.com', '2013-01-23 01:10:00.0', NULL, 'Major', 'Blocker', '2013-02-04 17:54:08.0', 'Won''t Fix', '2013-01-15 01:07:57.0', 'ewvewv ewvew', 'Fixed', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'eevew', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (2, 'wfefew grgre eefefewf', NULL, 'user2', '2013-01-25 10:47:41.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-13 10:42:30.0', 'Won''t Fix', NULL, 'gegg', 'Fixed', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'ggrgre', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (3, 'gegg', NULL, NULL, '2013-01-25 15:30:36.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-01-25 15:30:36.0', 'In Progress', NULL, 'geg', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'rre', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (4, 'weggr', NULL, 'b@esofthead.com', '2013-01-25 16:04:59.0', 'hainguyen@esofthead.com', 'Minor', 'Critical', '2013-02-18 11:05:27.0', 'Reopenned', NULL, 'grg ddd', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'regreg rr', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (5, 'saas s ee  eee', NULL, NULL, '2013-01-25 16:05:19.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-01-25 18:43:15.0', 'Test Pending', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (6, 'grg wegrgre d', NULL, NULL, '2013-01-25 18:02:15.0', 'hainguyen@esofthead.com', NULL, NULL, '2012-12-23 18:02:15.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (7, 'vv grehhe', NULL, NULL, '2013-01-25 18:06:53.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-01-24 18:06:53.0', 'In Progress', NULL, 'grhrehe', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (8, 'tts greger', NULL, NULL, '2013-01-25 23:11:58.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-01-28 10:20:22.0', 'Open', NULL, 'efewf', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'ffew', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (9, 'vsdvd ewg', NULL, NULL, '2013-01-28 13:42:57.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-13 06:56:00.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew ', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (10, 'TEst bug related item', NULL, 'hainguyen@esofthead.com', '2013-01-28 15:57:40.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-01-28 22:10:15.0', 'Won''t Fix', NULL, NULL, 'Duplicate', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (11, 'gggreg eefewfewgewg vdsvd', NULL, 'b@esofthead.com', '2013-01-30 21:17:35.0', 'hainguyen@esofthead.com', 'Minor', 'Blocker', '2013-02-13 06:41:18.0', 'Open', '2013-02-11 10:52:17.0', 'gregregre', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'wfwefewoo hohfoeiwhfgowh oihoehgw', NULL, NULL, 1, 2, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (12, 'test ex', NULL, NULL, '2013-02-07 15:27:23.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-13 06:55:29.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (13, 'fqwfwq', NULL, NULL, '2013-02-13 11:30:39.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-13 11:30:39.0', 'Open', NULL, 'fqwf e', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (14, 'fwqfwqf', NULL, NULL, '2013-02-13 13:45:41.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-13 13:45:41.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (15, 'a1', NULL, NULL, '2013-02-13 13:52:00.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-13 13:52:00.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (16, 'a2', NULL, NULL, '2013-02-13 13:55:40.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-13 13:55:40.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (17, 'r3r32', NULL, 'c@a.com', '2013-02-13 13:57:58.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-27 09:45:43.0', 'Reopenned', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (18, 'a3', NULL, 'b@esofthead.com', '2013-02-13 14:04:23.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-19 21:40:35.0', 'Open', NULL, 'vgr', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'gregre ee', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (19, 'grgrg', NULL, NULL, '2013-02-20 08:44:46.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-20 08:44:46.0', 'Open', NULL, 'grgr', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'ggre', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (20, 'aaa', NULL, NULL, '2013-02-20 08:53:57.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-22 09:33:26.0', 'Open', NULL, 't4', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 't4t43 4htoh4toi4 oj4gpj4ptj43ptj pjpjrp32jtp32jt pj3tpj32tp32jtp pj32trpj32tp32jt 3ptj32ptj32 4gvv ds,v , gehgeow ewgkhewlgn egnelwng elgnewlg', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (22, 'wgg', NULL, 'hainguyen@esofthead.com', '2013-02-20 10:24:27.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-27 09:36:21.0', 'Won''t Fix', NULL, 'grgr', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'y5y54 y5yk54yk5[5]l] l]5l]l5uy]5lu]45]l ]ly]5ly]5ly]l45]l ]l5]yl5]yl54]ul54]l] l]5ly5]ly5]l l]5l5]ly5]yl l]5ly]5ly54w rm;rmh;reh<br><br>fefewfew e<b>gewgewgewg <u>fewfewgewgew<br></u></b><i>gewgewgewg</i><br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (23, 'egewg e', NULL, 'hainguyen@esofthead.com', '2013-02-27 16:46:02.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-27 16:46:02.0', 'Open', NULL, 'fewg<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'gege<br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (24, 'ewfewf', NULL, 'hainguyen@esofthead.com', '2013-02-27 16:53:06.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-27 16:53:06.0', 'Open', NULL, 'efew<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (25, 'gegw', NULL, 'hainguyen@esofthead.com', '2013-02-27 17:00:22.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-27 17:00:22.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (26, 'csacsa', NULL, 'hainguyen@esofthead.com', '2013-02-27 17:10:04.0', 'hainguyen@esofthead.com', NULL, NULL, '2013-02-27 17:10:04.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (27, 'csacsa', NULL, 'hainguyen@esofthead.com', '2013-02-27 18:32:29.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-27 18:32:29.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (28, 't4y43y', NULL, 'hainguyen@esofthead.com', '2013-02-27 18:42:26.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-20 16:24:11.0', 'Open', '2013-03-04 14:19:30.0', 'y4y43eee egewgew rehreh<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 't4t43tu jeee gewgewg ds<br>', NULL, NULL, 1, 3, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (29, 'fefef', NULL, 'hainguyen@esofthead.com', '2013-02-28 12:26:56.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-28 12:26:56.0', 'Open', NULL, 'fewgeg gewg<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'gewg e<br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (30, 'fewfgew', NULL, NULL, '2013-02-28 12:44:48.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-28 12:44:48.0', 'Open', NULL, 'gegew<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'gewg<br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (31, 'fxxxx', NULL, 'hainguyen@esofthead.com', '2013-02-28 12:45:08.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-28 12:45:08.0', 'Open', NULL, 'gewg<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'gewgew<br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (32, 'fefew e', NULL, NULL, '2013-02-28 13:44:58.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-28 13:44:58.0', 'Open', NULL, 'ew<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'gewg e<br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (33, 'gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee', NULL, 'hainguyen@esofthead.com', '2013-02-28 13:45:48.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-08 09:09:31.0', 'Won''t Fix', NULL, 'rgregre gregergre r<br><br>grpeogjre[hgjkre[k [k[wkgr[kh[rek[k p[kr[hkreh[kreh[pk [k[rkhg[rekhr[ek[k [kg[rekh<br>vvdsvd<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'gger rr ewgjpoewjgewj pojegpoewjgewpjgpj pjepojgewgew<br>egegew<br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (34, 'grgre', NULL, 'hainguyen@esofthead.com', '2013-02-28 14:03:37.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-28 14:03:37.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (35, 'tet', NULL, 'hainguyen@esofthead.com', '2013-02-28 14:10:40.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-28 14:32:59.0', 'Open', NULL, 'gewg ggrgre afewgewg<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'gew eewegew<br>', NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (36, 'test upload image', NULL, NULL, '2013-02-28 18:26:50.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-02-28 23:23:08.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (37, 'regre', NULL, 'hainguyen@esofthead.com', '2013-03-09 21:00:24.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-25 22:22:55.0', 'Won''t Fix', NULL, NULL, 'Fixed', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (38, 'f3f34f43', NULL, 'hainguyen@esofthead.com', '2013-03-09 21:01:23.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-19 21:01:33.0', 'Close', NULL, '3f3<br>', 'Fixed', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 'fefew w<br>', NULL, NULL, 1, 6, 2);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (39, 'f3f34', NULL, NULL, '2013-03-09 21:01:50.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-09 21:01:50.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (40, 'fefew', NULL, 'hainguyen@esofthead.com', '2013-03-18 16:09:33.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-18 16:09:33.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (41, 'fewfewf', NULL, 'hainguyen@esofthead.com', '2013-03-18 16:09:48.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-21 22:29:12.0', 'In Progress', NULL, 'few<br>', 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, 2);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (42, 'xxx', NULL, 'hainguyen@esofthead.com', '2013-03-22 09:32:09.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-22 09:32:09.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, 1, 111, 1, NULL, 3);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (43, 'xxaa', NULL, 'hainguyen@esofthead.com', '2013-03-22 15:01:52.0', 'hainguyen@esofthead.com', 'Critical', 'Major', '2013-03-25 22:54:22.0', 'Close', NULL, 'gegewgewg<br>', 'Fixed', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, 2.2222222222222223e20, 0, 1, NULL, 3);
INSERT INTO m_tracker_bug(id, summary, detail, assignuser, createdTime, logby, severity, priority, lastUpdatedTime, status, duedate, environment, resolution, cus_int_01, cus_int_02, cus_int_03, cus_int_04, cus_int_05, cus_int_06, cus_int_07, cus_int_08, cus_int_09, cus_int_10, cus_str_01, cus_str_02, cus_str_03, cus_str_04, cus_str_05, cus_time_01, cus_time_02, cus_time_03, cus_time_04, cus_dbl_01, cus_dbl_02, cus_dbl_03, projectid, resolveddate, description, estimateTime, estimateRemainTime, sAccountId, milestoneId, bugkey) VALUES (44, 'test upload big size', NULL, NULL, '2013-03-26 11:14:20.0', 'hainguyen@esofthead.com', 'Major', 'Major', '2013-03-26 11:14:20.0', 'Open', NULL, NULL, 'New issue', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, NULL, 4);
ALTER TABLE m_tracker_bug ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_tracker_bug_related_item -----------------------------------
ALTER TABLE m_tracker_bug_related_item ALTER COLUMN id INT ;
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (1, 8, 'AffVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (2, 8, 'FixVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (3, 8, 'Component', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (4, 8, 'Component', 2);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (34, 10, 'AffVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (35, 10, 'FixVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (36, 10, 'Component', 9);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (37, 10, 'Component', 8);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (38, 10, 'Component', 7);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (39, 10, 'Component', 6);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (40, 10, 'Component', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (41, 10, 'Component', 2);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (42, 10, 'Component', 5);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (43, 10, 'Component', 4);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (44, 10, 'Component', 3);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (61, 4, 'AffVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (63, 4, 'Component', 9);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (64, 4, 'Component', 2);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (65, 4, 'Component', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (66, 4, 'Component', 3);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (70, 2, 'AffVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (72, 2, 'Component', 9);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (73, 2, 'Component', 6);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (74, 2, 'Component', 5);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (145, 1, 'AffVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (146, 1, 'FixVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (147, 1, 'Component', 7);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (148, 1, 'Component', 6);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (149, 1, 'Component', 5);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (160, 11, 'AffVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (161, 11, 'FixVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (162, 11, 'Component', 9);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (163, 11, 'Component', 8);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (164, 11, 'Component', 7);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (165, 11, 'Component', 6);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (166, 11, 'Component', 5);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (167, 11, 'Component', 4);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (168, 11, 'Component', 3);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (169, 11, 'Component', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (170, 9, 'Component', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (171, 9, 'Component', 3);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (172, 9, 'Component', 4);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (173, 9, 'Component', 5);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (174, 9, 'Component', 6);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (175, 9, 'Component', 7);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (176, 9, 'Component', 8);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (177, 9, 'Component', 9);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (178, 2, 'FixVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (179, 4, 'FixVersion', 1);
INSERT INTO m_tracker_bug_related_item(id, bugid, type, typeid) VALUES (181, 37, 'FixVersion', 2);
ALTER TABLE m_tracker_bug_related_item ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_tracker_component -----------------------------------
ALTER TABLE m_tracker_component ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (1, 1, 'fwqfqw', NULL, 'fwfqw', NULL, 1, NULL, NULL);
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (3, 1, 'fewfew', 'a@a.com', 'ggre rr sgergre', 'hainguyen@esofthead.com', 1, '2013-01-28 11:51:16.0', '2013-01-28 11:50:36.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (4, 1, 'fewjfoewjgowj jpjpejgpewgweg', NULL, NULL, 'hainguyen@esofthead.com', 1, '2013-01-28 13:40:55.0', '2013-01-28 13:40:55.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (5, 1, 'gewgewpgj egegewg', NULL, NULL, 'hainguyen@esofthead.com', 1, '2013-01-28 13:41:02.0', '2013-01-28 13:41:02.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (6, 1, 'gewgewg k[krh[ktj[kt', NULL, NULL, 'hainguyen@esofthead.com', 1, '2013-01-28 13:41:06.0', '2013-01-28 13:41:06.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (7, 1, 'dvoidsvojdso ojpjkt[jke', NULL, NULL, 'hainguyen@esofthead.com', 1, '2013-01-28 13:41:10.0', '2013-01-28 13:41:10.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (8, 1, 'gewogewogj pejgpewjgpwe ewgewgw', NULL, NULL, 'hainguyen@esofthead.com', 1, '2013-01-28 13:41:14.0', '2013-01-28 13:41:14.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (9, 1, 'eowgeowh pewjgpewjg ewjgpwg', 'c@a.com', 'ofjewjf\nfewfjewfj fewgew\ngepwjgew', 'hainguyen@esofthead.com', 1, '2013-01-31 20:47:12.0', '2013-01-28 13:41:18.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (10, 2, 'ffew', 'a@a.com', 'wqfwf w', 'hainguyen@esofthead.com', 1, '2013-03-13 11:19:22.0', '2013-03-13 11:19:22.0');
INSERT INTO m_tracker_component(id, projectid, componentname, userlead, description, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (11, 6, 'eew', 'user2', 'gewg e', 'hainguyen@esofthead.com', 1, '2013-03-20 10:59:44.0', '2013-03-20 10:59:44.0');
ALTER TABLE m_tracker_component ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_tracker_version -----------------------------------
ALTER TABLE m_tracker_version ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_tracker_version(id, projectid, description, duedate, versionname, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (1, 1, 'gewgewge', NULL, 'ewgewg', NULL, 1, NULL, NULL);
INSERT INTO m_tracker_version(id, projectid, description, duedate, versionname, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (2, 2, 't4t43', NULL, 'fewt', NULL, 1, '2013-03-13 13:35:00.0', '2013-03-13 13:35:00.0');
INSERT INTO m_tracker_version(id, projectid, description, duedate, versionname, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (3, 6, 'fefew', '2013-03-20 11:13:04.0', 'ff', NULL, 1, '2013-03-20 11:13:10.0', '2013-03-20 11:13:10.0');
INSERT INTO m_tracker_version(id, projectid, description, duedate, versionname, createdUser, sAccountId, lastUpdatedTime, createdTime) VALUES (4, 6, 'vsdvs', '2013-03-18 11:51:39.0', 'vdvs', NULL, 1, '2013-03-22 11:51:54.0', '2013-03-22 11:51:46.0');
ALTER TABLE m_tracker_version ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for s_account -----------------------------------
ALTER TABLE s_account ALTER COLUMN id INT ;
INSERT INTO s_account(id, createdTime, billingPlanId, accountName, status, paymentMethod) VALUES (1, NULL, NULL, NULL, NULL, NULL);
ALTER TABLE s_account ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for s_activitystream -----------------------------------
ALTER TABLE s_activitystream ALTER COLUMN id INT ;
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (1, 1, 'Account', 1, '2013-01-22 21:50:34.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'dwqdwqdwq', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (2, 1, 'Account', 1, '2013-01-22 22:53:53.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'dwqdwqdwq', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (3, 1, 'Message', 1, '2013-01-22 22:54:38.0', 'create', 'hainguyen@esofthead.com', 'Project', 'cscsac', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (4, 1, 'Contact', 1, '2013-01-22 22:55:34.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'c s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (5, 1, 'Call', 1, '2013-01-22 23:02:05.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'ccsaca', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (6, 1, 'Campaign', 1, '2013-01-22 23:07:10.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gewgwe ded', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (7, 1, 'Opportunity', 1, '2013-01-22 23:07:37.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gegewg', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (8, 1, 'Milestone', 1, '2013-01-22 23:13:02.0', 'create', 'hainguyen@esofthead.com', 'Project', 'qdqdf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (9, 1, 'Risk', 1, '2013-01-22 23:14:42.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fwqfv qwfqw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (10, 1, 'TaskList', 1, '2013-01-23 01:01:26.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dwwq', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (13, 1, 'Bug', 1, '2013-01-23 01:10:00.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ceew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (14, 1, 'TaskList', 2, '2013-01-23 08:32:13.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (15, 1, 'Bug', 1, '2013-01-23 09:05:10.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (16, 1, 'Bug', 1, '2013-01-23 09:09:42.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (17, 1, 'Bug', 1, '2013-01-23 09:09:59.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (18, 1, 'Bug', 1, '2013-01-23 09:11:47.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (19, 1, 'Bug', 1, '2013-01-23 09:11:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew  e', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (20, 1, 'Lead', 1, '2013-01-23 09:52:57.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gergre', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (21, 1, 'Problem', 1, '2013-01-23 10:49:49.0', 'create', 'hainguyen@esofthead.com', 'Project', 'eee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (22, 1, 'Message', 2, '2013-01-23 10:51:01.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dwqdwq e', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (23, 1, 'Bug', 1, '2013-01-23 11:25:00.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew  e ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (24, 1, 'Bug', 1, '2013-01-23 12:24:17.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew  e ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (25, 1, 'Component', 1, '2013-01-23 18:10:34.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fwqfqw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (26, 1, 'Component', 2, '2013-01-23 18:10:47.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fqwf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (27, 1, 'Version', 1, '2013-01-23 18:11:04.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (28, 1, 'Task', 1, '2013-01-23 21:40:02.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ffewfew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (29, 1, 'Milestone', 2, '2013-01-24 09:28:10.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefewfw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (30, 1, 'Risk', 2, '2013-01-24 11:44:28.0', 'create', 'hainguyen@esofthead.com', 'Project', 'risk', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (31, 1, 'Task', 2, '2013-01-24 13:27:41.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewfefew ewgwgew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (33, 1, 'Project', 2, '2013-01-24 16:41:37.0', 'create', 'hainguyen@esofthead.com', 'Project', 'MyCollab', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (35, 1, 'Project', 3, '2013-01-24 16:45:50.0', 'create', 'hainguyen@esofthead.com', 'Project', 'TEST', 3);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (36, 1, 'Task', 1, '2013-01-24 20:59:26.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ffewfew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (37, 1, 'Task', 3, '2013-01-24 21:00:03.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (38, 1, 'TaskList', 3, '2013-01-24 21:00:43.0', 'create', 'hainguyen@esofthead.com', 'Project', 'task group 1', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (39, 1, 'TaskList', 4, '2013-01-24 21:01:09.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ffew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (40, 1, 'TaskList', 5, '2013-01-24 21:01:21.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (41, 1, 'TaskList', 6, '2013-01-24 21:01:53.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egeg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (42, 1, 'Task', 4, '2013-01-24 21:02:10.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ss ss', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (43, 1, 'Task', 4, '2013-01-24 21:02:29.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ss ss', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (44, 1, 'TaskList', 7, '2013-01-24 21:02:53.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ww ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (45, 1, 'Task', 5, '2013-01-24 21:03:09.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egwgrg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (46, 1, 'TaskList', 8, '2013-01-25 09:33:13.0', 'create', 'hainguyen@esofthead.com', 'Project', 'grgre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (47, 1, 'Bug', 2, '2013-01-25 10:47:41.0', 'create', 'hainguyen@esofthead.com', 'Project', 'wfefew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (48, 1, 'Meeting', 1, '2013-01-25 12:36:26.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gregre', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (49, 1, 'Meeting', 1, '2013-01-25 12:36:29.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'gregre', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (50, 1, 'Meeting', 2, '2013-01-25 12:36:48.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'vdvdsv', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (51, 1, 'Meeting', 2, '2013-01-25 12:37:16.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'vdvdsv', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (52, 1, 'Meeting', 2, '2013-01-25 12:37:43.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'vdvdsv', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (53, 1, 'Meeting', 2, '2013-01-25 12:37:43.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'vdvdsv', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (55, 1, 'Project', 4, '2013-01-25 14:01:18.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dwqdwqfwq wfwfwqf', 4);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (56, 1, 'Bug', 2, '2013-01-25 14:07:59.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wfefew grgre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (57, 1, 'Bug', 2, '2013-01-25 14:08:23.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wfefew grgre eefefewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (58, 1, 'Account', 1, '2013-01-25 14:24:32.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'dwqdwqdwq', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (59, 1, 'Bug', 3, '2013-01-25 15:30:36.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gegg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (60, 1, 'Bug', 4, '2013-01-25 16:04:59.0', 'create', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (61, 1, 'Bug', 5, '2013-01-25 16:05:19.0', 'create', 'hainguyen@esofthead.com', 'Project', 'saas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (62, 1, 'Message', 3, '2013-01-25 17:48:08.0', 'create', 'hainguyen@esofthead.com', 'Project', 'sss', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (63, 1, 'Bug', 6, '2013-01-25 18:02:15.0', 'create', 'hainguyen@esofthead.com', 'Project', 'grg wegrgre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (64, 1, 'Bug', 6, '2013-01-25 18:04:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'grg wegrgre d', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (65, 1, 'Bug', 7, '2013-01-25 18:06:53.0', 'create', 'hainguyen@esofthead.com', 'Project', 'vv grehhe', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (66, 1, 'Bug', 5, '2013-01-25 18:43:08.0', 'update', 'hainguyen@esofthead.com', 'Project', 'saas s ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (67, 1, 'Bug', 5, '2013-01-25 18:43:15.0', 'update', 'hainguyen@esofthead.com', 'Project', 'saas s ee  eee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (68, 1, 'Bug', 8, '2013-01-25 23:11:58.0', 'create', 'hainguyen@esofthead.com', 'Project', 'tts', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (69, 1, 'TaskList', 8, '2013-01-28 09:22:13.0', 'update', 'hainguyen@esofthead.com', 'Project', 'grgre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (70, 1, 'TaskList', 6, '2013-01-28 09:23:18.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egeg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (71, 1, 'TaskList', 5, '2013-01-28 09:33:47.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (72, 1, 'TaskList', 1, '2013-01-28 09:39:09.0', 'update', 'hainguyen@esofthead.com', 'Project', 'dwwq', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (73, 1, 'TaskList', 4, '2013-01-28 09:44:41.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ffew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (74, 1, 'TaskList', 3, '2013-01-28 09:44:49.0', 'update', 'hainguyen@esofthead.com', 'Project', 'task group 1', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (75, 1, 'Bug', 8, '2013-01-28 10:20:22.0', 'update', 'hainguyen@esofthead.com', 'Project', 'tts greger', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (76, 1, 'Meeting', 3, '2013-01-28 10:24:34.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gregre r', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (77, 1, 'Meeting', 3, '2013-01-28 10:24:38.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'gregre r', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (78, 1, 'Call', 2, '2013-01-28 10:25:01.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'grgre r', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (79, 1, 'Call', 2, '2013-01-28 10:58:40.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'grgre r', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (80, 1, 'Component', 3, '2013-01-28 11:50:36.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewfew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (81, 1, 'Component', 3, '2013-01-28 11:51:16.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewfew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (82, 1, 'Component', 4, '2013-01-28 13:40:55.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewjfoewjgowj jpjpejgpewgweg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (83, 1, 'Component', 5, '2013-01-28 13:41:02.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewgewpgj egegewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (84, 1, 'Component', 6, '2013-01-28 13:41:06.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewgewg k[krh[ktj[kt', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (85, 1, 'Component', 7, '2013-01-28 13:41:10.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dvoidsvojdso ojpjkt[jke', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (86, 1, 'Component', 8, '2013-01-28 13:41:14.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewogewogj pejgpewjgpwe ewgewgw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (87, 1, 'Component', 9, '2013-01-28 13:41:18.0', 'create', 'hainguyen@esofthead.com', 'Project', 'eowgeowh pewjgpewjg ewjgpwg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (88, 1, 'Bug', 9, '2013-01-28 13:42:57.0', 'create', 'hainguyen@esofthead.com', 'Project', 'vsdvd ewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (89, 1, 'Bug', 10, '2013-01-28 15:57:40.0', 'create', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (90, 1, 'Bug', 10, '2013-01-28 18:28:56.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (91, 1, 'Bug', 10, '2013-01-28 18:34:59.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (92, 1, 'Bug', 10, '2013-01-28 18:39:24.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (93, 1, 'Bug', 10, '2013-01-28 21:09:55.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (94, 1, 'Bug', 10, '2013-01-28 21:15:33.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (95, 1, 'Bug', 10, '2013-01-28 21:20:15.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (96, 1, 'Bug', 10, '2013-01-28 22:02:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (97, 1, 'Bug', 10, '2013-01-28 22:05:17.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (98, 1, 'Bug', 10, '2013-01-28 22:10:15.0', 'update', 'hainguyen@esofthead.com', 'Project', 'TEst bug related item', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (99, 1, 'Component', 9, '2013-01-28 22:19:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'eowgeowh pewjgpewjg ewjgpwg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (100, 1, 'Component', 9, '2013-01-28 22:20:01.0', 'update', 'hainguyen@esofthead.com', 'Project', 'eowgeowh pewjgpewjg ewjgpwg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (101, 1, 'Bug', 4, '2013-01-28 22:39:42.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (102, 1, 'Bug', 4, '2013-01-29 08:44:40.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (103, 1, 'Bug', 4, '2013-01-29 08:44:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (104, 1, 'Bug', 4, '2013-01-29 09:02:44.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (105, 1, 'Bug', 4, '2013-01-29 09:15:27.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (106, 1, 'Bug', 4, '2013-01-29 09:15:35.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (107, 1, 'Bug', 4, '2013-01-29 09:51:10.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (108, 1, 'Bug', 4, '2013-01-29 10:00:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (109, 1, 'Account', 2, '2013-01-29 11:16:10.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fewfew ee', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (110, 1, 'Account', 3, '2013-01-29 11:16:16.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fefe ee', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (111, 1, 'Bug', 4, '2013-01-29 11:47:28.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (112, 1, 'Bug', 4, '2013-01-29 11:49:49.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (113, 1, 'Bug', 4, '2013-01-29 11:59:06.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (114, 1, 'Bug', 4, '2013-01-29 12:01:14.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (115, 1, 'Problem', 2, '2013-01-29 13:30:44.0', 'create', 'hainguyen@esofthead.com', 'Project', 'rj3rjutu43j utu4qu43-yu43-yu34-u -u-3ut-43uyu4-4-3u -u-ut-43uy-34uy-3u- u-u-u43y', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (116, 1, 'Bug', 4, '2013-01-29 13:32:17.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (117, 1, 'Bug', 4, '2013-01-29 13:32:32.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (118, 1, 'Bug', 4, '2013-01-29 13:32:44.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (119, 1, 'Bug', 2, '2013-01-29 14:19:27.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wfefew grgre eefefewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (120, 1, 'Bug', 2, '2013-01-29 16:29:21.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wfefew grgre eefefewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (121, 1, 'Bug', 2, '2013-01-29 16:29:34.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wfefew grgre eefefewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (122, 1, 'Contact', 1, '2013-01-29 17:28:47.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'c s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (123, 1, 'Contact', 1, '2013-01-29 17:28:55.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'c s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (124, 1, 'Contact', 1, '2013-01-29 17:31:04.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'c s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (125, 1, 'Contact', 1, '2013-01-29 17:31:21.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'c s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (126, 1, 'Contact', 1, '2013-01-29 17:48:05.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'c s ww', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (127, 1, 'Contact', 2, '2013-01-29 17:48:36.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'ddd', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (128, 1, 'Problem', 2, '2013-01-29 22:58:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'rj3rjutu43j ut', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (129, 1, 'Task', 6, '2013-01-30 11:23:07.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewgewgew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (130, 1, 'Bug', 11, '2013-01-30 21:17:35.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gggreg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (131, 1, 'Bug', 11, '2013-01-31 13:31:21.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (132, 1, 'Bug', 11, '2013-01-31 20:44:05.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (133, 1, 'Component', 9, '2013-01-31 20:47:12.0', 'update', 'hainguyen@esofthead.com', 'Project', 'eowgeowh pewjgpewjg ewjgpwg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (134, 1, 'Account', 3, '2013-02-01 09:25:13.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'eSoftHead', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (135, 1, 'Account', 3, '2013-02-01 09:26:36.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'eSoftHead', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (136, 1, 'Call', 2, '2013-02-01 09:27:04.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'Meeting Android team to discuss a potential project', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (137, 1, 'Call', 2, '2013-02-01 09:27:23.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'Meeting Android team to discuss a potential project', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (138, 1, 'Call', 2, '2013-02-01 09:27:46.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'Meeting Android team to discuss a potential project', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (139, 1, 'Lead', 1, '2013-02-01 09:28:32.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'Nguyen', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (140, 1, 'Lead', 1, '2013-02-01 09:28:47.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'Nguyen', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (142, 1, 'Task', 6, '2013-02-03 12:56:08.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgewgew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (143, 1, 'Task', 7, '2013-02-03 13:31:24.0', 'create', 'hainguyen@esofthead.com', 'Project', 'csacas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (144, 1, 'Task', 8, '2013-02-03 13:31:53.0', 'create', 'hainguyen@esofthead.com', 'Project', 'cascas w', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (145, 1, 'Task', 9, '2013-02-03 13:32:28.0', 'create', 'hainguyen@esofthead.com', 'Project', 'www', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (146, 1, 'Task', 10, '2013-02-03 13:33:10.0', 'create', 'hainguyen@esofthead.com', 'Project', 'aaa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (147, 1, 'Opportunity', 2, '2013-02-03 21:10:47.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fefe e', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (148, 1, 'Risk', 1, '2013-02-03 21:35:41.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fwqfv qwfqw qwqwfewwe qfefewf eeee vefewgrehgre ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (149, 1, 'Risk', 1, '2013-02-03 21:36:05.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fwqfv qwfqw qwqwfewwe qfefewf eeee vefewgrehgre ee vrebreherhrehre ewggewgw eee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (150, 1, 'Task', 5, '2013-02-04 08:42:03.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egwgrg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (151, 1, 'Account', 2, '2013-02-04 09:03:16.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'fewfew ee eogogh4ghp5ghjp5jhp jpjgpj45yhp5jqypojq jpjpyj5yp5jw4ypow54jpj pjphj5w4yopj54pyoj54poj pjp5jyp54wjuy5pw4ojuw5p4ojupoj pojypo5jw4ypoj54ypo5w4jypo5w4jypj po5yjpo5w4jyp54wjy54pwoj ', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (152, 1, 'Account', 4, '2013-02-04 09:03:37.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gewgewg', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (153, 1, 'Account', 5, '2013-02-04 09:03:42.0', 'create', 'hainguyen@esofthead.com', 'Crm', ' d ds sgegew', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (154, 1, 'Account', 6, '2013-02-04 09:03:46.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fefewgew', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (155, 1, 'Risk', 3, '2013-02-04 09:04:45.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gwgre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (156, 1, 'Risk', 4, '2013-02-04 09:04:54.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (157, 1, 'Risk', 5, '2013-02-04 09:05:00.0', 'create', 'hainguyen@esofthead.com', 'Project', 'cscsac', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (158, 1, 'Risk', 6, '2013-02-04 09:05:09.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefe e', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (159, 1, 'Risk', 7, '2013-02-04 09:06:12.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ggre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (160, 1, 'Milestone', 1, '2013-02-04 10:11:09.0', 'update', 'hainguyen@esofthead.com', 'Project', 'qdqdf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (161, 1, 'Bug', 11, '2013-02-04 10:12:04.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg eefewfewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (162, 1, 'Bug', 11, '2013-02-04 10:13:08.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg eefewfewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (163, 1, 'Task', 10, '2013-02-04 10:14:49.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (164, 1, 'Bug', 11, '2013-02-04 10:52:25.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg eefewfewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (165, 1, 'Task', 7, '2013-02-04 10:53:38.0', 'update', 'hainguyen@esofthead.com', 'Project', 'csacas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (166, 1, 'Campaign', 2, '2013-02-04 11:33:43.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fewfew', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (167, 1, 'Campaign', 3, '2013-02-04 11:45:10.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'ggewgew e', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (168, 1, 'Contact', 3, '2013-02-04 13:09:28.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'grgregre', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (169, 1, 'Contact', 4, '2013-02-04 13:20:52.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'ffdsfds', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (170, 1, 'Contact', 4, '2013-02-04 15:30:22.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'ffdsfds', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (171, 1, 'Contact', 5, '2013-02-04 17:21:40.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fewgew', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (172, 1, 'Contact', 5, '2013-02-04 17:22:06.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'fewgew', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (173, 1, 'Bug', 11, '2013-02-04 17:42:15.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg eefewfewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (174, 1, 'Bug', 1, '2013-02-04 17:51:16.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew  e ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (175, 1, 'Bug', 1, '2013-02-04 17:54:08.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ceew  e ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (176, 1, 'Task', 7, '2013-02-04 17:56:52.0', 'update', 'hainguyen@esofthead.com', 'Project', 'csacas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (177, 1, 'Task', 7, '2013-02-04 17:56:57.0', 'update', 'hainguyen@esofthead.com', 'Project', 'csacas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (178, 1, 'Task', 7, '2013-02-04 17:56:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'csacas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (179, 1, 'Task', 7, '2013-02-04 17:56:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'csacas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (180, 1, 'Task', 7, '2013-02-04 17:56:59.0', 'update', 'hainguyen@esofthead.com', 'Project', 'csacas s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (181, 1, 'Task', 8, '2013-02-04 17:57:03.0', 'update', 'hainguyen@esofthead.com', 'Project', 'cascas w', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (182, 1, 'Task', 5, '2013-02-04 17:57:35.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egwgrg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (183, 1, 'Task', 9, '2013-02-04 18:02:52.0', 'update', 'hainguyen@esofthead.com', 'Project', 'www', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (184, 1, 'Task', 5, '2013-02-04 18:06:16.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egwgrg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (185, 1, 'TaskList', 9, '2013-02-04 18:07:40.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (186, 1, 'Task', 11, '2013-02-04 18:07:48.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (187, 1, 'Opportunity', 3, '2013-02-04 21:11:53.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'ffew e', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (188, 1, 'Opportunity', 4, '2013-02-04 21:12:10.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'rre r', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (189, 1, 'Case', 1, '2013-02-04 21:14:35.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'hthtr t', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (190, 1, 'Case', 2, '2013-02-04 21:14:53.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'hth t', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (191, 1, 'Lead', 2, '2013-02-05 10:04:16.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'wdwqdwq w', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (192, 1, 'Lead', 2, '2013-02-05 10:05:01.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'wdwqdwq w', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (193, 1, 'Lead', 2, '2013-02-05 14:00:00.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'wdwqdwq w', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (194, 1, 'Opportunity', 5, '2013-02-05 15:47:21.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'opp', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (195, 1, 'Opportunity', 6, '2013-02-05 15:52:18.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'cscsac', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (196, 1, 'Opportunity', 7, '2013-02-05 16:11:44.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fefewf', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (197, 1, 'Opportunity', 8, '2013-02-05 16:25:48.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fefe ee', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (198, 1, 'Opportunity', 9, '2013-02-05 16:25:59.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'xxx', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (199, 1, 'Account', 7, '2013-02-05 21:20:42.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'csacas s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (200, 1, 'Lead', 3, '2013-02-06 09:49:36.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gg e', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (201, 1, 'Lead', 4, '2013-02-06 10:14:06.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'hreher', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (202, 1, 'Task', 12, '2013-02-06 14:51:10.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (203, 1, 'Campaign', 4, '2013-02-06 15:46:01.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'csacsac s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (204, 1, 'Lead', 5, '2013-02-07 10:02:07.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'gewg e', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (205, 1, 'Lead', 6, '2013-02-07 10:13:40.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'ewfef', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (206, 1, 'Lead', 6, '2013-02-07 10:13:44.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'ewfef', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (207, 1, 'Lead', 7, '2013-02-07 10:14:19.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'fewf', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (208, 1, 'Lead', 8, '2013-02-07 10:49:17.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'sss', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (209, 1, 'Contact', 6, '2013-02-07 13:17:06.0', 'create', 'hainguyen@esofthead.com', 'Crm', 'eewg', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (210, 1, 'Task', 12, '2013-02-07 15:24:56.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (211, 1, 'Bug', 12, '2013-02-07 15:27:23.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test ex', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (212, 1, 'Task', 13, '2013-02-07 15:34:05.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egwegew r', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (213, 1, 'Task', 14, '2013-02-07 15:34:36.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (214, 1, 'Task', 15, '2013-02-07 15:38:10.0', 'create', 'hainguyen@esofthead.com', 'Project', 'xxx', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (215, 1, 'Task', 16, '2013-02-07 15:38:28.0', 'create', 'hainguyen@esofthead.com', 'Project', 'yyy', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (216, 1, 'Task', 17, '2013-02-08 10:33:04.0', 'create', 'hainguyen@esofthead.com', 'Project', 'grgrg r', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (217, 1, 'Task', 18, '2013-02-08 10:37:51.0', 'create', 'hainguyen@esofthead.com', 'Project', 'sss', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (218, 1, 'Task', 19, '2013-02-08 10:38:01.0', 'create', 'hainguyen@esofthead.com', 'Project', 'yyy', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (219, 1, 'Task', 20, '2013-02-08 11:04:07.0', 'create', 'hainguyen@esofthead.com', 'Project', 'qqq', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (220, 1, 'Task', 21, '2013-02-08 11:06:07.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewgewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (221, 1, 'Task', 22, '2013-02-08 11:06:14.0', 'create', 'hainguyen@esofthead.com', 'Project', 'xxxx', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (222, 1, 'Bug', 11, '2013-02-12 06:07:27.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg eefewfewgewg vdsvd', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (223, 1, 'Task', 10, '2013-02-12 17:55:41.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (224, 1, 'Task', 16, '2013-02-13 06:08:18.0', 'update', 'hainguyen@esofthead.com', 'Project', 'yyy', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (225, 1, 'Task', 18, '2013-02-13 06:08:23.0', 'update', 'hainguyen@esofthead.com', 'Project', 'sss', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (226, 1, 'Task', 14, '2013-02-13 06:08:32.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (227, 1, 'Task', 2, '2013-02-13 06:10:03.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewfefew ewgwgew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (228, 1, 'Task', 17, '2013-02-13 06:10:12.0', 'update', 'hainguyen@esofthead.com', 'Project', 'grgrg r', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (229, 1, 'Bug', 11, '2013-02-13 06:41:18.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gggreg eefewfewgewg vdsvd', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (230, 1, 'Bug', 12, '2013-02-13 06:55:29.0', 'update', 'hainguyen@esofthead.com', 'Project', 'test ex', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (231, 1, 'Bug', 9, '2013-02-13 06:56:00.0', 'update', 'hainguyen@esofthead.com', 'Project', 'vsdvd ewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (232, 1, 'Bug', 2, '2013-02-13 10:42:30.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wfefew grgre eefefewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (233, 1, 'Task', 15, '2013-02-13 10:44:20.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxx', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (234, 1, 'Task', 12, '2013-02-13 10:44:25.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (235, 1, 'Bug', 13, '2013-02-13 11:30:39.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fqwfwq', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (236, 1, 'Bug', 14, '2013-02-13 13:45:41.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fwqfwqf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (237, 1, 'Bug', 15, '2013-02-13 13:52:00.0', 'create', 'hainguyen@esofthead.com', 'Project', 'a1', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (238, 1, 'Bug', 16, '2013-02-13 13:55:40.0', 'create', 'hainguyen@esofthead.com', 'Project', 'a2', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (239, 1, 'Bug', 17, '2013-02-13 13:57:58.0', 'create', 'hainguyen@esofthead.com', 'Project', 'r3r32', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (240, 1, 'Bug', 18, '2013-02-13 14:04:23.0', 'create', 'hainguyen@esofthead.com', 'Project', 'a3', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (241, 1, 'Problem', 2, '2013-02-16 21:47:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'rj3rjutu43j ut', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (242, 1, 'Risk', 7, '2013-02-16 21:48:13.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ggre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (243, 1, 'Bug', 18, '2013-02-17 22:40:34.0', 'update', 'hainguyen@esofthead.com', 'Project', 'a3', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (244, 1, 'Bug', 4, '2013-02-18 11:05:27.0', 'update', 'hainguyen@esofthead.com', 'Project', 'weggr', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (245, 1, 'Bug', 18, '2013-02-19 21:40:35.0', 'update', 'a@a.com', 'Project', 'a3', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (246, 1, 'Bug', 19, '2013-02-20 08:44:46.0', 'create', 'hainguyen@esofthead.com', 'Project', 'grgrg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (247, 1, 'Bug', 20, '2013-02-20 08:53:57.0', 'create', 'hainguyen@esofthead.com', 'Project', 'aaa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (248, 1, 'Bug', 22, '2013-02-20 10:24:27.0', 'create', 'hainguyen@esofthead.com', 'Project', 'wgg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (249, 1, 'Problem', 3, '2013-02-20 15:47:34.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ifheofhewohoh oehgioehgioehwioh hegihegiewhgpewh phpghpogjewopgjewop jpejwgopewjgopejgopj pjeogpjewopgjewopj jegopejwgopjewopj pjepogjewpogj ewjgpewjh', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (250, 1, 'Problem', 3, '2013-02-20 17:38:31.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ifheofhewohoh oehgioehgioehwioh hegihegiewhgpewh phpghpogjewopgjewop jpejwgopewjgopejgopj pjeogpjewopgjewopj jegopejwgopjewopj pjepogjewpogj ewjgpewjh', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (251, 1, 'Task', 23, '2013-02-21 09:40:49.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (252, 1, 'Task', 24, '2013-02-21 17:16:09.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test 1', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (253, 1, 'Task', 25, '2013-02-21 17:19:47.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test 2', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (254, 1, 'Task', 26, '2013-02-21 17:21:40.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (255, 1, 'Task', 27, '2013-02-21 22:40:01.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ss', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (256, 1, 'TaskList', 7, '2013-02-21 22:40:44.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ww ee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (257, 1, 'Task', 29, '2013-02-21 23:57:50.0', 'create', 'hainguyen@esofthead.com', 'Project', 'aaa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (258, 1, 'Task', 30, '2013-02-22 00:02:01.0', 'create', 'hainguyen@esofthead.com', 'Project', 'www', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (259, 1, 'Bug', 22, '2013-02-22 09:17:31.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wgg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (260, 1, 'Bug', 22, '2013-02-22 09:17:55.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wgg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (261, 1, 'Bug', 20, '2013-02-22 09:33:26.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (262, 1, 'Risk', 7, '2013-02-22 13:23:01.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ggre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (263, 1, 'Risk', 7, '2013-02-22 13:23:16.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ggre', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (264, 1, 'Bug', 22, '2013-02-22 16:11:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wgg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (265, 1, 'Project', 5, '2013-02-22 16:25:55.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test', 5);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (266, 1, 'Task', 31, '2013-02-25 20:35:49.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (267, 1, 'Task', 32, '2013-02-25 20:40:19.0', 'create', 'hainguyen@esofthead.com', 'Project', 'grgreg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (268, 1, 'Task', 21, '2013-02-25 20:44:47.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ewgewgewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (269, 1, 'Task', 24, '2013-02-25 20:44:48.0', 'update', 'hainguyen@esofthead.com', 'Project', 'test 1', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (270, 1, 'Task', 32, '2013-02-25 20:44:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'grgreg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (271, 1, 'Task', 31, '2013-02-25 20:44:51.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ewee', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (272, 1, 'Task', 30, '2013-02-25 20:44:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'www', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (273, 1, 'Task', 29, '2013-02-25 20:44:54.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (274, 1, 'Task', 26, '2013-02-25 20:44:55.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fefew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (275, 1, 'Task', 25, '2013-02-25 20:44:56.0', 'update', 'hainguyen@esofthead.com', 'Project', 'test 2', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (276, 1, 'Task', 23, '2013-02-25 20:44:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'test', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (277, 1, 'Task', 33, '2013-02-25 20:45:05.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewgw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (278, 1, 'Task', 34, '2013-02-25 21:00:20.0', 'create', 'hainguyen@esofthead.com', 'Project', 'grgrg r', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (279, 1, 'Task', 35, '2013-02-25 21:07:53.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fafa s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (280, 1, 'Task', 36, '2013-02-25 21:41:16.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dds', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (281, 1, 'Task', 37, '2013-02-25 21:48:43.0', 'create', 'hainguyen@esofthead.com', 'Project', 'rey5y', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (282, 1, 'Task', 38, '2013-02-25 21:51:22.0', 'create', 'hainguyen@esofthead.com', 'Project', 'rree', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (283, 1, 'Task', 13, '2013-02-25 21:55:07.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egwegew r', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (284, 1, 'Task', 33, '2013-02-25 21:55:09.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (285, 1, 'Task', 35, '2013-02-25 21:55:10.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fafa s', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (286, 1, 'Task', 37, '2013-02-25 21:55:11.0', 'update', 'hainguyen@esofthead.com', 'Project', 'rey5y', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (287, 1, 'Task', 39, '2013-02-25 21:55:24.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ykytkt', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (288, 1, 'Task', 40, '2013-02-25 22:00:59.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ssafsa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (289, 1, 'Task', 41, '2013-02-25 22:06:08.0', 'create', 'hainguyen@esofthead.com', 'Project', 'xxxx', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (290, 1, 'Task', 41, '2013-02-25 22:16:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxxx', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (291, 1, 'Task', 40, '2013-02-25 22:16:59.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ssafsa', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (292, 1, 'Task', 39, '2013-02-25 22:17:01.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ykytkt', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (293, 1, 'Task', 42, '2013-02-25 22:17:09.0', 'create', 'hainguyen@esofthead.com', 'Project', 'zzz', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (294, 1, 'Task', 43, '2013-02-25 22:22:45.0', 'create', 'hainguyen@esofthead.com', 'Project', 'yyyy', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (295, 1, 'Task', 44, '2013-02-25 22:28:22.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewfew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (296, 1, 'Task', 44, '2013-02-25 23:08:08.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewfew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (297, 1, 'Task', 43, '2013-02-25 23:08:09.0', 'update', 'hainguyen@esofthead.com', 'Project', 'yyyy', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (298, 1, 'Task', 42, '2013-02-25 23:08:10.0', 'update', 'hainguyen@esofthead.com', 'Project', 'zzz', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (299, 1, 'Task', 38, '2013-02-25 23:08:12.0', 'update', 'hainguyen@esofthead.com', 'Project', 'rree', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (300, 1, 'Task', 45, '2013-02-25 23:08:23.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (301, 1, 'Task', 46, '2013-02-26 08:35:12.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefewf efew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (302, 1, 'Task', 47, '2013-02-26 10:11:53.0', 'create', 'hainguyen@esofthead.com', 'Project', 'afewfewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (303, 1, 'Task', 48, '2013-02-26 13:05:43.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test accc', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (304, 1, 'Task', 49, '2013-02-26 13:24:21.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egg egewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (305, 1, 'Bug', 22, '2013-02-27 09:36:21.0', 'update', 'hainguyen@esofthead.com', 'Project', 'wgg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (306, 1, 'Bug', 17, '2013-02-27 09:42:33.0', 'update', 'hainguyen@esofthead.com', 'Project', 'r3r32', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (307, 1, 'Bug', 17, '2013-02-27 09:44:52.0', 'update', 'hainguyen@esofthead.com', 'Project', 'r3r32', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (308, 1, 'Bug', 17, '2013-02-27 09:45:43.0', 'update', 'hainguyen@esofthead.com', 'Project', 'r3r32', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (309, 1, 'Project', 6, '2013-02-27 09:49:37.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (310, 1, 'Project', 7, '2013-02-27 09:49:54.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test1', 7);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (311, 1, 'Account', 7, '2013-02-27 10:18:28.0', 'update', 'hainguyen@esofthead.com', 'Crm', 'csacas s', NULL);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (312, 1, 'TaskList', 10, '2013-02-27 11:34:30.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dvds', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (313, 1, 'Task', 50, '2013-02-27 11:34:37.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefe e', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (314, 1, 'Task', 51, '2013-02-27 11:43:06.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egewgew', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (315, 1, 'Task', 52, '2013-02-27 14:32:59.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewgewg', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (316, 1, 'Task', 53, '2013-02-27 14:56:11.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewgewg', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (317, 1, 'Task', 54, '2013-02-27 16:44:20.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewgew', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (318, 1, 'Bug', 23, '2013-02-27 16:46:02.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egewg e', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (319, 1, 'Bug', 24, '2013-02-27 16:53:06.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewfewf', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (320, 1, 'Bug', 25, '2013-02-27 17:00:22.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gegw', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (321, 1, 'Bug', 26, '2013-02-27 17:10:04.0', 'create', 'hainguyen@esofthead.com', 'Project', 'csacsa', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (322, 1, 'Bug', 27, '2013-02-27 18:32:29.0', 'create', 'hainguyen@esofthead.com', 'Project', 'csacsa', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (323, 1, 'Bug', 28, '2013-02-27 18:42:26.0', 'create', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (324, 1, 'Bug', 28, '2013-02-28 08:40:06.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (325, 1, 'Bug', 28, '2013-02-28 09:56:13.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (326, 1, 'Bug', 28, '2013-02-28 09:56:48.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (327, 1, 'Bug', 28, '2013-02-28 09:57:28.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (328, 1, 'Bug', 28, '2013-02-28 10:28:49.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (329, 1, 'Bug', 28, '2013-02-28 10:38:14.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (330, 1, 'Bug', 28, '2013-02-28 10:47:21.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (331, 1, 'Bug', 28, '2013-02-28 11:01:00.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (332, 1, 'Bug', 28, '2013-02-28 11:06:05.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (333, 1, 'Bug', 28, '2013-02-28 11:08:47.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (334, 1, 'Bug', 28, '2013-02-28 11:15:15.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (335, 1, 'Bug', 28, '2013-02-28 11:18:06.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (336, 1, 'Bug', 28, '2013-02-28 11:19:04.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (337, 1, 'Bug', 28, '2013-02-28 11:24:07.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (338, 1, 'Bug', 28, '2013-02-28 11:26:46.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (339, 1, 'Bug', 28, '2013-02-28 11:36:41.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (340, 1, 'Bug', 28, '2013-02-28 11:40:21.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (341, 1, 'Bug', 28, '2013-02-28 11:46:46.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (342, 1, 'Bug', 29, '2013-02-28 12:26:57.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefef', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (343, 1, 'Bug', 30, '2013-02-28 12:44:48.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewfgew', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (344, 1, 'Bug', 31, '2013-02-28 12:45:08.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fxxxx', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (345, 1, 'Bug', 32, '2013-02-28 13:44:58.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefew e', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (346, 1, 'Bug', 33, '2013-02-28 13:45:48.0', 'create', 'hainguyen@esofthead.com', 'Project', 'gewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (347, 1, 'Bug', 33, '2013-02-28 13:52:54.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (348, 1, 'Bug', 33, '2013-02-28 13:56:28.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (349, 1, 'Bug', 33, '2013-02-28 13:59:25.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (350, 1, 'Bug', 34, '2013-02-28 14:03:37.0', 'create', 'hainguyen@esofthead.com', 'Project', 'grgre', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (351, 1, 'Bug', 35, '2013-02-28 14:10:40.0', 'create', 'hainguyen@esofthead.com', 'Project', 'tet', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (352, 1, 'Task', 55, '2013-02-28 14:12:59.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewgew wgewg', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (353, 1, 'Bug', 35, '2013-02-28 14:17:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'tet', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (354, 1, 'Bug', 35, '2013-02-28 14:32:59.0', 'update', 'hainguyen@esofthead.com', 'Project', 'tet', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (355, 1, 'Bug', 36, '2013-02-28 18:26:50.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test upload image', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (356, 1, 'Problem', 4, '2013-02-28 23:22:00.0', 'create', 'hainguyen@esofthead.com', 'Project', 'few e', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (357, 1, 'Bug', 36, '2013-02-28 23:23:08.0', 'update', 'hainguyen@esofthead.com', 'Project', 'test upload image', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (358, 1, 'Message', 4, '2013-03-01 11:49:21.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fwqfwqfqw', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (359, 1, 'Message', 5, '2013-03-05 20:58:36.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dwqdwq', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (360, 1, 'Task', 56, '2013-03-07 13:29:53.0', 'create', 'hainguyen@esofthead.com', 'Project', 'example', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (361, 1, 'Bug', 33, '2013-03-07 15:31:37.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (362, 1, 'Bug', 33, '2013-03-07 15:31:38.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (363, 1, 'Task', 56, '2013-03-08 08:45:58.0', 'update', 'hainguyen@esofthead.com', 'Project', 'example', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (364, 1, 'Task', 55, '2013-03-08 08:49:43.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ewgew wgewg', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (365, 1, 'Task', 54, '2013-03-08 08:51:27.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewgew', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (366, 1, 'Task', 50, '2013-03-08 09:07:47.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fefe e', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (367, 1, 'Bug', 33, '2013-03-08 09:08:37.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (368, 1, 'Bug', 33, '2013-03-08 09:09:31.0', 'update', 'hainguyen@esofthead.com', 'Project', 'gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (369, 1, 'Milestone', 3, '2013-03-08 13:57:46.0', 'create', 'hainguyen@esofthead.com', 'Project', 'wfwqf', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (370, 1, 'Milestone', 4, '2013-03-08 14:09:57.0', 'create', 'hainguyen@esofthead.com', 'Project', 'rwr', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (371, 1, 'Bug', 28, '2013-03-08 14:19:34.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (372, 1, 'Milestone', 5, '2013-03-09 00:26:20.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (373, 1, 'TaskList', 11, '2013-03-09 00:34:40.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewf', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (374, 1, 'Bug', 37, '2013-03-09 21:00:24.0', 'create', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (375, 1, 'Bug', 38, '2013-03-09 21:01:23.0', 'create', 'hainguyen@esofthead.com', 'Project', 'f3f34f43', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (376, 1, 'Bug', 39, '2013-03-09 21:01:50.0', 'create', 'hainguyen@esofthead.com', 'Project', 'f3f34', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (377, 1, 'Task', 57, '2013-03-11 09:41:56.0', 'create', 'hainguyen@esofthead.com', 'Project', 'aaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (378, 1, 'Task', 58, '2013-03-11 09:42:13.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ddd', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (379, 1, 'Task', 59, '2013-03-11 09:42:26.0', 'create', 'hainguyen@esofthead.com', 'Project', 'efegfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (380, 1, 'Task', 60, '2013-03-11 09:42:56.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ddsgfs', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (381, 1, 'Task', 59, '2013-03-11 10:17:11.0', 'update', 'hainguyen@esofthead.com', 'Project', 'efegfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (382, 1, 'Milestone', 5, '2013-03-12 14:00:37.0', 'update', 'hainguyen@esofthead.com', 'Project', 'test', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (383, 1, 'Milestone', 6, '2013-03-12 23:57:47.0', 'create', 'hainguyen@esofthead.com', 'Project', 'wqfwqfq', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (384, 1, 'Milestone', 7, '2013-03-12 23:58:11.0', 'create', 'hainguyen@esofthead.com', 'Project', 'dwqf', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (385, 1, 'Milestone', 8, '2013-03-12 23:58:41.0', 'create', 'hainguyen@esofthead.com', 'Project', '2211', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (386, 1, 'Milestone', 8, '2013-03-12 23:58:51.0', 'update', 'hainguyen@esofthead.com', 'Project', '2211 few', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (387, 1, 'Milestone', 8, '2013-03-12 23:59:59.0', 'update', 'hainguyen@esofthead.com', 'Project', '2211 few', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (388, 1, 'TaskList', 11, '2013-03-13 02:14:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewf', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (389, 1, 'Bug', 38, '2013-03-13 02:18:33.0', 'update', 'hainguyen@esofthead.com', 'Project', 'f3f34f43', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (390, 1, 'Component', 10, '2013-03-13 11:19:22.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ffew', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (391, 1, 'Version', 2, '2013-03-13 13:35:00.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewt', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (392, 1, 'Bug', 38, '2013-03-13 16:36:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'f3f34f43', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (393, 1, 'TaskList', 12, '2013-03-13 16:58:27.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ggre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (394, 1, 'Milestone', 9, '2013-03-14 10:07:00.0', 'create', 'hainguyen@esofthead.com', 'Project', 'oheowhto  oejwg4', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (395, 1, 'Task', 61, '2013-03-14 13:53:28.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (396, 1, 'Task', 59, '2013-03-14 14:19:18.0', 'update', 'hainguyen@esofthead.com', 'Project', 'efegfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (397, 1, 'Task', 59, '2013-03-14 14:19:23.0', 'update', 'hainguyen@esofthead.com', 'Project', 'efegfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (398, 1, 'Task', 59, '2013-03-14 14:19:28.0', 'update', 'hainguyen@esofthead.com', 'Project', 'efegfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (399, 1, 'Task', 59, '2013-03-14 14:19:46.0', 'update', 'hainguyen@esofthead.com', 'Project', 'efegfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (400, 1, 'Task', 58, '2013-03-14 14:20:30.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ddd', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (401, 1, 'Task', 59, '2013-03-14 14:20:39.0', 'update', 'hainguyen@esofthead.com', 'Project', 'efegfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (402, 1, 'Task', 57, '2013-03-14 14:27:33.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (403, 1, 'Task', 57, '2013-03-14 14:58:07.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (404, 1, 'Task', 61, '2013-03-14 15:16:41.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (405, 1, 'Task', 57, '2013-03-14 17:22:45.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (406, 1, 'Project', 2, '2013-03-15 10:51:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'MyCollab', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (407, 1, 'Task', 57, '2013-03-15 11:03:10.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (408, 1, 'Milestone', 10, '2013-03-17 07:07:04.0', 'create', 'hainguyen@esofthead.com', 'Project', 'egg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (409, 1, 'Task', 62, '2013-03-17 07:29:03.0', 'create', 'hainguyen@esofthead.com', 'Project', 'a', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (410, 1, 'TaskList', 13, '2013-03-17 07:31:22.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ccc', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (411, 1, 'TaskList', 13, '2013-03-17 07:37:27.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ccc', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (412, 1, 'Task', 45, '2013-03-18 10:07:31.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (413, 1, 'Task', 45, '2013-03-18 10:07:36.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (414, 1, 'Task', 57, '2013-03-18 10:20:56.0', 'update', 'hainguyen@esofthead.com', 'Project', 'aaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (415, 1, 'Task', 62, '2013-03-18 10:23:54.0', 'update', 'hainguyen@esofthead.com', 'Project', 'a', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (416, 1, 'Task', 49, '2013-03-18 10:24:08.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egg egewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (417, 1, 'Task', 45, '2013-03-18 10:30:01.0', 'update', 'hainguyen@esofthead.com', 'Project', 'egewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (418, 1, 'Task', 46, '2013-03-18 10:33:54.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fefewf efew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (419, 1, 'Task', 61, '2013-03-18 10:42:32.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewfew e', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (420, 1, 'Task', 34, '2013-03-18 10:44:28.0', 'update', 'hainguyen@esofthead.com', 'Project', 'grgrg r', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (421, 1, 'Task', 47, '2013-03-18 11:03:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'afewfewg', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (422, 1, 'Task', 46, '2013-03-18 11:04:18.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fefewf efew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (423, 1, 'Task', 44, '2013-03-18 11:35:10.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewfew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (424, 1, 'Task', 63, '2013-03-18 11:37:05.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fff', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (425, 1, 'Bug', 40, '2013-03-18 16:09:33.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fefew', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (426, 1, 'Bug', 41, '2013-03-18 16:09:48.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewfewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (427, 1, 'Task', 64, '2013-03-18 20:59:46.0', 'create', 'hainguyen@esofthead.com', 'Project', 'sss', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (428, 1, 'Bug', 38, '2013-03-19 21:01:28.0', 'update', 'hainguyen@esofthead.com', 'Project', 'f3f34f43', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (429, 1, 'Bug', 38, '2013-03-19 21:01:33.0', 'update', 'hainguyen@esofthead.com', 'Project', 'f3f34f43', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (430, 1, 'Component', 11, '2013-03-20 10:59:44.0', 'create', 'hainguyen@esofthead.com', 'Project', 'eew', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (431, 1, 'Version', 3, '2013-03-20 11:13:10.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ff', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (432, 1, 'TaskList', 10, '2013-03-20 14:15:31.0', 'update', 'hainguyen@esofthead.com', 'Project', 'dvds', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (433, 1, 'Bug', 28, '2013-03-20 16:24:11.0', 'update', 'hainguyen@esofthead.com', 'Project', 't4y43y', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (434, 1, 'TaskList', 14, '2013-03-20 21:19:34.0', 'create', 'hainguyen@esofthead.com', 'Project', 'ewgw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (435, 1, 'TaskList', 14, '2013-03-20 21:19:48.0', 'update', 'hainguyen@esofthead.com', 'Project', 'ewgw', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (436, 1, 'Risk', 8, '2013-03-20 23:09:23.0', 'create', 'hainguyen@esofthead.com', 'Project', 'fewfe', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (437, 1, 'Bug', 41, '2013-03-21 22:29:12.0', 'update', 'hainguyen@esofthead.com', 'Project', 'fewfewf', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (438, 1, 'Bug', 42, '2013-03-22 09:32:09.0', 'create', 'hainguyen@esofthead.com', 'Project', 'xxx', 1);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (439, 1, 'Version', 4, '2013-03-22 11:51:46.0', 'create', 'hainguyen@esofthead.com', 'Project', 'vdvs', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (440, 1, 'Version', 4, '2013-03-22 11:51:50.0', 'update', 'hainguyen@esofthead.com', 'Project', 'vdvs', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (441, 1, 'Version', 4, '2013-03-22 11:51:54.0', 'update', 'hainguyen@esofthead.com', 'Project', 'vdvs', 6);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (442, 1, 'Bug', 43, '2013-03-22 15:01:52.0', 'create', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (443, 1, 'Bug', 37, '2013-03-25 22:20:37.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (444, 1, 'Bug', 37, '2013-03-25 22:20:40.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (445, 1, 'Bug', 37, '2013-03-25 22:20:40.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (446, 1, 'Bug', 37, '2013-03-25 22:20:40.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (447, 1, 'Bug', 37, '2013-03-25 22:20:45.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (448, 1, 'Bug', 37, '2013-03-25 22:20:48.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (449, 1, 'Bug', 37, '2013-03-25 22:20:57.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (450, 1, 'Bug', 37, '2013-03-25 22:21:12.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (451, 1, 'Bug', 37, '2013-03-25 22:21:31.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (452, 1, 'Bug', 37, '2013-03-25 22:21:43.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (453, 1, 'Bug', 37, '2013-03-25 22:21:48.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (454, 1, 'Bug', 37, '2013-03-25 22:21:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (455, 1, 'Bug', 37, '2013-03-25 22:22:55.0', 'update', 'hainguyen@esofthead.com', 'Project', 'regre', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (456, 1, 'Bug', 43, '2013-03-25 22:23:14.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (457, 1, 'Bug', 43, '2013-03-25 22:23:29.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (458, 1, 'Bug', 43, '2013-03-25 22:23:40.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (459, 1, 'Bug', 43, '2013-03-25 22:23:43.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (460, 1, 'Bug', 43, '2013-03-25 22:23:53.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (461, 1, 'Bug', 43, '2013-03-25 22:24:55.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (462, 1, 'Bug', 43, '2013-03-25 22:35:14.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (463, 1, 'Bug', 43, '2013-03-25 22:35:43.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (464, 1, 'Bug', 43, '2013-03-25 22:54:09.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (465, 1, 'Bug', 43, '2013-03-25 22:54:15.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (466, 1, 'Bug', 43, '2013-03-25 22:54:22.0', 'update', 'hainguyen@esofthead.com', 'Project', 'xxaa', 2);
INSERT INTO s_activitystream(id, sAccountId, type, typeId, createdTime, action, createdUser, module, nameField, extraTypeId) VALUES (467, 1, 'Bug', 44, '2013-03-26 11:14:20.0', 'create', 'hainguyen@esofthead.com', 'Project', 'test upload big size', 2);
ALTER TABLE s_activitystream ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for s_roles -----------------------------------
ALTER TABLE s_roles ALTER COLUMN id INT ;
INSERT INTO s_roles(rolename, description, sAccountId, id) VALUES ('tes', 'fc fff', 1, 1);
ALTER TABLE s_roles ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for s_user -----------------------------------
INSERT INTO s_user(username, firstname, middlename, lastname, nickname, dateofbirth, password, email, website, registeredTime, lastAccessedTime, accountId, company, timezone, registrationSource, isAdmin, registerStatus, roleid, language, country, workPhone, homePhone, facebookAccount, twitterAccount, skypeContact) VALUES ('a@a.com', 'dw w', NULL, 'ww', 'dd', NULL, 'je/+80zTzo/1UhsspUwZ87+/c31HbchhVFAlKq5/jFYSzN4Szzkug1lKXyWuLnqk', 'b@a.com', NULL, NULL, '2013-02-19 00:32:35.0', 1, NULL, NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO s_user(username, firstname, middlename, lastname, nickname, dateofbirth, password, email, website, registeredTime, lastAccessedTime, accountId, company, timezone, registrationSource, isAdmin, registerStatus, roleid, language, country, workPhone, homePhone, facebookAccount, twitterAccount, skypeContact) VALUES ('b@esofthead.com', 'aa', NULL, 'aa', NULL, NULL, '8Ik7q/G+X7EmJbCOC6ZmMEuZlfUePZqaRRvCAy2thRrlxwx5G9v2JYMt423xQxWo', 'b@esofthead.com', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO s_user(username, firstname, middlename, lastname, nickname, dateofbirth, password, email, website, registeredTime, lastAccessedTime, accountId, company, timezone, registrationSource, isAdmin, registerStatus, roleid, language, country, workPhone, homePhone, facebookAccount, twitterAccount, skypeContact) VALUES ('c@a.com', 'dd', NULL, 'dd', 'dd dd', NULL, '4TMucbJRuDUJWvF2cGL1ubajBtP0pxZpbSZA5cBY0hnC90iCWllXJq7ET/0G4WoL', 'c@a.com', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO s_user(username, firstname, middlename, lastname, nickname, dateofbirth, password, email, website, registeredTime, lastAccessedTime, accountId, company, timezone, registrationSource, isAdmin, registerStatus, roleid, language, country, workPhone, homePhone, facebookAccount, twitterAccount, skypeContact) VALUES ('hainguyen@esofthead.com', 'a12', NULL, 'b12', NULL, '1979-03-13 18:17:50.0', '8Ik7q/G+X7EmJbCOC6ZmMEuZlfUePZqaRRvCAy2thRrlxwx5G9v2JYMt423xQxWo', 'hainguyen@esofthead.com', 'http://www.esofthead.com', NULL, '2013-03-12 18:50:36.0', 1, 'eSoftHead', NULL, NULL, 1, NULL, NULL, NULL, 'Vietnam', '', '', '', 'esofthead', '');
INSERT INTO s_user(username, firstname, middlename, lastname, nickname, dateofbirth, password, email, website, registeredTime, lastAccessedTime, accountId, company, timezone, registrationSource, isAdmin, registerStatus, roleid, language, country, workPhone, homePhone, facebookAccount, twitterAccount, skypeContact) VALUES ('user2', 'a2', NULL, 'b2', NULL, NULL, '8Ik7q/G+X7EmJbCOC6ZmMEuZlfUePZqaRRvCAy2thRrlxwx5G9v2JYMt423xQxWo', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO s_user(username, firstname, middlename, lastname, nickname, dateofbirth, password, email, website, registeredTime, lastAccessedTime, accountId, company, timezone, registrationSource, isAdmin, registerStatus, roleid, language, country, workPhone, homePhone, facebookAccount, twitterAccount, skypeContact) VALUES ('x@a.com', 'f', NULL, 'f', NULL, NULL, '4TMucbJRuDUJWvF2cGL1ubajBtP0pxZpbSZA5cBY0hnC90iCWllXJq7ET/0G4WoL', 'haiphucnguyen@gmail.com', NULL, NULL, NULL, 1, NULL, NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


-- Generate script insert data for s_user_preference -----------------------------------
ALTER TABLE s_user_preference ALTER COLUMN id INT ;
INSERT INTO s_user_preference(id, username, lastModuleVisit, lastAccessedTime) VALUES (1, 'hainguyen@esofthead.com', 'Project', '2013-03-27 16:01:13.0');
INSERT INTO s_user_preference(id, username, lastModuleVisit, lastAccessedTime) VALUES (2, 'a@a.com', 'Project', '2013-02-20 13:37:39.0');
INSERT INTO s_user_preference(id, username, lastModuleVisit, lastAccessedTime) VALUES (3, 'user2', 'Project', '2013-03-07 17:52:27.0');
ALTER TABLE s_user_preference ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_audit_log -----------------------------------
ALTER TABLE m_audit_log ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (1, 'com.esofthead.mycollab.module.crm.domain.Account', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-22T22:53:53+07:00"" oldvalue=""2013-01-22T21:50:34+07:00""/><changelog field=""website"" newvalue=""http://www.google.com"" oldvalue=""""/></changeset>', '2013-01-22 22:53:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Account', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (2, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-23T09:05:10+07:00"" oldvalue=""2013-01-23T01:10:00+07:00""/></changeset>', '2013-01-23 09:05:10.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (3, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-23T09:09:42+07:00"" oldvalue=""2013-01-23T09:05:10+07:00""/></changeset>', '2013-01-23 09:09:42.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (4, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-23T09:09:59+07:00"" oldvalue=""2013-01-23T09:09:42+07:00""/><changelog field=""severity"" newvalue=""Major"" oldvalue=""""/></changeset>', '2013-01-23 09:09:59.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (5, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-23T09:11:47+07:00"" oldvalue=""2013-01-23T09:09:59+07:00""/></changeset>', '2013-01-23 09:11:47.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (6, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-23T09:11:58+07:00"" oldvalue=""2013-01-23T09:11:47+07:00""/><changelog field=""summary"" newvalue=""ceew  e"" oldvalue=""ceew""/></changeset>', '2013-01-23 09:11:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (7, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-23T11:25:00+07:00"" oldvalue=""2013-01-23T09:11:58+07:00""/><changelog field=""summary"" newvalue=""ceew  e ee"" oldvalue=""ceew  e""/></changeset>', '2013-01-23 11:25:00.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (8, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""user2""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-23T12:24:17+07:00"" oldvalue=""2013-01-23T11:25:00+07:00""/></changeset>', '2013-01-23 12:24:17.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (9, 'com.esofthead.mycollab.module.crm.domain.Meeting', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T12:36:29+07:00"" oldvalue=""2013-01-25T12:36:26+07:00""/></changeset>', '2013-01-25 12:36:29.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Meeting', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (10, 'com.esofthead.mycollab.module.crm.domain.Meeting', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T12:37:16+07:00"" oldvalue=""2013-01-25T12:36:48+07:00""/></changeset>', '2013-01-25 12:37:16.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Meeting', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (11, 'com.esofthead.mycollab.module.crm.domain.Meeting', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T12:37:43+07:00"" oldvalue=""2013-01-25T12:37:16+07:00""/></changeset>', '2013-01-25 12:37:43.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Meeting', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (12, 'com.esofthead.mycollab.module.crm.domain.Meeting', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset/>', '2013-01-25 12:37:43.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Meeting', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (13, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T14:07:59+07:00"" oldvalue=""2013-01-25T10:47:41+07:00""/><changelog field=""summary"" newvalue=""wfefew grgre"" oldvalue=""wfefew""/></changeset>', '2013-01-25 14:07:59.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (14, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T14:08:23+07:00"" oldvalue=""2013-01-25T14:07:59+07:00""/><changelog field=""summary"" newvalue=""wfefew grgre eefefewf"" oldvalue=""wfefew grgre""/></changeset>', '2013-01-25 14:08:23.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (15, 'com.esofthead.mycollab.module.crm.domain.Account', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T14:24:32+07:00"" oldvalue=""2013-01-22T22:53:53+07:00""/><changelog field=""type"" newvalue=""Analysts"" oldvalue=""""/></changeset>', '2013-01-25 14:24:32.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Account', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (16, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T18:04:58+07:00"" oldvalue=""2013-01-22T18:02:15+07:00""/><changelog field=""summary"" newvalue=""grg wegrgre d"" oldvalue=""grg wegrgre""/></changeset>', '2013-01-25 18:04:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 6, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (17, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T18:43:08+07:00"" oldvalue=""2013-01-25T16:05:19+07:00""/><changelog field=""summary"" newvalue=""saas s ee"" oldvalue=""saas s""/></changeset>', '2013-01-25 18:43:08.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 5, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (18, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-25T18:43:15+07:00"" oldvalue=""2013-01-25T18:43:08+07:00""/><changelog field=""summary"" newvalue=""saas s ee  eee"" oldvalue=""saas s ee""/></changeset>', '2013-01-25 18:43:15.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 5, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (19, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T10:20:22+07:00"" oldvalue=""2013-01-25T23:11:58+07:00""/><changelog field=""summary"" newvalue=""tts greger"" oldvalue=""tts""/></changeset>', '2013-01-28 10:20:22.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 8, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (20, 'com.esofthead.mycollab.module.crm.domain.Meeting', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T10:24:38+07:00"" oldvalue=""2013-01-28T10:24:34+07:00""/></changeset>', '2013-01-28 10:24:38.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Meeting', 3, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (21, 'com.esofthead.mycollab.module.crm.domain.Call', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""isclosed"" newvalue=""true"" oldvalue=""false""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T10:58:40+07:00"" oldvalue=""2013-01-28T10:25:01+07:00""/></changeset>', '2013-01-28 10:58:40.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Call', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (22, 'com.esofthead.mycollab.module.tracker.domain.Component', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""createduser"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""description"" newvalue=""ggre rr sgergre"" oldvalue=""ggre rr""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T11:51:16+07:00"" oldvalue=""2013-01-28T11:50:36+07:00""/><changelog field=""userlead"" newvalue=""a@a.com"" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-01-28 11:51:16.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Component', 3, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (23, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T18:28:56+07:00"" oldvalue=""2013-01-28T15:57:40+07:00""/></changeset>', '2013-01-28 18:28:56.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (24, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""a@a.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T18:34:59+07:00"" oldvalue=""2013-01-28T18:28:56+07:00""/></changeset>', '2013-01-28 18:34:59.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (25, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T18:39:24+07:00"" oldvalue=""2013-01-28T18:34:59+07:00""/></changeset>', '2013-01-28 18:39:24.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (26, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T21:09:55+07:00"" oldvalue=""2013-01-28T18:39:24+07:00""/></changeset>', '2013-01-28 21:09:55.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (27, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T21:15:33+07:00"" oldvalue=""2013-01-28T21:09:55+07:00""/></changeset>', '2013-01-28 21:15:33.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (28, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T21:20:15+07:00"" oldvalue=""2013-01-28T21:15:33+07:00""/></changeset>', '2013-01-28 21:20:15.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (29, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T22:02:58+07:00"" oldvalue=""2013-01-28T21:58:24+07:00""/></changeset>', '2013-01-28 22:02:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (30, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""a@a.com""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T22:05:17+07:00"" oldvalue=""2013-01-28T22:02:58+07:00""/></changeset>', '2013-01-28 22:05:17.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (31, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T22:10:15+07:00"" oldvalue=""2013-01-28T22:05:17+07:00""/><changelog field=""resolution"" newvalue=""Duplicate"" oldvalue=""New issue""/></changeset>', '2013-01-28 22:10:15.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (32, 'com.esofthead.mycollab.module.tracker.domain.Component', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""createduser"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T22:19:50+07:00"" oldvalue=""2013-01-28T13:41:18+07:00""/><changelog field=""userlead"" newvalue=""c@a.com"" oldvalue=""""/></changeset>', '2013-01-28 22:19:50.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Component', 9, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (33, 'com.esofthead.mycollab.module.tracker.domain.Component', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""createduser"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""description"" newvalue=""ofjewjf&#10;fewfjewfj&#10;gepwjgew"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T22:20:01+07:00"" oldvalue=""2013-01-28T22:19:50+07:00""/></changeset>', '2013-01-28 22:20:01.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Component', 9, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (34, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""regreg rr"" oldvalue=""""/><changelog field=""environment"" newvalue=""grg"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-28T22:39:42+07:00"" oldvalue=""2013-01-25T16:04:59+07:00""/><changelog field=""priority"" newvalue=""Critical"" oldvalue=""""/><changelog field=""severity"" newvalue=""Minor"" oldvalue=""""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Open""/></changeset>', '2013-01-28 22:39:42.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (35, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T08:44:40+07:00"" oldvalue=""2013-01-28T22:39:42+07:00""/></changeset>', '2013-01-29 08:44:40.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (36, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T08:44:53+07:00"" oldvalue=""2013-01-29T08:44:40+07:00""/></changeset>', '2013-01-29 08:44:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (37, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T09:02:44+07:00"" oldvalue=""2013-01-29T08:44:53+07:00""/></changeset>', '2013-01-29 09:02:44.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (38, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T09:15:27+07:00"" oldvalue=""2013-01-29T09:02:44+07:00""/></changeset>', '2013-01-29 09:15:27.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (39, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T09:15:35+07:00"" oldvalue=""2013-01-29T09:15:27+07:00""/></changeset>', '2013-01-29 09:15:35.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (40, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""environment"" newvalue=""grg ddd"" oldvalue=""grg""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T09:51:10+07:00"" oldvalue=""2013-01-29T09:15:35+07:00""/></changeset>', '2013-01-29 09:51:10.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (41, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T10:00:53+07:00"" oldvalue=""2013-01-29T09:51:10+07:00""/></changeset>', '2013-01-29 10:00:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (42, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""b@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T11:47:28+07:00"" oldvalue=""2013-01-29T10:00:53+07:00""/></changeset>', '2013-01-29 11:47:28.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (43, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T11:49:49+07:00"" oldvalue=""2013-01-29T11:47:28+07:00""/></changeset>', '2013-01-29 11:49:49.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (44, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T11:59:06+07:00"" oldvalue=""2013-01-29T11:49:49+07:00""/></changeset>', '2013-01-29 11:59:06.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (45, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T12:01:14+07:00"" oldvalue=""2013-01-29T11:59:06+07:00""/><changelog field=""status"" newvalue=""Open"" oldvalue=""Won''t Fix""/></changeset>', '2013-01-29 12:01:14.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (46, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T13:32:17+07:00"" oldvalue=""2013-01-29T12:01:14+07:00""/><changelog field=""status"" newvalue=""In Progress"" oldvalue=""Open""/></changeset>', '2013-01-29 13:32:17.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (47, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T13:32:32+07:00"" oldvalue=""2013-01-29T13:32:17+07:00""/><changelog field=""status"" newvalue=""Test Pending"" oldvalue=""In Progress""/></changeset>', '2013-01-29 13:32:32.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (48, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T13:32:44+07:00"" oldvalue=""2013-01-29T13:32:32+07:00""/></changeset>', '2013-01-29 13:32:44.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (49, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""ggrgre"" oldvalue=""""/><changelog field=""environment"" newvalue=""gegg"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T14:19:27+07:00"" oldvalue=""2013-01-25T14:08:23+07:00""/></changeset>', '2013-01-29 14:19:27.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (50, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T16:29:21+07:00"" oldvalue=""2013-01-29T14:19:27+07:00""/></changeset>', '2013-01-29 16:29:21.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (51, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T16:29:34+07:00"" oldvalue=""2013-01-29T16:29:21+07:00""/></changeset>', '2013-01-29 16:29:34.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (52, 'com.esofthead.mycollab.module.crm.domain.SimpleContact', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T17:28:47+07:00"" oldvalue=""2013-01-22T22:55:34+07:00""/></changeset>', '2013-01-29 17:28:47.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Contact', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (53, 'com.esofthead.mycollab.module.crm.domain.SimpleContact', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""accountId"" newvalue=""3"" oldvalue=""1""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T17:28:55+07:00"" oldvalue=""2013-01-29T17:28:47+07:00""/></changeset>', '2013-01-29 17:28:55.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Contact', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (54, 'com.esofthead.mycollab.module.crm.domain.SimpleContact', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T17:31:04+07:00"" oldvalue=""2013-01-29T17:28:55+07:00""/></changeset>', '2013-01-29 17:31:04.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Contact', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (55, 'com.esofthead.mycollab.module.crm.domain.SimpleContact', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""accountId"" newvalue=""3"" oldvalue=""1""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T17:31:21+07:00"" oldvalue=""2013-01-29T17:31:04+07:00""/></changeset>', '2013-01-29 17:31:21.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Contact', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (56, 'com.esofthead.mycollab.module.crm.domain.SimpleContact', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""contactName"" newvalue=""cascas c s ww"" oldvalue=""cascas c s""/><changelog field=""lastname"" newvalue=""c s ww"" oldvalue=""c s""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T17:48:05+07:00"" oldvalue=""2013-01-29T17:31:21+07:00""/></changeset>', '2013-01-29 17:48:05.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Contact', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (57, 'com.esofthead.mycollab.module.project.domain.Problem', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""issuename"" newvalue=""rj3rjutu43j ut"" oldvalue=""rj3rjutu43j utu4qu43-yu43-yu34-u -u-3ut-43uyu4-4-3u -u-ut-43uy-34uy-3u- u-u-u43y""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-29T22:58:50+07:00"" oldvalue=""2013-01-29T13:30:44+07:00""/></changeset>', '2013-01-29 22:58:50.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Problem', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (58, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-31T13:31:21+07:00"" oldvalue=""2013-01-30T21:17:35+07:00""/></changeset>', '2013-01-31 13:31:21.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (59, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-01-31T20:44:05+07:00"" oldvalue=""2013-01-31T13:31:21+07:00""/></changeset>', '2013-01-31 20:44:05.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (60, 'com.esofthead.mycollab.module.tracker.domain.Component', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""createduser"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""description"" newvalue=""ofjewjf&#10;fewfjewfj fewgew&#10;gepwjgew"" oldvalue=""ofjewjf&#10;fewfjewfj&#10;gepwjgew""/><changelog field=""lastupdatedtime"" newvalue=""2013-01-31T20:47:12+07:00"" oldvalue=""2013-01-28T22:20:01+07:00""/></changeset>', '2013-01-31 20:47:12.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Component', 9, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (61, 'com.esofthead.mycollab.module.crm.domain.Account', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""accountname"" newvalue=""eSoftHead"" oldvalue=""fefe ee""/><changelog field=""assignuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-01T09:25:13+07:00"" oldvalue=""2013-01-29T11:16:15+07:00""/></changeset>', '2013-02-01 09:25:13.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Account', 3, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (62, 'com.esofthead.mycollab.module.crm.domain.Account', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""email"" newvalue=""hainguyen@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-01T09:26:36+07:00"" oldvalue=""2013-02-01T09:25:13+07:00""/></changeset>', '2013-02-01 09:26:36.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Account', 3, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (63, 'com.esofthead.mycollab.module.crm.domain.Call', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-01T09:27:04+07:00"" oldvalue=""2013-01-28T10:58:40+07:00""/><changelog field=""subject"" newvalue=""Meeting Android team to discuss a potential project"" oldvalue=""grgre r""/></changeset>', '2013-02-01 09:27:04.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Call', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (64, 'com.esofthead.mycollab.module.crm.domain.Call', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-01T09:27:23+07:00"" oldvalue=""2013-02-01T09:27:04+07:00""/><changelog field=""startdate"" newvalue=""2013-02-21T09:27:17+07:00"" oldvalue=""""/></changeset>', '2013-02-01 09:27:23.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Call', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (65, 'com.esofthead.mycollab.module.crm.domain.Call', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""calltype"" newvalue=""Inbound"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-01T09:27:46+07:00"" oldvalue=""2013-02-01T09:27:23+07:00""/><changelog field=""startdate"" newvalue=""2013-02-01T09:27:17+07:00"" oldvalue=""2013-02-21T09:27:17+07:00""/><changelog field=""status"" newvalue=""Held"" oldvalue=""""/></changeset>', '2013-02-01 09:27:46.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Call', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (66, 'com.esofthead.mycollab.module.crm.domain.Lead', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""firstname"" newvalue=""Hang"" oldvalue=""grege""/><changelog field=""lastname"" newvalue=""Nguyen"" oldvalue=""gergre""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-01T09:28:32+07:00"" oldvalue=""2013-01-23T09:52:57+07:00""/><changelog field=""othercountry"" newvalue=""Vietnam"" oldvalue=""Andorra""/><changelog field=""primcountry"" newvalue=""Vietnam"" oldvalue=""Antigua and Barbuda""/></changeset>', '2013-02-01 09:28:32.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Lead', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (67, 'com.esofthead.mycollab.module.crm.domain.Lead', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-01T09:28:47+07:00"" oldvalue=""2013-02-01T09:28:32+07:00""/></changeset>', '2013-02-01 09:28:47.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Lead', 1, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (68, 'com.esofthead.mycollab.module.project.domain.Risk', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-03T21:35:41+07:00"" oldvalue=""2013-01-22T23:14:42+07:00""/><changelog field=""riskname"" newvalue=""fwqfv qwfqw qwqwfewwe qfefewf eeee vefewgrehgre ee"" oldvalue=""fwqfv qwfqw""/></changeset>', '2013-02-03 21:35:41.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Risk', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (69, 'com.esofthead.mycollab.module.project.domain.Risk', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-03T21:36:05+07:00"" oldvalue=""2013-02-03T21:35:41+07:00""/><changelog field=""riskname"" newvalue=""fwqfv qwfqw qwqwfewwe qfefewf eeee vefewgrehgre ee vrebreherhrehre ewggewgw eee"" oldvalue=""fwqfv qwfqw qwqwfewwe qfefewf eeee vefewgrehgre ee""/></changeset>', '2013-02-03 21:36:05.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Risk', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (70, 'com.esofthead.mycollab.module.crm.domain.Account', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""accountname"" newvalue=""fewfew ee eogogh4ghp5ghjp5jhp jpjgpj45yhp5jqypojq jpjpyj5yp5jw4ypow54jpj pjphj5w4yopj54pyoj54poj pjp5jyp54wjuy5pw4ojuw5p4ojupoj pojypo5jw4ypoj54ypo5w4jypo5w4jypj po5yjpo5w4jyp54wjy54pwoj "" oldvalue=""fewfew ee""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T09:03:15+07:00"" oldvalue=""2013-01-29T11:16:10+07:00""/></changeset>', '2013-02-04 09:03:16.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Account', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (71, 'com.esofthead.mycollab.module.project.domain.Milestone', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T10:11:09+07:00"" oldvalue=""2013-01-22T23:13:02+07:00""/><changelog field=""startdate"" newvalue=""2013-01-14T23:12:49+07:00"" oldvalue=""2013-01-22T23:12:49+07:00""/></changeset>', '2013-02-04 10:11:09.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Milestone', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (72, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T10:12:04+07:00"" oldvalue=""2013-01-31T20:44:05+07:00""/><changelog field=""summary"" newvalue=""gggreg eefewfewgewg"" oldvalue=""gggreg""/></changeset>', '2013-02-04 10:12:04.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (73, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""b@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T10:13:08+07:00"" oldvalue=""2013-02-04T10:12:04+07:00""/></changeset>', '2013-02-04 10:13:08.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (74, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""duedate"" newvalue=""2013-02-11T10:52:17+07:00"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T10:52:25+07:00"" oldvalue=""2013-02-04T10:13:08+07:00""/><changelog field=""severity"" newvalue=""Minor"" oldvalue=""""/></changeset>', '2013-02-04 10:52:25.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (75, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""enddate"" newvalue=""2013-02-28T10:53:15+07:00"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T10:53:38+07:00"" oldvalue=""2013-02-03T13:31:24+07:00""/><changelog field=""startdate"" newvalue=""2013-02-04T10:53:15+07:00"" oldvalue=""""/></changeset>', '2013-02-04 10:53:38.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (76, 'com.esofthead.mycollab.module.crm.domain.SimpleContact', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""firstname"" newvalue=""fwfwqf"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T15:30:22+07:00"" oldvalue=""2013-02-04T13:20:52+07:00""/></changeset>', '2013-02-04 15:30:22.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Contact', 4, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (77, 'com.esofthead.mycollab.module.crm.domain.SimpleContact', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""accountId"" newvalue=""2"" oldvalue=""3""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:22:06+07:00"" oldvalue=""2013-02-04T17:21:40+07:00""/></changeset>', '2013-02-04 17:22:06.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Contact', 5, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (78, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:42:15+07:00"" oldvalue=""2013-02-04T10:52:25+07:00""/><changelog field=""milestoneid"" newvalue=""2"" oldvalue=""""/></changeset>', '2013-02-04 17:42:15.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (79, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:51:16+07:00"" oldvalue=""2013-01-23T12:24:17+07:00""/><changelog field=""resolution"" newvalue=""Fixed"" oldvalue=""UnResolved""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Open""/></changeset>', '2013-02-04 17:51:16.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (80, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:54:08+07:00"" oldvalue=""2013-02-04T17:51:16+07:00""/></changeset>', '2013-02-04 17:54:08.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 1, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (81, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:56:52+07:00"" oldvalue=""2013-02-04T10:53:38+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-04 17:56:52.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (82, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:56:57+07:00"" oldvalue=""2013-02-04T17:56:52+07:00""/></changeset>', '2013-02-04 17:56:57.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (83, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:56:58+07:00"" oldvalue=""2013-02-04T17:56:57+07:00""/></changeset>', '2013-02-04 17:56:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (84, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset/>', '2013-02-04 17:56:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (85, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:56:59+07:00"" oldvalue=""2013-02-04T17:56:58+07:00""/></changeset>', '2013-02-04 17:56:59.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (86, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:57:03+07:00"" oldvalue=""2013-02-03T13:31:53+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-04 17:57:03.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 8, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (87, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T17:57:35+07:00"" oldvalue=""2013-02-04T08:42:03+07:00""/><changelog field=""percentagecomplete"" newvalue=""10.0"" oldvalue=""100.0""/><changelog field=""status"" newvalue=""Open"" oldvalue=""Closed""/></changeset>', '2013-02-04 17:57:35.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 5, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (88, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T18:02:52+07:00"" oldvalue=""2013-02-03T13:32:28+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-04 18:02:52.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 9, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (89, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-04T18:06:16+07:00"" oldvalue=""2013-02-04T17:57:35+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""10.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-04 18:06:16.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 5, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (90, 'com.esofthead.mycollab.module.crm.domain.Lead', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""firstname"" newvalue=""fefewfew"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-05T10:05:01+07:00"" oldvalue=""2013-02-05T10:04:16+07:00""/></changeset>', '2013-02-05 10:05:01.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Lead', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (91, 'com.esofthead.mycollab.module.crm.domain.Lead', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-05T13:59:59+07:00"" oldvalue=""2013-02-05T10:05:01+07:00""/><changelog field=""prefixname"" newvalue=""Mrs."" oldvalue=""""/></changeset>', '2013-02-05 14:00:00.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Lead', 2, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (92, 'com.esofthead.mycollab.module.crm.domain.Lead', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""extraData"" newvalue=""com.esofthead.mycollab.module.crm.domain.SimpleOpportunity@5cd077d3"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-07T10:13:44+07:00"" oldvalue=""2013-02-07T10:13:40+07:00""/></changeset>', '2013-02-07 10:13:44.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Lead', 6, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (93, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-07T15:24:56+07:00"" oldvalue=""2013-02-06T14:51:10+07:00""/><changelog field=""percentagecomplete"" newvalue=""0.0"" oldvalue=""""/><changelog field=""priority"" newvalue=""Urgent"" oldvalue=""""/></changeset>', '2013-02-07 15:24:56.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 12, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (94, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-12T06:07:27+07:00"" oldvalue=""2013-02-04T17:42:15+07:00""/><changelog field=""summary"" newvalue=""gggreg eefewfewgewg vdsvd"" oldvalue=""gggreg eefewfewgewg""/></changeset>', '2013-02-12 06:07:27.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (95, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-12T17:55:41+07:00"" oldvalue=""2013-02-04T10:14:49+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""40.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-12 17:55:41.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 10, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (96, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:08:18+07:00"" oldvalue=""2013-02-07T15:38:28+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-13 06:08:18.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 16, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (97, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:08:23+07:00"" oldvalue=""2013-02-08T10:37:51+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-13 06:08:23.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 18, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (98, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:08:32+07:00"" oldvalue=""2013-02-07T15:34:36+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-13 06:08:32.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 14, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (99, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:10:03+07:00"" oldvalue=""2013-01-24T13:27:41+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-13 06:10:03.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (100, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:10:12+07:00"" oldvalue=""2013-02-08T10:33:04+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-13 06:10:12.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 17, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (101, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""wfwefewoo hohfoeiwhfgowh oihoehgw"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:41:18+07:00"" oldvalue=""2013-02-12T06:07:27+07:00""/></changeset>', '2013-02-13 06:41:18.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 11, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (102, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh hfoehfeohgoh ohgohgohwo ohgoghoerh wejreh"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:55:29+07:00"" oldvalue=""2013-02-07T15:27:23+07:00""/></changeset>', '2013-02-13 06:55:29.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 12, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (103, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew fheowfhweoh ohohoghwo hohgoew "" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T06:56:00+07:00"" oldvalue=""2013-01-28T13:42:57+07:00""/></changeset>', '2013-02-13 06:56:00.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 9, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (104, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""user2"" oldvalue=""hainguyen@esofthead.com""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T10:42:30+07:00"" oldvalue=""2013-01-29T16:29:34+07:00""/><changelog field=""resolution"" newvalue=""Fixed"" oldvalue=""New issue""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Open""/></changeset>', '2013-02-13 10:42:30.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (105, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T10:44:20+07:00"" oldvalue=""2013-02-07T15:38:10+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-13 10:44:20.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 15, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (106, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-13T10:44:25+07:00"" oldvalue=""2013-02-07T15:24:56+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-13 10:44:25.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 12, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (107, 'com.esofthead.mycollab.module.project.domain.Problem', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assigntouser"" newvalue=""c@a.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-16T21:47:50+07:00"" oldvalue=""2013-01-29T22:58:50+07:00""/><changelog field=""raisedbyuser"" newvalue=""a@a.com"" oldvalue=""""/></changeset>', '2013-02-16 21:47:50.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Problem', 2, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (108, 'com.esofthead.mycollab.module.project.domain.Risk', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assigntouser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-16T21:48:13+07:00"" oldvalue=""2013-02-04T09:06:12+07:00""/><changelog field=""raisedbyuser"" newvalue=""a@a.com"" oldvalue=""""/></changeset>', '2013-02-16 21:48:13.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Risk', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (109, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-17T22:40:34+07:00"" oldvalue=""2013-02-13T14:04:23+07:00""/></changeset>', '2013-02-17 22:40:34.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 18, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (110, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-18T11:05:27+07:00"" oldvalue=""2013-01-29T13:32:44+07:00""/><changelog field=""status"" newvalue=""Reopenned"" oldvalue=""Test Pending""/></changeset>', '2013-02-18 11:05:27.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (111, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""b@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-19T21:40:35+07:00"" oldvalue=""2013-02-17T22:40:34+07:00""/></changeset>', '2013-02-19 21:40:35.0', 'a@a.com', NULL, NULL, 1, 'Bug', 18, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (112, 'com.esofthead.mycollab.module.project.domain.Problem', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assigntouser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-20T17:38:31+07:00"" oldvalue=""2013-02-20T15:47:34+07:00""/><changelog field=""raisedbyuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""""/></changeset>', '2013-02-20 17:38:31.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Problem', 3, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (113, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""y5y54"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-22T09:17:31+07:00"" oldvalue=""2013-02-20T10:24:27+07:00""/></changeset>', '2013-02-22 09:17:31.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 22, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (114, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""y5y54 y5yk54yk5[5]l] l]5l]l5uy]5lu]45]l ]ly]5ly]5ly]l45]l ]l5]yl5]yl54]ul54]l] l]5ly5]ly5]l l]5l5]ly5]yl l]5ly]5ly54w rm;rmh;reh"" oldvalue=""y5y54""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-22T09:17:55+07:00"" oldvalue=""2013-02-22T09:17:31+07:00""/></changeset>', '2013-02-22 09:17:55.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 22, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (115, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43 4htoh4toi4 oj4gpj4ptj43ptj pjpjrp32jtp32jt pj3tpj32tp32jtp pj32trpj32tp32jt 3ptj32ptj32 4gvv ds,v , gehgeow ewgkhewlgn egnelwng elgnewlg"" oldvalue=""t4t43""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-22T09:33:26+07:00"" oldvalue=""2013-02-20T08:53:57+07:00""/></changeset>', '2013-02-22 09:33:26.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 20, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (116, 'com.esofthead.mycollab.module.project.domain.Risk', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""ggrg fefew&lt;br&gt;"" oldvalue=""ggrg&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-22T13:23:01+07:00"" oldvalue=""2013-02-16T21:48:13+07:00""/></changeset>', '2013-02-22 13:23:01.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Risk', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (117, 'com.esofthead.mycollab.module.project.domain.Risk', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""&lt;b&gt;fwfwqf&lt;/b&gt;ggrg fefew&lt;br&gt;&lt;br&gt;ewgewg&lt;br&gt;&lt;br&gt;"" oldvalue=""ggrg fefew&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-22T13:23:16+07:00"" oldvalue=""2013-02-22T13:23:01+07:00""/></changeset>', '2013-02-22 13:23:16.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Risk', 7, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (118, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""y5y54 y5yk54yk5[5]l] l]5l]l5uy]5lu]45]l ]ly]5ly]5ly]l45]l ]l5]yl5]yl54]ul54]l] l]5ly5]ly5]l l]5l5]ly5]yl l]5ly]5ly54w rm;rmh;reh&lt;br&gt;&lt;br&gt;fefewfew e&lt;b&gt;gewgewgewg &lt;u&gt;fewfewgewgew&lt;br&gt;&lt;/u&gt;&lt;/b&gt;&lt;i&gt;gewgewgewg&lt;/i&gt;&lt;br&gt;"" oldvalue=""y5y54 y5yk54yk5[5]l] l]5l]l5uy]5lu]45]l ]ly]5ly]5ly]l45]l ]l5]yl5]yl54]ul54]l] l]5ly5]ly5]l l]5l5]ly5]yl l]5ly]5ly54w rm;rmh;reh""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-22T16:11:50+07:00"" oldvalue=""2013-02-22T09:17:55+07:00""/></changeset>', '2013-02-22 16:11:50.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 22, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (119, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:47+07:00"" oldvalue=""2013-02-08T11:06:07+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:47.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 21, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (120, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:48+07:00"" oldvalue=""2013-02-21T17:16:09+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:48.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 24, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (121, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:50+07:00"" oldvalue=""2013-02-25T20:40:19+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:50.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 32, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (122, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:51+07:00"" oldvalue=""2013-02-25T20:35:49+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:51.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 31, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (123, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:53+07:00"" oldvalue=""2013-02-22T00:02:01+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 30, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (124, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:54+07:00"" oldvalue=""2013-02-21T23:57:50+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:54.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 29, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (125, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:55+07:00"" oldvalue=""2013-02-21T17:21:40+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:55.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 26, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (126, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:56+07:00"" oldvalue=""2013-02-21T17:19:47+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:56.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 25, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (127, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T20:44:58+07:00"" oldvalue=""2013-02-21T09:40:49+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 20:44:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 23, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (128, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T21:55:07+07:00"" oldvalue=""2013-02-07T15:34:05+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 21:55:07.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 13, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (129, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T21:55:09+07:00"" oldvalue=""2013-02-25T20:45:05+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 21:55:09.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (130, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T21:55:10+07:00"" oldvalue=""2013-02-25T21:07:53+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 21:55:10.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 35, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (131, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T21:55:11+07:00"" oldvalue=""2013-02-25T21:48:43+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 21:55:11.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (132, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T22:16:58+07:00"" oldvalue=""2013-02-25T22:06:08+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 22:16:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 41, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (133, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T22:16:59+07:00"" oldvalue=""2013-02-25T22:00:59+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 22:16:59.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 40, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (134, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T22:17:01+07:00"" oldvalue=""2013-02-25T21:55:24+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 22:17:01.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 39, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (135, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T23:08:08+07:00"" oldvalue=""2013-02-25T22:28:22+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 23:08:08.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 44, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (136, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T23:08:09+07:00"" oldvalue=""2013-02-25T22:22:45+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 23:08:09.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (137, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T23:08:10+07:00"" oldvalue=""2013-02-25T22:17:09+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 23:08:10.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 42, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (138, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-25T23:08:12+07:00"" oldvalue=""2013-02-25T21:51:22+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-02-25 23:08:12.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 38, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (139, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-27T09:42:33+07:00"" oldvalue=""2013-02-13T13:57:58+07:00""/><changelog field=""status"" newvalue=""In Progress"" oldvalue=""Open""/></changeset>', '2013-02-27 09:42:33.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 17, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (140, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""c@a.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-27T09:44:52+07:00"" oldvalue=""2013-02-27T09:42:33+07:00""/><changelog field=""status"" newvalue=""Test Pending"" oldvalue=""In Progress""/></changeset>', '2013-02-27 09:44:52.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 17, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (141, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-27T09:45:43+07:00"" oldvalue=""2013-02-27T09:44:52+07:00""/><changelog field=""status"" newvalue=""Reopenned"" oldvalue=""Test Pending""/></changeset>', '2013-02-27 09:45:43.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 17, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (142, 'com.esofthead.mycollab.module.crm.domain.Account', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-27T10:18:28+07:00"" oldvalue=""2013-02-05T21:20:42+07:00""/><changelog field=""website"" newvalue=""http://www.esofthead.com"" oldvalue=""""/></changeset>', '2013-02-27 10:18:28.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Account', 7, 'Crm');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (143, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgpt43jt43q&lt;br&gt;"" oldvalue=""""/><changelog field=""environment"" newvalue=""y4y43y ggg&lt;br&gt;"" oldvalue=""y4y43y&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T08:40:06+07:00"" oldvalue=""2013-02-27T18:42:26+07:00""/></changeset>', '2013-02-28 08:40:06.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (144, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgpt43jt43q rherhre&lt;br&gt;"" oldvalue=""t4t43tu jgpt43jt43q&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y ggg hhth&lt;br&gt;"" oldvalue=""y4y43y ggg&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T09:56:13+07:00"" oldvalue=""2013-02-28T08:40:06+07:00""/></changeset>', '2013-02-28 09:56:13.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (145, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""environment"" newvalue=""y4y43y ggg hhth rgrehreh&lt;br&gt;"" oldvalue=""y4y43y ggg hhth&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T09:56:48+07:00"" oldvalue=""2013-02-28T09:56:13+07:00""/></changeset>', '2013-02-28 09:56:48.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (146, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""environment"" newvalue=""y4y43y ggg hhth rgrehreh hrehreh&lt;br&gt;"" oldvalue=""y4y43y ggg hhth rgrehreh&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T09:57:28+07:00"" oldvalue=""2013-02-28T09:56:48+07:00""/></changeset>', '2013-02-28 09:57:28.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (147, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgpt43jt43q rherhre afewgwg ewgew&lt;br&gt;"" oldvalue=""t4t43tu jgpt43jt43q rherhre&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y ggg hhth rgrehreh hrehreh ewgewg&lt;br&gt;"" oldvalue=""y4y43y ggg hhth rgrehreh hrehreh&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T10:28:49+07:00"" oldvalue=""2013-02-28T09:57:28+07:00""/></changeset>', '2013-02-28 10:28:49.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (148, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgpt43jt43q rherhre afewgwg ewgew grgre&lt;br&gt;"" oldvalue=""t4t43tu jgpt43jt43q rherhre afewgwg ewgew&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y ggg hhth rgrehreh hrehreh ewgewg grgre&lt;br&gt;"" oldvalue=""y4y43y ggg hhth rgrehreh hrehreh ewgewg&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T10:38:14+07:00"" oldvalue=""2013-02-28T10:28:49+07:00""/></changeset>', '2013-02-28 10:38:14.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (149, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgpt43jt43q rheafewgre&lt;br&gt;"" oldvalue=""t4t43tu jgpt43jt43q rherhre afewgwg ewgew grgre&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y ggg hhth rgddd&lt;br&gt;"" oldvalue=""y4y43y ggg hhth rgrehreh hrehreh ewgewg grgre&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T10:47:21+07:00"" oldvalue=""2013-02-28T10:38:14+07:00""/></changeset>', '2013-02-28 10:47:21.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (150, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgptee&lt;br&gt;"" oldvalue=""t4t43tu jgpt43jt43q rheafewgre&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y ggg hfewfe e&lt;br&gt;"" oldvalue=""y4y43y ggg hhth rgddd&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:01:00+07:00"" oldvalue=""2013-02-28T10:47:21+07:00""/></changeset>', '2013-02-28 11:01:00.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (151, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgwwwptee&lt;br&gt;"" oldvalue=""t4t43tu jgptee&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y gg greger&lt;br&gt;"" oldvalue=""y4y43y ggg hfewfe e&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:06:05+07:00"" oldvalue=""2013-02-28T11:01:00+07:00""/></changeset>', '2013-02-28 11:06:05.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (152, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgwwwptee reh&lt;br&gt;"" oldvalue=""t4t43tu jgwwwptee&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y gg greger rhre&lt;br&gt;"" oldvalue=""y4y43y gg greger&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:08:47+07:00"" oldvalue=""2013-02-28T11:06:05+07:00""/></changeset>', '2013-02-28 11:08:47.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (153, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgwww&lt;br&gt;"" oldvalue=""t4t43tu jgwwwptee reh&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43y gg efewf&lt;br&gt;"" oldvalue=""y4y43y gg greger rhre&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:15:15+07:00"" oldvalue=""2013-02-28T11:08:47+07:00""/></changeset>', '2013-02-28 11:15:15.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (154, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgewww&lt;br&gt;"" oldvalue=""t4t43tu jgwww&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43yegewg r&lt;br&gt;"" oldvalue=""y4y43y gg efewf&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:18:06+07:00"" oldvalue=""2013-02-28T11:15:15+07:00""/></changeset>', '2013-02-28 11:18:06.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (155, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jgewww rr&lt;br&gt;"" oldvalue=""t4t43tu jgewww&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43yegewg r r&lt;br&gt;"" oldvalue=""y4y43yegewg r&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:19:04+07:00"" oldvalue=""2013-02-28T11:18:06+07:00""/></changeset>', '2013-02-28 11:19:04.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (156, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jee&lt;br&gt;"" oldvalue=""t4t43tu jgewww rr&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43ggre r&lt;br&gt;"" oldvalue=""y4y43yegewg r r&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:24:07+07:00"" oldvalue=""2013-02-28T11:19:04+07:00""/></changeset>', '2013-02-28 11:24:07.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (157, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jee ehreh&lt;br&gt;"" oldvalue=""t4t43tu jee&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43ggre r rgrhre&lt;br&gt;"" oldvalue=""y4y43ggre r&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:26:46+07:00"" oldvalue=""2013-02-28T11:24:07+07:00""/></changeset>', '2013-02-28 11:26:46.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (158, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jeee&lt;br&gt;"" oldvalue=""t4t43tu jee ehreh&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43eee&lt;br&gt;"" oldvalue=""y4y43ggre r rgrhre&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:36:41+07:00"" oldvalue=""2013-02-28T11:26:46+07:00""/></changeset>', '2013-02-28 11:36:41.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (159, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jeee gewgewg&lt;br&gt;"" oldvalue=""t4t43tu jeee&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43eee egewgew&lt;br&gt;"" oldvalue=""y4y43eee&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:40:21+07:00"" oldvalue=""2013-02-28T11:36:41+07:00""/></changeset>', '2013-02-28 11:40:21.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (160, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""t4t43tu jeee gewgewg ds&lt;br&gt;"" oldvalue=""t4t43tu jeee gewgewg&lt;br&gt;""/><changelog field=""environment"" newvalue=""y4y43eee egewgew rehreh&lt;br&gt;"" oldvalue=""y4y43eee egewgew&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T11:46:46+07:00"" oldvalue=""2013-02-28T11:40:21+07:00""/></changeset>', '2013-02-28 11:46:46.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (161, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""gger rr&lt;br&gt;"" oldvalue=""""/><changelog field=""environment"" newvalue=""rgregre grege&lt;br&gt;"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T13:52:54+07:00"" oldvalue=""2013-02-28T13:45:48+07:00""/></changeset>', '2013-02-28 13:52:54.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (162, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""gger rr ewgjpoewjgewj pojegpoewjgewpjgpj pjepojgewgew&lt;br&gt;"" oldvalue=""gger rr&lt;br&gt;""/><changelog field=""environment"" newvalue=""rgregre gregergre r&lt;br&gt;&lt;br&gt;grpeogjre[hgjkre[k [k[wkgr[kh[rek[k p[kr[hkreh[kreh[pk [k[rkhg[rekhr[ek[k [kg[rekh&lt;br&gt;"" oldvalue=""rgregre grege&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T13:56:28+07:00"" oldvalue=""2013-02-28T13:52:54+07:00""/><changelog field=""summary"" newvalue=""gewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew eegewgew ee"" oldvalue=""gewgew ee""/></changeset>', '2013-02-28 13:56:29.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (163, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""gger rr ewgjpoewjgewj pojegpoewjgewpjgpj pjepojgewgew&lt;br&gt;egegew&lt;br&gt;"" oldvalue=""gger rr ewgjpoewjgewj pojegpoewjgewpjgpj pjepojgewgew&lt;br&gt;""/><changelog field=""environment"" newvalue=""rgregre gregergre r&lt;br&gt;&lt;br&gt;grpeogjre[hgjkre[k [k[wkgr[kh[rek[k p[kr[hkreh[kreh[pk [k[rkhg[rekhr[ek[k [kg[rekh&lt;br&gt;vvdsvd&lt;br&gt;"" oldvalue=""rgregre gregergre r&lt;br&gt;&lt;br&gt;grpeogjre[hgjkre[k [k[wkgr[kh[rek[k p[kr[hkreh[kreh[pk [k[rkhg[rekhr[ek[k [kg[rekh&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T13:59:25+07:00"" oldvalue=""2013-02-28T13:56:28+07:00""/></changeset>', '2013-02-28 13:59:25.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (164, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""gew eewegew&lt;br&gt;"" oldvalue=""gew&lt;br&gt;""/><changelog field=""environment"" newvalue=""gewg ggrgre&lt;br&gt;"" oldvalue=""gewg&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T14:17:53+07:00"" oldvalue=""2013-02-28T14:10:40+07:00""/></changeset>', '2013-02-28 14:17:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 35, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (165, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""environment"" newvalue=""gewg ggrgre afewgewg&lt;br&gt;"" oldvalue=""gewg ggrgre&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T14:32:59+07:00"" oldvalue=""2013-02-28T14:17:53+07:00""/></changeset>', '2013-02-28 14:32:59.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 35, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (166, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-02-28T23:23:08+07:00"" oldvalue=""2013-02-28T18:26:50+07:00""/></changeset>', '2013-02-28 23:23:08.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 36, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (167, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-07T15:31:37+07:00"" oldvalue=""2013-02-28T13:59:25+07:00""/><changelog field=""status"" newvalue=""In Progress"" oldvalue=""Open""/></changeset>', '2013-03-07 15:31:37.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (168, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-07T15:31:38+07:00"" oldvalue=""2013-03-07T15:31:37+07:00""/><changelog field=""status"" newvalue=""Open"" oldvalue=""In Progress""/></changeset>', '2013-03-07 15:31:38.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (169, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-08T08:45:58+07:00"" oldvalue=""2013-03-07T13:29:53+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-08 08:45:58.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 56, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (170, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-08T08:49:43+07:00"" oldvalue=""2013-02-28T14:12:59+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-08 08:49:43.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 55, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (171, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-08T08:51:27+07:00"" oldvalue=""2013-02-27T16:44:20+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-08 08:51:27.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 54, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (172, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-08T09:07:47+07:00"" oldvalue=""2013-02-27T11:34:37+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-08 09:07:47.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 50, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (173, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-08T09:08:37+07:00"" oldvalue=""2013-03-07T15:31:38+07:00""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Open""/></changeset>', '2013-03-08 09:08:37.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (174, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-08T09:09:31+07:00"" oldvalue=""2013-03-08T09:08:37+07:00""/></changeset>', '2013-03-08 09:09:31.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 33, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (175, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""duedate"" newvalue=""2013-03-04T14:19:30+07:00"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-08T14:19:34+07:00"" oldvalue=""2013-02-28T11:46:46+07:00""/></changeset>', '2013-03-08 14:19:34.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (176, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-11T10:17:11+07:00"" oldvalue=""2013-03-11T09:42:26+07:00""/><changelog field=""percentagecomplete"" newvalue=""50.0"" oldvalue=""0.0""/></changeset>', '2013-03-11 10:17:11.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 59, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (177, 'com.esofthead.mycollab.module.project.domain.Milestone', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-12T14:00:37+07:00"" oldvalue=""2013-03-09T00:26:20+07:00""/><changelog field=""status"" newvalue=""In Progress"" oldvalue=""Open""/></changeset>', '2013-03-12 14:00:37.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Milestone', 5, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (178, 'com.esofthead.mycollab.module.project.domain.Milestone', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""fewfew e&lt;br&gt;"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-12T23:58:51+07:00"" oldvalue=""2013-03-12T23:58:41+07:00""/><changelog field=""name"" newvalue=""2211 few"" oldvalue=""2211""/></changeset>', '2013-03-12 23:58:51.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Milestone', 8, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (179, 'com.esofthead.mycollab.module.project.domain.Milestone', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""fewfew e www&lt;br&gt;"" oldvalue=""fewfew e&lt;br&gt;""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-12T23:59:59+07:00"" oldvalue=""2013-03-12T23:58:51+07:00""/></changeset>', '2013-03-12 23:59:59.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Milestone', 8, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (180, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-13T02:18:33+07:00"" oldvalue=""2013-03-09T21:01:23+07:00""/><changelog field=""milestoneid"" newvalue=""6"" oldvalue=""""/></changeset>', '2013-03-13 02:18:33.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 38, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (181, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""description"" newvalue=""fefew w&lt;br&gt;"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-13T16:36:50+07:00"" oldvalue=""2013-03-13T02:18:33+07:00""/></changeset>', '2013-03-13 16:36:50.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 38, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (182, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:19:18+07:00"" oldvalue=""2013-03-11T10:17:11+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""0.0"" oldvalue=""50.0""/></changeset>', '2013-03-14 14:19:18.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 59, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (183, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:19:23+07:00"" oldvalue=""2013-03-14T14:19:18+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-14 14:19:23.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 59, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (184, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:19:28+07:00"" oldvalue=""2013-03-14T14:19:23+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-14 14:19:28.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 59, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (185, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:19:46+07:00"" oldvalue=""2013-03-14T14:19:28+07:00""/><changelog field=""percentagecomplete"" newvalue=""0.0"" oldvalue=""100.0""/><changelog field=""status"" newvalue=""Open"" oldvalue=""Closed""/></changeset>', '2013-03-14 14:19:46.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 59, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (186, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:20:30+07:00"" oldvalue=""2013-03-11T09:42:13+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-14 14:20:30.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 58, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (187, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:20:39+07:00"" oldvalue=""2013-03-14T14:19:46+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-14 14:20:39.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 59, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (188, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:27:33+07:00"" oldvalue=""2013-03-11T09:41:56+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-14 14:27:33.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 57, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (189, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T14:58:07+07:00"" oldvalue=""2013-03-14T14:27:33+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-14 14:58:07.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 57, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (190, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T15:16:41+07:00"" oldvalue=""2013-03-14T13:53:28+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-14 15:16:41.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 61, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (191, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-14T17:22:45+07:00"" oldvalue=""2013-03-14T14:58:07+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-14 17:22:45.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 57, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (192, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""deadline"" newvalue=""2013-03-27T11:03:03+07:00"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-15T11:03:10+07:00"" oldvalue=""2013-03-14T17:22:45+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-15 11:03:10.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 57, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (193, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:07:31+07:00"" oldvalue=""2013-02-25T23:08:23+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-18 10:07:31.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 45, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (194, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:07:36+07:00"" oldvalue=""2013-03-18T10:07:31+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/></changeset>', '2013-03-18 10:07:36.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 45, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (195, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:20:56+07:00"" oldvalue=""2013-03-15T11:03:10+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""status"" newvalue=""Pending"" oldvalue=""Open""/></changeset>', '2013-03-18 10:20:56.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 57, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (196, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:23:54+07:00"" oldvalue=""2013-03-17T07:29:03+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""status"" newvalue=""Pending"" oldvalue=""Open""/><changelog field=""taskkey"" newvalue="""" oldvalue=""1""/></changeset>', '2013-03-18 10:23:54.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 62, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (197, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:24:08+07:00"" oldvalue=""2013-02-26T13:24:21+07:00""/><changelog field=""status"" newvalue=""Pending"" oldvalue=""Open""/></changeset>', '2013-03-18 10:24:08.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 49, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (198, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:30:01+07:00"" oldvalue=""2013-03-18T10:07:36+07:00""/><changelog field=""status"" newvalue=""Pending"" oldvalue=""Open""/></changeset>', '2013-03-18 10:30:01.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 45, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (199, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:33:54+07:00"" oldvalue=""2013-02-26T08:35:12+07:00""/><changelog field=""status"" newvalue=""Pending"" oldvalue=""Open""/></changeset>', '2013-03-18 10:33:54.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 46, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (200, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:42:32+07:00"" oldvalue=""2013-03-14T15:16:41+07:00""/><changelog field=""percentagecomplete"" newvalue=""0.0"" oldvalue=""100.0""/><changelog field=""status"" newvalue=""Open"" oldvalue=""Closed""/></changeset>', '2013-03-18 10:42:32.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 61, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (201, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T10:44:28+07:00"" oldvalue=""2013-02-25T21:00:20+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Open""/></changeset>', '2013-03-18 10:44:28.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 34, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (202, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T11:03:53+07:00"" oldvalue=""2013-02-26T10:11:53+07:00""/><changelog field=""logby"" newvalue="""" oldvalue=""hainguyen@esofthead.com""/><changelog field=""status"" newvalue=""Pending"" oldvalue=""Open""/></changeset>', '2013-03-18 11:03:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 47, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (203, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T11:04:18+07:00"" oldvalue=""2013-03-18T10:33:54+07:00""/><changelog field=""percentagecomplete"" newvalue=""100.0"" oldvalue=""0.0""/><changelog field=""status"" newvalue=""Closed"" oldvalue=""Pending""/></changeset>', '2013-03-18 11:04:18.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 46, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (204, 'com.esofthead.mycollab.module.project.domain.Task', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-18T11:35:10+07:00"" oldvalue=""2013-02-25T23:08:08+07:00""/><changelog field=""percentagecomplete"" newvalue=""0.0"" oldvalue=""100.0""/><changelog field=""status"" newvalue=""Open"" oldvalue=""Closed""/></changeset>', '2013-03-18 11:35:10.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Task', 44, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (205, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-19T21:01:28+07:00"" oldvalue=""2013-03-13T16:36:50+07:00""/><changelog field=""resolution"" newvalue=""Fixed"" oldvalue=""New issue""/><changelog field=""status"" newvalue=""Test Pending"" oldvalue=""Open""/></changeset>', '2013-03-19 21:01:28.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 38, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (206, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-19T21:01:33+07:00"" oldvalue=""2013-03-19T21:01:28+07:00""/><changelog field=""status"" newvalue=""Close"" oldvalue=""Test Pending""/></changeset>', '2013-03-19 21:01:33.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 38, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (207, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-20T16:24:11+07:00"" oldvalue=""2013-03-08T14:19:34+07:00""/><changelog field=""milestoneid"" newvalue=""3"" oldvalue=""""/></changeset>', '2013-03-20 16:24:11.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 28, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (208, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-21T22:29:12+07:00"" oldvalue=""2013-03-18T16:09:48+07:00""/><changelog field=""status"" newvalue=""In Progress"" oldvalue=""Open""/></changeset>', '2013-03-21 22:29:12.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 41, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (209, 'com.esofthead.mycollab.module.tracker.domain.Version', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-22T11:51:50+07:00"" oldvalue=""2013-03-22T11:51:46+07:00""/></changeset>', '2013-03-22 11:51:50.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Version', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (210, 'com.esofthead.mycollab.module.tracker.domain.Version', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-22T11:51:54+07:00"" oldvalue=""2013-03-22T11:51:50+07:00""/></changeset>', '2013-03-22 11:51:54.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Version', 4, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (211, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:20:37+07:00"" oldvalue=""2013-03-09T21:00:24+07:00""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Open""/></changeset>', '2013-03-25 22:20:37.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (212, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:20:40+07:00"" oldvalue=""2013-03-25T22:20:37+07:00""/></changeset>', '2013-03-25 22:20:40.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (213, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset/>', '2013-03-25 22:20:40.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (214, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset/>', '2013-03-25 22:20:41.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (215, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:20:45+07:00"" oldvalue=""2013-03-25T22:20:40+07:00""/></changeset>', '2013-03-25 22:20:45.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (216, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:20:48+07:00"" oldvalue=""2013-03-25T22:20:45+07:00""/></changeset>', '2013-03-25 22:20:48.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (217, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:20:57+07:00"" oldvalue=""2013-03-25T22:20:48+07:00""/><changelog field=""status"" newvalue=""Reopenned"" oldvalue=""Won''t Fix""/></changeset>', '2013-03-25 22:20:57.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (218, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:21:12+07:00"" oldvalue=""2013-03-25T22:20:57+07:00""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Reopenned""/></changeset>', '2013-03-25 22:21:12.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (219, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""a@a.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:21:31+07:00"" oldvalue=""2013-03-25T22:21:12+07:00""/><changelog field=""resolution"" newvalue=""Fixed"" oldvalue=""New issue""/><changelog field=""status"" newvalue=""Reopenned"" oldvalue=""Won''t Fix""/></changeset>', '2013-03-25 22:21:31.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (220, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""a@a.com""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:21:43+07:00"" oldvalue=""2013-03-25T22:21:31+07:00""/><changelog field=""status"" newvalue=""Test Pending"" oldvalue=""Reopenned""/></changeset>', '2013-03-25 22:21:43.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (221, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:21:48+07:00"" oldvalue=""2013-03-25T22:21:43+07:00""/><changelog field=""status"" newvalue=""Reopenned"" oldvalue=""Test Pending""/></changeset>', '2013-03-25 22:21:48.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (222, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:21:53+07:00"" oldvalue=""2013-03-25T22:21:48+07:00""/></changeset>', '2013-03-25 22:21:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (223, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:22:55+07:00"" oldvalue=""2013-03-25T22:21:53+07:00""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Reopenned""/></changeset>', '2013-03-25 22:22:55.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 37, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (224, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:23:14+07:00"" oldvalue=""2013-03-22T15:01:52+07:00""/><changelog field=""status"" newvalue=""In Progress"" oldvalue=""Open""/></changeset>', '2013-03-25 22:23:14.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (225, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:23:29+07:00"" oldvalue=""2013-03-25T22:23:14+07:00""/><changelog field=""status"" newvalue=""Open"" oldvalue=""In Progress""/></changeset>', '2013-03-25 22:23:29.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (226, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""a@a.com"" oldvalue=""""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:23:40+07:00"" oldvalue=""2013-03-25T22:23:29+07:00""/><changelog field=""status"" newvalue=""Won''t Fix"" oldvalue=""Open""/></changeset>', '2013-03-25 22:23:40.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (227, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:23:43+07:00"" oldvalue=""2013-03-25T22:23:40+07:00""/></changeset>', '2013-03-25 22:23:43.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (228, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:23:53+07:00"" oldvalue=""2013-03-25T22:23:43+07:00""/><changelog field=""status"" newvalue=""Reopenned"" oldvalue=""Won''t Fix""/></changeset>', '2013-03-25 22:23:53.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (229, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:24:55+07:00"" oldvalue=""2013-03-25T22:23:53+07:00""/><changelog field=""status"" newvalue=""In Progress"" oldvalue=""Reopenned""/></changeset>', '2013-03-25 22:24:55.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (230, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""assignuser"" newvalue=""hainguyen@esofthead.com"" oldvalue=""a@a.com""/><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:35:14+07:00"" oldvalue=""2013-03-25T22:24:55+07:00""/><changelog field=""resolution"" newvalue=""Fixed"" oldvalue=""New issue""/><changelog field=""status"" newvalue=""Test Pending"" oldvalue=""In Progress""/></changeset>', '2013-03-25 22:35:14.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (231, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:35:43+07:00"" oldvalue=""2013-03-25T22:35:14+07:00""/><changelog field=""status"" newvalue=""Close"" oldvalue=""Test Pending""/></changeset>', '2013-03-25 22:35:43.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (232, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:54:09+07:00"" oldvalue=""2013-03-25T22:35:43+07:00""/><changelog field=""priority"" newvalue=""Blocker"" oldvalue=""Major""/></changeset>', '2013-03-25 22:54:09.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (233, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:54:15+07:00"" oldvalue=""2013-03-25T22:54:09+07:00""/><changelog field=""priority"" newvalue=""Major"" oldvalue=""Blocker""/></changeset>', '2013-03-25 22:54:15.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
INSERT INTO m_audit_log(id, object_class, changeset, posteddate, posteduser, createdTime, lastUpdatedTime, sAccountId, type, typeid, module) VALUES (234, 'com.esofthead.mycollab.module.tracker.domain.Bug', '<?xml version=""1.0"" encoding=""UTF-8"" standalone=""no""?><changeset><changelog field=""lastupdatedtime"" newvalue=""2013-03-25T22:54:22+07:00"" oldvalue=""2013-03-25T22:54:15+07:00""/><changelog field=""severity"" newvalue=""Critical"" oldvalue=""Major""/></changeset>', '2013-03-25 22:54:22.0', 'hainguyen@esofthead.com', NULL, NULL, 1, 'Bug', 43, 'Project');
ALTER TABLE m_audit_log ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_comment -----------------------------------
ALTER TABLE m_comment ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (1, 'edew e<br>', 'hainguyen@esofthead.com', '2013-01-22 21:51:16.0', 'Crm-Note', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (2, 'ewcewcew ecewcew<br>', 'hainguyen@esofthead.com', '2013-01-22 21:51:32.0', 'Crm-Note', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (3, 'cwcewcew e<br>', 'hainguyen@esofthead.com', '2013-01-22 21:54:13.0', 'Crm-Note', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (4, 'cecewc d<br>', 'hainguyen@esofthead.com', '2013-01-22 21:54:49.0', 'Crm-Note', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (5, 'cascsac', 'hainguyen@esofthead.com', '2013-01-22 22:01:03.0', 'Crm-Note', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (6, 'ccdsvds', 'hainguyen@esofthead.com', '2013-01-22 22:54:57.0', 'Project-Message', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (7, 'vdvds d<br>', 'hainguyen@esofthead.com', '2013-01-22 22:55:05.0', 'Project-Message', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (8, 'fewgew ee<br>', 'hainguyen@esofthead.com', '2013-01-22 23:13:15.0', 'Project-Milestone', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (9, 'ewfgew e<br>', 'hainguyen@esofthead.com', '2013-01-22 23:13:23.0', 'Project-Milestone', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (10, 'wqfqwf eeg<br>', 'hainguyen@esofthead.com', '2013-01-22 23:14:55.0', 'Project-Risk', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (11, 'ccew cvewv<br>', 'hainguyen@esofthead.com', '2013-01-23 01:10:27.0', 'Project-Bug', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (12, 'rgregr r<br>', 'hainguyen@esofthead.com', '2013-01-23 08:29:59.0', 'Project-Bug', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (13, 'csacasca', 'hainguyen@esofthead.com', '2013-01-23 10:49:59.0', 'Project-Problem', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (14, 'wrwrw&nbsp; ee<br>', 'hainguyen@esofthead.com', '2013-01-23 21:24:48.0', 'Project-Message', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (15, 'fewfewf ee<br>', 'hainguyen@esofthead.com', '2013-01-23 22:11:53.0', 'Project-Task', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (16, '', 'hainguyen@esofthead.com', '2013-01-23 22:12:05.0', 'Project-Task', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (17, 'ffewfew ee<br>', 'hainguyen@esofthead.com', '2013-01-23 22:12:18.0', 'Project-Task', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (18, 'dsggre rrhreher<br>', 'hainguyen@esofthead.com', '2013-01-28 21:54:45.0', 'Project-Bug', 10, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (19, 'fgrg e<br>', 'hainguyen@esofthead.com', '2013-01-28 21:58:24.0', 'Project-Bug', 10, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (20, 'fewfew ee<br>', 'hainguyen@esofthead.com', '2013-01-28 22:02:59.0', 'Project-Bug', 10, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (21, 'egewgewgewg egewg<br>', 'hainguyen@esofthead.com', '2013-01-28 22:05:17.0', 'Project-Bug', 10, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (22, 'cdsvd vdsvdsv<br>', 'hainguyen@esofthead.com', '2013-01-28 22:10:15.0', 'Project-Bug', 10, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (23, 'egerg<br>', 'hainguyen@esofthead.com', '2013-01-28 22:39:55.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (24, 'fefewf eewgew<br>', 'hainguyen@esofthead.com', '2013-01-29 11:47:28.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (25, 'wfwqfwq', 'hainguyen@esofthead.com', '2013-01-29 11:49:49.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (26, 'afefewfew', 'hainguyen@esofthead.com', '2013-01-29 11:59:06.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (27, 'grgrgr rr<br>', 'hainguyen@esofthead.com', '2013-01-29 12:01:14.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (28, 'bfdbfd<br>ggre wggreh<br>', 'hainguyen@esofthead.com', '2013-01-29 13:32:32.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (29, 'grgrg keg[kgr<br>', 'hainguyen@esofthead.com', '2013-01-29 13:32:44.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (30, 'wfefew e<br>', 'hainguyen@esofthead.com', '2013-01-30 14:23:12.0', 'Project-Milestone', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (31, 'efewfew e<br>', 'hainguyen@esofthead.com', '2013-01-30 21:23:34.0', 'Crm-Note', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (32, 'ewfewfw ee<br>', 'hainguyen@esofthead.com', '2013-01-31 23:11:24.0', 'Project-Milestone', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (33, 'gjrepgreg grge<br>', 'hainguyen@esofthead.com', '2013-02-01 11:38:42.0', 'Project-Risk', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (34, 'ffewf', 'hainguyen@esofthead.com', '2013-02-04 17:51:16.0', 'Project-Bug', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (35, 'ewghgwe wggrgregrag gewgewgew grehrther egewgewgewg ewgewgewg<br>gegewgew<br>gewgew<br>wegewo [tj[tk[tk r[k[rehker<br><br>', 'hainguyen@esofthead.com', '2013-02-04 17:53:42.0', 'Project-Bug', 1, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (36, 'fewfew e<br>', 'hainguyen@esofthead.com', '2013-02-09 23:01:44.0', '', 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (37, 'fefew<br>', 'hainguyen@esofthead.com', '2013-02-09 23:01:49.0', '', 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (38, 'ssss<br>', 'hainguyen@esofthead.com', '2013-02-09 23:02:23.0', '', 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (39, 'fefew<br>', 'hainguyen@esofthead.com', '2013-02-09 23:34:23.0', 'Project-Message', 3, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (40, 'fewf<br>', 'hainguyen@esofthead.com', '2013-02-10 07:18:18.0', NULL, 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (41, 'pewjgpewg<br>', 'hainguyen@esofthead.com', '2013-02-12 06:07:15.0', 'Project-Bug', 11, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (42, 'gegewg rr<br>', 'hainguyen@esofthead.com', '2013-02-12 06:07:17.0', 'Project-Bug', 11, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (43, 'wfffe qwffew<br>', 'hainguyen@esofthead.com', '2013-02-13 10:42:30.0', 'Project-Bug', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (44, 'efefew ee<br>', 'hainguyen@esofthead.com', '2013-02-18 11:05:27.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (45, 'xxx<br>', 'hainguyen@esofthead.com', '2013-02-18 11:06:04.0', 'Project-Bug', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (46, 'fefewf', 'hainguyen@esofthead.com', '2013-02-18 14:18:54.0', 'Project-Risk', 10, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (47, 'afewfew fqw<b>fqwf<br>ewgew<br></b><i>ggreg</i><br>', 'hainguyen@esofthead.com', '2013-02-22 13:22:46.0', 'Project-Problem', 3, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (48, 'cascsa', 'hainguyen@esofthead.com', '2013-02-23 00:03:17.0', 'Project-Bug', 22, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (49, 'vrvrbe<br>', 'hainguyen@esofthead.com', '2013-02-23 00:06:48.0', 'Project-Bug', 22, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (50, 'cewcew<br>', 'hainguyen@esofthead.com', '2013-02-23 00:24:58.0', NULL, 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (51, 'wcece<br>', 'hainguyen@esofthead.com', '2013-02-23 00:25:30.0', NULL, 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (52, 'fewfew<br>', 'hainguyen@esofthead.com', '2013-02-23 00:30:00.0', NULL, 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (53, 'cecewc<br>', 'hainguyen@esofthead.com', '2013-02-23 00:32:15.0', NULL, 0, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (54, '&nbsp;d ds<br>', 'hainguyen@esofthead.com', '2013-02-23 00:42:22.0', 'Project-Milestone', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (55, 'cewcewcew<br>', 'hainguyen@esofthead.com', '2013-02-23 00:42:25.0', 'Project-Milestone', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (56, 'fewfew<br>', 'hainguyen@esofthead.com', '2013-02-23 00:45:59.0', 'Project-Milestone', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (57, 'csacasc<br>', 'hainguyen@esofthead.com', '2013-02-23 00:55:58.0', 'Project-Milestone', 2, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (58, 'egegew', 'hainguyen@esofthead.com', '2013-02-27 09:44:52.0', 'Project-Bug', 17, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (59, 'wgtgr ewgrgwe ewgewgw<br>', 'hainguyen@esofthead.com', '2013-03-05 22:04:38.0', 'Crm-Note', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (60, 'ewgegew egewgew<br>', 'hainguyen@esofthead.com', '2013-03-05 22:04:48.0', 'Crm-Note', 4, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (61, 'ss', 'hainguyen@esofthead.com', '2013-03-08 09:09:31.0', 'Project-Bug', 33, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (62, 'egewg', 'hainguyen@esofthead.com', '2013-03-25 22:20:48.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (63, 'egewg', 'hainguyen@esofthead.com', '2013-03-25 22:20:57.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (64, 'egewg', 'hainguyen@esofthead.com', '2013-03-25 22:21:12.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (65, 'egewg', 'hainguyen@esofthead.com', '2013-03-25 22:21:31.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (66, 'egewg', 'hainguyen@esofthead.com', '2013-03-25 22:21:43.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (67, 'egewg', 'hainguyen@esofthead.com', '2013-03-25 22:21:48.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (68, 'egewg', 'hainguyen@esofthead.com', '2013-03-25 22:21:53.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (69, 'egewg sss<br>', 'hainguyen@esofthead.com', '2013-03-25 22:22:55.0', 'Project-Bug', 37, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (70, 'ss', 'hainguyen@esofthead.com', '2013-03-25 22:23:43.0', 'Project-Bug', 43, 1);
INSERT INTO m_comment(id, comment, createdUser, createdTime, type, typeId, sAccountId) VALUES (71, 'ss', 'hainguyen@esofthead.com', '2013-03-25 22:23:53.0', 'Project-Bug', 43, 1);
ALTER TABLE m_comment ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_account -----------------------------------
ALTER TABLE m_crm_account ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_account(id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, city, postalCode, description, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, type, industry, lastUpdatedTime, billingCountry, shippingCountry) VALUES (1, 'dwqdwqdwq', 'http://www.google.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-01-22 21:50:34.0', NULL, 1, NULL, 'Analysts', NULL, '2013-01-25 14:24:32.0', NULL, NULL);
INSERT INTO m_crm_account(id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, city, postalCode, description, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, type, industry, lastUpdatedTime, billingCountry, shippingCountry) VALUES (2, 'fewfew ee eogogh4ghp5ghjp5jhp jpjgpj45yhp5jqypojq jpjpyj5yp5jw4ypow54jpj pjphj5w4yopj54pyoj54poj pjp5jyp54wjuy5pw4ojuw5p4ojupoj pojypo5jw4ypoj54ypo5w4jypo5w4jypj po5yjpo5w4jyp54wjy54pwoj ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-01-29 11:16:10.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 09:03:15.0', NULL, NULL);
INSERT INTO m_crm_account(id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, city, postalCode, description, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, type, industry, lastUpdatedTime, billingCountry, shippingCountry) VALUES (3, 'eSoftHead', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'hainguyen@esofthead.com', NULL, NULL, NULL, NULL, NULL, NULL, '2013-01-29 11:16:15.0', NULL, 1, 'hainguyen@esofthead.com', NULL, NULL, '2013-02-01 09:26:36.0', NULL, NULL);
INSERT INTO m_crm_account(id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, city, postalCode, description, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, type, industry, lastUpdatedTime, billingCountry, shippingCountry) VALUES (4, 'gewgewg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-04 09:03:37.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 09:03:37.0', NULL, NULL);
INSERT INTO m_crm_account(id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, city, postalCode, description, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, type, industry, lastUpdatedTime, billingCountry, shippingCountry) VALUES (5, ' d ds sgegew', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-04 09:03:42.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 09:03:42.0', NULL, NULL);
INSERT INTO m_crm_account(id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, city, postalCode, description, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, type, industry, lastUpdatedTime, billingCountry, shippingCountry) VALUES (6, 'fefewgew', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-04 09:03:46.0', NULL, 1, NULL, NULL, NULL, '2013-02-04 09:03:46.0', NULL, NULL);
INSERT INTO m_crm_account(id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, city, postalCode, description, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, type, industry, lastUpdatedTime, billingCountry, shippingCountry) VALUES (7, 'csacas s', 'http://www.esofthead.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2013-02-05 21:20:42.0', NULL, 1, NULL, NULL, NULL, '2013-02-27 10:18:28.0', NULL, NULL);
ALTER TABLE m_crm_account ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_accounts_contacts -----------------------------------
ALTER TABLE m_crm_accounts_contacts ALTER COLUMN id INT ;
INSERT INTO m_crm_accounts_contacts(id, accountId, contactId, createdTime) VALUES (26, 7, 6, '2013-03-18 15:14:57.0');
ALTER TABLE m_crm_accounts_contacts ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_crm_accounts_leads -----------------------------------
ALTER TABLE m_crm_accounts_leads ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_accounts_leads(id, accountId, leadId, createTime) VALUES (1, 7, 3, '2013-02-06 14:36:03.0');
INSERT INTO m_crm_accounts_leads(id, accountId, leadId, createTime) VALUES (2, 7, 4, '2013-02-06 14:36:03.0');
INSERT INTO m_crm_accounts_leads(id, accountId, leadId, createTime) VALUES (3, 7, 2, '2013-02-06 14:47:27.0');
ALTER TABLE m_crm_accounts_leads ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_call -----------------------------------
ALTER TABLE m_crm_call ALTER COLUMN id INT ;
INSERT INTO m_crm_call(id, subject, startDate, durationInSeconds, calltype, type, typeid, lastUpdatedTime, createdTime, createdUser, assignUser, description, contactId, sAccountId, status, purpose, result, isClosed) VALUES (1, 'ccsaca', NULL, NULL, 'Inbound', 'Account', 1, '2013-01-22 23:02:05.0', '2013-01-22 23:02:05.0', NULL, NULL, NULL, NULL, 1, 'Held', NULL, NULL, 0);
INSERT INTO m_crm_call(id, subject, startDate, durationInSeconds, calltype, type, typeid, lastUpdatedTime, createdTime, createdUser, assignUser, description, contactId, sAccountId, status, purpose, result, isClosed) VALUES (2, 'Meeting Android team to discuss a potential project', '2013-02-01 09:27:17.0', NULL, 'Inbound', NULL, NULL, '2013-02-01 09:27:46.0', '2013-01-28 10:25:01.0', NULL, 'hainguyen@esofthead.com', 'gregre rere', NULL, 1, 'Held', NULL, 'grgre r', 0);
ALTER TABLE m_crm_call ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_crm_case -----------------------------------
ALTER TABLE m_crm_case ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_case(id, priority, status, type, subject, description, resolution, accountId, createdTime, createdUser, sAccountId, assignUser, lastUpdatedTime, reason, origin, email, phonenumber) VALUES (1, 'Medium', NULL, NULL, 'hthtr t', NULL, NULL, 5, '2013-02-04 21:14:35.0', NULL, 1, NULL, '2013-02-04 21:14:35.0', NULL, NULL, NULL, NULL);
INSERT INTO m_crm_case(id, priority, status, type, subject, description, resolution, accountId, createdTime, createdUser, sAccountId, assignUser, lastUpdatedTime, reason, origin, email, phonenumber) VALUES (2, NULL, NULL, NULL, 'hth t', NULL, NULL, 6, '2013-02-04 21:14:53.0', NULL, 1, NULL, '2013-02-04 21:14:53.0', NULL, NULL, NULL, NULL);
ALTER TABLE m_crm_case ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_crm_meeting -----------------------------------
ALTER TABLE m_crm_meeting ALTER COLUMN id INT ;
INSERT INTO m_crm_meeting(id, subject, status, type, typeid, startDate, endDate, lastUpdatedTime, createdTime, createdUser, sAccountId, location, description, isRecurrence, recurrenceType, recurrenceStartDate, recurrenceEndDate, contactType, contactTypeId, isClosed) VALUES (1, 'gregre', NULL, NULL, NULL, NULL, NULL, '2013-01-25 12:36:29.0', '2013-01-25 12:36:26.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO m_crm_meeting(id, subject, status, type, typeid, startDate, endDate, lastUpdatedTime, createdTime, createdUser, sAccountId, location, description, isRecurrence, recurrenceType, recurrenceStartDate, recurrenceEndDate, contactType, contactTypeId, isClosed) VALUES (2, 'vdvdsv', NULL, NULL, NULL, NULL, NULL, '2013-01-25 12:37:43.0', '2013-01-25 12:36:48.0', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO m_crm_meeting(id, subject, status, type, typeid, startDate, endDate, lastUpdatedTime, createdTime, createdUser, sAccountId, location, description, isRecurrence, recurrenceType, recurrenceStartDate, recurrenceEndDate, contactType, contactTypeId, isClosed) VALUES (3, 'gregre r', 'Planned', 'Account', 1, NULL, NULL, '2013-01-28 10:24:38.0', '2013-01-28 10:24:34.0', NULL, 1, NULL, 'gregreg', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
ALTER TABLE m_crm_meeting ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_crm_note -----------------------------------
ALTER TABLE m_crm_note ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_crm_note(id, subject, note, type, typeid, createdTime, createdUser, sAccountId, lastUpdatedTime) VALUES (1, '', 'deev e<br>', 'Account', 1, '2013-01-22 21:50:42.0', 'hainguyen@esofthead.com', 1, '2013-01-22 21:50:42.0');
INSERT INTO m_crm_note(id, subject, note, type, typeid, createdTime, createdUser, sAccountId, lastUpdatedTime) VALUES (2, '', 'fewfew ee<br>', 'Account', 1, '2013-01-22 21:51:00.0', 'hainguyen@esofthead.com', 1, '2013-01-22 21:51:00.0');
INSERT INTO m_crm_note(id, subject, note, type, typeid, createdTime, createdUser, sAccountId, lastUpdatedTime) VALUES (3, '', 'ojt4tj4tj43 pp4j43y43<br>', 'Account', 7, '2013-03-05 21:26:07.0', 'hainguyen@esofthead.com', 1, '2013-03-05 21:26:07.0');
INSERT INTO m_crm_note(id, subject, note, type, typeid, createdTime, createdUser, sAccountId, lastUpdatedTime) VALUES (4, '', '4tpk4ptk43t[ k[hkh[kre [kh43h<br>', 'Account', 7, '2013-03-05 21:26:19.0', 'hainguyen@esofthead.com', 1, '2013-03-05 21:26:19.0');
INSERT INTO m_crm_note(id, subject, note, type, typeid, createdTime, createdUser, sAccountId, lastUpdatedTime) VALUES (5, '', 'fefew e<br>', 'Account', 3, '2013-03-06 15:12:29.0', 'hainguyen@esofthead.com', 1, '2013-03-06 15:12:29.0');
ALTER TABLE m_crm_note ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_monitor_item -----------------------------------
ALTER TABLE m_monitor_item ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (1, 'hainguyen@esofthead.com', '2013-02-13 14:04:23.0', 'Project-Bug', 18);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (2, 'hainguyen@esofthead.com', '2013-02-20 08:44:46.0', 'Project-Bug', 19);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (3, 'hainguyen@esofthead.com', '2013-02-20 08:53:57.0', 'Project-Bug', 20);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (4, 'hainguyen@esofthead.com', '2013-02-20 10:24:27.0', 'Project-Bug', 22);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (5, 'hainguyen@esofthead.com', '2013-02-21 09:40:49.0', 'Project-Task', 23);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (6, 'c@a.com', '2013-02-21 09:40:49.0', 'Project-Task', 23);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (7, 'hainguyen@esofthead.com', '2013-02-21 17:16:09.0', 'Project-Task', 24);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (8, 'b@esofthead.com', '2013-02-21 17:16:09.0', 'Project-Task', 24);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (9, 'hainguyen@esofthead.com', '2013-02-21 17:19:47.0', 'Project-Task', 25);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (10, 'c@a.com', '2013-02-21 17:19:47.0', 'Project-Task', 25);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (11, 'hainguyen@esofthead.com', '2013-02-21 17:21:40.0', 'Project-Task', 26);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (12, 'c@a.com', '2013-02-21 17:21:40.0', 'Project-Task', 26);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (13, 'hainguyen@esofthead.com', '2013-02-21 22:40:01.0', 'Project-Task', 27);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (14, 'hainguyen@esofthead.com', '2013-02-21 23:57:50.0', 'Project-Task', 29);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (15, 'hainguyen@esofthead.com', '2013-02-22 00:02:01.0', 'Project-Task', 30);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (16, 'hainguyen@esofthead.com', '2013-02-25 20:35:49.0', 'Project-Task', 31);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (17, 'hainguyen@esofthead.com', '2013-02-25 20:40:19.0', 'Project-Task', 32);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (18, 'hainguyen@esofthead.com', '2013-02-25 20:45:05.0', 'Project-Task', 33);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (19, 'hainguyen@esofthead.com', '2013-02-25 21:00:20.0', 'Project-Task', 34);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (20, 'hainguyen@esofthead.com', '2013-02-25 21:07:53.0', 'Project-Task', 35);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (21, 'hainguyen@esofthead.com', '2013-02-25 21:41:16.0', 'Project-Task', 36);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (22, 'hainguyen@esofthead.com', '2013-02-25 21:48:43.0', 'Project-Task', 37);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (23, 'hainguyen@esofthead.com', '2013-02-25 21:51:22.0', 'Project-Task', 38);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (24, 'hainguyen@esofthead.com', '2013-02-25 21:55:24.0', 'Project-Task', 39);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (25, 'hainguyen@esofthead.com', '2013-02-25 22:00:59.0', 'Project-Task', 40);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (26, 'hainguyen@esofthead.com', '2013-02-25 22:06:08.0', 'Project-Task', 41);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (27, 'hainguyen@esofthead.com', '2013-02-25 22:17:09.0', 'Project-Task', 42);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (28, 'hainguyen@esofthead.com', '2013-02-25 22:22:45.0', 'Project-Task', 43);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (29, 'hainguyen@esofthead.com', '2013-02-25 22:28:22.0', 'Project-Task', 44);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (30, 'hainguyen@esofthead.com', '2013-02-25 23:08:23.0', 'Project-Task', 45);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (31, 'hainguyen@esofthead.com', '2013-02-26 08:35:12.0', 'Project-Task', 46);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (32, 'hainguyen@esofthead.com', '2013-02-26 10:11:53.0', 'Project-Task', 47);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (33, 'x@a.com', '2013-02-26 10:11:53.0', 'Project-Task', 47);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (34, 'hainguyen@esofthead.com', '2013-02-26 13:05:43.0', 'Project-Task', 48);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (35, 'hainguyen@esofthead.com', '2013-02-26 13:24:21.0', 'Project-Task', 49);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (36, 'hainguyen@esofthead.com', '2013-02-27 11:34:37.0', 'Project-Task', 50);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (37, 'hainguyen@esofthead.com', '2013-02-27 11:43:06.0', 'Project-Task', 51);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (38, 'hainguyen@esofthead.com', '2013-02-27 14:32:59.0', 'Project-Task', 52);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (39, 'hainguyen@esofthead.com', '2013-02-27 14:56:11.0', 'Project-Task', 53);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (40, 'hainguyen@esofthead.com', '2013-02-27 16:44:20.0', 'Project-Task', 54);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (41, 'hainguyen@esofthead.com', '2013-02-27 16:46:02.0', 'Project-Bug', 23);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (42, 'hainguyen@esofthead.com', '2013-02-27 16:53:06.0', 'Project-Bug', 24);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (43, 'hainguyen@esofthead.com', '2013-02-27 17:00:22.0', 'Project-Bug', 25);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (44, 'hainguyen@esofthead.com', '2013-02-27 17:10:04.0', 'Project-Bug', 26);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (45, 'hainguyen@esofthead.com', '2013-02-27 18:32:29.0', 'Project-Bug', 27);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (46, 'hainguyen@esofthead.com', '2013-02-27 18:42:26.0', 'Project-Bug', 28);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (47, 'hainguyen@esofthead.com', '2013-02-28 12:26:57.0', 'Project-Bug', 29);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (48, 'hainguyen@esofthead.com', '2013-02-28 12:44:48.0', 'Project-Bug', 30);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (49, 'hainguyen@esofthead.com', '2013-02-28 12:45:08.0', 'Project-Bug', 31);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (50, 'hainguyen@esofthead.com', '2013-02-28 13:44:58.0', 'Project-Bug', 32);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (51, 'hainguyen@esofthead.com', '2013-02-28 13:45:48.0', 'Project-Bug', 33);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (52, 'hainguyen@esofthead.com', '2013-02-28 14:03:37.0', 'Project-Bug', 34);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (53, 'hainguyen@esofthead.com', '2013-02-28 14:10:40.0', 'Project-Bug', 35);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (54, 'hainguyen@esofthead.com', '2013-02-28 14:12:59.0', 'Project-Task', 55);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (55, 'hainguyen@esofthead.com', '2013-02-28 18:26:50.0', 'Project-Bug', 36);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (56, 'hainguyen@esofthead.com', '2013-03-07 13:29:53.0', 'Project-Task', 56);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (57, 'hainguyen@esofthead.com', '2013-03-09 21:00:24.0', 'Project-Bug', 37);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (58, 'hainguyen@esofthead.com', '2013-03-09 21:01:23.0', 'Project-Bug', 38);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (59, 'hainguyen@esofthead.com', '2013-03-09 21:01:50.0', 'Project-Bug', 39);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (60, 'hainguyen@esofthead.com', '2013-03-11 09:41:56.0', 'Project-Task', 57);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (61, 'hainguyen@esofthead.com', '2013-03-11 09:42:13.0', 'Project-Task', 58);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (62, 'hainguyen@esofthead.com', '2013-03-11 09:42:26.0', 'Project-Task', 59);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (63, 'hainguyen@esofthead.com', '2013-03-11 09:42:56.0', 'Project-Task', 60);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (64, 'hainguyen@esofthead.com', '2013-03-14 13:53:28.0', 'Project-Task', 61);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (65, 'hainguyen@esofthead.com', '2013-03-17 07:29:03.0', 'Project-Task', 62);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (66, 'hainguyen@esofthead.com', '2013-03-18 11:37:05.0', 'Project-Task', 63);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (67, 'hainguyen@esofthead.com', '2013-03-18 16:09:33.0', 'Project-Bug', 40);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (68, 'hainguyen@esofthead.com', '2013-03-18 16:09:48.0', 'Project-Bug', 41);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (69, 'hainguyen@esofthead.com', '2013-03-18 20:59:46.0', 'Project-Task', 64);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (70, 'hainguyen@esofthead.com', '2013-03-22 09:32:09.0', 'Project-Bug', 42);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (71, 'hainguyen@esofthead.com', '2013-03-22 15:01:52.0', 'Project-Bug', 43);
INSERT INTO m_monitor_item(id, user, monitor_date, type, typeid) VALUES (72, 'hainguyen@esofthead.com', '2013-03-26 11:14:20.0', 'Project-Bug', 44);
ALTER TABLE m_monitor_item ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_prj_member -----------------------------------
ALTER TABLE m_prj_member ALTER COLUMN id INT ;
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (1, 'hainguyen@esofthead.com', 2, '2013-01-24 16:41:37.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (2, 'hainguyen@esofthead.com', 3, '2013-01-24 16:45:50.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (3, 'hainguyen@esofthead.com', 4, '2013-01-25 14:01:18.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (7, 'a@a.com', 1, '2013-02-18 14:02:41.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (8, 'c@a.com', 1, '2013-02-18 14:02:55.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (9, 'b@esofthead.com', 1, '2013-02-18 15:03:11.0', 5, 0);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (10, 'hainguyen@esofthead.com', 1, '2013-02-18 14:18:15.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (11, 'a@a.com', 2, '2013-02-18 23:36:11.0', 6, NULL);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (12, 'hainguyen@esofthead.com', 5, '2013-02-22 16:25:55.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (13, 'x@a.com', 1, '2013-02-26 10:11:34.0', NULL, NULL);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (14, 'hainguyen@esofthead.com', 6, '2013-02-27 09:49:37.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (15, 'hainguyen@esofthead.com', 7, '2013-02-27 09:49:54.0', NULL, 1);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (16, 'user2', 6, '2013-03-07 16:31:36.0', 7, 0);
INSERT INTO m_prj_member(id, username, projectId, joinDate, projectRoleId, isAdmin) VALUES (18, 'b@esofthead.com', 6, '2013-03-07 16:44:09.0', 7, 0);
ALTER TABLE m_prj_member ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_prj_message -----------------------------------
ALTER TABLE m_prj_message ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_prj_message(id, title, message, posteddate, posteduser, projectid, category, createdTime, lastUpdatedTime, sAccountId, isStick) VALUES (1, 'cscsac', 'cascsa ccwq<br>', '2013-01-22 22:54:38.0', 'hainguyen@esofthead.com', 1, NULL, '2013-01-22 22:54:38.0', '2013-01-22 22:54:38.0', 1, NULL);
INSERT INTO m_prj_message(id, title, message, posteddate, posteduser, projectid, category, createdTime, lastUpdatedTime, sAccountId, isStick) VALUES (2, 'dwqdwq e', 'fefew <br>', '2013-01-23 10:51:01.0', 'hainguyen@esofthead.com', 1, NULL, '2013-01-23 10:51:01.0', '2013-01-23 10:51:01.0', 1, NULL);
INSERT INTO m_prj_message(id, title, message, posteddate, posteduser, projectid, category, createdTime, lastUpdatedTime, sAccountId, isStick) VALUES (3, 'sss', 'vsdvsd ee<br>', '2013-01-25 17:48:08.0', 'hainguyen@esofthead.com', 1, NULL, '2013-01-25 17:48:08.0', '2013-01-25 17:48:08.0', 1, NULL);
INSERT INTO m_prj_message(id, title, message, posteddate, posteduser, projectid, category, createdTime, lastUpdatedTime, sAccountId, isStick) VALUES (4, 'fwqfwqfqw', 'wfwqfqwf', '2013-03-01 11:49:21.0', 'hainguyen@esofthead.com', 6, NULL, '2013-03-01 11:49:21.0', '2013-03-01 11:49:21.0', 1, NULL);
INSERT INTO m_prj_message(id, title, message, posteddate, posteduser, projectid, category, createdTime, lastUpdatedTime, sAccountId, isStick) VALUES (5, 'dwqdwq', 'fwqfwq ee<br>', '2013-03-05 20:58:36.0', 'hainguyen@esofthead.com', 2, NULL, '2013-03-05 20:58:36.0', '2013-03-05 20:58:36.0', 1, NULL);
ALTER TABLE m_prj_message ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for m_prj_milestone -----------------------------------
ALTER TABLE m_prj_milestone ALTER COLUMN id INT ;
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (1, 'qdqdf', NULL, '2013-01-14 23:12:49.0', '2013-01-28 23:12:49.0', 'hainguyen@esofthead.com', 'External', 1, '2013-01-22 23:13:02.0', '2013-02-04 10:11:09.0', 1, 'In Progress');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (2, 'fefewfw', NULL, NULL, NULL, 'hainguyen@esofthead.com', NULL, 1, '2013-01-24 09:28:10.0', '2013-01-24 09:28:10.0', 1, 'In Progress');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (3, 'wfwqf', 'fwfw efewg<br>', NULL, NULL, 'b@esofthead.com', 'External', 6, '2013-03-08 13:57:46.0', '2013-03-08 13:57:46.0', 1, 'In Progress');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (4, 'rwr', 'rwar<br>', NULL, NULL, 'b@esofthead.com', 'External', 6, '2013-03-08 14:09:57.0', '2013-03-08 14:09:57.0', 1, 'In Progress');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (5, 'test', NULL, NULL, NULL, 'a@a.com', 'External', 2, '2013-03-09 00:26:20.0', '2013-03-12 14:00:37.0', 1, 'In Progress');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (6, 'wqfwqfq', 'fwqfwq ww<br>', '2013-03-11 23:57:26.0', '2013-03-29 23:57:26.0', 'hainguyen@esofthead.com', NULL, 2, '2013-03-12 23:57:47.0', '2013-03-12 23:57:47.0', 1, 'Future');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (7, 'dwqf', 'fwqfwq<br>', '2013-01-29 23:57:53.0', '2013-03-03 23:57:53.0', 'a@a.com', NULL, 2, '2013-03-12 23:58:11.0', '2013-03-12 23:58:11.0', 1, 'Closed');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (8, '2211 few', 'fewfew e www<br>', '2013-03-19 23:58:30.0', '2013-03-29 23:58:30.0', 'a@a.com', NULL, 2, '2013-03-12 23:58:41.0', '2013-03-12 23:59:59.0', 1, 'In Progress');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (9, 'oheowhto  oejwg4', 't4t43<br>', NULL, NULL, 'a@a.com', NULL, 2, '2013-03-14 10:07:00.0', '2013-03-14 10:07:00.0', 1, 'Closed');
INSERT INTO m_prj_milestone(id, name, description, startdate, enddate, owner, flag, projectid, createdTime, lastUpdatedTime, sAccountId, status) VALUES (10, 'egg', 'ege ee', NULL, NULL, 'b@esofthead.com', NULL, 1, '2013-03-17 07:07:04.0', '2013-03-17 07:07:04.0', 1, 'In Progress');
ALTER TABLE m_prj_milestone ALTER COLUMN id INT AUTO_INCREMENT ;


-- Generate script insert data for m_prj_problem -----------------------------------
ALTER TABLE m_prj_problem ALTER COLUMN id INT UNSIGNED ;
INSERT INTO m_prj_problem(id, issuename, description, projectid, raisedbyuser, assigntouser, impact, priority, status, dateraised, datedue, actualstartdate, actualenddate, level, resolution, state, problemsource, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (1, 'eee', 'gewg ee<br>', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'fewgeg<br>', NULL, NULL, '2013-01-23 10:49:49.0', '2013-01-23 10:49:49.0', NULL, NULL, 1);
INSERT INTO m_prj_problem(id, issuename, description, projectid, raisedbyuser, assigntouser, impact, priority, status, dateraised, datedue, actualstartdate, actualenddate, level, resolution, state, problemsource, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (2, 'rj3rjutu43j ut', 'rpgreh0rueu -uw-gurh-ure 0ir-0hit<br>', 1, 'a@a.com', 'c@a.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2013-01-29 13:30:44.0', '2013-02-16 21:47:50.0', NULL, NULL, 1);
INSERT INTO m_prj_problem(id, issuename, description, projectid, raisedbyuser, assigntouser, impact, priority, status, dateraised, datedue, actualstartdate, actualenddate, level, resolution, state, problemsource, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (3, 'ifheofhewohoh oehgioehgioehwioh hegihegiewhgpewh phpghpogjewopgjewop jpejwgopewjgopejgopj pjeogpjewopgjewopj jegopejwgopjewopj pjepogjewpogj ewjgpewjh', 'wfhwqofhwq ohwofw<br>', 1, 'hainguyen@esofthead.com', 'hainguyen@esofthead.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2013-02-20 15:47:34.0', '2013-02-20 17:38:31.0', NULL, NULL, 1);
INSERT INTO m_prj_problem(id, issuename, description, projectid, raisedbyuser, assigntouser, impact, priority, status, dateraised, datedue, actualstartdate, actualenddate, level, resolution, state, problemsource, createdTime, lastUpdatedTime, type, typeid, sAccountId) VALUES (4, 'few e', 'ffewf<br>', 6, 'hainguyen@esofthead.com', NULL, NULL, 'Medium', 'Open', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, '2013-02-28 23:22:00.0', '2013-02-28 23:22:00.0', NULL, NULL, 1);
ALTER TABLE m_prj_problem ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;


-- Generate script insert data for s_role_permission -----------------------------------
ALTER TABLE s_role_permission ALTER COLUMN id INT UNSIGNED ;
INSERT INTO s_role_permission(id, roleid, roleVal) VALUES (1, 1, '<?xml version=""1.0"" ?><com.esofthead.mycollab.common.domain.PermissionMap serialization=""custom""><unserializable-parents></unserializable-parents><map><default><loadFactor>0.75</loadFactor><threshold>12</threshold></default><int>16</int><int>11</int><string>Campaign</string><int>0</int><string>User</string><int>0</int><string>Call</string><int>0</int><string>Contact</string><int>0</int><string>Opportunity</string><int>0</int><string>Task</string><int>0</int><string>Account</string><int>2</int><string>Case</string><int>0</int><string>Meeting</string><int>0</int><string>Role</string><int>0</int><string>Lead</string><int>0</int></map></com.esofthead.mycollab.common.domain.PermissionMap>');
ALTER TABLE s_role_permission ALTER COLUMN id INT UNSIGNED AUTO_INCREMENT ;

-- Add unique constraint -------------------------------------------


ALTER TABLE m_hr_employee_contact ADD UNIQUE INDEX `employeeid` (`employeeid` ASC);

ALTER TABLE m_hr_employee_contact ADD UNIQUE INDEX `employeeid_2` (`employeeid` ASC);

ALTER TABLE m_hr_employee_job ADD UNIQUE INDEX `jobtitleid` (`jobtitleid` ASC);

ALTER TABLE m_hr_employee_job ADD UNIQUE INDEX `jobtitleid_2` (`jobtitleid` ASC);

ALTER TABLE s_country ADD UNIQUE INDEX `id` (`id` ASC);

ALTER TABLE s_user ADD UNIQUE INDEX `username` (`username` ASC);

ALTER TABLE s_user_information ADD UNIQUE INDEX `FK_hr_employee_2` (`username` ASC);

ALTER TABLE schema_version ADD UNIQUE INDEX `version` (`version` ASC);


-- Add foreign key constraint -------------------------------------------


ALTER TABLE m_crm_accounts_contacts ADD CONSTRAINT `FK_m_crm_accounts_contacts_1` FOREIGN KEY (`accountId`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_accounts_leads ADD CONSTRAINT `FK_m_crm_accounts_leads_1` FOREIGN KEY (`accountId`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_campaigns_accounts ADD CONSTRAINT `FK_m_crm_campaigns_accounts_2` FOREIGN KEY (`accountId`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_case ADD CONSTRAINT `FK_m_crm_case_1` FOREIGN KEY (`accountId`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contract ADD CONSTRAINT `FK_m_crm_contract_1` FOREIGN KEY (`accountid`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_product ADD CONSTRAINT `FK_m_crm_product_1` FOREIGN KEY (`accountid`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_4` FOREIGN KEY (`shipaccount`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_6` FOREIGN KEY (`billaccount`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_project ADD CONSTRAINT `FK_m_project_project_1` FOREIGN KEY (`account`) REFERENCES m_crm_account(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_campaigns_accounts ADD CONSTRAINT `FK_m_crm_campaigns_accounts_1` FOREIGN KEY (`campaignId`) REFERENCES m_crm_campaign(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_campaigns_contacts ADD CONSTRAINT `FK_m_crm_campaigns_contacts_1` FOREIGN KEY (`campaignId`) REFERENCES m_crm_campaign(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_campaigns_leads ADD CONSTRAINT `FK_m_crm_campaigns_leads_1` FOREIGN KEY (`campaignId`) REFERENCES m_crm_campaign(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contact ADD CONSTRAINT `FK_m_crm_contact_3` FOREIGN KEY (`campaignId`) REFERENCES m_crm_campaign(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_lead ADD CONSTRAINT `FK_m_crm_lead_2` FOREIGN KEY (`campaignId`) REFERENCES m_crm_campaign(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_opportunity ADD CONSTRAINT `FK_m_crm_opportunity_2` FOREIGN KEY (`campaignid`) REFERENCES m_crm_campaign(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_contacts_cases ADD CONSTRAINT `FK_m_crm_contacts_cases_2` FOREIGN KEY (`caseId`) REFERENCES m_crm_case(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_accounts_contacts ADD CONSTRAINT `FK_m_crm_accounts_contacts_2` FOREIGN KEY (`contactId`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_call ADD CONSTRAINT `FK_m_crm_call_2` FOREIGN KEY (`contactId`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_campaigns_contacts ADD CONSTRAINT `FK_m_crm_campaigns_contacts_2` FOREIGN KEY (`contactId`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contacts_cases ADD CONSTRAINT `FK_m_crm_contacts_cases_1` FOREIGN KEY (`contactId`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contacts_opportunities ADD CONSTRAINT `FK_m_crm_contacts_opportunities_1` FOREIGN KEY (`contactId`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_opportunities_contacts ADD CONSTRAINT `FK_m_crm_opportunities_contacts_2` FOREIGN KEY (`contactId`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_product ADD CONSTRAINT `FK_m_crm_product_2` FOREIGN KEY (`contactid`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_3` FOREIGN KEY (`billcontact`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_5` FOREIGN KEY (`shipcontact`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_task ADD CONSTRAINT `FK_m_crm_task_1` FOREIGN KEY (`contactId`) REFERENCES m_crm_contact(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_accounts_leads ADD CONSTRAINT `FK_m_crm_accounts_leads_2` FOREIGN KEY (`leadId`) REFERENCES m_crm_lead(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_campaigns_leads ADD CONSTRAINT `FK_m_crm_campaigns_leads_2` FOREIGN KEY (`leadId`) REFERENCES m_crm_lead(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_opportunities_leads ADD CONSTRAINT `FK_m_crm_opportunities_leads_2` FOREIGN KEY (`leadId`) REFERENCES m_crm_lead(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contacts_opportunities ADD CONSTRAINT `FK_m_crm_contacts_opportunities_2` FOREIGN KEY (`opportunityId`) REFERENCES m_crm_opportunity(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contract ADD CONSTRAINT `FK_m_crm_contract_2` FOREIGN KEY (`opportunityid`) REFERENCES m_crm_opportunity(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_opportunities_contacts ADD CONSTRAINT `FK_m_crm_opportunities_contacts_1` FOREIGN KEY (`opportunityId`) REFERENCES m_crm_opportunity(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_opportunities_leads ADD CONSTRAINT `FK_m_crm_opportunities_leads_1` FOREIGN KEY (`opportunityId`) REFERENCES m_crm_opportunity(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_2` FOREIGN KEY (`opportunityid`) REFERENCES m_crm_opportunity(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_quote_group_product ADD CONSTRAINT `FK_m_crm_quote_group_product_1` FOREIGN KEY (`quoteid`) REFERENCES m_crm_quote(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_quote_group_product ADD CONSTRAINT `FK_m_crm_quote_group_product_2` FOREIGN KEY (`quoteid`) REFERENCES m_crm_quote(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_product ADD CONSTRAINT `FK_m_crm_product_3` FOREIGN KEY (`groupid`) REFERENCES m_crm_quote_group_product(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_division ADD CONSTRAINT `FK60BF604224D3B4D2` FOREIGN KEY (`companyid`) REFERENCES m_hr_company(`id`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_hr_division ADD CONSTRAINT `FK60BF6042E79A998C` FOREIGN KEY (`divisionparentid`) REFERENCES m_hr_division(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_employee_job ADD CONSTRAINT `FK318707C12CF69922` FOREIGN KEY (`divisionid`) REFERENCES m_hr_division(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_division ADD CONSTRAINT `FK60BF6042B281B3E3` FOREIGN KEY (`divisiontypeid`) REFERENCES m_hr_divisiontype(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_hr_employee_education ADD CONSTRAINT `FK_hr_employee_education_1` FOREIGN KEY (`educationid`) REFERENCES m_hr_education(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_employee_job ADD CONSTRAINT `FK318707C172DE643` FOREIGN KEY (`employmentstatusid`) REFERENCES m_hr_employment_status(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_employee_job ADD CONSTRAINT `FK318707C165C6D723` FOREIGN KEY (`jobtitleid`) REFERENCES m_hr_jobtitle(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_employee_language ADD CONSTRAINT `FK_m_hr_employee_language_3` FOREIGN KEY (`languageid`) REFERENCES m_hr_language(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_employee_language ADD CONSTRAINT `FK_m_hr_employee_language_1` FOREIGN KEY (`levelid`) REFERENCES m_hr_language_level(`id`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_hr_employee_leave ADD CONSTRAINT `FK_m_hr_employee_leave_2` FOREIGN KEY (`leavetypeid`) REFERENCES m_hr_leave_type(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_employee_licenses ADD CONSTRAINT `FK_hr_employee_licenses_1` FOREIGN KEY (`licenseid`) REFERENCES m_hr_licenses(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_employee_payment ADD CONSTRAINT `FK_m_hr_employee_payment_2` FOREIGN KEY (`paygradeid`) REFERENCES m_hr_pay_grades(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_job_categories ADD CONSTRAINT `FK12FA3B39D772EDD` FOREIGN KEY (`paygradeid`) REFERENCES m_hr_pay_grades(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_employee_skill ADD CONSTRAINT `FKEC6145555D1A8552` FOREIGN KEY (`skillid`) REFERENCES m_hr_skill(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_task_list ADD CONSTRAINT `PK_m_prj_task_list_2` FOREIGN KEY (`milestoneId`) REFERENCES m_prj_milestone(`id`) ON UPDATE SET NULL ON DELETE CASCADE;
ALTER TABLE m_tracker_bug ADD CONSTRAINT `FK_m_tracker_bug_6` FOREIGN KEY (`milestoneId`) REFERENCES m_prj_milestone(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_member ADD CONSTRAINT `FK_m_prj_member_2` FOREIGN KEY (`projectId`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_message ADD CONSTRAINT `FK_m_prj_message_2` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_milestone ADD CONSTRAINT `PK_m_prj_milestone_2` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_resource ADD CONSTRAINT `FK_m_prj_resource_2` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_risk ADD CONSTRAINT `FK_m_prj_risk1_1` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_role ADD CONSTRAINT `FK_m_prj_role_2` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_role_permission ADD CONSTRAINT `FK_m_prj_role_permission_2` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_standup ADD CONSTRAINT `FK_m_prj_standup_3` FOREIGN KEY (`projectId`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_task ADD CONSTRAINT `FK_m_prj_task_1` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_task_list ADD CONSTRAINT `PK_m_prj_task_list_1` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_time_logging ADD CONSTRAINT `FK_m_prj_time_logging_1` FOREIGN KEY (`projectId`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_bug ADD CONSTRAINT `FK_m_tracker_bug_4` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_component ADD CONSTRAINT `FK_m_tracker_component_1` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_metadata ADD CONSTRAINT `FK_m_tracker_metadata_1` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_query ADD CONSTRAINT `FK_m_tracker_query_2` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_tracker_version ADD CONSTRAINT `FK_m_tracker_version_1` FOREIGN KEY (`projectid`) REFERENCES m_prj_project(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_task_resource ADD CONSTRAINT `FK_m_prj_task_resource_1` FOREIGN KEY (`resourceid`) REFERENCES m_prj_resource(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_resource ADD CONSTRAINT `FK_m_prj_resource_3` FOREIGN KEY (`roleid`) REFERENCES m_prj_role(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_role_permission ADD CONSTRAINT `FK_m_prj_role_permission_1` FOREIGN KEY (`roleid`) REFERENCES m_prj_role(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_task_resource ADD CONSTRAINT `FK_m_prj_task_resource_2` FOREIGN KEY (`taskid`) REFERENCES m_prj_task(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_task ADD CONSTRAINT `FK_m_prj_task_2` FOREIGN KEY (`tasklistid`) REFERENCES m_prj_task_list(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tag_taxonomy ADD CONSTRAINT `FK_m_ecm_tag_taxonomy_1` FOREIGN KEY (`tagid`) REFERENCES m_tag_tag(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tag_relationship ADD CONSTRAINT `FK_m_ecm_tag_relationship_1` FOREIGN KEY (`tagtaxonomy`) REFERENCES m_tag_taxonomy(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_todo ADD CONSTRAINT `FK_m_todo_3` FOREIGN KEY (`categoryId`) REFERENCES m_todo_category(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_todo ADD CONSTRAINT `FK_m_todo_4` FOREIGN KEY (`statusId`) REFERENCES m_todo_status(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_tracker_bug_related_item ADD CONSTRAINT `FK_ m_tracker_bug_related_item_1` FOREIGN KEY (`bugid`) REFERENCES m_tracker_bug(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_history ADD CONSTRAINT `FK_m_tracker_history_1` FOREIGN KEY (`relatedbug`) REFERENCES m_tracker_bug(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_related_bug ADD CONSTRAINT `FK_m_tracker_related_bug_1` FOREIGN KEY (`bugid`) REFERENCES m_tracker_bug(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_related_bug ADD CONSTRAINT `FK_m_tracker_related_bug_2` FOREIGN KEY (`relatedid`) REFERENCES m_tracker_bug(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_audit_log ADD CONSTRAINT `FK_m_audit_log_2` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_colla_workgroup ADD CONSTRAINT `FK_m_colla_workgroup_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_comment ADD CONSTRAINT `FK_m_comment_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_account ADD CONSTRAINT `FK_m_crm_account_7` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_call ADD CONSTRAINT `FK_m_crm_call_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_campaign ADD CONSTRAINT `FK_m_crm_campaign_6` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_case ADD CONSTRAINT `FK_m_crm_case_3` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contact ADD CONSTRAINT `FK_m_crm_contact_7` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_contract ADD CONSTRAINT `FK_m_crm_contract_6` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_customer ADD CONSTRAINT `FK_crm_customer_4` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_lead ADD CONSTRAINT `FK_m_crm_lead_6` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_meeting ADD CONSTRAINT `FK_m_crm_meeting_2` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_note ADD CONSTRAINT `FK_m_crm_note_4` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_opportunity ADD CONSTRAINT `FK_m_crm_opportunity_6` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_product ADD CONSTRAINT `FK_m_crm_product_5` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_product_catalog ADD CONSTRAINT `FK_m_crm_product_catalog_2` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_8` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_target ADD CONSTRAINT `FK_m_crm_target_4` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_target_list ADD CONSTRAINT `FK_m_crm_target_list_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_task ADD CONSTRAINT `FK_m_crm_task_5` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_company ADD CONSTRAINT `FK_m_hr_company_2` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_company_policy ADD CONSTRAINT `m_hr_company_policy_fk2` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_hr_education ADD CONSTRAINT `FK_hr_education_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_employment_status ADD CONSTRAINT `FK_m_hr_employment_status_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_holiday ADD CONSTRAINT `m_hr_holiday_pk_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_hr_jobtitle ADD CONSTRAINT `FK_m_hr_jobtitle_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_language ADD CONSTRAINT `FK_m_hr_language_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_licenses ADD CONSTRAINT `FK_m_hr_licenses_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_location ADD CONSTRAINT `FK_m_hr_location_2` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_pay_grades ADD CONSTRAINT `FK_m_hr_pay_grades_2` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_skill ADD CONSTRAINT `FK_m_hr_skill_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_message ADD CONSTRAINT `FK_m_prj_message_3` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_problem ADD CONSTRAINT `FK_m_prj_risk_3` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_project ADD CONSTRAINT `FK_m_prj_project_4` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_risk ADD CONSTRAINT `FK_m_prj_risk1_4` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_role ADD CONSTRAINT `FK_m_prj_role_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_standup ADD CONSTRAINT `FK_m_prj_standup_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_task ADD CONSTRAINT `FK_m_prj_task_4` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_bug ADD CONSTRAINT `FK_m_tracker_bug_5` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_component ADD CONSTRAINT `FK_m_tracker_component_4` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_tracker_version ADD CONSTRAINT `FK_m_tracker_version_3` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_account_settings ADD CONSTRAINT `FK_s_account_settings_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_activitystream ADD CONSTRAINT `FK_m_crm_activitystream_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_relay_mail ADD CONSTRAINT `FK_s_relay_mail_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_report_bug_issue ADD CONSTRAINT `FK_s_report_bug_issue_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE s_roles ADD CONSTRAINT `FK_s_roles_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_user ADD CONSTRAINT `s_user_fk_1` FOREIGN KEY (`accountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_user_tracking ADD CONSTRAINT `FK_s_user_tracking_1` FOREIGN KEY (`sAccountId`) REFERENCES s_account(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_account ADD CONSTRAINT `s_account_fk_1` FOREIGN KEY (`billingPlanId`) REFERENCES s_billing_plan(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_crm_target ADD CONSTRAINT `FK_m_crm_target_2` FOREIGN KEY (`primaryCountryId`) REFERENCES s_country(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_company ADD CONSTRAINT `FK986464871461B5C` FOREIGN KEY (`countryid`) REFERENCES s_country(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_employee_contact ADD CONSTRAINT `FK11DEEE2471461B5C` FOREIGN KEY (`countryid`) REFERENCES s_country(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_employee_immigration ADD CONSTRAINT `FKEEB3D72E99EA25CF` FOREIGN KEY (`citizenship`) REFERENCES s_country(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_employee_personal ADD CONSTRAINT `FK_hr_employee_personal_1` FOREIGN KEY (`countryid`) REFERENCES s_country(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_hr_location ADD CONSTRAINT `FKBC51CF4A1B8FF853` FOREIGN KEY (`countryid`) REFERENCES s_country(`id`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_crm_campaign ADD CONSTRAINT `FK_m_crm_campaign_2` FOREIGN KEY (`currencyId`) REFERENCES s_currency(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_contract ADD CONSTRAINT `FK_m_crm_contract_4` FOREIGN KEY (`currencyid`) REFERENCES s_currency(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_product_catalog ADD CONSTRAINT `FK_m_crm_product_catalog_1` FOREIGN KEY (`currencyid`) REFERENCES s_currency(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_pay_grades ADD CONSTRAINT `FK125999482E338778` FOREIGN KEY (`currencyid`) REFERENCES s_currency(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_project ADD CONSTRAINT `FK_m_prj_project_3` FOREIGN KEY (`currencyid`) REFERENCES s_currency(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE s_role_permission ADD CONSTRAINT `FK_role_permission_1` FOREIGN KEY (`roleid`) REFERENCES s_roles(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_user ADD CONSTRAINT `FK_s_user_3` FOREIGN KEY (`roleid`) REFERENCES s_roles(`id`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_audit_log ADD CONSTRAINT `FK_m_audit_log_1` FOREIGN KEY (`posteduser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_account ADD CONSTRAINT `FK_m_crm_account_6` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE SET NULL ON DELETE SET NULL;
ALTER TABLE m_crm_account ADD CONSTRAINT `FK_m_crm_account_8` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_call ADD CONSTRAINT `FK_m_crm_call_3` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_call ADD CONSTRAINT `FK_m_crm_call_4` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_campaign ADD CONSTRAINT `FK_m_crm_campaign_5` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_campaign ADD CONSTRAINT `FK_m_crm_campaign_7` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_case ADD CONSTRAINT `FK_m_crm_case_2` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_case ADD CONSTRAINT `FK_m_crm_case_4` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_contact ADD CONSTRAINT `FK_m_crm_contact_6` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_contact ADD CONSTRAINT `FK_m_crm_contact_8` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_contract ADD CONSTRAINT `FK_m_crm_contract_5` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_contract ADD CONSTRAINT `FK_m_crm_contract_7` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_customer ADD CONSTRAINT `FK_crm_customer_3` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_lead ADD CONSTRAINT `FK_m_crm_lead_5` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_lead ADD CONSTRAINT `FK_m_crm_lead_7` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_meeting ADD CONSTRAINT `FK_m_crm_meeting_1` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_note ADD CONSTRAINT `FK_m_crm_note_3` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_opportunity ADD CONSTRAINT `FK_m_crm_opportunity_5` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_opportunity ADD CONSTRAINT `FK_m_crm_opportunity_7` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_product ADD CONSTRAINT `FK_m_crm_product_4` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_7` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_crm_quote ADD CONSTRAINT `FK_m_crm_quote_9` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_target ADD CONSTRAINT `FK_m_crm_target_3` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_crm_target ADD CONSTRAINT `FK_m_crm_target_5` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_target_list ADD CONSTRAINT `FK_m_crm_target_list_2` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_crm_target_list ADD CONSTRAINT `FK_m_crm_target_list_3` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_task ADD CONSTRAINT `FK_m_crm_task_4` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_crm_task ADD CONSTRAINT `FK_m_crm_task_6` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_ecm_transaction ADD CONSTRAINT `FK_m_ecm_transaction_1` FOREIGN KEY (`owner`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_friend_friend ADD CONSTRAINT `FK_m_friend_friend_1` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_friend_friend ADD CONSTRAINT `FK_m_friend_friend_2` FOREIGN KEY (`friendname`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_friend_request ADD CONSTRAINT `FK_m_friend_request_1` FOREIGN KEY (`requestFrom`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_friend_request ADD CONSTRAINT `FK_m_friend_request_2` FOREIGN KEY (`requestTo`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_group ADD CONSTRAINT `FK_m_group_1` FOREIGN KEY (`owner`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_hr_company_policy ADD CONSTRAINT `m_hr_company_policy_fk1` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_hr_employee_leave ADD CONSTRAINT `FK_m_hr_employee_leave_1` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_monitor_item ADD CONSTRAINT `FK_m_monitor_item_1` FOREIGN KEY (`user`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_member ADD CONSTRAINT `FK_m_prj_member_1` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_prj_milestone ADD CONSTRAINT `PK_m_prj_milestone_1` FOREIGN KEY (`owner`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_prj_problem ADD CONSTRAINT `FK_m_prj_risk_1` FOREIGN KEY (`raisedbyuser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_problem ADD CONSTRAINT `FK_m_prj_risk_2` FOREIGN KEY (`assigntouser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_project ADD CONSTRAINT `FK_m_prj_project_2` FOREIGN KEY (`owner`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_resource ADD CONSTRAINT `FK_m_prj_resource_1` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_risk ADD CONSTRAINT `FK_m_prj_risk1_2` FOREIGN KEY (`raisedbyuser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_risk ADD CONSTRAINT `FK_m_prj_risk1_3` FOREIGN KEY (`assigntouser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_standup ADD CONSTRAINT `FK_m_prj_standup_2` FOREIGN KEY (`logBy`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE m_prj_task ADD CONSTRAINT `FK_m_prj_task_3` FOREIGN KEY (`assignUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_task ADD CONSTRAINT `FK_m_prj_task_5` FOREIGN KEY (`logBy`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_prj_task_list ADD CONSTRAINT `PK_m_prj_task_list_3` FOREIGN KEY (`owner`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_rss_agreegator ADD CONSTRAINT `FK_m_module_agreegator_1` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_social_message ADD CONSTRAINT `FK_m_social_message_1` FOREIGN KEY (`postedUser`) REFERENCES s_user(`username`) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE m_todo ADD CONSTRAINT `FK_m_todo_1` FOREIGN KEY (`owner`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE m_todo ADD CONSTRAINT `FK_m_todo_2` FOREIGN KEY (`sender`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_tracker_bug ADD CONSTRAINT `FK_m_tracker_bug_1` FOREIGN KEY (`assignuser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_tracker_bug ADD CONSTRAINT `FK_m_tracker_bug_2` FOREIGN KEY (`logby`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_tracker_component ADD CONSTRAINT `FK_m_tracker_component_2` FOREIGN KEY (`userlead`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_tracker_component ADD CONSTRAINT `FK_m_tracker_component_3` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_tracker_query ADD CONSTRAINT `FK_m_tracker_query_1` FOREIGN KEY (`owner`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE m_tracker_version ADD CONSTRAINT `FK_m_tracker_version_2` FOREIGN KEY (`createdUser`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE s_report_bug_issue ADD CONSTRAINT `FK_s_report_bug_issue_2` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE s_user_permission ADD CONSTRAINT `s_user_permission` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_user_preference ADD CONSTRAINT `FK_s_user_preference_1` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE s_user_tracking ADD CONSTRAINT `FK_s_user_tracking_2` FOREIGN KEY (`username`) REFERENCES s_user(`username`) ON UPDATE CASCADE ON DELETE NO ACTION;

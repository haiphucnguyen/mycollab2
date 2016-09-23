--
-- Table structure for table `JGROUPSPING`
--

DROP TABLE IF EXISTS `JGROUPSPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JGROUPSPING` (
  `own_addr` varchar(200) NOT NULL,
  `cluster_name` varchar(200) NOT NULL,
  `ping_data` varbinary(5000) DEFAULT NULL,
  PRIMARY KEY (`own_addr`,`cluster_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


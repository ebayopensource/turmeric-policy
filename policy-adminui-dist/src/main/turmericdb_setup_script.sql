-- MySQL dump 10.13  Distrib 5.1.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: turmericdb
-- ------------------------------------------------------
-- Server version	5.1.49-1ubuntu8.1

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
-- Table structure for table `AuditHistory`
--

DROP TABLE IF EXISTS `AuditHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuditHistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `entityId` bigint(20) NOT NULL,
  `entityName` varchar(255) DEFAULT NULL,
  `entityType` varchar(255) DEFAULT NULL,
  `operationType` varchar(255) DEFAULT NULL,
  `subjectId` bigint(20) NOT NULL,
  `subjectName` varchar(255) DEFAULT NULL,
  `subjectType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AuditHistory`
--

LOCK TABLES `AuditHistory` WRITE;
/*!40000 ALTER TABLE `AuditHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `AuditHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BasicAuth`
--

DROP TABLE IF EXISTS `BasicAuth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BasicAuth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `subjectName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BasicAuth`
--

LOCK TABLES `BasicAuth` WRITE;
/*!40000 ALTER TABLE `BasicAuth` DISABLE KEYS */;
INSERT INTO `BasicAuth` VALUES (1,'jose','2010-12-22 14:13:54',NULL,NULL,'admin','admin'),(2,'jose','2010-12-22 14:13:54',NULL,NULL,'guest','guest');
/*!40000 ALTER TABLE `BasicAuth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConditionTbl`
--

DROP TABLE IF EXISTS `ConditionTbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConditionTbl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `expression_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC21C54C3643700EB` (`expression_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConditionTbl`
--

LOCK TABLES `ConditionTbl` WRITE;
/*!40000 ALTER TABLE `ConditionTbl` DISABLE KEYS */;
INSERT INTO `ConditionTbl` VALUES (1,NULL,NULL,NULL,NULL,1),(2,NULL,NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `ConditionTbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Expression`
--

DROP TABLE IF EXISTS `Expression`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Expression` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `primitiveValue_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBCD6EB8FD60970B` (`primitiveValue_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Expression`
--

LOCK TABLES `Expression` WRITE;
/*!40000 ALTER TABLE `Expression` DISABLE KEYS */;
INSERT INTO `Expression` VALUES (1,NULL,NULL,NULL,NULL,'Hits count','Hits',1),(2,NULL,NULL,NULL,NULL,'service count','serverCount',2);
/*!40000 ALTER TABLE `Expression` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `Operation`
--

DROP TABLE IF EXISTS `Operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `operationName` varchar(255) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDA8CF547555CDD6B` (`resource_id`)
) ENGINE=MyISAM AUTO_INCREMENT=977 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Operation`
--

LOCK TABLES `Operation` WRITE;
/*!40000 ALTER TABLE `Operation` DISABLE KEYS */;
INSERT INTO `Operation` VALUES (1,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'checkout',1),(2,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'commit',2),(3,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'merge',1),(4,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'createPolicy',3),(5,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'getMetaData',3),(6,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'findPolicies',3),(95,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'69',4),(96,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'policydelete',4),(97,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'69',5),(98,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'policydelete',5),(99,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'69',6),(100,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'policydelete',6),(101,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'69',7),(102,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'policydelete',7),(103,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'Admin_Policy_policydelete',8),(104,'admin','2011-01-03 13:51:33',NULL,NULL,NULL,'28',8),(7,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'findSubjectGroups',3),(8,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'findSubjects',3),(9,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'findResources',3),(10,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'createSubjectGroups',3),(13,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'getResources',3),(14,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'getEntityHistory',3),(11,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'updateSubjectGroups',3),(12,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'deletePolicy',3),(20,'admin','2010-01-31 10:49:55',NULL,NULL,NULL,'enablePolicy',3),(21,'admin','2010-01-31 10:49:55',NULL,NULL,NULL,'disablePolicy',3),(22,'admin','2010-01-31 10:49:55',NULL,NULL,NULL,'updatePolicy',3),(23,'admin','2010-01-31 10:49:55',NULL,NULL,NULL,'deleteSubjectGroups',3),(24,'admin','2010-01-31 10:49:55',NULL,NULL,NULL,'findExternalSubjects',3),(25,'admin','2010-01-31 10:49:55',NULL,NULL,NULL,'createSubjects',3),(106,'admin','2011-02-11 19:01:29',NULL,NULL,NULL,'71',4),(108,'admin','2011-02-11 19:01:29',NULL,NULL,NULL,'71',5),(110,'admin','2011-02-11 19:01:29',NULL,NULL,NULL,'71',6),(112,'admin','2011-02-11 19:01:29',NULL,NULL,NULL,'71',7),(113,'admin','2011-02-11 19:01:29',NULL,NULL,NULL,'Admin_Policy_dd',8),(114,'admin','2011-02-11 19:01:29',NULL,NULL,NULL,'29',8),(901,'admin','2011-01-17 00:00:00',NULL,NULL,NULL,'All',4),(912,'admin','2011-01-17 00:00:00',NULL,NULL,NULL,'All',5),(913,'admin','2011-01-17 00:00:00',NULL,NULL,NULL,'All',6),(914,'admin','2011-01-17 00:00:00',NULL,NULL,NULL,'All',7),(915,'admin','2011-01-17 00:00:00',NULL,NULL,NULL,'All',8),(916,'admin','2011-01-17 00:00:00',NULL,NULL,NULL,'All',9),(917,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'SuperPolicy',4),(918,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'73',4),(919,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'SuperPolicy',5),(920,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'73',5),(921,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'SuperPolicy',6),(922,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'73',6),(923,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'SuperPolicy',7),(924,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'73',7),(925,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'Admin_Policy_SuperPolicy',8),(926,'admin','2011-02-11 19:27:09',NULL,NULL,NULL,'30',8);
/*!40000 ALTER TABLE `Operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy`
--

DROP TABLE IF EXISTS `Policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `policyName` varchar(255) DEFAULT NULL,
  `policyType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy`
--

LOCK TABLES `Policy` WRITE;
/*!40000 ALTER TABLE `Policy` DISABLE KEYS */;
INSERT INTO `Policy` VALUES (1,'admin','2010-12-14 10:12:58','admin','2010-12-20 15:02:32','','description of BLPolicy1','BLPolicy1','BLACKLIST'),(3,'admin','2010-12-14 16:39:04','admin','2010-12-23 16:35:55','','description of BLPolicy2','BLPolicy2','BLACKLIST'),(4,'admin','2010-12-14 16:39:04',NULL,NULL,'','description of RL1','RL1','RL'),(5,'admin','2010-12-14 16:39:04',NULL,NULL,'','description of RL2','RL2','RL'),(6,'admin','2010-12-14 16:39:04',NULL,NULL,'','description of whitelist1','whitelist1','WHITELIST'),(8,'admin','2010-12-23 15:25:07',NULL,NULL,'','an auth policy','PolicyService','AUTHZ'),(71,'admin','2011-02-11 19:01:29','admin','2011-02-11 19:02:10','','description of BLPolicy3','BLPolicy3','BLACKLIST'),(73,'admin','2011-02-11 19:27:09','admin','2011-02-17 17:09:52','','The base admin policy','SuperPolicy','AUTHZ'),(74,'admin','2011-02-11 19:27:09','admin','2011-02-11 19:27:10','','Managing SubjectGroup SuperPolicy','Admin_Policy_SuperPolicy','AUTHZ');
/*!40000 ALTER TABLE `Policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_ExclusionSubjectGroups`
--

DROP TABLE IF EXISTS `Policy_ExclusionSubjectGroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_ExclusionSubjectGroups` (
  `Policy_id` bigint(20) NOT NULL,
  `exclusionSubjectGroups_id` bigint(20) NOT NULL,
  KEY `FK45E0F89F741F702C` (`exclusionSubjectGroups_id`),
  KEY `FK45E0F89FD5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy_ExclusionSubjectGroups`
--

LOCK TABLES `Policy_ExclusionSubjectGroups` WRITE;
/*!40000 ALTER TABLE `Policy_ExclusionSubjectGroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `Policy_ExclusionSubjectGroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_ExclusionSubjects`
--

DROP TABLE IF EXISTS `Policy_ExclusionSubjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_ExclusionSubjects` (
  `Policy_id` bigint(20) NOT NULL,
  `exclusionSubjects_id` bigint(20) NOT NULL,
  KEY `FKC8318EE8B3D781A0` (`exclusionSubjects_id`),
  KEY `FKC8318EE8D5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy_ExclusionSubjects`
--

LOCK TABLES `Policy_ExclusionSubjects` WRITE;
/*!40000 ALTER TABLE `Policy_ExclusionSubjects` DISABLE KEYS */;
/*!40000 ALTER TABLE `Policy_ExclusionSubjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_Operation`
--

DROP TABLE IF EXISTS `Policy_Operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_Operation` (
  `Policy_id` bigint(20) NOT NULL,
  `operations_id` bigint(20) NOT NULL,
  KEY `FK6CA0829A4BFA9764` (`operations_id`),
  KEY `FK6CA0829AD5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy_Operation`
--

LOCK TABLES `Policy_Operation` WRITE;
/*!40000 ALTER TABLE `Policy_Operation` DISABLE KEYS */;
INSERT INTO `Policy_Operation` VALUES (1,1),(5,2),(8,12),(8,11),(8,10),(8,14),(8,20),(8,21),(8,22),(8,23),(8,9),(8,8),(8,7),(8,6),(8,13),(8,5),(8,4),(8,24),(8,25),(73,916),(73,901),(73,914),(73,915),(73,912),(73,913),(74,926),(74,924),(74,925),(74,922),(74,923),(74,920),(74,921),(74,918),(74,919),(74,917);
/*!40000 ALTER TABLE `Policy_Operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_Resource`
--

DROP TABLE IF EXISTS `Policy_Resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_Resource` (
  `Policy_id` bigint(20) NOT NULL,
  `resources_id` bigint(20) NOT NULL,
  KEY `FKCAF2247BF4EC17F4` (`resources_id`),
  KEY `FKCAF2247BD5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy_Resource`
--

LOCK TABLES `Policy_Resource` WRITE;
/*!40000 ALTER TABLE `Policy_Resource` DISABLE KEYS */;
INSERT INTO `Policy_Resource` VALUES (1,1),(5,2),(8,3);
/*!40000 ALTER TABLE `Policy_Resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_Rule`
--

DROP TABLE IF EXISTS `Policy_Rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_Rule` (
  `Policy_id` bigint(20) NOT NULL,
  `rules_id` bigint(20) NOT NULL,
  KEY `FKE46F35E941837750` (`rules_id`),
  KEY `FKE46F35E9D5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy_Rule`
--

LOCK TABLES `Policy_Rule` WRITE;
/*!40000 ALTER TABLE `Policy_Rule` DISABLE KEYS */;
INSERT INTO `Policy_Rule` VALUES (1,1),(4,1),(5,1),(5,2),(6,1);
/*!40000 ALTER TABLE `Policy_Rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_Subject`
--

DROP TABLE IF EXISTS `Policy_Subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_Subject` (
  `Policy_id` bigint(20) NOT NULL,
  `subjects_id` bigint(20) NOT NULL,
  KEY `FK5E0FB31FAB44C2E` (`subjects_id`),
  KEY `FK5E0FB31FD5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy_Subject`
--

LOCK TABLES `Policy_Subject` WRITE;
/*!40000 ALTER TABLE `Policy_Subject` DISABLE KEYS */;
INSERT INTO `Policy_Subject` VALUES (1,1),(6,2),(4,3),(6,5),(6,4),(3,3),(8,1),(71,7),(8,7),(73,1);
/*!40000 ALTER TABLE `Policy_Subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_SubjectType`
--

DROP TABLE IF EXISTS `Policy_SubjectType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_SubjectType` (
  `Policy_id` bigint(20) NOT NULL,
  `subjectTypes_id` bigint(20) NOT NULL,
  KEY `FK5E0FB31FAB44C2E` (`subjectTypes_id`),
  KEY `FK5E0FB31FD5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `Policy_SubjectType` WRITE;
/*!40000 ALTER TABLE `Policy_SubjectType` DISABLE KEYS */;
INSERT INTO `Policy_SubjectType` VALUES (1,3);
/*!40000 ALTER TABLE `Policy_SubjectType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Policy_SubjectGroup`
--

DROP TABLE IF EXISTS `Policy_SubjectGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy_SubjectGroup` (
  `Policy_id` bigint(20) NOT NULL,
  `subjectGroups_id` bigint(20) NOT NULL,
  KEY `FKBFCC6EA0AC010E5E` (`subjectGroups_id`),
  KEY `FKBFCC6EA0D5D8832B` (`Policy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Policy_SubjectGroup`
--

LOCK TABLES `Policy_SubjectGroup` WRITE;
/*!40000 ALTER TABLE `Policy_SubjectGroup` DISABLE KEYS */;
INSERT INTO `Policy_SubjectGroup` VALUES (74,30);
/*!40000 ALTER TABLE `Policy_SubjectGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PrimitiveValue`
--

DROP TABLE IF EXISTS `PrimitiveValue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PrimitiveValue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PrimitiveValue`
--

LOCK TABLES `PrimitiveValue` WRITE;
/*!40000 ALTER TABLE `PrimitiveValue` DISABLE KEYS */;
INSERT INTO `PrimitiveValue` VALUES (1,NULL,NULL,NULL,NULL,0,'HITS>5'),(2,NULL,NULL,NULL,NULL,0,'PaymentService.commit:count>3');
/*!40000 ALTER TABLE `PrimitiveValue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Resource`
--

DROP TABLE IF EXISTS `Resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `resourceName` varchar(255) DEFAULT NULL,
  `resourceType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Resource`
--

LOCK TABLES `Resource` WRITE;
/*!40000 ALTER TABLE `Resource` DISABLE KEYS */;
INSERT INTO `Resource` VALUES (1,'admin','2010-12-14 10:49:55',NULL,NULL,'test','testService','SERVICE'),(2,'admin','2010-12-14 11:08:27',NULL,NULL,'desc Payment','PaymentService','SERVICE'),(3,'admin','2010-12-14 10:49:55',NULL,NULL,NULL,'PolicyService','SERVICE'),(4,'admin','2010-12-29 17:38:46',NULL,NULL,NULL,'SERVICE.PolicyService.disablePolicy','OBJECT'),(5,'admin','2010-12-29 17:38:46',NULL,NULL,NULL,'SERVICE.PolicyService.deletePolicy','OBJECT'),(6,'admin','2010-12-29 17:38:46',NULL,NULL,NULL,'SERVICE.PolicyService.enablePolicy','OBJECT'),(7,'admin','2010-12-29 17:38:46',NULL,NULL,NULL,'SERVICE.PolicyService.updatePolicy','OBJECT'),(8,'admin','2010-12-29 17:38:46',NULL,NULL,NULL,'SERVICE.PolicyService.updateSubjectGroups','OBJECT'),(9,'admin','2011-01-02 18:03:17',NULL,NULL,NULL,'SERVICE.PolicyService.deleteSubjectGroups','OBJECT');
/*!40000 ALTER TABLE `Resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rule`
--

DROP TABLE IF EXISTS `Rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `effect` int(11) DEFAULT NULL,
  `effectDuration` bigint(20) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `rolloverPeriod` bigint(20) DEFAULT NULL,
  `ruleName` varchar(255) DEFAULT NULL,
  `condition_id` bigint(20) DEFAULT NULL,
  `notifyEmails` varchar(255) DEFAULT NULL,
  `notifyActive` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK270B1C6850D789` (`condition_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rule`
--

LOCK TABLES `Rule` WRITE;
/*!40000 ALTER TABLE `Rule` DISABLE KEYS */;
INSERT INTO `Rule` VALUES (1,'admin','2011-02-17 19:02:55',NULL,NULL,'HITS TEST',1,10000,3,30000,'rul1',1,NULL,'\0'),(2,'admin','2011-02-17 19:01:55',NULL,NULL,'ServiceXXX.OperationXX.count test',3,10000,NULL,30000,'server',2,NULL,'\0');
/*!40000 ALTER TABLE `Rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subject`
--

DROP TABLE IF EXISTS `Subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `emailContact` varchar(255) DEFAULT NULL,
  `externalSubjectId` bigint(20) NOT NULL,
  `ipMask` varchar(255) DEFAULT NULL,
  `subjectName` varchar(255) DEFAULT NULL,
  `subjectType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subject`
--

LOCK TABLES `Subject` WRITE;
/*!40000 ALTER TABLE `Subject` DISABLE KEYS */;
INSERT INTO `Subject` VALUES (1,'admin','2011-02-17 17:08:33','',NULL,'Admin User',NULL,0,NULL,'admin','USER'),(2,'admin','2010-12-14 10:37:42',NULL,NULL,'IPDESK',NULL,0,NULL,'1.102.96.107','IP'),(3,'admin','2010-12-14 10:37:42',NULL,NULL,'IPDESK',NULL,0,NULL,'1.102.96.103','IP'),(4,'admin','2010-12-14 10:37:42',NULL,NULL,'IPDESK',NULL,0,NULL,'1.102.96.104','IP'),(5,'admin','2010-12-14 10:37:42',NULL,NULL,'IPDESK',NULL,0,NULL,'1.102.96.105','IP'),(6,'admin','2010-12-14 10:37:42',NULL,NULL,'IPDESK',NULL,0,NULL,'1.102.96.106','IP'),(7,'admin','2011-02-17 19:01:55',NULL,NULL,'Guest user',NULL,0,NULL,'guest','USER');
/*!40000 ALTER TABLE `Subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectGroup`
--

DROP TABLE IF EXISTS `SubjectGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectGroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `applyToAll` bit(1) NOT NULL,
  `applyToEach` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `subjectGroupCalculator` varchar(255) DEFAULT NULL,
  `subjectGroupName` varchar(255) DEFAULT NULL,
  `subjectType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectGroup`
--

LOCK TABLES `SubjectGroup` WRITE;
/*!40000 ALTER TABLE `SubjectGroup` DISABLE KEYS */;
INSERT INTO `SubjectGroup` VALUES (23,'admin','2011-01-02 18:03:17',NULL,NULL,'\0','\0','first subjectgroup',NULL,'SG_1','IP'),(30,'admin','2011-02-11 19:27:09','admin','2011-02-18 13:55:47','\0','\0','Managing Admin_Policy_SuperPolicy',NULL,'Admin_Policy_SuperPolicy','USER');
/*!40000 ALTER TABLE `SubjectGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectGroup_Subject`
--

DROP TABLE IF EXISTS `SubjectGroup_Subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectGroup_Subject` (
  `SubjectGroup_id` bigint(20) NOT NULL,
  `subjects_id` bigint(20) NOT NULL,
  KEY `FKFBFB71A0AB44C2E` (`subjects_id`),
  KEY `FKFBFB71A0B009DAAB` (`SubjectGroup_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectGroup_Subject`
--

LOCK TABLES `SubjectGroup_Subject` WRITE;
/*!40000 ALTER TABLE `SubjectGroup_Subject` DISABLE KEYS */;
INSERT INTO `SubjectGroup_Subject` VALUES (12,2),(12,3),(13,3),(12,1),(11,5),(12,4),(12,5),(12,6),(14,5),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,3),(30,1);
/*!40000 ALTER TABLE `SubjectGroup_Subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectType`
--

DROP TABLE IF EXISTS `SubjectType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedBy` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `external` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectType`
--

LOCK TABLES `SubjectType` WRITE;
/*!40000 ALTER TABLE `SubjectType` DISABLE KEYS */;
INSERT INTO `SubjectType` VALUES (1,'admin','2010-12-14 10:49:55',NULL,NULL,'USER','','USER'),(2,'admin','2010-12-14 11:08:27',NULL,NULL,'IP','','IP'),(3,'admin','2010-12-14 10:49:55',NULL,NULL,'DEV','','DEV'),(4,'admin','2010-12-29 17:38:46',NULL,NULL,'APP','','APP');

/*!40000 ALTER TABLE `SubjectType` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-02-21 12:48:31

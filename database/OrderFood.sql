-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 192.168.43.186    Database: order_food
-- ------------------------------------------------------
-- Server version	5.6.39

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
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `MenuId` varchar(2) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`MenuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('1','Hawaiian Jumbo Pizza Puff',129),('10','Kheemao Seafood Spaghetti',139),('11','Spicy Sausage Spaghetti',99),('12','Ham & Mushroom Spaghetti',99),('13','Baked Spinach',179),('14','Korean Style Chicken Wings 6 pcs',129),('15','Chicken Nuggets 6 pcs with Ketchup',79),('16','Chicken Nuggets 6 pcs with Chili Sauce',79),('17','Chicken Sticks',89),('18','Garlic Bread',79),('19','Cheese Garlic Bread',89),('2','Pineapple Chili Chicken Jumbo Pizza',129),('20','Bread Stick with Dipping',79),('21','Cocktail Sauce',10),('22','BBQ Dipping Sauce',10),('23','BBQ Chicken Wings 10 pcs',199),('24','BBQ Pork Ribs',279),('25','BBQ Chicken Wings 6 pcs',129),('26','Korean Style Chicken Wings 10 pcs',199),('3','Meat Trio Jumbo Pizza Puff',129),('4','Sausage & Crab Stick Jumbo Pizza Puff',129),('5','Baked Mac & Cheese with Chicken',99),('6','Baked Mac & Cheese with Shrimp',129),('7','Baked Carbonara Spaghetti',139),('8','Chicken Bolognese Spaghetti',99),('9','Spicy Bacon Spaghetti',109);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `OrderId` int(11) NOT NULL AUTO_INCREMENT,
  `OrderDate` timestamp(5) NULL DEFAULT NULL,
  `TableId` varchar(2) DEFAULT NULL,
  `StatusId` varchar(2) DEFAULT NULL,
  `TotalPrice` float DEFAULT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2017-11-08 05:51:09.00000','1','1',486),(2,'2017-11-06 05:51:09.00000','4','2',228);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `OrderId` int(11) NOT NULL,
  `MenuId` varchar(2) NOT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`OrderId`,`MenuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'1',1),(1,'3',2),(1,'5',1),(2,'5',1),(2,'6',1);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `StatusId` varchar(2) NOT NULL,
  `StstusName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`StatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES ('1','New Order'),('2','Accept');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_number`
--

DROP TABLE IF EXISTS `table_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_number` (
  `TableId` varchar(2) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TableId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_number`
--

LOCK TABLES `table_number` WRITE;
/*!40000 ALTER TABLE `table_number` DISABLE KEYS */;
INSERT INTO `table_number` VALUES ('1','Table Number 1'),('2','Table Number 2'),('3','Table Number 3'),('4','Table Number 4');
/*!40000 ALTER TABLE `table_number` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-25 13:11:27

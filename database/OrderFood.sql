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
  `img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MenuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('1','Hawaiian Jumbo Pizza Puff',129,'https://1112.minorcdn.com/1112/public/images/products/puff/website/itm102409.png'),('10','Kheemao Seafood Spaghetti',139,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115580.png'),('11','Spicy Sausage Spaghetti',99,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115536.png'),('12','Ham & Mushroom Spaghetti',99,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115538.png'),('13','Baked Spinach',179,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115505.png'),('14','Korean Style Chicken Wings 6 pcs',129,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116554.png'),('15','Chicken Nuggets 6 pcs with Ketchup',79,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116569.png'),('16','Chicken Nuggets 6 pcs with Chili Sauce',79,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116536.png'),('17','Chicken Sticks',89,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116570.png'),('18','Garlic Bread',79,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116525.png'),('19','Cheese Garlic Bread',89,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116526.png'),('2','Pineapple Chili Chicken Jumbo Pizza',129,'https://1112.minorcdn.com/1112/public/images/products/puff/website/itm102407.png'),('20','Bread Stick with Dipping',79,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116527.png'),('21','Cocktail Sauce',10,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116528.png'),('22','BBQ Dipping Sauce',10,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116522.png'),('23','BBQ Chicken Wings 10 pcs',199,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116539.png'),('24','BBQ Pork Ribs',279,'https://1112.minorcdn.com/1112/public/images/products/maindish/website/itm114002.png'),('25','BBQ Chicken Wings 6 pcs',129,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116536.png'),('26','Korean Style Chicken Wings 10 pcs',199,'https://1112.minorcdn.com/1112/public/images/products/appetizer/website/itm116561.png'),('3','Meat Trio Jumbo Pizza Puff',129,'https://1112.minorcdn.com/1112/public/images/products/puff/website/itm102410.png'),('4','Sausage & Crab Stick Jumbo Pizza Puff',129,'https://1112.minorcdn.com/1112/public/images/products/puff/website/itm102408.png'),('5','Baked Mac & Cheese with Chicken',99,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115503.png'),('6','Baked Mac & Cheese with Shrimp',129,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115504.png'),('7','Baked Carbonara Spaghetti',139,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115506.png'),('8','Chicken Bolognese Spaghetti',99,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115535.png'),('9','Spicy Bacon Spaghetti',109,'https://1112.minorcdn.com/1112/public/images/products/pasta/website/itm115539.png');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `OrderId` int(11) NOT NULL,
  `OrderDate` timestamp(5) NULL DEFAULT NULL,
  `TableId` varchar(2) DEFAULT NULL,
  `StatusId` varchar(2) DEFAULT NULL,
  `TotalPrice` float DEFAULT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (0,NULL,NULL,'',NULL),(1,'2018-04-01 12:26:09.00000','2','1',466),(2,'2018-04-01 12:26:29.00000','2','1',426),(3,'2018-04-01 12:31:25.00000','2','1',466),(4,'2018-04-01 12:33:28.00000','2','1',407),(5,'2018-04-01 12:33:39.00000','2','1',247),(6,'2018-04-01 12:33:55.00000','2','2',875),(7,'2018-04-01 12:34:29.00000','2','2',307),(8,'2018-04-01 12:34:48.00000','2','2',119),(9,'2018-04-01 12:35:10.00000','2','2',615),(10,'2018-04-01 12:35:22.00000','2','2',466),(11,'2018-04-01 12:35:54.00000','2','2',605),(12,'2018-04-01 12:36:44.00000','2','3',635),(13,'2018-04-01 12:37:03.00000','2','3',485),(14,'2018-04-01 12:37:49.00000','2','3',506),(15,'2018-04-01 12:39:19.00000','2','3',437),(16,'2018-04-01 12:48:11.00000','2','3',466),(17,'2018-04-01 16:34:10.00000','2','3',328),(18,'2018-04-01 16:34:58.00000','2','3',337),(19,'2018-04-01 22:21:34.00000','2','3',377),(20,'2018-04-01 22:30:57.00000','2','3',436),(21,'2018-04-01 22:31:34.00000','2','3',198),(22,'2018-04-01 22:33:58.00000','2','3',496),(23,'2018-04-02 08:32:02.00000','2','2',247),(24,'2018-04-02 08:33:00.00000','2','2',247),(25,'2018-04-02 10:43:36.00000','2','2',793),(26,'2018-04-02 11:48:58.00000','2','2',417),(27,'2018-04-02 12:49:43.00000','2','3',506),(28,'2018-04-02 12:53:37.00000','2','3',367),(29,'2018-04-02 12:56:36.00000','2','3',109),(30,'2018-04-02 12:56:47.00000','2','3',318),(31,'2018-04-02 12:57:14.00000','2','3',595),(32,'2018-04-02 12:58:14.00000','2','3',576),(33,'2018-04-02 13:02:31.00000','2','3',545),(34,'2018-04-02 13:02:53.00000','2','3',178),(35,'2018-04-02 13:04:40.00000','2','3',536),(36,'2018-04-02 13:04:59.00000','2','3',267),(37,'2018-04-02 15:08:58.00000','2','3',0),(38,'2018-04-02 15:11:17.00000','2','1',636),(39,'2018-04-02 15:16:33.00000','2','3',695),(40,'2018-04-02 15:16:59.00000','2','3',556),(41,'2018-04-02 15:17:54.00000','2','3',436),(42,'2018-04-02 15:18:15.00000','2','3',247),(43,'2018-04-02 15:19:20.00000','2','3',346),(44,'2018-04-02 16:05:06.00000','2','1',129),(45,'2018-04-02 16:07:22.00000','2','3',168),(46,'2018-04-02 16:13:52.00000','2','3',258),(47,'2018-04-02 16:19:17.00000','2','3',139),(48,'2018-04-02 16:24:54.00000','2','4',376),(49,'2018-04-02 16:25:56.00000','2','4',139),(50,'2018-04-02 16:50:34.00000','2','4',635);
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
INSERT INTO `order_detail` VALUES (1,'13',1),(1,'14',1),(1,'15',1),(1,'16',1),(2,'13',1),(2,'15',1),(2,'16',1),(2,'17',1),(3,'13',1),(3,'14',1),(3,'15',1),(3,'16',1),(4,'12',1),(4,'13',1),(4,'14',1),(5,'15',1),(5,'16',1),(5,'17',1),(6,'22',1),(6,'23',1),(6,'24',1),(6,'25',1),(6,'3',2),(7,'10',1),(7,'19',1),(7,'20',1),(8,'19',1),(8,'21',1),(8,'22',2),(9,'6',1),(9,'7',2),(9,'8',1),(9,'9',1),(10,'13',1),(10,'14',1),(10,'15',1),(10,'16',1),(11,'1',1),(11,'11',1),(11,'12',2),(11,'13',1),(12,'11',1),(12,'12',1),(12,'13',1),(12,'14',2),(13,'17',1),(13,'18',1),(13,'19',1),(13,'2',1),(13,'20',1),(13,'21',2),(14,'1',1),(14,'10',2),(14,'11',1),(15,'1',1),(15,'13',1),(15,'14',1),(16,'13',1),(16,'14',1),(16,'15',1),(16,'16',6),(17,'26',1),(17,'3',1),(18,'10',1),(18,'11',1),(18,'12',1),(19,'7',2),(19,'8',1),(20,'10',1),(20,'11',1),(20,'12',2),(21,'17',1),(21,'19',1),(21,'21',2),(22,'1',2),(22,'10',1),(22,'11',1),(23,'15',1),(23,'16',1),(23,'17',1),(24,'15',6),(24,'17',1),(25,'1',1),(25,'11',1),(25,'15',1),(25,'17',1),(25,'21',1),(25,'25',3),(26,'10',5),(26,'11',1),(26,'12',1),(26,'13',1),(27,'6',1),(27,'7',2),(27,'8',1),(28,'1',1),(28,'10',1),(28,'11',1),(29,'20',1),(29,'22',3),(30,'10',1),(30,'13',1),(31,'1',1),(31,'13',1),(31,'14',1),(31,'15',2),(32,'10',1),(32,'13',1),(32,'14',2),(33,'13',1),(33,'14',1),(33,'15',1),(33,'16',1),(33,'18',1),(34,'19',1),(34,'20',1),(34,'21',1),(35,'10',1),(35,'13',1),(35,'14',1),(35,'17',1),(36,'17',1),(36,'19',1),(36,'20',1),(36,'21',1),(38,'10',2),(38,'13',2),(39,'13',1),(39,'14',4),(40,'11',1),(40,'12',1),(40,'13',2),(41,'10',2),(41,'15',1),(41,'16',1),(42,'15',1),(42,'17',1),(42,'18',1),(43,'17',3),(43,'18',8),(44,'1',1),(44,'10',1),(45,'15',5),(45,'17',1),(46,'1',2),(47,'7',1),(48,'1',1),(48,'17',1),(48,'20',2),(49,'10',8),(50,'12',9),(50,'13',1),(50,'14',2);
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
  `StatusName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`StatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES ('1','New Order'),('2','Accept'),('3','Finish'),('4','Reject');
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
INSERT INTO `table_number` VALUES ('1','A1'),('2','A2'),('3','B1'),('4','B2');
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

-- Dump completed on 2018-04-04 14:43:01

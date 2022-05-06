CREATE DATABASE  IF NOT EXISTS `sqaspring` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sqaspring`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: sqaspring
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `payment_date` datetime NOT NULL,
                        `sale_off` float NOT NULL DEFAULT '0',
                        `note` varchar(45) DEFAULT NULL,
                        `is_paid` tinyint NOT NULL DEFAULT '1',
                        `is_active` tinyint NOT NULL DEFAULT '1',
                        `client_id` int NOT NULL,
                        `user_id` int NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `id_UNIQUE` (`id`),
                        KEY `fk_bill_client1_idx` (`client_id`),
                        KEY `fk_bill_user1_idx` (`user_id`),
                        CONSTRAINT `fk_bill_client1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
                        CONSTRAINT `fk_bill_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'2022-04-24 16:00:44',0,'for test',1,1,4,2),(2,'2022-04-24 15:55:06',0,NULL,0,0,4,2),(3,'2022-04-24 16:00:44',0,'qwe',0,1,4,2),(17,'2022-05-04 14:07:15',0,NULL,1,1,1,2),(18,'2022-05-04 14:10:16',0,NULL,1,1,1,2),(19,'2022-05-04 23:39:00',0,NULL,1,1,4,2),(20,'2022-05-04 23:39:12',0,NULL,1,1,4,2),(21,'2022-05-04 23:39:23',0,NULL,1,1,4,2),(22,'2022-05-04 23:39:34',0,NULL,1,1,4,2),(23,'2022-05-04 23:41:23',0,NULL,1,1,8,2),(24,'2022-05-04 23:41:33',0,NULL,1,1,8,2),(25,'2022-05-04 23:41:44',0,NULL,1,1,8,2),(26,'2022-05-04 23:41:54',0,NULL,1,1,8,2),(27,'2022-05-04 23:46:00',0,NULL,1,1,8,2),(28,'2022-05-04 23:47:01',0,NULL,1,1,8,2),(29,'2022-05-04 23:51:17',0,NULL,1,1,8,2),(30,'2022-05-04 23:52:52',0,NULL,1,1,8,2),(31,'2022-05-04 23:54:40',0,NULL,1,1,8,2),(32,'2022-05-04 23:57:53',0,NULL,1,1,8,2),(33,'2022-05-04 23:58:26',0,NULL,1,1,8,2);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buying_goods`
--

DROP TABLE IF EXISTS `buying_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buying_goods` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `amount` int NOT NULL,
                                `price_per_unit` bigint NOT NULL,
                                `note` varchar(255) DEFAULT NULL,
                                `goods_id` int NOT NULL,
                                `bill_id` int NOT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `id_UNIQUE` (`id`),
                                KEY `goods_id_idx` (`goods_id`),
                                KEY `fk_buying_goods_bill1_idx` (`bill_id`),
                                CONSTRAINT `fk_buying_goods_bill1` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
                                CONSTRAINT `goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buying_goods`
--

LOCK TABLES `buying_goods` WRITE;
/*!40000 ALTER TABLE `buying_goods` DISABLE KEYS */;
INSERT INTO `buying_goods` VALUES (1,200,10000,NULL,3,2),(2,11,10000,NULL,3,3),(23,1,20000,NULL,1,17),(24,1,20000,NULL,1,18),(25,1,20000,NULL,1,19),(26,1,20000,NULL,1,20),(27,1,20000,NULL,1,21),(28,1,20000,NULL,1,22),(29,1,20000,NULL,1,23),(30,1,20000,NULL,1,24),(31,1,20000,NULL,1,25),(32,1,20000,NULL,1,26),(33,1,20000,NULL,1,27),(34,1,20000,NULL,1,28),(35,1,20000,NULL,1,29),(36,1,20000,NULL,1,30),(37,1,20000,NULL,1,31),(38,1,20000,NULL,1,32),(39,1,20000,NULL,1,33);
/*!40000 ALTER TABLE `buying_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(45) NOT NULL,
                          `address` varchar(255) DEFAULT NULL,
                          `phone_number` varchar(45) NOT NULL,
                          `is_active` tinyint NOT NULL DEFAULT '1',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Nguyễn Ngọc Duy','hanoi','0966215413',1),(2,'Nguyen Ngoc Duy','4883 Locust View Drive','0966123456',1),(3,'a','active false','1111111111',0),(4,'b','active true','2222222222',1),(8,'test kh','ptit','0978123456',1);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         `unity` varchar(45) NOT NULL,
                         `price_per_unit` bigint NOT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `is_active` tinyint NOT NULL DEFAULT '1',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'bia','lon',20000,NULL,1),(2,'bim bim','goi',5000,NULL,1),(3,'coca','lon',10000,NULL,1),(4,'22','22',22,'test active false',0),(5,'11','11',11,'test active true',1);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(45) NOT NULL,
                        `password` varchar(45) NOT NULL,
                        `name` varchar(45) NOT NULL,
                        `position` varchar(45) NOT NULL,
                        `phone_number` varchar(45) NOT NULL,
                        `is_active` tinyint NOT NULL DEFAULT '1',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'manager','manager','manager','manager','0123456789',1),(2,'seller','seller','seller','seller','0123666666',1),(3,'manager2','manager2','manager2','manager2','0123456788',1),(4,'seller2','seller2','seller2','seller2','0123456787',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06 11:11:00

CREATE DATABASE  IF NOT EXISTS `carport` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: carport
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `billing_address`
--

DROP TABLE IF EXISTS `billing_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billing_address` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `user_id` int NOT NULL,
                                   `address` varchar(100) NOT NULL,
                                   `city` varchar(100) NOT NULL,
                                   `zipcode` int NOT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `fk_billing_adress_user1_idx` (`user_id`),
                                   CONSTRAINT `fk_billing_adress_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing_address`
--

LOCK TABLES `billing_address` WRITE;
/*!40000 ALTER TABLE `billing_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `billing_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemlist`
--

DROP TABLE IF EXISTS `itemlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itemlist` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `order_id` int NOT NULL,
                            `product_variant_id` int NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `fk_itemlist_order1_idx` (`order_id`),
                            KEY `fk_itemlist_product_variant1_idx` (`product_variant_id`),
                            CONSTRAINT `fk_itemlist_order1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
                            CONSTRAINT `fk_itemlist_product_variant1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemlist`
--

LOCK TABLES `itemlist` WRITE;
/*!40000 ALTER TABLE `itemlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `user_id` int NOT NULL,
                          `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `price` float NOT NULL,
                          `length` int NOT NULL,
                          `width` int NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_order_user1_idx` (`user_id`),
                          CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(100) NOT NULL,
                           `description` varchar(100) NOT NULL,
                           `unit` varchar(100) NOT NULL,
                           `price_pr_unit` float NOT NULL,
                           `type` varchar(100) NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_product_product_type1_idx` (`type`),
                           KEY `fk_product_unit1_idx` (`unit`),
                           CONSTRAINT `fk_product_product_type1` FOREIGN KEY (`type`) REFERENCES `product_type` (`type`),
                           CONSTRAINT `fk_product_unit1` FOREIGN KEY (`unit`) REFERENCES `unit` (`unit`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'stern','sternbrædder til forenden','cm',0.37,'wood'),(2,'stern','sternbrædder til bagi','cm',0.37,'wood'),(3,'stern','sternbrædder til siderne','cm',0.37,'wood'),(4,'stolpe','Stolper nedgraves 90 cm. i jord','cm',0.58,'wood'),(5,'rem','Remme i sider, sadles ned i stolper','cm',0.37,'wood'),(6,'spær','Spær, monteres på rem','cm',0.28,'wood'),(7,'beslag','universal beslag til montering af spær på rem (Pr. stk)','pieces',0.49,'hardware'),(8,'skruer','Til montering af stern, vandbrædt & beklædning (200stk)','box',2.59,'hardware'),(9,'bræddebolt','Til montering af rem på stolper (25stk)','box',40.9,'hardware');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type` (
                                `type` varchar(100) NOT NULL,
                                PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES ('hardware'),('rooftile'),('wood');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_variant`
--

DROP TABLE IF EXISTS `product_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_variant` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `product_id` int NOT NULL,
                                   `heigth` float NOT NULL,
                                   `width` float NOT NULL,
                                   `length` varchar(45) NOT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `fk_product_variant_product_idx` (`product_id`),
                                   CONSTRAINT `fk_product_variant_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_variant`
--

LOCK TABLES `product_variant` WRITE;
/*!40000 ALTER TABLE `product_variant` DISABLE KEYS */;
INSERT INTO `product_variant` VALUES (1,1,12.5,2.5,'360'),(2,1,15,2.5,'360'),(3,2,12.5,2.5,'360'),(4,2,15,2.5,'360'),(5,3,12.5,2.5,'540'),(6,3,15,2.5,'540'),(7,4,9.7,9.7,'300'),(8,4,12.5,12.5,'300'),(9,5,4.5,14.5,'600'),(10,6,4.5,19.5,'600'),(11,7,19,0,'0'),(12,8,4.5,6,'0'),(13,8,4.5,7.5,'0'),(14,9,1,12,'0');
/*!40000 ALTER TABLE `product_variant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
                        `unit` varchar(100) NOT NULL,
                        PRIMARY KEY (`unit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES ('box'),('cm'),('mm'),('pieces');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(100) NOT NULL,
                        `password` varchar(100) NOT NULL,
                        `firstname` varchar(100) NOT NULL,
                        `lastname` varchar(100) NOT NULL,
                        `phonenumber` int NOT NULL,
                        `role` varchar(10) NOT NULL DEFAULT 'user',
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@mail.com','1234','admin','admin',12345678,'admin');
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

-- Dump completed on 2023-05-12 10:46:41

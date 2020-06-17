-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: registro
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `verifica`
--

DROP TABLE IF EXISTS `verifica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verifica` (
  `durata` int DEFAULT NULL,
  `data` date NOT NULL,
  `ora` int NOT NULL,
  `insegnante` int NOT NULL,
  `materia` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`data`,`ora`,`insegnante`),
  KEY `insegnante` (`insegnante`,`materia`),
  CONSTRAINT `verifica_ibfk_1` FOREIGN KEY (`insegnante`, `materia`) REFERENCES `insegnamento` (`insegnante`, `materia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verifica`
--

LOCK TABLES `verifica` WRITE;
/*!40000 ALTER TABLE `verifica` DISABLE KEYS */;
INSERT INTO `verifica` VALUES (2,'2020-01-09',2,30,'st1'),(2,'2020-02-01',2,30,'st1'),(2,'2020-04-01',2,30,'st1'),(1,'2020-04-02',1,20,'ble'),(4,'2020-04-05',2,7,'ino'),(5,'2020-04-05',2,30,'st1'),(3,'2020-04-06',3,7,'etr'),(4,'2020-04-07',2,24,'bor'),(2,'2020-04-11',2,30,'its'),(2,'2020-04-15',2,20,'ccc'),(1,'2020-04-20',2,7,'woa');
/*!40000 ALTER TABLE `verifica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 18:21:19

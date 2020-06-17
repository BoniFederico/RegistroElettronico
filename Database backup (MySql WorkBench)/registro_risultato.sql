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
-- Table structure for table `risultato`
--

DROP TABLE IF EXISTS `risultato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `risultato` (
  `voto` int DEFAULT NULL,
  `data` date NOT NULL,
  `ora` int NOT NULL,
  `insegnante` int NOT NULL,
  `studente` int NOT NULL,
  PRIMARY KEY (`data`,`ora`,`insegnante`,`studente`),
  KEY `studente` (`studente`),
  CONSTRAINT `risultato_ibfk_1` FOREIGN KEY (`studente`) REFERENCES `studente` (`matricola`),
  CONSTRAINT `risultato_ibfk_2` FOREIGN KEY (`data`, `ora`, `insegnante`) REFERENCES `verifica` (`data`, `ora`, `insegnante`),
  CONSTRAINT `votoCheck` CHECK ((`voto` between 1 and 10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `risultato`
--

LOCK TABLES `risultato` WRITE;
/*!40000 ALTER TABLE `risultato` DISABLE KEYS */;
INSERT INTO `risultato` VALUES (10,'2020-01-09',2,30,303),(5,'2020-02-01',2,30,303),(5,'2020-04-01',2,30,303),(9,'2020-04-05',2,7,243),(5,'2020-04-05',2,7,303),(6,'2020-04-15',2,20,207),(8,'2020-04-15',2,20,303),(8,'2020-04-15',2,20,304),(3,'2020-04-20',2,7,303);
/*!40000 ALTER TABLE `risultato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 18:21:20

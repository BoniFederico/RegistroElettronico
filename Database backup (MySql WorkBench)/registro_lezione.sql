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
-- Table structure for table `lezione`
--

DROP TABLE IF EXISTS `lezione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lezione` (
  `data` date NOT NULL,
  `ora` int NOT NULL,
  `classe` int DEFAULT NULL,
  `locazione` int DEFAULT NULL,
  `materia` varchar(3) DEFAULT NULL,
  `insegnante` int NOT NULL,
  `argomento` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`data`,`ora`,`insegnante`),
  UNIQUE KEY `data` (`data`,`ora`,`classe`),
  UNIQUE KEY `data_2` (`data`,`ora`,`locazione`),
  KEY `insegnante` (`insegnante`,`materia`),
  KEY `classe` (`classe`),
  KEY `locazione` (`locazione`),
  CONSTRAINT `lezione_ibfk_1` FOREIGN KEY (`insegnante`, `materia`) REFERENCES `insegnamento` (`insegnante`, `materia`),
  CONSTRAINT `lezione_ibfk_2` FOREIGN KEY (`classe`) REFERENCES `classe` (`codiceClasse`),
  CONSTRAINT `lezione_ibfk_3` FOREIGN KEY (`locazione`) REFERENCES `stanza` (`codiceStanza`),
  CONSTRAINT `oraCheck` CHECK ((`ora` between 1 and 6))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lezione`
--

LOCK TABLES `lezione` WRITE;
/*!40000 ALTER TABLE `lezione` DISABLE KEYS */;
INSERT INTO `lezione` VALUES ('2020-04-01',2,10,10,'st1',30,'prima guerra mondiale'),('2020-04-01',3,10,20,'its',30,'Boccaccio'),('2020-04-02',4,10,50,'st1',30,'seconda guerra mondiale'),('2020-04-02',5,10,60,'bor',24,'pallavolo'),('2020-04-03',1,10,50,'ccc',20,'reazioni redox'),('2020-04-03',6,10,80,'ble',20,'articles'),('2020-04-04',3,10,30,'ino',7,'prima guerra mondiale'),('2020-04-04',4,10,20,'its',30,'D\'Annunzio'),('2020-04-04',5,10,30,'ccc',20,'modello di Bohr'),('2020-04-05',1,10,10,'st1',30,'1922, Marcia su Roma'),('2020-04-05',2,10,10,'woa',7,'Darwinismo'),('2020-04-06',1,10,40,'ble',20,'Food and drink'),('2020-04-06',2,10,10,'woa',7,'montagne e vulcani'),('2020-04-06',3,22,10,'etr',7,'oscillatore di Wien'),('2020-04-06',5,10,15,'ccc',20,'Rutherford'),('2020-04-07',1,4,10,'st1',30,'Guerra del Golfo'),('2020-04-07',2,4,40,'ccc',20,'Reazioni Redox'),('2020-04-07',2,10,10,'bor',24,'curling bisac'),('2020-04-07',3,4,10,'ble',20,'Past Simple and Present Simple'),('2020-04-07',3,9,50,'st1',30,'guerra fredda'),('2020-04-07',4,4,90,'ble',20,'Crollo muro di Berlino'),('2020-04-07',5,4,10,'st1',30,'Guerra del Vietnam'),('2020-04-08',5,10,10,'ble',20,'Past continuous'),('2020-04-09',1,10,10,'st1',30,'Guerra del Vietnam'),('2020-04-10',2,10,80,'ccc',20,'Ossidoriduzione'),('2020-04-11',3,10,20,'ccc',20,'Fotosintesi'),('2020-05-15',5,9,90,'st1',30,'prima guerra mondiale'),('2020-05-22',5,9,90,'st1',30,'prima guerra mondiale'),('2020-05-23',5,9,90,'st1',30,'prima guerra mondiale'),('2020-08-01',2,10,10,'st1',30,'terza guerra mondiale');
/*!40000 ALTER TABLE `lezione` ENABLE KEYS */;
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

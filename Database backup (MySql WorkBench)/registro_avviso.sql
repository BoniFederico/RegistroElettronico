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
-- Table structure for table `avviso`
--

DROP TABLE IF EXISTS `avviso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avviso` (
  `numero` int NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `insegnante` int NOT NULL,
  `classe` int NOT NULL,
  `testo` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `insegnante` (`insegnante`),
  KEY `classe` (`classe`),
  CONSTRAINT `avviso_ibfk_1` FOREIGN KEY (`insegnante`) REFERENCES `insegnante` (`cp`),
  CONSTRAINT `avviso_ibfk_2` FOREIGN KEY (`classe`) REFERENCES `classe` (`codiceClasse`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avviso`
--

LOCK TABLES `avviso` WRITE;
/*!40000 ALTER TABLE `avviso` DISABLE KEYS */;
INSERT INTO `avviso` VALUES (1,'2020-04-12',30,10,'La classe è stata molto brava in questi mesi'),(2,'2020-05-04',24,22,'La classe è autorizzata ad uscire prima oggi'),(3,'2020-02-28',20,22,'Sarò assente domani'),(6,'2020-06-13',30,10,'Avviso automatico! Studente con più di 200 assenze!'),(8,'2020-01-22',24,10,'Domani e dopodomani non ci sarà lezione'),(9,'2020-01-22',24,10,'Domani e dopodomani non ci sarà lezione'),(14,'2020-02-12',20,4,'Le lezioni di domani proseguiranno regolarmente'),(15,'2020-02-12',20,4,'Le lezioni di domani proseguiranno regolarmente'),(16,'2020-04-12',30,4,'La classe è autorizzata ad uscire dopo la 4a ora.'),(17,'2020-04-12',30,4,'La classe è autorizzata ad uscire dopo la 4a ora.'),(18,'2020-10-11',7,10,'Tutto ok!'),(19,'2020-01-22',7,10,'Nuovo avviso, la classe domani può uscire prima.'),(20,'2020-12-20',7,4,'Sto inviando un avviso dal futuro! - Marco Cartone'),(21,'2020-12-20',7,4,'Sto inviando un avviso dal futuro! - Marco Cartone'),(22,'2020-11-02',7,4,'Prova ciao');
/*!40000 ALTER TABLE `avviso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 18:21:18

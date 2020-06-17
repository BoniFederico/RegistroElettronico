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
-- Table structure for table `assenza`
--

DROP TABLE IF EXISTS `assenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assenza` (
  `status` varchar(15) DEFAULT NULL,
  `data` date NOT NULL,
  `ora` int NOT NULL,
  `insegnante` int NOT NULL,
  `studente` int NOT NULL,
  PRIMARY KEY (`data`,`ora`,`insegnante`,`studente`),
  KEY `studente` (`studente`),
  CONSTRAINT `assenza_ibfk_1` FOREIGN KEY (`studente`) REFERENCES `studente` (`matricola`),
  CONSTRAINT `assenza_ibfk_2` FOREIGN KEY (`data`, `ora`, `insegnante`) REFERENCES `lezione` (`data`, `ora`, `insegnante`),
  CONSTRAINT `statusCheck` CHECK ((`status` in (_utf8mb4'giustificata',_utf8mb4'nongiustificata')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assenza`
--

LOCK TABLES `assenza` WRITE;
/*!40000 ALTER TABLE `assenza` DISABLE KEYS */;
INSERT INTO `assenza` VALUES ('giustificata','2020-04-01',3,30,303),('nongiustificata','2020-04-01',3,30,304),('nongiustificata','2020-04-03',1,20,303),('giustificata','2020-04-04',5,20,303),('giustificata','2020-04-05',2,7,303),('giustificata','2020-04-06',1,20,303),('giustificata','2020-04-06',3,7,246),('nongiustificata','2020-04-07',4,20,207);
/*!40000 ALTER TABLE `assenza` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `controlloAssenza` BEFORE INSERT ON `assenza` FOR EACH ROW BEGIN
 
 IF (( SELECT a.classe FROM lezione a
		WHERE a.data=NEW.data AND a.ora=NEW.ora AND a.insegnante=NEW.insegnante)<>
        (SELECT s.classe FROM studente s WHERE s.matricola=NEW.studente)) THEN
		signal sqlstate '45000' set message_text='errorino';
        
        END IF;
   
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `controlloPresenze` AFTER INSERT ON `assenza` FOR EACH ROW BEGIN
 DECLARE  cl INT DEFAULT(SELECT classe FROM studente s WHERE s.matricola=new.studente );
 DECLARE cord INT DEFAULT(SELECT coordinatore FROM classe WHERE codiceClasse=cl);
 
 IF ((SELECT count(*) as ass FROM assenza a WHERE a.studente=new.studente)>2 ) THEN
		 INSERT INTO avviso(data,insegnante,classe,testo) VALUES
 ( curdate(),cord,cl,'Avviso automatico! Studente con più di 200 assenze!');
        
        END IF;
   
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 18:21:18

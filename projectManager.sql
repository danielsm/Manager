CREATE DATABASE  IF NOT EXISTS `projectManager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `projectManager`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: controlemateriais
-- ------------------------------------------------------
-- Server version 5.6.22-log

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
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `id_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  `id_tarefa` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `funcao` varchar(100) NOT NULL,
  `custo` float(11) NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  KEY `pessoa_tarefa_idx` (`id_tarefa`),
  CONSTRAINT `pessoa_tarefa` FOREIGN KEY (`id_tarefa`) REFERENCES `tarefa` (`id_tarefa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `id_material` int(11) NOT NULL AUTO_INCREMENT,
  `id_tarefa` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `custo` float(11) NOT NULL,
  PRIMARY KEY (`id_material`),
  KEY `material_tarefa_idx` (`id_tarefa`),
  CONSTRAINT `material_tarefa` FOREIGN KEY (`id_tarefa`) REFERENCES `tarefa` (`id_tarefa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projeto` (
  `nome` varchar(100) NOT NULL,
  `duracao` int(11) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tarefa`
--

DROP TABLE IF EXISTS `tarefa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarefa` (
  `id_tarefa` int(11) NOT NULL AUTO_INCREMENT,
  `nome_projeto` varchar(100) NOT NULL,
  `nome_tarefa` varchar(100) NOT NULL,
  `semanaInicio` int(11) NOT NULL,
  `duracao` int(11) NOT NULL,
  PRIMARY KEY (`id_tarefa`),
  KEY `tarefa_projeto_idx` (`nome_projeto`),
  CONSTRAINT `material_projeto` FOREIGN KEY (`nome_projeto`) REFERENCES `projeto` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-22 16:52:59

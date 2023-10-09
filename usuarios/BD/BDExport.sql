-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: tecnoschool
-- ------------------------------------------------------
-- Server version	11.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `Id_alumno` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(250) NOT NULL,
  `Apellido_Paterno` varchar(250) NOT NULL,
  `Apellido_Materno` varchar(250) DEFAULT NULL,
  `Fecha_Nacimiento` date DEFAULT NULL,
  `Edad` int(11) DEFAULT NULL,
  `Semestre` int(11) DEFAULT NULL,
  `Codigo_Carrera` varchar(250) DEFAULT NULL,
  `Direccion` varchar(250) DEFAULT NULL,
  `Colonia` varchar(250) DEFAULT NULL,
  `Municipio` varchar(250) DEFAULT NULL,
  `Estado` varchar(250) DEFAULT NULL,
  `Nacionalidad` varchar(250) DEFAULT NULL,
  `Grupo` varchar(250) DEFAULT NULL,
  `Fecha_Actualizacion` datetime DEFAULT NULL,
  `IdUsuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_alumno`),
  KEY `alumnos_FK` (`IdUsuario`),
  CONSTRAINT `alumnos_FK` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`Id_Usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calificaciones`
--

DROP TABLE IF EXISTS `calificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calificaciones` (
  `Id_calificacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `Id_Alumno` bigint(20) DEFAULT NULL,
  `Id_Materia` bigint(20) DEFAULT NULL,
  `CalificacionFinal` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`Id_calificacion`),
  KEY `Calificaciones_FK` (`Id_Alumno`),
  KEY `Calificaciones_FK_1` (`Id_Materia`),
  CONSTRAINT `Calificaciones_FK` FOREIGN KEY (`Id_Alumno`) REFERENCES `alumnos` (`Id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Calificaciones_FK_1` FOREIGN KEY (`Id_Materia`) REFERENCES `materias` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calificacionesfinales`
--

DROP TABLE IF EXISTS `calificacionesfinales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calificacionesfinales` (
  `Id_CalificacionFinal` bigint(20) NOT NULL AUTO_INCREMENT,
  `AlumnoId` bigint(20) DEFAULT NULL,
  `MateriaID` bigint(20) DEFAULT NULL,
  `CalificacionFinal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id_CalificacionFinal`),
  KEY `CalificacionesFinales_FK` (`AlumnoId`),
  KEY `CalificacionesFinales_FK_1` (`MateriaID`),
  CONSTRAINT `CalificacionesFinales_FK` FOREIGN KEY (`AlumnoId`) REFERENCES `alumnos` (`Id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CalificacionesFinales_FK_1` FOREIGN KEY (`MateriaID`) REFERENCES `materias` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carrera_materias`
--

DROP TABLE IF EXISTS `carrera_materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrera_materias` (
  `codigo_carrera` varchar(100) DEFAULT NULL,
  `id_materia` bigint(20) DEFAULT NULL,
  KEY `carrera_materias_FK` (`codigo_carrera`),
  KEY `carrera_materias_FK_1` (`id_materia`),
  CONSTRAINT `carrera_materias_FK` FOREIGN KEY (`codigo_carrera`) REFERENCES `carreras` (`Codigo_Carrera`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `carrera_materias_FK_1` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras` (
  `Codigo_Carrera` varchar(100) NOT NULL,
  `Nombre_Carrera` varchar(100) DEFAULT NULL,
  `Fecha_Alta` datetime DEFAULT NULL,
  `Fecha_Actualizacion` datetime DEFAULT NULL,
  `Fecha_Baja` datetime DEFAULT NULL,
  `Estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`Codigo_Carrera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entregas`
--

DROP TABLE IF EXISTS `entregas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregas` (
  `Id_Entrega` bigint(20) NOT NULL AUTO_INCREMENT,
  `ubicacionArchivo` varchar(250) DEFAULT NULL,
  `Fecha_Entrega` varchar(100) DEFAULT NULL,
  `TrabajoID` bigint(20) DEFAULT NULL,
  `AlumnoID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Entrega`),
  KEY `Entregas_FK` (`TrabajoID`),
  KEY `Entregas_FK_1` (`AlumnoID`),
  CONSTRAINT `Entregas_FK` FOREIGN KEY (`TrabajoID`) REFERENCES `trabajos_escolares` (`IdTrabajo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Entregas_FK_1` FOREIGN KEY (`AlumnoID`) REFERENCES `alumnos` (`Id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examenes`
--

DROP TABLE IF EXISTS `examenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examenes` (
  `ID_Examen` bigint(20) NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(250) NOT NULL,
  `Fecha_Inicio` datetime DEFAULT NULL,
  `Fecha_Finalizacion` datetime DEFAULT NULL,
  `TipoExamen` varchar(100) DEFAULT NULL,
  `MateriaID` bigint(20) DEFAULT NULL,
  `ProfesorID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_Examen`),
  KEY `Examen_FK_1` (`ProfesorID`),
  CONSTRAINT `Examen_FK` FOREIGN KEY (`ID_Examen`) REFERENCES `materias` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_FK_1` FOREIGN KEY (`ProfesorID`) REFERENCES `profesores` (`Id_profesor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materias`
--

DROP TABLE IF EXISTS `materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materias` (
  `Id_materia` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nombre_Materia` varchar(250) DEFAULT NULL,
  `Codigo_Materia` varchar(250) DEFAULT NULL,
  `Fecha_Alta` datetime DEFAULT NULL,
  `Fecha_Baja` datetime DEFAULT NULL,
  `Estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_materia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materias_alumnos`
--

DROP TABLE IF EXISTS `materias_alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materias_alumnos` (
  `id_alumno` bigint(20) NOT NULL,
  `id_materia` bigint(20) NOT NULL,
  KEY `materias_alumnos_FK` (`id_materia`),
  KEY `materias_alumnos_FK_1` (`id_alumno`),
  CONSTRAINT `materias_alumnos_FK` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `materias_alumnos_FK_1` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`Id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materias_profesores`
--

DROP TABLE IF EXISTS `materias_profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materias_profesores` (
  `id_profesor` bigint(20) DEFAULT NULL,
  `id_materia` bigint(20) DEFAULT NULL,
  KEY `materias_profesores_FK` (`id_profesor`),
  KEY `materias_profesores_FK_1` (`id_materia`),
  CONSTRAINT `materias_profesores_FK` FOREIGN KEY (`id_profesor`) REFERENCES `profesores` (`Id_profesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `materias_profesores_FK_1` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preguntas` (
  `Id_Pregunta` bigint(20) NOT NULL AUTO_INCREMENT,
  `ExamenID` bigint(20) DEFAULT NULL,
  `EnunciadoPregunta` mediumtext DEFAULT NULL,
  PRIMARY KEY (`Id_Pregunta`),
  KEY `Preguntas_FK` (`ExamenID`),
  CONSTRAINT `Preguntas_FK` FOREIGN KEY (`ExamenID`) REFERENCES `examenes` (`ID_Examen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `Id_profesor` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(250) DEFAULT NULL,
  `Apellido_Paterno` varchar(250) DEFAULT NULL,
  `Apellido_Materno` varchar(250) DEFAULT NULL,
  `Fecha_Nacimiento` date DEFAULT NULL,
  `Edad` int(11) DEFAULT NULL,
  `Nivel_Estudios` varchar(250) DEFAULT NULL,
  `Dirrecion` varchar(100) DEFAULT NULL,
  `Colonia` varchar(250) DEFAULT NULL,
  `Municipio` varchar(250) DEFAULT NULL,
  `Estado` varchar(250) DEFAULT NULL,
  `Nacionalidad` varchar(250) DEFAULT NULL,
  `Fecha_Alta` datetime DEFAULT NULL,
  `Fecha_Actualizacion` datetime DEFAULT NULL,
  `Fecha_Baja` datetime DEFAULT NULL,
  `IdUsuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_profesor`),
  KEY `profesores_FK` (`IdUsuario`),
  CONSTRAINT `profesores_FK` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`Id_Usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuestas` (
  `ID_Respuesta` bigint(20) NOT NULL,
  `PreguntaID` bigint(20) DEFAULT NULL,
  `OpcionesDeRespuesta` varchar(250) DEFAULT NULL,
  `IndicadorRespuestaCorrecta` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Respuesta`),
  KEY `Respuestas_FK` (`PreguntaID`),
  CONSTRAINT `Respuestas_FK` FOREIGN KEY (`PreguntaID`) REFERENCES `preguntas` (`Id_Pregunta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `Id_Rol` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `fecha_alta` datetime(6) DEFAULT NULL,
  `fecha_baja` datetime(6) DEFAULT NULL,
  `estatus` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id_Rol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trabajos_escolares`
--

DROP TABLE IF EXISTS `trabajos_escolares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajos_escolares` (
  `IdTrabajo` bigint(20) NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(250) NOT NULL,
  `Descripcion` longtext DEFAULT NULL,
  `Fecha_Publicacion` datetime NOT NULL,
  `Fecha_Corte` datetime NOT NULL,
  `MateriaID` bigint(20) NOT NULL DEFAULT 0,
  `ProfesorID` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`IdTrabajo`),
  KEY `trabajos_escolares_FK` (`MateriaID`),
  KEY `trabajos_escolares_FK_1` (`ProfesorID`),
  CONSTRAINT `trabajos_escolares_FK` FOREIGN KEY (`MateriaID`) REFERENCES `materias` (`Id_materia`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `id_usuario` bigint(20) NOT NULL,
  `id_rol` bigint(20) NOT NULL,
  KEY `usuario_rol_FK` (`id_usuario`),
  KEY `usuario_rol_FK_1` (`id_rol`),
  CONSTRAINT `usuario_rol_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`Id_Usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuario_rol_FK_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`Id_Rol`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `Id_Usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_alta` datetime(6) DEFAULT NULL,
  `fecha_baja` datetime(6) DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'tecnoschool'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-08

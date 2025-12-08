-- --------------------------------------------------------
-- Host:                         asidb.cle8qc2ek8bq.us-east-2.rds.amazonaws.com
-- Server version:               11.4.8-MariaDB-log - managed by https://aws.amazon.com/rds/
-- Server OS:                    Linux
-- HeidiSQL Version:             12.13.0.7147
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for asidb
CREATE DATABASE IF NOT EXISTS `asidb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `asidb`;

-- Dumping structure for table asidb.Alumno
CREATE TABLE IF NOT EXISTS `Alumno` (
  `idAlumno` int(11) NOT NULL AUTO_INCREMENT,
  `nombresAlumno` varchar(255) NOT NULL,
  `apellidosAlumno` varchar(255) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `codigo` varchar(50) DEFAULT NULL,
  `carrera` varchar(255) DEFAULT NULL,
  `curso` varchar(255) DEFAULT NULL,
  `docenteACargo` varchar(255) DEFAULT NULL,
  `correoElectronico` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  PRIMARY KEY (`idAlumno`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `correoElectronico` (`correoElectronico`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table asidb.Contrato
CREATE TABLE IF NOT EXISTS `Contrato` (
  `idContrato` int(11) NOT NULL AUTO_INCREMENT,
  `idAlumno` int(11) NOT NULL,
  `idOferta` int(11) NOT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `estadoContrato` varchar(50) DEFAULT NULL,
  `documentoContrato` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idContrato`),
  KEY `idAlumno` (`idAlumno`),
  KEY `idOferta` (`idOferta`) USING BTREE,
  CONSTRAINT `Contrato_ibfk_1` FOREIGN KEY (`idAlumno`) REFERENCES `Alumno` (`idAlumno`),
  CONSTRAINT `FK_Contrato_Oferta` FOREIGN KEY (`idOferta`) REFERENCES `Oferta` (`idOferta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table asidb.EmpleadoEmpresa
CREATE TABLE IF NOT EXISTS `EmpleadoEmpresa` (
  `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(255) DEFAULT NULL,
  `nombreEmpresa` varchar(255) DEFAULT NULL,
  `correoCorporativo` varchar(255) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `ruc` varchar(11) DEFAULT NULL,
  `contrasena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEmpleado`),
  UNIQUE KEY `correoInstitucional` (`correoCorporativo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table asidb.Informe
CREATE TABLE IF NOT EXISTS `Informe` (
  `idInforme` int(11) NOT NULL AUTO_INCREMENT,
  `archivo` varchar(255) DEFAULT NULL,
  `fechaEnvio` datetime DEFAULT current_timestamp(),
  `calificacion` decimal(4,2) DEFAULT NULL,
  `comentariosProfesor` text DEFAULT NULL,
  PRIMARY KEY (`idInforme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table asidb.Oferta
CREATE TABLE IF NOT EXISTS `Oferta` (
  `idOferta` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEmpresa` varchar(255) DEFAULT NULL,
  `descripcionPerfil` text DEFAULT NULL,
  `puestoPractica` varchar(255) DEFAULT NULL,
  `requisitos` text DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `modalidad` varchar(100) DEFAULT NULL,
  `habilidadesCompetencias` text DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `beneficios` text DEFAULT NULL,
  `consultas` text DEFAULT NULL,
  `empleadoID` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOferta`),
  KEY `id_empleado` (`empleadoID`),
  CONSTRAINT `id_empleado` FOREIGN KEY (`empleadoID`) REFERENCES `EmpleadoEmpresa` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table asidb.Postulacion
CREATE TABLE IF NOT EXISTS `Postulacion` (
  `idPostulacion` int(11) NOT NULL AUTO_INCREMENT,
  `idAlumno` int(11) NOT NULL,
  `idOferta` int(11) NOT NULL,
  `fechaPostulacion` datetime DEFAULT current_timestamp(),
  `requisitoLink` varchar(255) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idPostulacion`),
  KEY `idAlumno` (`idAlumno`),
  KEY `idOferta` (`idOferta`),
  CONSTRAINT `Postulacion_ibfk_1` FOREIGN KEY (`idAlumno`) REFERENCES `Alumno` (`idAlumno`),
  CONSTRAINT `Postulacion_ibfk_2` FOREIGN KEY (`idOferta`) REFERENCES `Oferta` (`idOferta`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table asidb.Profesor
CREATE TABLE IF NOT EXISTS `Profesor` (
  `idProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `nombresProfesor` varchar(255) DEFAULT NULL,
  `apellidosProfesor` varchar(255) NOT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `codigoCurso` varchar(100) DEFAULT NULL,
  `nombreCurso` varchar(255) DEFAULT NULL,
  `carrera` varchar(255) DEFAULT NULL,
  `correoInstitucional` varchar(255) NOT NULL,
  `contrasena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  UNIQUE KEY `correoInstitucional` (`correoInstitucional`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

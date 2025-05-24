-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 24-05-2025 a las 10:29:04
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `colegio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `id` int NOT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `apellidos` varchar(30) DEFAULT NULL,
  `clase` char(1) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `edad` tinyint DEFAULT NULL,
  `genero` char(1) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `nota1trim` decimal(4,2) DEFAULT NULL,
  `nota2trim` decimal(4,2) DEFAULT NULL,
  `nota3trim` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id`, `nombre`, `apellidos`, `clase`, `dni`, `edad`, `genero`, `telefono`, `fecha_ingreso`, `nota1trim`, `nota2trim`, `nota3trim`) VALUES
(1, 'Daniel', 'Lopez Serrano', 'B', '11223344L', 16, 'M', '611223344', '2023-09-05', 7.20, 6.80, 7.50),
(2, 'Claudia', 'Morales Castro', 'C', '22334455M', 17, 'F', '622334455', '2023-09-05', 8.60, 9.00, 8.90),
(3, 'Alejandro', 'Ramirez Rojas', 'A', '33445566N', 15, 'M', '633445566', '2023-09-05', 4.25, 4.00, 3.90),
(4, 'Irene', 'Suarez Medina', 'B', '44556677O', 16, 'F', '644556677', '2023-09-05', 9.10, 8.80, 9.20),
(5, 'Diego', 'Cruz Delgado', 'C', '55667788P', 17, 'M', '655667788', '2023-09-05', 2.75, 3.50, 4.00),
(6, 'Nerea', 'Jimenez Cabrera', 'A', '66778899Q', 15, 'F', '666778899', '2023-09-05', 8.00, 8.30, 8.50),
(7, 'Oscar', 'Mendez Aguado', 'B', '77889900R', 16, 'M', '677889900', '2023-09-05', 4.20, 4.40, 3.90),
(8, 'Alba', 'Ramos Nieto', 'C', '88990011S', 17, 'F', '688990011', '2023-09-05', 9.00, 9.25, 9.50),
(9, 'Manuel', 'Blanco Pardo', 'A', '99001122T', 16, 'M', '699001122', '2023-09-05', 6.75, 7.00, 7.25),
(10, 'Eva', 'Marin Redondo', 'B', '10111213U', 15, 'F', '601011213', '2023-09-05', 8.70, 8.90, 9.10),
(11, 'Victor', 'Campos Soler', 'C', '11121314V', 17, 'M', '611121314', '2023-09-05', 7.10, 7.60, 7.90),
(12, 'Raquel', 'Lorenzo Vidal', 'A', '12131415W', 16, 'F', '621213141', '2023-09-05', 8.50, 8.75, 9.00),
(13, 'Alvaro', 'Lopez Galvez', 'B', '13141516X', 15, 'M', '631314151', '2023-09-05', 4.10, 3.90, 4.25),
(14, 'Carmen', 'Herrera Bravo', 'C', '14151617Y', 17, 'F', '641415161', '2023-09-05', 9.40, 9.60, 9.80),
(15, 'Gabriel', 'Nieto Lozano', 'A', '15161718Z', 16, 'M', '651516171', '2023-09-05', 3.00, 4.00, 3.50);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

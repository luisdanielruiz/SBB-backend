-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 22-07-2018 a las 22:06:48
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `SmartBoxingBag`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Categories`
--

CREATE TABLE `Categories` (
  `id` int(11) NOT NULL,
  `nombre` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Categories`
--

INSERT INTO `Categories` (`id`, `nombre`) VALUES
(1, 'Amateur'),
(2, 'Profesional'),
(3, 'Principiante'),
(4, 'Libre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Challengue`
--

CREATE TABLE `Challengue` (
  `idChallengue` int(11) NOT NULL,
  `tipoChallengue` varchar(200) CHARACTER SET utf8 NOT NULL,
  `nombre` varchar(200) CHARACTER SET utf8 NOT NULL,
  `hits` text CHARACTER SET utf8 NOT NULL,
  `time` text CHARACTER SET utf8
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Challengue`
--

INSERT INTO `Challengue` (`idChallengue`, `tipoChallengue`, `nombre`, `hits`, `time`) VALUES
(9, 'Fuerza', 'Principiante', '80', '0'),
(10, 'Fuerza', 'Amateur', '120', '0'),
(11, 'Fuerza', 'Profesional', '180', '0'),
(12, 'Velocidad', 'Principiante', '40', '15'),
(13, 'Velocidad', 'Amateur', '100', '30'),
(14, 'Velocidad', 'Profesional', '150', '45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Hit`
--

CREATE TABLE `Hit` (
  `id` int(11) NOT NULL,
  `valueForce` double DEFAULT NULL,
  `valueVel` double DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Hit`
--

INSERT INTO `Hit` (`id`, `valueForce`, `valueVel`, `date`) VALUES
(1, 467, 666, '2018-07-14 10:56:58'),
(2, 467, 668, '2018-07-16 01:49:15'),
(3, 467, 0, '2018-07-16 01:49:35'),
(4, 467, NULL, '2018-07-16 01:51:25'),
(5, 467, NULL, '2018-07-16 01:56:11'),
(6, NULL, 467, '2018-07-16 01:56:46'),
(7, NULL, 467, '2018-07-16 15:59:46'),
(8, NULL, 324, '2018-07-16 15:59:51'),
(9, NULL, 334, '2018-07-16 15:59:58'),
(10, NULL, 627, '2018-07-16 16:30:17'),
(11, NULL, 627, '2018-07-16 16:31:44'),
(12, NULL, 627, '2018-07-16 16:31:46'),
(13, NULL, 624, '2018-07-16 16:31:49'),
(14, NULL, 624, '2018-07-16 16:32:14');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Statistics`
--

CREATE TABLE `Statistics` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `challengue` varchar(300) NOT NULL,
  `tipoChallengue` varchar(300) NOT NULL,
  `time` text,
  `hits` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Statistics`
--

INSERT INTO `Statistics` (`id`, `user`, `challengue`, `tipoChallengue`, `time`, `hits`) VALUES
(1, 46710, 'Principiante', 'Fuerza', '0', '[234,166,185]'),
(2, 46713, 'Profesional', 'Fuerza', '0', '[234,166,185]'),
(3, 46710, 'Profesional', 'Fuerza', '0', '[89,120,234]');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Statistics_Hit`
--

CREATE TABLE `Statistics_Hit` (
  `idHit` int(11) NOT NULL,
  `idStatistic` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TipoChallengue`
--

CREATE TABLE `TipoChallengue` (
  `id` int(11) NOT NULL,
  `nombre` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `TipoChallengue`
--

INSERT INTO `TipoChallengue` (`id`, `nombre`) VALUES
(1, 'Fuerza'),
(2, 'Velocidad'),
(3, 'Libre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserApp`
--

CREATE TABLE `UserApp` (
  `idUser` int(11) NOT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(16) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  `idFacebook` varchar(20) DEFAULT NULL,
  `idGoogle` varchar(20) DEFAULT NULL,
  `profilePicture` varchar(10) DEFAULT NULL,
  `phone_cod` varchar(10) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `status` text,
  `axisY` double DEFAULT NULL,
  `axisX` double DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `mailConfirmado` varchar(2) NOT NULL DEFAULT '0',
  `dni` varchar(22) DEFAULT NULL,
  `cuil` varchar(20) DEFAULT NULL,
  `premium` tinyint(1) NOT NULL DEFAULT '0',
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sesionHistory` varchar(300) DEFAULT NULL,
  `statistics` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `UserApp`
--

INSERT INTO `UserApp` (`idUser`, `userName`, `password`, `email`, `name`, `surname`, `country`, `state`, `city`, `address`, `postalCode`, `idFacebook`, `idGoogle`, `profilePicture`, `phone_cod`, `phone`, `status`, `axisY`, `axisX`, `location`, `mailConfirmado`, `dni`, `cuil`, `premium`, `fechaRegistro`, `sesionHistory`, `statistics`) VALUES
(46710, NULL, '1234', 'danirr.plenus@gmail.com', 'daniel', 'ruiz', 'AR', 'Capital Federal', 'ALMAGRO', 'fwerferw 321', '1416', NULL, NULL, NULL, NULL, '1231231234', NULL, NULL, NULL, NULL, 'si', '1234123', '1324123', 0, '2018-05-24 15:06:28', '', ''),
(46713, NULL, '12345', 'luis.ruizozo@davinci.edu.ar', 'daniel', 'perez', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, 0, '2018-07-11 15:59:22', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `user` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `mail` varchar(50) DEFAULT '',
  `idPerfil` int(11) NOT NULL,
  `estado` int(11) DEFAULT '1',
  `recibeMail` int(11) DEFAULT '0',
  `codigoTablet` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`user`, `password`, `mail`, `idPerfil`, `estado`, `recibeMail`, `codigoTablet`) VALUES
('daniel', '1234', 'erwerw@ewerr.com', 4235, 1, 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Categories`
--
ALTER TABLE `Categories`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Challengue`
--
ALTER TABLE `Challengue`
  ADD PRIMARY KEY (`idChallengue`);

--
-- Indices de la tabla `Hit`
--
ALTER TABLE `Hit`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Statistics`
--
ALTER TABLE `Statistics`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `TipoChallengue`
--
ALTER TABLE `TipoChallengue`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `UserApp`
--
ALTER TABLE `UserApp`
  ADD PRIMARY KEY (`idUser`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`user`),
  ADD UNIQUE KEY `idPerfil` (`idPerfil`),
  ADD KEY `user` (`user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Categories`
--
ALTER TABLE `Categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `Challengue`
--
ALTER TABLE `Challengue`
  MODIFY `idChallengue` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `Hit`
--
ALTER TABLE `Hit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `Statistics`
--
ALTER TABLE `Statistics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `TipoChallengue`
--
ALTER TABLE `TipoChallengue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `UserApp`
--
ALTER TABLE `UserApp`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46714;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idPerfil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4236;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 05, 2015 at 08:16 PM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Alphacab`
--
CREATE DATABASE IF NOT EXISTS `Alphacab` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `Alphacab`;

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
CREATE TABLE IF NOT EXISTS `Customer` (
  `Name` text,
  `Address` text,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custPass` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`Name`, `Address`, `id`) VALUES
('Eva Smith', '129 Station Rd, London, N3 2AS', 1),
('Rob Manton', '23 Bow Lane, London, N3', 2),
('Bob Currie', '54 Teignmouth Rd, London, NW2', 3),
('Jim Hunter', '765 High Road, London, N12', 4),
('Phil Johnson', '75 Squires Lane, London, N3', 5),
('Saim Soyler', '2 Rosemary Ave, London, N3', 6),
('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', 7);

-- --------------------------------------------------------

--
-- Table structure for table `Demands`
--

DROP TABLE IF EXISTS `Demands`;
CREATE TABLE IF NOT EXISTS `Demands` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Address` text,
  `Destination` text,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `Status` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Demands`
--

INSERT INTO `Demands` (`id`, `Name`, `Address`, `Destination`, `Date`, `Time`, `Status`) VALUES
(1, 'M. E. Aydin', 'Finchley, London', 'King''s Cross, London', '2015-11-02', '09:22:18', 'Outstanding');

-- --------------------------------------------------------

--
-- Table structure for table `Drivers`
--

DROP TABLE IF EXISTS `Drivers`;
CREATE TABLE IF NOT EXISTS `Drivers` (
  `Registration` varchar(10) NOT NULL,
  `Name` text,
  `password` text,
  PRIMARY KEY (`Registration`(7)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Drivers`
--

INSERT INTO `Drivers` (`Registration`, `Name`, `password`) VALUES
('AK52VZV', 'John Smith', 'JohnSmith'),
('BN60WKA', 'Mehmet Aydin', 'MehmetAydin'),
('R34AKP', 'Mark Atley', 'MarkAtley');

-- --------------------------------------------------------

--
-- Table structure for table `Journey`
--

DROP TABLE IF EXISTS `Journey`;
CREATE TABLE IF NOT EXISTS `Journey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Destination` text,
  `Distance` int(11) NOT NULL DEFAULT '1',
  `Customer.id` int(11) NOT NULL,
  `Drivers.Registration` varchar(10) NOT NULL,
  `Date` date NOT NULL,
  `Time` time DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Journey`
--

INSERT INTO `Journey` (`id`, `Destination`, `Distance`, `Customer.id`, `Drivers.Registration`, `Date`, `Time`) VALUES
(1, 'King''s Cross Station, London', 5, 1, 'BN60WKA', '2015-10-14', '09:30:00'),
(2, 'Heathrow Terminal 3, London', 20, 7, 'BN60WKA', '2015-10-14', '12:00:00'),
(3, '120 Green Lanes, London, N13', 7, 7, 'AK52VZV', '2015-10-15', '06:00:00'),
(4, '131 Stoke Newington High Road, London, N12', 8, 7, 'AK52VZV', '2015-10-15', '12:00:00'),
(5, 'Luton Airport, Luton', 30, 1, 'R34AKP', '2015-10-22', '10:00:00');


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

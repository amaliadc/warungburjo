-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 23, 2015 at 10:57 AM
-- Server version: 5.5.27
-- PHP Version: 5.5.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pjw`
--

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nama_makanan` varchar(255) NOT NULL,
  `harga_makanan` varchar(10) NOT NULL,
  `deskripsi_makanan` varchar(255) NOT NULL,
  `tersedia` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `nama_makanan`, `harga_makanan`, `deskripsi_makanan`, `tersedia`) VALUES
(1, 'Burjo', '10000', 'Bubur kacang ijo', 'tersedia'),
(2, 'Gorengan', '5000', 'Paket gorengan yang terdiri dari 2 tempe goreng & 3 tahu goreng', 'tersedia'),
(3, 'Mie Goreng Telur', '5000', 'Mie goreng dengan telur diatasnya', 'tersedia'),
(4, 'Nasi Telor', '5000', 'Nasi putih dengan lauk telor goreng', 'tersedia'),
(5, 'Nasi Ayam', '10000', 'Nasi putih dengan lauk ayam goreng', 'tersedia'),
(6, 'Nasi Goreng', '10000', 'Nasi goreng', 'tersedia'),
(7, 'Es Teh', '2500', 'Es Teh', 'tersedia');

-- --------------------------------------------------------

--
-- Table structure for table `pemesan`
--

CREATE TABLE IF NOT EXISTS `pemesan` (
  `no_pesanan` int(10) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(250) NOT NULL,
  `telepon` varchar(25) NOT NULL,
  `keterangan` varchar(250) NOT NULL,
  PRIMARY KEY (`no_pesanan`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `pemesan`
--

INSERT INTO `pemesan` (`no_pesanan`, `nama`, `alamat`, `telepon`, `keterangan`) VALUES
(1, 'Asep', 'Jl. Grafika 5, Yogyakarta', '081321234567', '');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE IF NOT EXISTS `pesanan` (
  `nomor` int(5) NOT NULL AUTO_INCREMENT,
  `no_pesanan` int(5) NOT NULL,
  `idMakanan` int(5) NOT NULL,
  `jumlah` int(5) NOT NULL,
  PRIMARY KEY (`nomor`),
  KEY `no_pesanan` (`no_pesanan`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`nomor`, `no_pesanan`, `idMakanan`, `jumlah`) VALUES
(1, 1, 1, 5),
(2, 1, 2, 1),
(3, 1, 3, 3),
(4, 1, 7, 5),
(5, 1, 6, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`no_pesanan`) REFERENCES `pemesan` (`no_pesanan`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

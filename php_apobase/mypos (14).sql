-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2020 at 04:27 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mypos`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id_cart` varchar(32) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `qty_dibeli` int(11) NOT NULL,
  `total_berat` int(40) NOT NULL,
  `tgl_transaksi` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id_cart`, `customer_id`, `id_item`, `qty_dibeli`, `total_berat`, `tgl_transaksi`) VALUES
('IDC01606', 0, 21, 0, 0, '2020-06-16 15:28:49');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` enum('L','P') NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email_db` varchar(128) NOT NULL,
  `password_db` varchar(128) NOT NULL,
  `address` text NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `name`, `gender`, `phone`, `email_db`, `password_db`, `address`, `created`, `updated`) VALUES
(1, 'Wahyu Sudrajat', 'L', '085815823775', 'wahyu@gmail.com', '1', 'Jember', '2020-03-22 16:43:16', NULL),
(5, 'Ali', 'L', '081336886911', 'alianda@gmail.com', '1', 'Jember', '2020-04-02 00:00:00', NULL),
(6, 'Alex', 'L', '082823774116', 'alex@gmail.com', '12', 'Jember', '2020-04-22 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dtl_transaksi`
--

CREATE TABLE `dtl_transaksi` (
  `id_transaksi` varchar(32) NOT NULL,
  `item_id` varchar(32) NOT NULL,
  `no` int(11) DEFAULT NULL,
  `harga_satuan` int(20) NOT NULL,
  `jml_dibeli_tmp` int(11) NOT NULL,
  `jumlah_beli` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dtl_transaksi`
--

INSERT INTO `dtl_transaksi` (`id_transaksi`, `item_id`, `no`, `harga_satuan`, `jml_dibeli_tmp`, `jumlah_beli`) VALUES
('IDC01105', '28', 1, 100000, 0, 3),
('IDC01105', '26', 2, 1200000, 0, 3),
('IDC11105', '28', 3, 100000, 0, 4),
('IDC21205', '28', 4, 100000, 0, 0),
('IDC21205', '26', 5, 1200000, 0, 0),
('IDC31205', '14', 6, 121213, 0, 3),
('IDC31205', '26', 7, 1200000, 0, 3),
('IDC41305', '28', 8, 100000, 0, 6),
('IDC50506', '28', 9, 100000, 0, 0),
('IDC50506', '14', 10, 121213, 0, 0),
('IDC61906', '2', 11, 12, 2, 0),
('IDC71906', '23', 12, 1938190, 4, 0);

--
-- Triggers `dtl_transaksi`
--
DELIMITER $$
CREATE TRIGGER `stok_min_online` AFTER UPDATE ON `dtl_transaksi` FOR EACH ROW BEGIN
	UPDATE p_item SET stock = stock - NEW.jumlah_beli
    WHERE item_id = NEW.item_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `p_category`
--

CREATE TABLE `p_category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `p_category`
--

INSERT INTO `p_category` (`category_id`, `name`, `created`, `updated`) VALUES
(2, 'Perbiji', '2020-03-22 17:27:55', '2020-03-22 14:08:00'),
(3, 'Perkotakk', '2020-03-23 02:54:41', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `p_item`
--

CREATE TABLE `p_item` (
  `item_id` int(11) NOT NULL,
  `barcode` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `berat` int(129) NOT NULL,
  `deskripsi` text DEFAULT NULL,
  `stock` int(10) NOT NULL DEFAULT 0,
  `image` varchar(100) DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `p_item`
--

INSERT INTO `p_item` (`item_id`, `barcode`, `name`, `category_id`, `unit_id`, `price`, `berat`, `deskripsi`, `stock`, `image`, `created`, `updated`) VALUES
(2, 'A002', 'Kopi hijau / Green coffee New', 2, 5, 55000, 500, 'Barang Baru', 28, 'item-200629-0fdabcd482.jpg', '2020-03-22 23:21:18', '2020-06-29 15:13:39'),
(5, 'A005', 'Kopi Lanang', 2, 5, 26000, 150, '', 0, 'item-200629-ecbaaf7812.jpg', '2020-06-19 09:01:04', '2020-06-29 15:14:03'),
(6, 'A006', 'Kopi Arabica', 2, 5, 28000, 140, '', 0, 'item-200629-2f548364aa.jpg', '2020-06-19 09:01:54', '2020-06-29 15:14:51'),
(14, 'A014', 'Kopi Stamina', 2, 2, 45000, 150, '', 16, 'item-200629-dd1fa09d3a.jpg', '2020-03-26 03:27:43', '2020-06-29 15:15:19'),
(21, 'A021', 'Kopi Rempah', 3, 5, 45000, 200, '', 18, 'item-200629-4f553f2766.jpg', '2020-03-29 16:23:06', '2020-06-29 15:15:36'),
(23, 'A023', 'Kopi Robusta', 2, 2, 25000, 200, '', 0, 'item-200629-e3ec5c582e.jpg', '2020-04-05 20:55:21', '2020-06-29 15:15:57'),
(26, 'A026', 'Kopi Roasting', 3, 2, 30000, 250000, '', 10, 'item-200629-d4650a7470.jpg', '2020-05-08 22:24:45', '2020-06-29 15:16:26');

-- --------------------------------------------------------

--
-- Table structure for table `p_unit`
--

CREATE TABLE `p_unit` (
  `unit_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `p_unit`
--

INSERT INTO `p_unit` (`unit_id`, `name`, `created`, `updated`) VALUES
(2, 'Perkotak', '2020-03-22 17:27:55', '2020-05-09 23:53:51'),
(5, 'Perlusin', '2020-04-01 11:20:50', NULL),
(11, 'Per Ons', '2020-07-03 14:37:58', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplier_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` varchar(200) NOT NULL,
  `description` text DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplier_id`, `name`, `phone`, `address`, `description`, `created`, `updated`) VALUES
(5, 'Andi', '082005678234', 'Jember', 'Supplier Baju', '2020-06-17 10:17:51', NULL),
(6, 'Eko Cahyono', '081223665132', 'Probolinggo', 'Supplier kopi', '2020-06-17 10:18:59', NULL),
(7, 'Lintang Permatasari', '082527823775', 'Banyuwangi', 'Supplier Celana', '2020-06-17 10:20:18', NULL),
(8, 'Bambang Prasetyoo', '082114927352', 'Banyuwangi', 'Supplier Lagganan', '2020-06-17 10:20:59', NULL),
(9, 'Prisilia Fadilla', '082753441972', 'Jember', 'Supplier Lagganan', '2020-06-17 10:21:42', NULL),
(10, 'Luluk Mukaromah', '085312327336', 'Jember', 'Supplier Token', '2020-06-17 10:27:10', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `alamat_kirim` text NOT NULL,
  `tgl_kirim` int(32) DEFAULT NULL,
  `total_harga` int(20) NOT NULL,
  `total_final` int(20) NOT NULL,
  `status_bayar` int(11) DEFAULT NULL,
  `status_kirim` int(11) DEFAULT NULL,
  `tgl_transaksi` varchar(32) DEFAULT NULL,
  `bukti_transfer` varchar(123) DEFAULT NULL,
  `no_rek` varchar(22) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `user_id`, `customer_id`, `alamat_kirim`, `tgl_kirim`, `total_harga`, `total_final`, `status_bayar`, `status_kirim`, `tgl_transaksi`, `bukti_transfer`, `no_rek`) VALUES
('IDC01105', 1, 5, 'JL. Banyuwangi Garahan Pasar alasa', 200513, 3900000, 4150000, 1, 1, '2020-05-11 19:20:41', 'bukti-200512-86ff46a8a5.jpg', '11111'),
('IDC11105', 1, 5, 'AA', 200513, 400000, 544000, 1, 1, '2020-05-11 19:24:19', 'bukti-200512-45f4bdd50c.png', '12322'),
('IDC21205', 1, 5, 'koko', 200605, 2600000, 2696000, 1, 1, '2020-05-12 13:57:42', NULL, '2123'),
('IDC31205', 1, 1, 'awdawd', 200513, 3963639, 4107639, 1, 1, '2020-05-12 23:49:40', 'bukti-200512-a6670fd0ae.jpg', '1212123'),
('IDC41305', 1, 5, 'AWD', 200513, 600000, 830000, 1, 1, '2020-05-13 20:09:14', 'bukti-200513-8ff6f8fa69.jpg', '876609'),
('IDC50506', 1, 5, 'awdadwad', 200605, 706065, 759065, 1, 1, '2020-06-05 18:05:28', 'bukti-200605-d7af4bc9a3.jpg', '2323123'),
('IDC61906', 0, 5, 'klhklhlkjhjhjhjghjghg', NULL, 24, 110024, NULL, NULL, '2020-06-19 17:19:34', NULL, NULL),
('IDC71906', 0, 5, 'i7t6y6y67y6', NULL, 7752760, 7806760, NULL, NULL, '2020-06-19 17:54:52', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_cart`
--

CREATE TABLE `t_cart` (
  `cart_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `qty` int(10) NOT NULL,
  `discount_item` int(11) NOT NULL DEFAULT 0,
  `total` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `t_sale`
--

CREATE TABLE `t_sale` (
  `sale_id` int(11) NOT NULL,
  `invoice` varchar(50) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `total_price` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `final_price` int(11) NOT NULL,
  `cash` int(11) NOT NULL,
  `remaining` int(11) NOT NULL,
  `note` text NOT NULL,
  `date` date NOT NULL,
  `user_id` int(11) NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_sale`
--

INSERT INTO `t_sale` (`sale_id`, `invoice`, `customer_id`, `total_price`, `discount`, `final_price`, `cash`, `remaining`, `note`, `date`, `user_id`, `created`) VALUES
(53, 'PC2005090001', NULL, 90012, 0, 90012, 900122, 810110, 'aa', '2020-05-09', 1, '2020-05-10 03:44:43'),
(54, 'PC2005130001', NULL, 121213, 0, 121213, 121213, 0, '', '2020-05-13', 1, '2020-05-13 06:11:06');

-- --------------------------------------------------------

--
-- Table structure for table `t_sale_detail`
--

CREATE TABLE `t_sale_detail` (
  `detail_id` int(11) NOT NULL,
  `sale_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `qty` int(10) NOT NULL,
  `discount_item` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `t_sale_detail`
--
DELIMITER $$
CREATE TRIGGER `stock_min` AFTER INSERT ON `t_sale_detail` FOR EACH ROW BEGIN
	UPDATE p_item SET stock = stock - NEW.qty
    WHERE item_id = NEW.item_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_stock`
--

CREATE TABLE `t_stock` (
  `stock_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `type` enum('in','out') NOT NULL,
  `detail` varchar(200) NOT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `qty` int(11) NOT NULL,
  `date` date NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `level` int(1) NOT NULL COMMENT '1:admin, 2:kasir'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `name`, `address`, `level`) VALUES
(8, 'alex', '7b21848ac9af35be0ddb2d6b9fc3851934db8420', 'alex', 'Jogja', 2),
(10, 'admina', '7b21848ac9af35be0ddb2d6b9fc3851934db8420', 'Andrea', 'situbondo', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `p_category`
--
ALTER TABLE `p_category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `p_item`
--
ALTER TABLE `p_item`
  ADD PRIMARY KEY (`item_id`),
  ADD UNIQUE KEY `barcode` (`barcode`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `unit_id` (`unit_id`);

--
-- Indexes for table `p_unit`
--
ALTER TABLE `p_unit`
  ADD PRIMARY KEY (`unit_id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `t_cart`
--
ALTER TABLE `t_cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `t_sale`
--
ALTER TABLE `t_sale`
  ADD PRIMARY KEY (`sale_id`);

--
-- Indexes for table `t_sale_detail`
--
ALTER TABLE `t_sale_detail`
  ADD PRIMARY KEY (`detail_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `t_stock`
--
ALTER TABLE `t_stock`
  ADD PRIMARY KEY (`stock_id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `p_category`
--
ALTER TABLE `p_category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `p_item`
--
ALTER TABLE `p_item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `p_unit`
--
ALTER TABLE `p_unit`
  MODIFY `unit_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplier_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `t_sale`
--
ALTER TABLE `t_sale`
  MODIFY `sale_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `t_sale_detail`
--
ALTER TABLE `t_sale_detail`
  MODIFY `detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `t_stock`
--
ALTER TABLE `t_stock`
  MODIFY `stock_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `p_item`
--
ALTER TABLE `p_item`
  ADD CONSTRAINT `p_item_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `p_category` (`category_id`),
  ADD CONSTRAINT `p_item_ibfk_2` FOREIGN KEY (`unit_id`) REFERENCES `p_unit` (`unit_id`);

--
-- Constraints for table `t_cart`
--
ALTER TABLE `t_cart`
  ADD CONSTRAINT `t_cart_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `p_item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `t_sale_detail`
--
ALTER TABLE `t_sale_detail`
  ADD CONSTRAINT `t_sale_detail_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `p_item` (`item_id`);

--
-- Constraints for table `t_stock`
--
ALTER TABLE `t_stock`
  ADD CONSTRAINT `t_stock_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `p_item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_stock_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`),
  ADD CONSTRAINT `t_stock_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

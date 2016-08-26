-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2016 at 01:13 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sale`
--

-- --------------------------------------------------------

--
-- Table structure for table `category_product`
--

CREATE TABLE `category_product` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category_product`
--

INSERT INTO `category_product` (`id`, `name`, `status`, `create_date`, `description`) VALUES
(1, 'Laptop', 'open', '2016-07-25 08:35:30', 'Danh mục laptop'),
(2, 'Điện thoại', 'open', '2016-07-25 08:36:33', 'Danh mục điện thoại'),
(3, 'Rượu', 'open', '2016-08-13 04:19:56', 'Danh mục rượu'),
(5, 'Quần áo', 'open', '2016-08-13 09:07:29', 'Danh mục quần áo');

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `id` int(9) NOT NULL,
  `url` varchar(500) NOT NULL,
  `parent` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `party_id` int(20) DEFAULT NULL,
  `url_thumb` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`id`, `url`, `parent`, `type`, `create_date`, `party_id`, `url_thumb`) VALUES
(10, '017_0.jpg', '017', 'product', '2016-07-26 14:26:30', 0, ''),
(11, '017_1.jpg', '017', 'product', '2016-07-26 14:26:31', 0, ''),
(14, '019_1.jpg', '019', 'product', '2016-07-26 14:31:30', 0, ''),
(15, '019_1.jpg', '019', 'product', '2016-07-26 14:31:31', 0, ''),
(118, '001_0.jpg', '001', 'product', '2016-08-14 06:58:13', 0, '001_0_thumb.jpg'),
(119, '001_1.jpg', '001', 'product', '2016-08-14 06:58:13', 0, '001_1_thumb.jpg'),
(120, '001_2.jpg', '001', 'product', '2016-08-14 06:58:14', 0, '001_2_thumb.jpg'),
(121, '005_0.jpg', '005', 'product', '2016-08-14 06:58:30', 0, '005_0_thumb.jpg'),
(122, '005_1.jpg', '005', 'product', '2016-08-14 06:58:31', 0, '005_1_thumb.jpg'),
(123, '005_2.jpg', '005', 'product', '2016-08-14 06:58:31', 0, '005_2_thumb.jpg'),
(124, '005_3.jpg', '005', 'product', '2016-08-14 06:58:31', 0, '005_3_thumb.jpg'),
(125, '006_0.jpg', '006', 'product', '2016-08-14 06:58:49', 0, '006_0_thumb.jpg'),
(126, '006_1.jpg', '006', 'product', '2016-08-14 06:58:50', 0, '006_1_thumb.jpg'),
(127, '006_2.jpg', '006', 'product', '2016-08-14 06:58:50', 0, '006_2_thumb.jpg'),
(128, '006_3.jpg', '006', 'product', '2016-08-14 06:58:51', 0, '006_3_thumb.jpg'),
(129, '006_4.jpg', '006', 'product', '2016-08-14 06:58:51', 0, '006_4_thumb.jpg'),
(130, '007_0.jpg', '007', 'product', '2016-08-14 06:59:08', 0, '007_0_thumb.jpg'),
(131, '007_1.jpg', '007', 'product', '2016-08-14 06:59:09', 0, '007_1_thumb.jpg'),
(132, '007_2.jpg', '007', 'product', '2016-08-14 06:59:09', 0, '007_2_thumb.jpg'),
(133, '007_3.jpg', '007', 'product', '2016-08-14 06:59:09', 0, '007_3_thumb.jpg'),
(134, '008_0.jpg', '008', 'product', '2016-08-14 06:59:32', 0, '008_0_thumb.jpg'),
(135, '008_1.jpg', '008', 'product', '2016-08-14 06:59:32', 0, '008_1_thumb.jpg'),
(136, '008_2.jpg', '008', 'product', '2016-08-14 06:59:32', 0, '008_2_thumb.jpg'),
(137, '009_0.jpg', '009', 'product', '2016-08-14 06:59:50', 0, '009_0_thumb.jpg'),
(138, '009_1.jpg', '009', 'product', '2016-08-14 06:59:51', 0, '009_1_thumb.jpg'),
(139, '009_2.jpg', '009', 'product', '2016-08-14 06:59:51', 0, '009_2_thumb.jpg'),
(140, '009_3.jpg', '009', 'product', '2016-08-14 06:59:51', 0, '009_3_thumb.jpg'),
(141, '009_4.jpg', '009', 'product', '2016-08-14 06:59:52', 0, '009_4_thumb.jpg'),
(142, '009_5.jpg', '009', 'product', '2016-08-14 06:59:52', 0, '009_5_thumb.jpg'),
(143, '009_6.jpg', '009', 'product', '2016-08-14 06:59:52', 0, '009_6_thumb.jpg'),
(144, '010_0.jpg', '010', 'product', '2016-08-14 07:00:12', 0, '010_0_thumb.jpg'),
(145, '010_1.jpg', '010', 'product', '2016-08-14 07:00:12', 0, '010_1_thumb.jpg'),
(146, '010_2.jpg', '010', 'product', '2016-08-14 07:00:13', 0, '010_2_thumb.jpg'),
(147, '010_3.jpg', '010', 'product', '2016-08-14 07:00:13', 0, '010_3_thumb.jpg'),
(150, '013_0.jpg', '013', 'product', '2016-08-14 07:00:50', 0, '013_0_thumb.jpg'),
(151, '013_1.jpg', '013', 'product', '2016-08-14 07:00:50', 0, '013_1_thumb.jpg'),
(152, '013_2.jpg', '013', 'product', '2016-08-14 07:00:51', 0, '013_2_thumb.jpg'),
(153, '014_0.jpg', '014', 'product', '2016-08-14 07:01:08', 0, '014_0_thumb.jpg'),
(154, '014_1.jpg', '014', 'product', '2016-08-14 07:01:09', 0, '014_1_thumb.jpg'),
(155, '014_2.jpg', '014', 'product', '2016-08-14 07:01:09', 0, '014_2_thumb.jpg'),
(156, '011_0.jpg', '011', 'product', '2016-08-14 07:11:47', 0, '011_0_thumb.jpg'),
(157, '011_1.jpg', '011', 'product', '2016-08-14 07:11:48', 0, '011_1_thumb.jpg'),
(158, '011_2.jpg', '011', 'product', '2016-08-14 07:11:48', 0, '011_2_thumb.jpg'),
(159, '002_0.jpg', '002', 'product', '2016-08-14 08:42:22', 0, '002_0_thumb.jpg'),
(160, '002_1.jpg', '002', 'product', '2016-08-14 08:42:23', 0, '002_1_thumb.jpg'),
(161, '002_2.jpg', '002', 'product', '2016-08-14 08:42:23', 0, '002_2_thumb.jpg'),
(162, '002_3.jpg', '002', 'product', '2016-08-14 08:42:23', 0, '002_3_thumb.jpg'),
(163, '002_0.jpg', '002', 'product', '2016-08-14 08:43:39', 0, '002_0_thumb.jpg'),
(164, '002_1.jpg', '002', 'product', '2016-08-14 08:43:39', 0, '002_1_thumb.jpg'),
(165, '002_2.jpg', '002', 'product', '2016-08-14 08:43:40', 0, '002_2_thumb.jpg'),
(166, '002_3.jpg', '002', 'product', '2016-08-14 08:43:40', 0, '002_3_thumb.jpg'),
(173, '003_0.jpg', '003', 'product', '2016-08-14 09:09:54', 0, '003_0_thumb.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `level` int(1) NOT NULL,
  `state` varchar(10) NOT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `gender` varchar(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `position` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `username`, `email`, `phone_number`, `name`, `address`, `level`, `state`, `birthday`, `create_date`, `gender`, `password`, `role`, `position`) VALUES
(1, 'thevinh', 'thevinh205', '1663810003', 'thế vinh', 'tô ký, quận 12', 1, 'open', '1990-05-19 17:00:00', '2016-07-20 17:00:00', 'Nam', 'thevinh', 'customer', ''),
(2, 'thuytrang', 'thuytrang@gmail.com', '1656502376', 'Thùy Trang', 'nha trang', 1, 'open', '2016-06-30 17:00:00', '2016-07-22 03:41:05', 'Nữ', 'thuytrang', 'employee', ''),
(7, 'trangbaby', 'thuytrang@gmail.com', '01656502376', 'Nguyễn Thị Thùy Trang', 'suối cát, cam lâm, khánh hòa', 1, 'open', '2016-06-30 17:00:00', '2016-07-22 06:55:07', '', 'thuytrang', 'customer', ''),
(13, 'vinhdeptrai', 'nhtvinh@gmail.com', '14325234342', 'Nguyễn Hoàng Thế Vinh', 'sài gòn - nha trang', 1, 'open', '2016-07-31 17:00:00', '2016-08-21 08:13:19', 'Nam', 'thevinh', 'employee', '');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `group_id` int(5) NOT NULL,
  `description` longtext,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `size` varchar(20) DEFAULT NULL,
  `weight` int(10) NOT NULL,
  `style` varchar(50) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `category_name` varchar(50) NOT NULL,
  `price_buy` bigint(15) NOT NULL,
  `price_sell` bigint(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `group_id`, `description`, `create_date`, `type`, `color`, `size`, `weight`, `style`, `avatar`, `status`, `category_name`, `price_buy`, `price_sell`) VALUES
('001', 'test001', 1, 'GrabPay - Tính năng thanh toán bằng thẻ qua ứng dụng Grab cho 2 dịch vụ GrabCar và GrabBike vừa mới ra mắt chưa lâu. Có lẽ vì vậy mà nhiều bạn vẫn chưa biết cách làm sao để nhập thông tin thẻ vào GrabPay, nhân đây Grab hướng dẫn bạn qua 4 bước đơn giản sau nha:✅ Bước 1: Chọn biểu tượng ba dấu gạch ngang ở góc trên cùng bên trái ứng dụng✅ Bước 2: Chọn GrabPay ✅ Bước 3: Chọn Thêm thẻ tín dụng/Thẻ ghi nợ (hoặc Add Credit/Debit Card)', '2016-08-14 09:00:01', NULL, NULL, NULL, 0, NULL, '001_0_thumb.jpg', 'open', 'Laptop', 1000000, 20000000),
('002', 'ngon', 1, 'When the question is specific, it makes sense for the answer to be specific.<div><br></div><div>&nbsp;As for NumberFormat.getInstance potentially not returning DecimalFormat - yes, that''s a possibility.&nbsp;</div><div><br></div><div>I''m not sure the best way round that, to be honest; the API isn''t particularly useful there :(</div>', '2016-08-14 09:13:17', NULL, NULL, NULL, 0, NULL, '002_0_thumb.jpg', 'open', 'Laptop', 10000000, 30000000),
('003', 'sản phẩm ngon', 1, 'I haven''t bothered&nbsp;<div><br></div><div>with&nbsp;locales since you specifically&nbsp;</div><div><br></div><div>stated you wanted commas				            </div>', '2016-08-14 09:12:45', NULL, NULL, NULL, 0, NULL, '003_0_thumb.jpg', 'open', 'Laptop', 2222222, 4444444),
('005', 'điện thoại samsung', 1, 'test 10', '2016-08-14 08:39:36', NULL, NULL, NULL, 0, NULL, '005_0_thumb.jpg', 'open', 'Laptop', 2132132, 1234567),
('006', 'iphone 6', 1, '<p>điện thoại iphone 6 trung quốc chính hãng</p>\r\n', '2016-08-14 06:40:32', NULL, NULL, NULL, 0, NULL, '006_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('007', 'macbook', 1, 'máy tính macbook giá rẻ', '2016-08-14 06:59:10', NULL, NULL, NULL, 0, NULL, '007_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('008', 'test', 1, 'fadfdsa fdsaf123', '2016-08-14 07:18:32', NULL, NULL, NULL, 0, NULL, '008_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('009', 'máy tính asus', 1, 'máy tính asus', '2016-08-14 06:59:53', NULL, NULL, NULL, 0, NULL, '009_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('010', 'máy tính samsung', 1, '123 test thôi', '2016-08-14 06:40:18', NULL, NULL, NULL, 0, NULL, '010_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('011', 'test 011', 1, 'hehe', '2016-08-14 07:00:19', NULL, NULL, NULL, 0, NULL, '011_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('013', 'laptop hp', 1, 'máy tính hp test<div>hi</div>', '2016-08-14 07:00:51', NULL, NULL, NULL, 0, NULL, '013_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('014', 'vertu', 1, 'điện thoại vetu', '2016-08-14 07:01:09', NULL, NULL, NULL, 0, NULL, '014_0_thumb.jpg', 'open', 'Laptop', 0, 0),
('015', 'điện thoại cục gạch', 1, 'test điện thoại cục gạch', '2016-08-07 10:56:22', NULL, NULL, NULL, 0, NULL, '015_0.jpg', 'open', 'Laptop', 0, 0),
('016', 'htc', 1, 'điện thoại htc', '2016-08-07 11:05:19', NULL, NULL, NULL, 0, NULL, '016_0.jpg', 'open', 'Laptop', 0, 0),
('017', 'test 017', 1, 'test sản phẩm', '2016-07-26 14:26:29', NULL, NULL, NULL, 0, NULL, '017_0.jpg', 'open', 'Laptop', 0, 0),
('018', 'test018', 1, 'test', '2016-07-26 14:29:57', NULL, NULL, NULL, 0, NULL, '018_0.jpg', 'open', 'Laptop', 0, 0),
('019', 'test 019', 1, 'test 019', '2016-07-26 14:31:28', NULL, NULL, NULL, 0, NULL, '019_0.jpg', 'open', 'Laptop', 0, 0),
('020', '123tesst', 1, 'hêh test', '2016-07-26 14:34:39', NULL, NULL, NULL, 0, NULL, '020_0.jpg', 'open', 'Laptop', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE `shop` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text,
  `address` varchar(200) NOT NULL,
  `phone_number` bigint(20) NOT NULL,
  `create_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop`
--

INSERT INTO `shop` (`id`, `name`, `description`, `address`, `phone_number`, `create_date`) VALUES
(1, 'Shop quận 12', 'Shop cung cấp rượu vang đà lạt, rượu nho Ninh Thuận', '112 Tô Ký, quận 12', 1663810003, '2016-08-24'),
(2, 'Shop Tô Hiến Thành', 'Mua bán dày dép phụ nữ giá rẻ', 'Tô Hiến Thành, quận 10, Hồ Chi  minh', 1656502376, '2016-08-24');

-- --------------------------------------------------------

--
-- Table structure for table `shop_party_relationship`
--

CREATE TABLE `shop_party_relationship` (
  `shop_id` int(10) NOT NULL,
  `product_id` varchar(20) DEFAULT NULL,
  `member_id` int(10) NOT NULL,
  `order_id` int(10) NOT NULL,
  `type` varchar(20) NOT NULL,
  `create_date` date NOT NULL,
  `count` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_party_relationship`
--

INSERT INTO `shop_party_relationship` (`shop_id`, `product_id`, `member_id`, `order_id`, `type`, `create_date`, `count`) VALUES
(1, '001', 0, 0, 'product', '2016-08-02', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category_product`
--
ALTER TABLE `category_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shop_party_relationship`
--
ALTER TABLE `shop_party_relationship`
  ADD PRIMARY KEY (`shop_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category_product`
--
ALTER TABLE `category_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=174;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

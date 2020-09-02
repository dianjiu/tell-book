-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2019-10-06 18:02:50
-- 服务器版本： 5.7.26-log
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tellbook`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_book`
--

CREATE TABLE IF NOT EXISTS `t_book` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tell` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_book`
--

INSERT INTO `t_book` (`id`, `name`, `tell`, `email`, `addr`, `user_code`) VALUES
(2, '李四', '13666925219', 'lisi@qq.com', '湖南长沙', 9),
(3, '王二', '13666925218', 'wanger@qq.com', '湖南长沙', 9),
(4, '张三', '13666925217', '468792388@qq.com', '安徽合肥', 9),
(5, '点九', '13666925216', 'mail@point9.cn', '湖南长沙', 9),
(6, '欧阳紫君', '13166965210', '752601286@qq.com', '湖南长沙', 9);

-- --------------------------------------------------------

--
-- 表的结构 `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `pass_word` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_user`
--

INSERT INTO `t_user` (`id`, `user_name`, `pass_word`, `email`, `age`) VALUES
(9, 'admin', '123456', 'admin@qq.com', 28),
(10, 'zhangsan', '123456', 'zhansan@qq.com', 18),
(11, 'lisi', '123456', 'lisi@qq.com', 28),
(12, 'wanger', '654321', 'wanger@qq.com', 28);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_book`
--
ALTER TABLE `t_book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_book`
--
ALTER TABLE `t_book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `t_user`
--
ALTER TABLE `t_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

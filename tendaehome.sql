/*
Navicat MySQL Data Transfer

Source Server         : 2_Marshall
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : tendaehome

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2017-05-12 18:51:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `time` bigint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1', '12', '1478000000');

-- ----------------------------
-- Table structure for `garousel`
-- ----------------------------
DROP TABLE IF EXISTS `garousel`;
CREATE TABLE `garousel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `url` varchar(200) NOT NULL,
  `catalog_id` int(11) NOT NULL,
  `hyperlink` varchar(200) DEFAULT '',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of garousel
-- ----------------------------
INSERT INTO `garousel` VALUES ('1', '哈哈', '', '1', '', '2017-05-10 16:58:12');
INSERT INTO `garousel` VALUES ('2', '阿斯达', ' ', '2', '', '2017-05-10 17:01:45');
INSERT INTO `garousel` VALUES ('5', '', '', '1', '', '2017-05-12 14:48:23');
INSERT INTO `garousel` VALUES ('6', '', '', '1', '', '2017-05-12 14:48:32');
INSERT INTO `garousel` VALUES ('7', '', '', '1', '', '2017-05-12 15:08:29');
INSERT INTO `garousel` VALUES ('8', '', '', '1', '', '2017-05-12 15:10:11');
INSERT INTO `garousel` VALUES ('9', '', '', '1', '', '2017-05-12 15:10:23');
INSERT INTO `garousel` VALUES ('10', '', '', '1', '', '2017-05-12 15:20:19');

-- ----------------------------
-- Table structure for `garousel_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `garousel_catalog`;
CREATE TABLE `garousel_catalog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `garousel_catalog` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of garousel_catalog
-- ----------------------------
INSERT INTO `garousel_catalog` VALUES ('1', '首页轮播图');
INSERT INTO `garousel_catalog` VALUES ('2', '一米菜园轮播图');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `url` varchar(200) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `description` varchar(10) DEFAULT '',
  `time` bigint(11) NOT NULL,
  `origin` varchar(20) DEFAULT '' COMMENT '源产地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '白菜', '5.40', 'upload/goods/cabbage.jpg', '2', ' ', '0', '');
INSERT INTO `goods` VALUES ('2', '黄瓜', '8.20', 'upload/goods/cucumber.jpg', '2', '', '0', '');
INSERT INTO `goods` VALUES ('3', '西红柿', '6.50', 'upload/goods/tomato.jpg', '2', ' ', '0', '');
INSERT INTO `goods` VALUES ('4', 'asda', '64.00', 'asda', '1', '', '0', '');

-- ----------------------------
-- Table structure for `goods_activity`
-- ----------------------------
DROP TABLE IF EXISTS `goods_activity`;
CREATE TABLE `goods_activity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL,
  `start_time` bigint(11) NOT NULL,
  `end_time` bigint(11) NOT NULL,
  `discount` decimal(11,2) DEFAULT '1.00' COMMENT '折扣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_activity
-- ----------------------------
INSERT INTO `goods_activity` VALUES ('1', '1', '0', '1496764800', '0.12');
INSERT INTO `goods_activity` VALUES ('2', '2', '0', '1494309600', '1.00');

-- ----------------------------
-- Table structure for `goods_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `goods_catalog`;
CREATE TABLE `goods_catalog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `catalog` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_catalog
-- ----------------------------
INSERT INTO `goods_catalog` VALUES ('1', '土豆');
INSERT INTO `goods_catalog` VALUES ('2', '西红柿');
INSERT INTO `goods_catalog` VALUES ('3', '西兰花');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `time` bigint(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '0取消订单,1未支付，2已支付未发货，3发货未签收，4发货已签收',
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '1494135144', '2', '1');
INSERT INTO `orders` VALUES ('2', '1', '1494136308', '2', '2');
INSERT INTO `orders` VALUES ('3', '1', '1494314099', '0', '1');
INSERT INTO `orders` VALUES ('4', '1', '1494314196', '1', '1');
INSERT INTO `orders` VALUES ('5', '1', '1494314516', '1', '1');

-- ----------------------------
-- Table structure for `orders_detail`
-- ----------------------------
DROP TABLE IF EXISTS `orders_detail`;
CREATE TABLE `orders_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `num` decimal(11,2) NOT NULL,
  `prices` decimal(11,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders_detail
-- ----------------------------
INSERT INTO `orders_detail` VALUES ('1', '1', '1', '4.00', '1.00');
INSERT INTO `orders_detail` VALUES ('2', '1', '2', '12.00', '1.00');
INSERT INTO `orders_detail` VALUES ('3', '2', '1', '76.00', '1.00');
INSERT INTO `orders_detail` VALUES ('4', '2', '2', '5.00', '1.00');
INSERT INTO `orders_detail` VALUES ('5', '2', '3', '55.00', '1.00');
INSERT INTO `orders_detail` VALUES ('6', '3', '2', '3.00', '24.60');
INSERT INTO `orders_detail` VALUES ('7', '3', '3', '3.00', '19.50');
INSERT INTO `orders_detail` VALUES ('8', '4', '2', '3.00', '24.60');
INSERT INTO `orders_detail` VALUES ('9', '4', '3', '3.00', '19.50');
INSERT INTO `orders_detail` VALUES ('10', '5', '2', '3.00', '24.60');
INSERT INTO `orders_detail` VALUES ('11', '5', '3', '3.00', '19.50');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `time` bigint(11) NOT NULL,
  `nickname` varchar(20) DEFAULT '',
  `head_url` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '13590440184', '123456789', '0', '', 'upload/image/12312839sdasd.jpg');
INSERT INTO `user` VALUES ('2', '13156464658', '123456', '0', '', 'upload/image/12312839sdasd.jpg');
INSERT INTO `user` VALUES ('3', '13148700419', '1234567', '1494401572', '', '');
INSERT INTO `user` VALUES ('4', '13590440185', '123456', '0', '', '');

-- ----------------------------
-- Table structure for `user_address`
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `receiver` varchar(20) NOT NULL COMMENT '收件人',
  `default_` tinyint(4) DEFAULT '0',
  `tel` varchar(20) NOT NULL,
  `sex` varchar(20) NOT NULL DEFAULT '先生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES ('1', '1', 'x路x号', '小马', '0', '13590440185', '先生');
INSERT INTO `user_address` VALUES ('2', '2', '2路2号', '小李', '0', '13714515160', '先生');
INSERT INTO `user_address` VALUES ('3', '1', 'xxx', '哈哈', '0', '13113131313', '先生');
INSERT INTO `user_address` VALUES ('4', '1', 'fff', '哈啊', '0', '13848456412', '先生');
INSERT INTO `user_address` VALUES ('5', '1', '行行行行想', '收件人1号', '0', 'teltelteltel', '先生');
INSERT INTO `user_address` VALUES ('6', '1', '行行行行想', '收件人1号', '0', 'teltelteltel', '先生');
INSERT INTO `user_address` VALUES ('8', '4', '', '', '0', '', '先生');
INSERT INTO `user_address` VALUES ('11', '4', '', '', '0', '', '先生');
INSERT INTO `user_address` VALUES ('16', '3', 'xXXCCDD', 'xiaolong', '0', '13148700419', '先生');
INSERT INTO `user_address` VALUES ('18', '3', 'asdfasdf', 'asdfjkasdjf', '1', '13148700419', '先生');

-- ----------------------------
-- View structure for `v_admin`
-- ----------------------------
DROP VIEW IF EXISTS `v_admin`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_admin` AS select `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`time` AS `time`,date_format(from_unixtime(`a`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time` from `admin` `a` ;

-- ----------------------------
-- View structure for `v_garousel`
-- ----------------------------
DROP VIEW IF EXISTS `v_garousel`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_garousel` AS select `g`.`id` AS `id`,`g`.`title` AS `title`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`hyperlink` AS `hyperlink`,`g`.`create_time` AS `create_time`,(select `gc`.`garousel_catalog` from `garousel_catalog` `gc` where (`gc`.`id` = `g`.`catalog_id`)) AS `catalog`,concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) AS `gerousel_url` from `garousel` `g` ;

-- ----------------------------
-- View structure for `v_goods`
-- ----------------------------
DROP VIEW IF EXISTS `v_goods`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods` AS select `g`.`id` AS `goods_id`,`g`.`name` AS `name`,`g`.`price` AS `price`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`description` AS `description`,`g`.`time` AS `time`,date_format(from_unixtime(`g`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,(select `gc`.`catalog` from `goods_catalog` `gc` where (`g`.`catalog_id` = `gc`.`id`)) AS `catalog`,coalesce(`vgc`.`count`,0) AS `count`,coalesce(`ga`.`dis_price`,0) AS `dis_price`,coalesce(`ga`.`start_date`,'') AS `start_date`,coalesce(`ga`.`end_date`,'') AS `end_date`,concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) AS `goods_url`,`g`.`origin` AS `origin` from ((`goods` `g` left join `v_goods_count` `vgc` on((`vgc`.`goods_id` = `g`.`id`))) left join `v_goods_activity` `ga` on((`g`.`id` = `ga`.`goods_id`))) ;

-- ----------------------------
-- View structure for `v_goods_activity`
-- ----------------------------
DROP VIEW IF EXISTS `v_goods_activity`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods_activity` AS select `ga`.`id` AS `id`,`ga`.`goods_id` AS `goods_id`,`ga`.`start_time` AS `start_time`,`ga`.`end_time` AS `end_time`,`ga`.`discount` AS `discount`,date_format(from_unixtime(`ga`.`start_time`),'%Y-%m-%d %H:%i:%S') AS `start_date`,date_format(from_unixtime(`ga`.`end_time`),'%Y-%m-%d %H:%i:%S') AS `end_date`,cast((`ga`.`discount` * `g`.`price`) as decimal(11,2)) AS `dis_price` from (`goods_activity` `ga` join `goods` `g`) where ((unix_timestamp(now()) between `ga`.`start_time` and (`ga`.`end_time` + 86400)) and (`g`.`id` = `ga`.`goods_id`) and ((`ga`.`end_time` + 86400) <> unix_timestamp(now()))) ;

-- ----------------------------
-- View structure for `v_goods_count`
-- ----------------------------
DROP VIEW IF EXISTS `v_goods_count`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods_count` AS select `od`.`goods_id` AS `goods_id`,sum(`od`.`num`) AS `count` from (`orders_detail` `od` join `orders` `o`) where ((`o`.`state` > 1) and (`o`.`id` = `od`.`order_id`)) group by `od`.`goods_id` ;

-- ----------------------------
-- View structure for `v_orders`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders` AS select `o`.`id` AS `id`,`o`.`userid` AS `userid`,`o`.`time` AS `time`,(select sum(`od`.`prices`) from `orders_detail` `od` where (`od`.`order_id` = `o`.`id`) group by `od`.`order_id`) AS `total`,date_format(from_unixtime(`o`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`o`.`state` AS `state`,(case `o`.`state` when 0 then '取消订单' when 1 then '未付款' when 2 then '已付款，未发货' when 3 then '已发货，未签收' else '已签收，订单完成' end) AS `status`,(select concat(`ua`.`receiver`,' ',`ua`.`sex`,' ',`ua`.`tel`,' ',`ua`.`address`) from `user_address` `ua` where (`ua`.`id` = `o`.`address_id`)) AS `address` from `orders` `o` ;

-- ----------------------------
-- View structure for `v_orders_details`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders_details`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders_details` AS select `od`.`id` AS `id`,`od`.`order_id` AS `order_id`,`od`.`goods_id` AS `goods_id`,(select `g`.`name` from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_name`,(select concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_url`,`od`.`num` AS `num`,`od`.`prices` AS `prices` from `orders_detail` `od` ;

-- ----------------------------
-- View structure for `v_user`
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user` AS select `u`.`id` AS `id`,`u`.`phone` AS `phone`,`u`.`password` AS `password`,`u`.`time` AS `time`,`u`.`nickname` AS `nickname`,`u`.`head_url` AS `head_url`,date_format(from_unixtime(`u`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,concat('http://192.168.1.150:8080/TendaEHome/',`u`.`head_url`) AS `url`,coalesce(`ua`.`address`,'') AS `address`,coalesce(`ua`.`id`,0) AS `address_id` from (`user` `u` left join `v_user_address` `ua` on((`ua`.`userid` = `u`.`id`))) ;

-- ----------------------------
-- View structure for `v_user_address`
-- ----------------------------
DROP VIEW IF EXISTS `v_user_address`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_address` AS select `u`.`id` AS `userid`,`ua`.`id` AS `id`,concat(`ua`.`receiver`,' ',`ua`.`sex`,' ',`ua`.`tel`,' ',`ua`.`address`) AS `address` from (`user_address` `ua` join `user` `u`) where ((`u`.`id` = `ua`.`userid`) and (`ua`.`default_` = 1)) ;

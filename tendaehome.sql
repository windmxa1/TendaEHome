/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50543
Source Host           : localhost:3306
Source Database       : tendaehome

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2017-05-02 22:34:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `url` varchar(200) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `description` varchar(10) DEFAULT '',
  `state` int(11) DEFAULT '0' COMMENT '活动状态，0为活动状态，1为非活动状态',
  `discount` decimal(11,2) DEFAULT '0.00' COMMENT '折扣价格',
  `time` bigint(20) NOT NULL,
  `count` decimal(20,0) DEFAULT '0' COMMENT '已售数目',
  `origin` varchar(20) DEFAULT '' COMMENT '源产地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '白菜', '11.00', 'upload/goods/cagg.jpg', '2', ' ', '0', '0.00', '0', '0', '');
INSERT INTO `goods` VALUES ('2', '黄瓜', '10.00', 'upload/goods/kjs.jpg', '2', '', '0', '12.24', '0', '0', '');

-- ----------------------------
-- Table structure for goods_catalog
-- ----------------------------
DROP TABLE IF EXISTS `goods_catalog`;
CREATE TABLE `goods_catalog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `catalog` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_catalog
-- ----------------------------
INSERT INTO `goods_catalog` VALUES ('1', '土豆');
INSERT INTO `goods_catalog` VALUES ('2', '西红柿');
INSERT INTO `goods_catalog` VALUES ('3', '西兰花');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '0未支付，1已支付未发货，2发货未签收，3发货已签收',
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('10', '1', '1493718997', '0', '1');
INSERT INTO `orders` VALUES ('11', '1', '1493719039', '0', '1');
INSERT INTO `orders` VALUES ('12', '1', '1493719049', '0', '1');

-- ----------------------------
-- Table structure for orders_detail
-- ----------------------------
DROP TABLE IF EXISTS `orders_detail`;
CREATE TABLE `orders_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `num` decimal(11,2) NOT NULL,
  `prices` decimal(11,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders_detail
-- ----------------------------
INSERT INTO `orders_detail` VALUES ('8', '10', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('9', '11', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('10', '11', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('11', '12', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('12', '12', '2', '1.00', '1.00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  `nickname` varchar(20) DEFAULT '',
  `head_url` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '13590440185', '123456', '0', '', 'upload/image/12312839sdasd.jpg');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `recevier` varchar(20) NOT NULL COMMENT '收件人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------

-- ----------------------------
-- View structure for v_goods
-- ----------------------------
DROP VIEW IF EXISTS `v_goods`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_goods` AS select `g`.`id` AS `id`,`g`.`name` AS `name`,`g`.`price` AS `price`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`description` AS `description`,`g`.`state` AS `state`,`g`.`discount` AS `discount`,`g`.`time` AS `time`,date_format(from_unixtime(`g`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,(select `gc`.`catalog` from `goods_catalog` `gc` where (`g`.`catalog_id` = `gc`.`id`)) AS `catalog`,`g`.`count` AS `count`,concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) AS `goods_url`,`g`.`origin` AS `origin` from `goods` `g` ; ;

-- ----------------------------
-- View structure for v_orders
-- ----------------------------
DROP VIEW IF EXISTS `v_orders`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_orders` AS select `o`.`id` AS `id`,`o`.`userid` AS `userid`,`o`.`time` AS `time`,date_format(from_unixtime(`o`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`o`.`state` AS `state`,(case `o`.`state` when 0 then '未付款' when 1 then '已付款，未发货' when 2 then '已发货，未签收' when 3 then '已签收，订单完成' else '订单已取消' end) AS `status` from `orders` `o` ; ;

-- ----------------------------
-- View structure for v_orders_details
-- ----------------------------
DROP VIEW IF EXISTS `v_orders_details`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_orders_details` AS SELECT 
od.id,od.order_id,od.goods_id,(select g.name from goods g where od.goods_id=g.id) goods_name,(select concat('http://192.168.1.150:8080/TendaEHome/',g.url) from goods g where od.goods_id=g.id )goods_url,od.num,od.prices from orders_detail od ;

-- ----------------------------
-- View structure for v_user
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_user` AS select `u`.`id` AS `id`,`u`.`phone` AS `phone`,`u`.`password` AS `password`,`u`.`time` AS `time`,`u`.`nickname` AS `nickname`,`u`.`head_url` AS `head_url`,date_format(from_unixtime(`u`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,concat('http://192.168.1.150:8080/TendaEHome/',`u`.`head_url`) AS `url` from `user` `u` ;

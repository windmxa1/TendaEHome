/*
Navicat MySQL Data Transfer

Source Server         : 2_Marshall
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : tendaehome

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2017-05-03 19:58:54
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `goods` VALUES ('1', '白菜', '11.00', 'upload/goods/cabbage.jpg', '2', ' ', '0', '0.00', '0', '0', '');
INSERT INTO `goods` VALUES ('2', '黄瓜', '10.00', 'upload/goods/cucumber.jpg', '2', '', '0', '12.24', '0', '0', '');

-- ----------------------------
-- Table structure for `goods_catalog`
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
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '0未支付，1已支付未发货，2发货未签收，3发货已签收',
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('10', '1', '1493718997', '0', '1');
INSERT INTO `orders` VALUES ('11', '1', '1493719039', '0', '1');
INSERT INTO `orders` VALUES ('12', '1', '1493719049', '0', '1');
INSERT INTO `orders` VALUES ('13', '1', '1493800017', '0', '1');
INSERT INTO `orders` VALUES ('14', '1', '1493800070', '0', '1');
INSERT INTO `orders` VALUES ('15', '1', '1493800276', '0', '1');
INSERT INTO `orders` VALUES ('16', '1', '1493800809', '0', '1');
INSERT INTO `orders` VALUES ('17', '1', '1493800903', '0', '1');
INSERT INTO `orders` VALUES ('18', '1', '1493801011', '0', '1');
INSERT INTO `orders` VALUES ('19', '1', '1493801053', '0', '1');
INSERT INTO `orders` VALUES ('20', '1', '1493801244', '0', '1');
INSERT INTO `orders` VALUES ('21', '1', '1493801314', '0', '1');
INSERT INTO `orders` VALUES ('22', '1', '1493801353', '0', '1');
INSERT INTO `orders` VALUES ('23', '1', '1493801590', '0', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders_detail
-- ----------------------------
INSERT INTO `orders_detail` VALUES ('8', '10', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('9', '11', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('10', '11', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('11', '12', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('12', '12', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('13', '13', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('14', '13', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('15', '14', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('16', '14', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('17', '15', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('18', '15', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('19', '16', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('20', '16', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('21', '17', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('22', '17', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('23', '18', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('24', '18', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('25', '19', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('26', '19', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('27', '20', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('28', '20', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('29', '21', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('30', '21', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('31', '22', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('32', '22', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('33', '23', '1', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('34', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('35', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('36', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('37', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('38', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('39', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('40', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('41', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('42', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('43', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('44', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('45', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('46', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('47', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('48', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('49', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('50', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('51', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('52', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('53', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('54', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('55', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('56', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('57', '23', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('58', '23', '2', '12.00', '57.60');

-- ----------------------------
-- Table structure for `user`
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
-- Table structure for `user_address`
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `receiver` varchar(20) NOT NULL COMMENT '收件人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES ('1', '1', 'xx路xx号', '小马');

-- ----------------------------
-- View structure for `v_goods`
-- ----------------------------
DROP VIEW IF EXISTS `v_goods`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods` AS select `g`.`id` AS `goods_id`,`g`.`name` AS `name`,`g`.`price` AS `price`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`description` AS `description`,`g`.`state` AS `state`,`g`.`discount` AS `discount`,`g`.`time` AS `time`,date_format(from_unixtime(`g`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,(select `gc`.`catalog` from `goods_catalog` `gc` where (`g`.`catalog_id` = `gc`.`id`)) AS `catalog`,`g`.`count` AS `count`,concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) AS `goods_url`,`g`.`origin` AS `origin` from `goods` `g` ;

-- ----------------------------
-- View structure for `v_orders`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders` AS select `o`.`id` AS `id`,`o`.`userid` AS `userid`,`o`.`time` AS `time`,date_format(from_unixtime(`o`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`o`.`state` AS `state`,(case `o`.`state` when 0 then '未付款' when 1 then '已付款，未发货' when 2 then '已发货，未签收' else '已签收，订单完成' end) AS `status`,(select concat(`ua`.`receiver`,' ',`ua`.`address`) from `user_address` `ua` where (`ua`.`userid` = `o`.`userid`)) AS `address` from `orders` `o` ;

-- ----------------------------
-- View structure for `v_orders_details`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders_details`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders_details` AS select `od`.`id` AS `id`,`od`.`order_id` AS `order_id`,`od`.`goods_id` AS `goods_id`,(select `g`.`name` from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_name`,(select concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_url`,`od`.`num` AS `num`,`od`.`prices` AS `prices` from `orders_detail` `od` ;

-- ----------------------------
-- View structure for `v_user`
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user` AS select `u`.`id` AS `id`,`u`.`phone` AS `phone`,`u`.`password` AS `password`,`u`.`time` AS `time`,`u`.`nickname` AS `nickname`,`u`.`head_url` AS `head_url`,date_format(from_unixtime(`u`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,concat('http://192.168.1.150:8080/TendaEHome/',`u`.`head_url`) AS `url` from `user` `u` ;

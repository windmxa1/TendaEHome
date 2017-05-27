/*
Navicat MySQL Data Transfer

Source Server         : 2_Marshall
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : tendaehome

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2017-05-27 19:03:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `time` bigint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1', '12', '1478000000');
INSERT INTO `admin` VALUES ('2', '12', '12', '1495847591');
INSERT INTO `admin` VALUES ('3', 'qqq', '202cb962ac59075b964b07152d234b70', '1495873888');
INSERT INTO `admin` VALUES ('4', '111', '698d51a19d8a121ce581499d7b701668', '1495873953');
INSERT INTO `admin` VALUES ('5', 'tb', 'e44d967f3e8a44f6a7fee562af4d82f4', '1495874097');
INSERT INTO `admin` VALUES ('6', '22', 'b6d767d2f8ed5d21a44b0e5886680cb9', '1495874319');

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
  `time` bigint(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of garousel
-- ----------------------------
INSERT INTO `garousel` VALUES ('1', '送菜上门服务', 'upload/garousel/1.jpg', '1', '', '0');
INSERT INTO `garousel` VALUES ('2', '电视直播服务', 'upload/garousel/2.png', '1', '', '0');
INSERT INTO `garousel` VALUES ('5', '家居保修服务', 'upload/garousel/3.jpg', '1', '', '0');
INSERT INTO `garousel` VALUES ('6', '送货上门', 'upload/garousel/g2.png', '2', '', '0');
INSERT INTO `garousel` VALUES ('7', '买的健康', 'upload/garousel/g5.png', '2', '', '0');
INSERT INTO `garousel` VALUES ('8', '吃的放心', 'upload/garousel/g6.png', '2', '', '0');
INSERT INTO `garousel` VALUES ('9', '健康蔬菜', 'upload/garousel/psb.png', '2', '', '0');

-- ----------------------------
-- Table structure for `garousel_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `garousel_catalog`;
CREATE TABLE `garousel_catalog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `garousel_catalog` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of garousel_catalog
-- ----------------------------
INSERT INTO `garousel_catalog` VALUES ('1', '首页轮播图');
INSERT INTO `garousel_catalog` VALUES ('2', '一米菜园轮播图');
INSERT INTO `garousel_catalog` VALUES ('3', '啊实打asdasdf');
INSERT INTO `garousel_catalog` VALUES ('5', 'asdas');
INSERT INTO `garousel_catalog` VALUES ('6', '啊实打实');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '白菜', '5.40', 'upload/goods/cabbage.jpg', '5', ' ', '0', '');
INSERT INTO `goods` VALUES ('2', '黄瓜', '8.20', 'upload/goods/cucumber.jpg', '7', '', '0', '');
INSERT INTO `goods` VALUES ('3', '西红柿', '6.50', 'upload/goods/tomato.jpg', '2', ' ', '0', '');
INSERT INTO `goods` VALUES ('4', '土豆', '5.56', 'upload/goods/sweetpotato.jpg', '1', '', '0', '');
INSERT INTO `goods` VALUES ('5', '西兰花', '6.20', 'upload/goods/Broccoli.jpg', '3', '', '0', '');
INSERT INTO `goods` VALUES ('6', '胡萝卜', '6.80', 'upload/goods/carrot.jpg', '4', '', '0', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_catalog
-- ----------------------------
INSERT INTO `goods_catalog` VALUES ('1', '土豆');
INSERT INTO `goods_catalog` VALUES ('2', '西红柿');
INSERT INTO `goods_catalog` VALUES ('3', '西兰花');
INSERT INTO `goods_catalog` VALUES ('4', '胡萝卜');
INSERT INTO `goods_catalog` VALUES ('5', '白菜');
INSERT INTO `goods_catalog` VALUES ('6', '绿豆');
INSERT INTO `goods_catalog` VALUES ('7', '黄瓜');
INSERT INTO `goods_catalog` VALUES ('8', '玉米');
INSERT INTO `goods_catalog` VALUES ('9', '葱');
INSERT INTO `goods_catalog` VALUES ('10', '生菜');

-- ----------------------------
-- Table structure for `logs`
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `operation` varchar(50) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `time` bigint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for `operation`
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `url` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation
-- ----------------------------

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
  `order_num` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '1494135144', '2', '1', '0132');
INSERT INTO `orders` VALUES ('2', '1', '1494136308', '3', '2', '1465');
INSERT INTO `orders` VALUES ('3', '1', '1494314099', '0', '1', '2456');
INSERT INTO `orders` VALUES ('4', '1', '1494314196', '1', '1', '3654');
INSERT INTO `orders` VALUES ('5', '1', '1494314516', '1', '1', '478');
INSERT INTO `orders` VALUES ('6', '1', '1495004541', '0', '1', '5453');
INSERT INTO `orders` VALUES ('7', '1', '1495004544', '0', '1', '6345');
INSERT INTO `orders` VALUES ('8', '1', '1495004609', '0', '1', '745');
INSERT INTO `orders` VALUES ('9', '1', '1495004611', '0', '1', '8654');
INSERT INTO `orders` VALUES ('10', '1', '1495004722', '0', '1', '978');
INSERT INTO `orders` VALUES ('11', '1', '1495006569', '0', '1', '10456');
INSERT INTO `orders` VALUES ('12', '1', '1495006571', '0', '1', '123');
INSERT INTO `orders` VALUES ('13', '1', '1495006861', '0', '1', '1278');
INSERT INTO `orders` VALUES ('14', '1', '1495006863', '0', '1', '12345');
INSERT INTO `orders` VALUES ('15', '1', '1495006882', '0', '1', '45678');
INSERT INTO `orders` VALUES ('16', '1', '1495006884', '0', '1', '456487');
INSERT INTO `orders` VALUES ('17', '1', '1495691220', '0', '1', '020215151');
INSERT INTO `orders` VALUES ('18', '1', '1495691224', '0', '1', '051516160320');
INSERT INTO `orders` VALUES ('19', '4', '1495780669', '0', '1', '1495780669655519885');
INSERT INTO `orders` VALUES ('20', '4', '1495789832', '0', '1', '1495789832969234551');
INSERT INTO `orders` VALUES ('21', '4', '1495791147', '0', '1', '1495791147603866267');
INSERT INTO `orders` VALUES ('22', '4', '1495791549', '0', '1', '1495791549934782018');

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
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;

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
INSERT INTO `orders_detail` VALUES ('12', '6', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('13', '6', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('14', '7', '1', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('15', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('16', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('17', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('18', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('19', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('20', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('21', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('22', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('23', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('24', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('25', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('26', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('27', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('28', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('29', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('30', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('31', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('32', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('33', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('34', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('35', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('36', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('37', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('38', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('39', '7', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('40', '8', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('41', '8', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('42', '9', '1', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('43', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('44', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('45', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('46', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('47', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('48', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('49', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('50', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('51', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('52', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('53', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('54', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('55', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('56', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('57', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('58', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('59', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('60', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('61', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('62', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('63', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('64', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('65', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('66', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('67', '9', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('68', '10', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('69', '10', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('70', '11', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('71', '11', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('72', '12', '1', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('73', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('74', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('75', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('76', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('77', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('78', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('79', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('80', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('81', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('82', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('83', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('84', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('85', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('86', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('87', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('88', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('89', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('90', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('91', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('92', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('93', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('94', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('95', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('96', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('97', '12', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('98', '13', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('99', '13', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('100', '14', '1', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('101', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('102', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('103', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('104', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('105', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('106', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('107', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('108', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('109', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('110', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('111', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('112', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('113', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('114', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('115', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('116', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('117', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('118', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('119', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('120', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('121', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('122', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('123', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('124', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('125', '14', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('126', '15', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('127', '15', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('128', '16', '1', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('129', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('130', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('131', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('132', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('133', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('134', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('135', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('136', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('137', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('138', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('139', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('140', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('141', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('142', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('143', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('144', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('145', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('146', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('147', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('148', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('149', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('150', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('151', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('152', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('153', '16', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('154', '17', '1', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('155', '17', '2', '1.00', '1.00');
INSERT INTO `orders_detail` VALUES ('156', '18', '1', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('157', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('158', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('159', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('160', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('161', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('162', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('163', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('164', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('165', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('166', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('167', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('168', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('169', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('170', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('171', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('172', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('173', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('174', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('175', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('176', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('177', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('178', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('179', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('180', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('181', '18', '2', '12.00', '57.60');
INSERT INTO `orders_detail` VALUES ('182', '19', '1', '3.00', '16.20');
INSERT INTO `orders_detail` VALUES ('183', '19', '2', '2.00', '16.40');
INSERT INTO `orders_detail` VALUES ('184', '20', '1', '3.00', '16.20');
INSERT INTO `orders_detail` VALUES ('185', '20', '2', '2.00', '16.40');
INSERT INTO `orders_detail` VALUES ('186', '21', '1', '3.00', '16.20');
INSERT INTO `orders_detail` VALUES ('187', '21', '2', '2.00', '16.40');
INSERT INTO `orders_detail` VALUES ('188', '22', '1', '3.00', '16.20');
INSERT INTO `orders_detail` VALUES ('189', '22', '2', '2.00', '16.40');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `permission_operation`
-- ----------------------------
DROP TABLE IF EXISTS `permission_operation`;
CREATE TABLE `permission_operation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) NOT NULL,
  `operation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_operation
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `tv`
-- ----------------------------
DROP TABLE IF EXISTS `tv`;
CREATE TABLE `tv` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `img_url` varchar(200) NOT NULL,
  `tv_url` varchar(200) NOT NULL,
  `time` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tv
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `password` varchar(200) NOT NULL,
  `time` bigint(11) NOT NULL,
  `nickname` varchar(20) DEFAULT '',
  `head_url` varchar(200) DEFAULT 'upload/headimg/user.png',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '13590440184', '123456789', '0', '', 'upload/headimg/user.png');
INSERT INTO `user` VALUES ('2', '13156464658', '123456', '0', '', 'upload/headimg/user.png');
INSERT INTO `user` VALUES ('3', '13148700419', '1234567', '1494401572', '', 'upload/headimg/headimg.jpg');
INSERT INTO `user` VALUES ('4', '13590440185', '123456', '0', '', 'upload/headimg/user.png');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

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
INSERT INTO `user_address` VALUES ('16', '3', '深圳市顺兴工业区', 'xiaolong199489899', '1', '13148700419', '先生');
INSERT INTO `user_address` VALUES ('18', '3', 'asdfa99090', 'asdfj456464564', '0', '13148700419', '先生');
INSERT INTO `user_address` VALUES ('19', '3', 'ooooxxxxxiiiiijkkk', 'llloo', '0', '13148700419', '先生');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------

-- ----------------------------
-- View structure for `v_admin`
-- ----------------------------
DROP VIEW IF EXISTS `v_admin`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_admin` AS select `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`time` AS `time`,date_format(from_unixtime(`a`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time` from `admin` `a` ;

-- ----------------------------
-- View structure for `v_garousel`
-- ----------------------------
DROP VIEW IF EXISTS `v_garousel`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_garousel` AS select `g`.`id` AS `id`,`g`.`title` AS `title`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`hyperlink` AS `hyperlink`,`g`.`time` AS `time`,(select `gc`.`garousel_catalog` from `garousel_catalog` `gc` where (`gc`.`id` = `g`.`catalog_id`)) AS `catalog`,concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) AS `gerousel_url`,date_format(from_unixtime(`g`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time` from `garousel` `g` ;

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
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders` AS select `o`.`id` AS `id`,`o`.`userid` AS `userid`,`o`.`time` AS `time`,`o`.`order_num` AS `order_num`,(select sum(`od`.`prices`) from `orders_detail` `od` where (`od`.`order_id` = `o`.`id`) group by `od`.`order_id`) AS `total`,date_format(from_unixtime(`o`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`o`.`state` AS `state`,(case `o`.`state` when 0 then '取消订单' when 1 then '未付款' when 2 then '已付款，未发货' when 3 then '已发货，未签收' else '已签收，订单完成' end) AS `status`,(select concat(`ua`.`receiver`,' ',`ua`.`sex`,' ',`ua`.`tel`,' ',`ua`.`address`) from `user_address` `ua` where (`ua`.`id` = `o`.`address_id`)) AS `address` from `orders` `o` ;

-- ----------------------------
-- View structure for `v_orders_details`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders_details`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders_details` AS select `od`.`id` AS `id`,`od`.`order_id` AS `order_id`,`od`.`goods_id` AS `goods_id`,(select `g`.`name` from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_name`,(select concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_url`,`od`.`num` AS `num`,`od`.`prices` AS `prices` from `orders_detail` `od` ;

-- ----------------------------
-- View structure for `v_tv`
-- ----------------------------
DROP VIEW IF EXISTS `v_tv`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_tv` AS select `t`.`id` AS `id`,`t`.`name` AS `name`,`t`.`img_url` AS `img_url`,`t`.`tv_url` AS `tv_url`,`t`.`time` AS `time`,date_format(from_unixtime(`t`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time` from `tv` `t` ;

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

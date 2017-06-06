/*
Navicat MySQL Data Transfer

Source Server         : 2_Marshall
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : tendaehome

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2017-06-05 17:00:28
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
INSERT INTO `admin` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1478000000');
INSERT INTO `admin` VALUES ('2', '12', 'e10adc3949ba59abbe56e057f20f883e', '1495847591');
INSERT INTO `admin` VALUES ('3', 'qqq', '202cb962ac59075b964b07152d234b70', '1495873888');
INSERT INTO `admin` VALUES ('4', '111', '698d51a19d8a121ce581499d7b701668', '1495873953');
INSERT INTO `admin` VALUES ('5', 'tb', 'e44d967f3e8a44f6a7fee562af4d82f4', '1495874097');
INSERT INTO `admin` VALUES ('6', '22', 'b6d767d2f8ed5d21a44b0e5886680cb9', '1495874319');

-- ----------------------------
-- Table structure for `admin_log`
-- ----------------------------
DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of garousel_catalog
-- ----------------------------
INSERT INTO `garousel_catalog` VALUES ('1', '首页轮播图');
INSERT INTO `garousel_catalog` VALUES ('2', '一米菜园轮播图');
INSERT INTO `garousel_catalog` VALUES ('3', '啊实打asdasdf');
INSERT INTO `garousel_catalog` VALUES ('5', 'asdas');
INSERT INTO `garousel_catalog` VALUES ('6', '啊实打实');
INSERT INTO `garousel_catalog` VALUES ('7', '啊实打实');

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
  `state` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '白菜', '5.60', 'upload/goods/5/cabbage.jpg', '5', ' ', '1496652467', '', '1');
INSERT INTO `goods` VALUES ('2', '黄瓜', '8.30', 'upload/goods/7/cucumber.jpg', '7', '', '1496652490', '', '1');
INSERT INTO `goods` VALUES ('3', '西红柿', '6.50', 'upload/goods/2/tomato.jpg', '2', ' ', '1496652470', '', '1');
INSERT INTO `goods` VALUES ('4', '土豆', '5.56', 'upload/goods/1/sweetpotato.jpg', '1', '', '1496652480', '', '1');
INSERT INTO `goods` VALUES ('5', '西兰花', '6.20', 'upload/goods/3/Broccoli.jpg', '3', '', '1496652484', '', '1');
INSERT INTO `goods` VALUES ('6', '胡萝卜', '6.80', 'upload/goods/4/carrot.jpg', '4', '', '1496652473', '', '1');
INSERT INTO `goods` VALUES ('8', '玉米', '8.20', 'upload/goods/8/corn.jpg', '8', '', '1496652495', '', '1');
INSERT INTO `goods` VALUES ('12', '阿斯达', '12.23', 'upload/goods/8/1496645951_223949062156597456.png', '8', '', '1496645951', '', '0');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `goods_catalog`;
CREATE TABLE `goods_catalog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `catalog` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

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
INSERT INTO `goods_catalog` VALUES ('11', '菠菜');
INSERT INTO `goods_catalog` VALUES ('12', '冬瓜');

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
  `state` int(11) DEFAULT '1' COMMENT '0取消订单,1未支付，2已支付未发货，3发货未签收，4发货已签收',
  `address_id` bigint(20) NOT NULL,
  `order_num` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '1494135144', '2', '1', '0132');
INSERT INTO `orders` VALUES ('2', '1', '1494136308', '4', '2', '1465');
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
INSERT INTO `orders` VALUES ('18', '1', '1495691224', '1', '1', '051516160320');
INSERT INTO `orders` VALUES ('19', '4', '1495780669', '1', '1', '1495780669655519885');
INSERT INTO `orders` VALUES ('20', '4', '1495789832', '1', '1', '1495789832969234551');
INSERT INTO `orders` VALUES ('21', '4', '1495791147', '1', '1', '1495791147603866267');
INSERT INTO `orders` VALUES ('22', '4', '1495791549', '1', '1', '1495791549934782018');
INSERT INTO `orders` VALUES ('30', '3', '1496369854', '1', '18', '1496369854702471820');
INSERT INTO `orders` VALUES ('33', '3', '1496370803', '1', '18', '1496370803985423681');
INSERT INTO `orders` VALUES ('34', '3', '1496374521', '1', '18', '1496374521391478921');
INSERT INTO `orders` VALUES ('35', '3', '1496374554', '1', '18', '1496374554487534858');
INSERT INTO `orders` VALUES ('36', '3', '1496374596', '1', '18', '1496374596618800393');
INSERT INTO `orders` VALUES ('37', '3', '1496375165', '1', '18', '1496375165613236062');
INSERT INTO `orders` VALUES ('38', '3', '1496375409', '1', '18', '1496375409584290550');
INSERT INTO `orders` VALUES ('39', '3', '1496375698', '1', '18', '1496375698701201888');
INSERT INTO `orders` VALUES ('40', '3', '1496383158', '1', '18', '1496383158410333427');
INSERT INTO `orders` VALUES ('41', '3', '1496383176', '1', '18', '1496383176968674049');
INSERT INTO `orders` VALUES ('42', '3', '1496384728', '1', '18', '1496384728821181971');
INSERT INTO `orders` VALUES ('43', '3', '1496386215', '3', '18', '1496386215762457787');
INSERT INTO `orders` VALUES ('44', '3', '1496390418', '4', '18', '1496390418982872902');
INSERT INTO `orders` VALUES ('46', '3', '1496398094', '0', '18', '1496398094543518735');
INSERT INTO `orders` VALUES ('48', '3', '1496636514', '0', '18', '1496636514532731979');
INSERT INTO `orders` VALUES ('49', '3', '1496647822', '0', '18', '1496647822194333968');

-- ----------------------------
-- Table structure for `orders_detail`
-- ----------------------------
DROP TABLE IF EXISTS `orders_detail`;
CREATE TABLE `orders_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders_detail
-- ----------------------------
INSERT INTO `orders_detail` VALUES ('1', '1', '1', '4');
INSERT INTO `orders_detail` VALUES ('2', '1', '2', '12');
INSERT INTO `orders_detail` VALUES ('3', '2', '1', '76');
INSERT INTO `orders_detail` VALUES ('4', '2', '2', '5');
INSERT INTO `orders_detail` VALUES ('5', '2', '3', '55');
INSERT INTO `orders_detail` VALUES ('6', '3', '2', '3');
INSERT INTO `orders_detail` VALUES ('7', '3', '3', '3');
INSERT INTO `orders_detail` VALUES ('8', '4', '2', '3');
INSERT INTO `orders_detail` VALUES ('9', '4', '3', '3');
INSERT INTO `orders_detail` VALUES ('10', '5', '2', '3');
INSERT INTO `orders_detail` VALUES ('11', '5', '3', '3');
INSERT INTO `orders_detail` VALUES ('12', '6', '1', '1');
INSERT INTO `orders_detail` VALUES ('13', '6', '2', '1');
INSERT INTO `orders_detail` VALUES ('14', '7', '1', '12');
INSERT INTO `orders_detail` VALUES ('15', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('16', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('17', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('18', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('19', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('20', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('21', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('22', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('23', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('24', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('25', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('26', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('27', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('28', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('29', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('30', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('31', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('32', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('33', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('34', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('35', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('36', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('37', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('38', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('39', '7', '2', '12');
INSERT INTO `orders_detail` VALUES ('40', '8', '1', '1');
INSERT INTO `orders_detail` VALUES ('41', '8', '2', '1');
INSERT INTO `orders_detail` VALUES ('42', '9', '1', '12');
INSERT INTO `orders_detail` VALUES ('43', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('44', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('45', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('46', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('47', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('48', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('49', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('50', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('51', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('52', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('53', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('54', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('55', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('56', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('57', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('58', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('59', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('60', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('61', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('62', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('63', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('64', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('65', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('66', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('67', '9', '2', '12');
INSERT INTO `orders_detail` VALUES ('68', '10', '1', '1');
INSERT INTO `orders_detail` VALUES ('69', '10', '2', '1');
INSERT INTO `orders_detail` VALUES ('70', '11', '1', '1');
INSERT INTO `orders_detail` VALUES ('71', '11', '2', '1');
INSERT INTO `orders_detail` VALUES ('72', '12', '1', '12');
INSERT INTO `orders_detail` VALUES ('73', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('74', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('75', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('76', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('77', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('78', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('79', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('80', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('81', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('82', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('83', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('84', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('85', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('86', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('87', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('88', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('89', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('90', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('91', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('92', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('93', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('94', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('95', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('96', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('97', '12', '2', '12');
INSERT INTO `orders_detail` VALUES ('98', '13', '1', '1');
INSERT INTO `orders_detail` VALUES ('99', '13', '2', '1');
INSERT INTO `orders_detail` VALUES ('100', '14', '1', '12');
INSERT INTO `orders_detail` VALUES ('101', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('102', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('103', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('104', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('105', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('106', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('107', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('108', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('109', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('110', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('111', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('112', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('113', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('114', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('115', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('116', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('117', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('118', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('119', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('120', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('121', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('122', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('123', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('124', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('125', '14', '2', '12');
INSERT INTO `orders_detail` VALUES ('126', '15', '1', '1');
INSERT INTO `orders_detail` VALUES ('127', '15', '2', '1');
INSERT INTO `orders_detail` VALUES ('128', '16', '1', '12');
INSERT INTO `orders_detail` VALUES ('129', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('130', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('131', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('132', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('133', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('134', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('135', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('136', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('137', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('138', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('139', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('140', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('141', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('142', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('143', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('144', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('145', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('146', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('147', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('148', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('149', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('150', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('151', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('152', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('153', '16', '2', '12');
INSERT INTO `orders_detail` VALUES ('154', '17', '1', '1');
INSERT INTO `orders_detail` VALUES ('155', '17', '2', '1');
INSERT INTO `orders_detail` VALUES ('156', '18', '1', '12');
INSERT INTO `orders_detail` VALUES ('157', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('158', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('159', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('160', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('161', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('162', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('163', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('164', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('165', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('166', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('167', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('168', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('169', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('170', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('171', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('172', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('173', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('174', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('175', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('176', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('177', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('178', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('179', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('180', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('181', '18', '2', '12');
INSERT INTO `orders_detail` VALUES ('182', '19', '1', '3');
INSERT INTO `orders_detail` VALUES ('183', '19', '2', '2');
INSERT INTO `orders_detail` VALUES ('184', '20', '1', '3');
INSERT INTO `orders_detail` VALUES ('185', '20', '2', '2');
INSERT INTO `orders_detail` VALUES ('186', '21', '1', '3');
INSERT INTO `orders_detail` VALUES ('187', '21', '2', '2');
INSERT INTO `orders_detail` VALUES ('188', '22', '1', '3');
INSERT INTO `orders_detail` VALUES ('189', '22', '2', '2');
INSERT INTO `orders_detail` VALUES ('208', '30', '6', '2');
INSERT INTO `orders_detail` VALUES ('209', '30', '5', '1');
INSERT INTO `orders_detail` VALUES ('210', '30', '4', '1');
INSERT INTO `orders_detail` VALUES ('217', '33', '6', '2');
INSERT INTO `orders_detail` VALUES ('218', '33', '5', '1');
INSERT INTO `orders_detail` VALUES ('219', '33', '4', '1');
INSERT INTO `orders_detail` VALUES ('220', '34', '6', '2');
INSERT INTO `orders_detail` VALUES ('221', '34', '5', '1');
INSERT INTO `orders_detail` VALUES ('222', '34', '4', '1');
INSERT INTO `orders_detail` VALUES ('223', '35', '6', '2');
INSERT INTO `orders_detail` VALUES ('224', '35', '5', '1');
INSERT INTO `orders_detail` VALUES ('225', '35', '4', '1');
INSERT INTO `orders_detail` VALUES ('226', '36', '6', '2');
INSERT INTO `orders_detail` VALUES ('227', '36', '5', '1');
INSERT INTO `orders_detail` VALUES ('228', '36', '4', '1');
INSERT INTO `orders_detail` VALUES ('229', '37', '6', '2');
INSERT INTO `orders_detail` VALUES ('230', '37', '5', '1');
INSERT INTO `orders_detail` VALUES ('231', '37', '4', '1');
INSERT INTO `orders_detail` VALUES ('232', '38', '6', '2');
INSERT INTO `orders_detail` VALUES ('233', '38', '5', '1');
INSERT INTO `orders_detail` VALUES ('234', '38', '4', '1');
INSERT INTO `orders_detail` VALUES ('235', '39', '6', '2');
INSERT INTO `orders_detail` VALUES ('236', '39', '5', '1');
INSERT INTO `orders_detail` VALUES ('237', '39', '4', '1');
INSERT INTO `orders_detail` VALUES ('238', '40', '6', '3');
INSERT INTO `orders_detail` VALUES ('239', '40', '5', '1');
INSERT INTO `orders_detail` VALUES ('240', '40', '4', '1');
INSERT INTO `orders_detail` VALUES ('241', '41', '6', '3');
INSERT INTO `orders_detail` VALUES ('242', '41', '5', '1');
INSERT INTO `orders_detail` VALUES ('243', '41', '4', '1');
INSERT INTO `orders_detail` VALUES ('244', '42', '4', '5');
INSERT INTO `orders_detail` VALUES ('245', '42', '6', '9');
INSERT INTO `orders_detail` VALUES ('246', '42', '2', '1');
INSERT INTO `orders_detail` VALUES ('247', '42', '5', '1');
INSERT INTO `orders_detail` VALUES ('248', '43', '6', '2');
INSERT INTO `orders_detail` VALUES ('249', '43', '5', '2');
INSERT INTO `orders_detail` VALUES ('250', '43', '4', '3');
INSERT INTO `orders_detail` VALUES ('251', '43', '2', '1');
INSERT INTO `orders_detail` VALUES ('252', '43', '1', '1');
INSERT INTO `orders_detail` VALUES ('253', '43', '3', '1');
INSERT INTO `orders_detail` VALUES ('254', '44', '6', '3');
INSERT INTO `orders_detail` VALUES ('255', '44', '4', '1');
INSERT INTO `orders_detail` VALUES ('256', '44', '3', '2');
INSERT INTO `orders_detail` VALUES ('260', '46', '6', '1');
INSERT INTO `orders_detail` VALUES ('264', '48', '6', '1');
INSERT INTO `orders_detail` VALUES ('265', '48', '4', '1');
INSERT INTO `orders_detail` VALUES ('266', '49', '6', '1');
INSERT INTO `orders_detail` VALUES ('267', '49', '5', '3');

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
INSERT INTO `user` VALUES ('1', '13590440184', '123456789', '0', '', 'upload/headimg/headimg.jpg');
INSERT INTO `user` VALUES ('2', '13156464658', '123456', '0', '', 'upload/headimg/headimg.jpg');
INSERT INTO `user` VALUES ('3', '13148700419', '1234567', '1494401572', '', 'upload/headimg/headimg.jpg');
INSERT INTO `user` VALUES ('4', '13590440185', '123456', '0', '', 'upload/headimg/headimg.jpg');

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
INSERT INTO `user_address` VALUES ('16', '3', '深圳市顺兴工业区', '小龙', '0', '13148700419', '先生');
INSERT INTO `user_address` VALUES ('18', '3', 'asdfa99090', '小李', '0', '13148700419', '先生');
INSERT INTO `user_address` VALUES ('19', '3', 'ooooxxxxxiiiiijkkk', '小黑', '1', '13148700419', '先生');

-- ----------------------------
-- Table structure for `user_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `user_feedback`;
CREATE TABLE `user_feedback` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(100) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `time` bigint(11) NOT NULL,
  `read` tinyint(4) DEFAULT '0' COMMENT '已读状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_feedback
-- ----------------------------
INSERT INTO `user_feedback` VALUES ('1', 'asdasfafdgsadg', '4', '1496215066', '0');
INSERT INTO `user_feedback` VALUES ('2', 'asdasfafdgsadg', '2', '1496216465', '1');
INSERT INTO `user_feedback` VALUES ('3', 'asdasfafdgsadg', '4', '1496217327', '0');
INSERT INTO `user_feedback` VALUES ('4', 'asdasfafdgsadg', '4', '1496217505', '0');
INSERT INTO `user_feedback` VALUES ('5', 'asdasfafdgsadg', '4', '1496217584', '0');

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
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_goods` AS select `g`.`id` AS `goods_id`,`g`.`name` AS `name`,`g`.`price` AS `price`,`g`.`url` AS `url`,`g`.`catalog_id` AS `catalog_id`,`g`.`description` AS `description`,`g`.`time` AS `time`,`g`.`state` AS `state`,date_format(from_unixtime(`g`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,(select `gc`.`catalog` from `goods_catalog` `gc` where (`g`.`catalog_id` = `gc`.`id`)) AS `catalog`,coalesce(`vgc`.`count`,0) AS `count`,coalesce(`ga`.`dis_price`,0) AS `dis_price`,coalesce(`ga`.`start_date`,'') AS `start_date`,coalesce(`ga`.`end_date`,'') AS `end_date`,concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) AS `goods_url`,`g`.`origin` AS `origin` from ((`goods` `g` left join `v_goods_count` `vgc` on((`vgc`.`goods_id` = `g`.`id`))) left join `v_goods_activity` `ga` on((`g`.`id` = `ga`.`goods_id`))) ;

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
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders` AS select `o`.`id` AS `id`,`o`.`userid` AS `userid`,`o`.`time` AS `time`,(select `vot`.`total` from `v_order_total` `vot` where (`vot`.`order_id` = `o`.`id`)) AS `total`,date_format(from_unixtime(`o`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`o`.`state` AS `state`,(case `o`.`state` when 0 then '取消订单' when 1 then '未付款' when 2 then '已付款，未发货' when 3 then '已发货，未签收' else '已签收，订单完成' end) AS `status`,(select concat(`ua`.`receiver`,' ',`ua`.`sex`,' ',`ua`.`tel`,' ',`ua`.`address`) from `user_address` `ua` where (`ua`.`id` = `o`.`address_id`)) AS `address`,`o`.`address_id` AS `address_id`,`o`.`order_num` AS `order_num` from `orders` `o` ;

-- ----------------------------
-- View structure for `v_orders_details`
-- ----------------------------
DROP VIEW IF EXISTS `v_orders_details`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_orders_details` AS select `od`.`id` AS `id`,`od`.`order_id` AS `order_id`,(select `o`.`order_num` from `orders` `o` where (`o`.`id` = `od`.`order_id`)) AS `order_num`,`od`.`goods_id` AS `goods_id`,(select `g`.`name` from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_name`,(select concat('http://192.168.1.150:8080/TendaEHome/',`g`.`url`) from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) AS `goods_url`,`od`.`num` AS `num`,cast((select (`od`.`num` * `g`.`price`) from `goods` `g` where (`od`.`goods_id` = `g`.`id`)) as decimal(11,2)) AS `prices` from `orders_detail` `od` ;

-- ----------------------------
-- View structure for `v_order_total`
-- ----------------------------
DROP VIEW IF EXISTS `v_order_total`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_order_total` AS select `od`.`order_id` AS `order_id`,cast(sum((`g`.`price` * `od`.`num`)) as decimal(11,2)) AS `total` from (`goods` `g` join `orders_detail` `od`) where (`g`.`id` = `od`.`goods_id`) group by `od`.`order_id` ;

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

-- ----------------------------
-- View structure for `v_user_feedback`
-- ----------------------------
DROP VIEW IF EXISTS `v_user_feedback`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_feedback` AS select `uf`.`id` AS `id`,`uf`.`message` AS `message`,`uf`.`user_id` AS `user_id`,`uf`.`time` AS `time`,`uf`.`read` AS `read`,date_format(from_unixtime(`uf`.`time`),'%Y-%m-%d %H:%i:%S') AS `create_time`,`u`.`phone` AS `phone` from (`user_feedback` `uf` left join `user` `u` on((`u`.`id` = `uf`.`user_id`))) ;

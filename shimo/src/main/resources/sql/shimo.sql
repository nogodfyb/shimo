/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : shimo

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-06-27 22:14:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for graphite_disc
-- ----------------------------
DROP TABLE IF EXISTS `graphite_disc`;
CREATE TABLE `graphite_disc` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` int(10) DEFAULT NULL COMMENT '编号',
  `feng_zhuang` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '封装类型',
  `is_used` tinyint(1) DEFAULT NULL COMMENT '(启用1，报废0)',
  `abandoned_reason` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '废弃原因',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `abandoned_time` datetime DEFAULT NULL COMMENT '废弃时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codeIndex` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of graphite_disc
-- ----------------------------
INSERT INTO `graphite_disc` VALUES ('21', '1', 'SMB-FL', '0', '11111111', '2020-06-26 23:20:54', '2020-06-27 08:32:52');
INSERT INTO `graphite_disc` VALUES ('22', '2', 'SOD323HE', '0', 'asfaafsa', '2020-06-26 23:21:21', '2020-06-27 09:34:59');
INSERT INTO `graphite_disc` VALUES ('23', '3', 'SMA-FL', '0', 'asfafsafaf', '2020-06-26 23:21:26', '2020-06-27 09:35:03');
INSERT INTO `graphite_disc` VALUES ('24', '4', 'SOD323HE', '1', null, '2020-06-27 09:07:36', null);
INSERT INTO `graphite_disc` VALUES ('25', '5', 'SMA-FL', '1', null, '2020-06-27 09:07:41', null);
INSERT INTO `graphite_disc` VALUES ('26', '6', 'SMA-FL', '1', null, '2020-06-27 09:07:49', null);
INSERT INTO `graphite_disc` VALUES ('27', '7', 'SMA-FL', '1', null, '2020-06-27 09:07:58', null);
INSERT INTO `graphite_disc` VALUES ('28', '8', 'SMB-FL', '1', null, '2020-06-27 09:35:24', null);
INSERT INTO `graphite_disc` VALUES ('29', '9', 'SMA-FL', '1', null, '2020-06-27 09:35:29', null);
INSERT INTO `graphite_disc` VALUES ('30', '10', 'SMA-FL', '1', null, '2020-06-27 09:35:36', null);
INSERT INTO `graphite_disc` VALUES ('31', '11', 'SMB-FL', '1', null, '2020-06-27 10:08:50', null);
INSERT INTO `graphite_disc` VALUES ('32', '12', 'SMA-FL', '1', null, '2020-06-27 10:09:14', null);
INSERT INTO `graphite_disc` VALUES ('33', '13', 'SOD323HE', '1', null, '2020-06-27 10:10:27', null);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `auth_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort_order` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '石墨盘管理', '', '1');
INSERT INTO `menu` VALUES ('2', '1', '石墨盘', 'shimo', '2');
INSERT INTO `menu` VALUES ('3', '1', '清洗校验记录', 'wash', '3');

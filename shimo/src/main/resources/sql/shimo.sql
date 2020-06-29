/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50724
Source Host           : localhost:3307
Source Database       : shimo

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-06-29 14:08:24
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
  `last_used_time` datetime DEFAULT NULL COMMENT '‌最近使用时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codeIndex` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of graphite_disc
-- ----------------------------
INSERT INTO `graphite_disc` VALUES ('21', '1', 'SMB-FL', '0', '11111111', '2020-06-26 23:20:54', '2020-06-27 08:32:52', null);
INSERT INTO `graphite_disc` VALUES ('22', '2', 'SOD323HE', '0', 'asfaafsa', '2020-06-26 23:21:21', '2020-06-27 09:34:59', null);
INSERT INTO `graphite_disc` VALUES ('23', '3', 'SMA-FL', '0', 'asfafsafaf', '2020-06-26 23:21:26', '2020-06-27 09:35:03', null);
INSERT INTO `graphite_disc` VALUES ('24', '4', 'SOD323HE', '1', null, '2020-06-27 09:07:36', null, '2020-05-29 10:22:05');
INSERT INTO `graphite_disc` VALUES ('25', '5', 'SMA-FL', '1', null, '2020-06-27 09:07:41', null, '2020-06-29 10:23:24');
INSERT INTO `graphite_disc` VALUES ('26', '6', 'SMA-FL', '1', null, '2020-06-27 09:07:49', null, '2020-06-29 10:23:27');
INSERT INTO `graphite_disc` VALUES ('27', '7', 'SMA-FL', '1', null, '2020-06-27 09:07:58', null, '2020-06-29 10:23:30');
INSERT INTO `graphite_disc` VALUES ('28', '8', 'SMB-FL', '1', null, '2020-06-27 09:35:24', null, '2020-06-29 10:23:33');
INSERT INTO `graphite_disc` VALUES ('29', '9', 'SMA-FL', '1', null, '2020-06-27 09:35:29', null, '2020-06-29 10:23:46');
INSERT INTO `graphite_disc` VALUES ('30', '10', 'SMA-FL', '1', null, '2020-06-27 09:35:36', null, '2020-06-29 10:23:50');
INSERT INTO `graphite_disc` VALUES ('31', '11', 'SMB-FL', '1', null, '2020-06-27 10:08:50', null, '2020-06-29 10:23:57');
INSERT INTO `graphite_disc` VALUES ('32', '12', 'SMA-FL', '1', null, '2020-06-27 10:09:14', null, '2020-05-20 10:21:30');
INSERT INTO `graphite_disc` VALUES ('33', '13', 'SOD323HE', '1', null, '2020-06-27 10:10:27', null, null);
INSERT INTO `graphite_disc` VALUES ('34', '14', 'SOD323HE', '1', null, '2020-06-29 13:59:27', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '石墨盘管理', '', '1');
INSERT INTO `menu` VALUES ('2', '1', '石墨盘', 'shimo', '2');
INSERT INTO `menu` VALUES ('3', '1', '清洗校验记录', 'wash', '3');

-- ----------------------------
-- Table structure for wash_record
-- ----------------------------
DROP TABLE IF EXISTS `wash_record`;
CREATE TABLE `wash_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` int(10) DEFAULT NULL COMMENT '石墨盘编码',
  `shift_group` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '班别',
  `shift` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '班次',
  `dwzDiameter1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter2` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter3` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter4` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter5` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter6` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter7` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter8` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwzDiameter9` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `bmbrcDepth1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `bmbrcDepth2` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `bmbrcDepth3` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `pzd1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `pzd2` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `pzd3` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDiameter1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDiameter2` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDiameter3` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDiameter4` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDepth1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDepth2` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDepth3` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dwxkDepth4` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of wash_record
-- ----------------------------
INSERT INTO `wash_record` VALUES ('47', '12', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:20:34');
INSERT INTO `wash_record` VALUES ('48', '12', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:21:30');
INSERT INTO `wash_record` VALUES ('49', '12', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:21:30');
INSERT INTO `wash_record` VALUES ('50', '4', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:22:03');
INSERT INTO `wash_record` VALUES ('51', '4', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:22:04');
INSERT INTO `wash_record` VALUES ('52', '4', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:22:04');
INSERT INTO `wash_record` VALUES ('53', '4', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:22:04');
INSERT INTO `wash_record` VALUES ('54', '4', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:22:05');
INSERT INTO `wash_record` VALUES ('55', '5', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:23:24');
INSERT INTO `wash_record` VALUES ('56', '6', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:23:27');
INSERT INTO `wash_record` VALUES ('57', '7', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:23:30');
INSERT INTO `wash_record` VALUES ('58', '8', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:23:33');
INSERT INTO `wash_record` VALUES ('59', '9', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:23:46');
INSERT INTO `wash_record` VALUES ('60', '10', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:23:50');
INSERT INTO `wash_record` VALUES ('61', '11', 'C', '白', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '1.465', '0.86', '0.86', '0.86', '34', '34', '34', '2.5', '2.5', '2.5', '2.5', '7.2', '7.2', '7.2', '7.2', '2020-06-29 10:23:57');

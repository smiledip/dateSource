/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50157
Source Host           : localhost:3306
Source Database       : bddj

Target Server Type    : MYSQL
Target Server Version : 50157
File Encoding         : 65001

Date: 2018-04-14 17:39:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t1
-- ----------------------------
DROP TABLE IF EXISTS `t1`;
CREATE TABLE `t1` (
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t1
-- ----------------------------
INSERT INTO `t1` VALUES ('1', null);
INSERT INTO `t1` VALUES ('1', '2');
INSERT INTO `t1` VALUES ('1', null);
INSERT INTO `t1` VALUES ('1', null);

/*
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : school_helper

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-12-19 11:12:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `connection`
-- ----------------------------
DROP TABLE IF EXISTS `connection`;
CREATE TABLE `connection` (
  `connection_id` int(10) NOT NULL AUTO_INCREMENT,
  `release_user_id` int(20) NOT NULL,
  `receive_user_id` int(20) NOT NULL,
  `reward_id` int(10) NOT NULL,
  PRIMARY KEY (`connection_id`),
  KEY `reward_id` (`reward_id`),
  CONSTRAINT `connection_ibfk_1` FOREIGN KEY (`reward_id`) REFERENCES `reward` (`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of connection
-- ----------------------------

-- ----------------------------
-- Table structure for `reward`
-- ----------------------------
DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward` (
  `reward_id` int(10) NOT NULL AUTO_INCREMENT,
  `release_user_id` int(20) NOT NULL,
  `reward_content` varchar(255) NOT NULL,
  `reward_key` varchar(20) NOT NULL,
  `reward_money` double(10,2) NOT NULL,
  `reward_time` timestamp NOT NULL,
  `reward_deadline` timestamp NOT NULL,
  `reward_receive_count` int(10) NOT NULL,
  `reward_state` varchar(10) NOT NULL,
  `reward_image` varchar(50) NOT NULL,
  PRIMARY KEY (`reward_id`),
  UNIQUE KEY `reward_id` (`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reward
-- ----------------------------

-- ----------------------------
-- Table structure for `school`
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `school_id` int(10) NOT NULL,
  `school_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '河北师范大学');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `school_id` int(20) NOT NULL,
  `user_student_num` varchar(20) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `user_image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_money` double(10,2) NOT NULL,
  `user_reputation_value` int(10) NOT NULL,
  `user_took_count` int(10) NOT NULL,
  `user_publish_count` int(10) NOT NULL,
  `user_identification` varchar(10) NOT NULL,
  `user_signature` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `user_realname` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_sex` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `school_id` (`school_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '111', '111', '1', '111', '111', 'images/geren.png', '0.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('2', 'xioyoutiao', '272884559', '1', '201699999', '199999999', 'images/geren.png', '0.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('3', '222', '222', '1', '222', '222', 'images/geren.png', '0.00', '60', '0', '0', '0', null, null, null);
INSERT INTO `user` VALUES ('4', 'xiaoxigua', '999', '1', '999', '999', 'images/geren.png', '0.00', '60', '0', '0', '', 'wohenfanfanfnafnanf', 'liudahau', 'nv');
INSERT INTO `user` VALUES ('5', '88', '888', '1', '88', '888', 'images/geren.png', '0.00', '60', '0', '0', '未认证', '99', '99', '99');

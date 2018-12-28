/*
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : school_helper

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-12-28 16:00:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `connection`
-- ----------------------------
DROP TABLE IF EXISTS `connection`;
CREATE TABLE `connection` (
  `connection_id` int(10) NOT NULL AUTO_INCREMENT,
  `poster_id` int(20) NOT NULL,
  `receiver_id` int(20) NOT NULL,
  `reward_id` int(10) NOT NULL,
  PRIMARY KEY (`connection_id`),
  KEY `reward_id` (`reward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of connection
-- ----------------------------
INSERT INTO `connection` VALUES ('39', '1', '2', '20');
INSERT INTO `connection` VALUES ('42', '2', '1', '43');
INSERT INTO `connection` VALUES ('45', '1', '3', '47');

-- ----------------------------
-- Table structure for `reward`
-- ----------------------------
DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward` (
  `reward_id` int(10) NOT NULL AUTO_INCREMENT,
  `poster_id` int(20) NOT NULL,
  `reward_content` varchar(255) NOT NULL,
  `reward_title` varchar(20) NOT NULL,
  `reward_money` double(10,2) NOT NULL,
  `reward_time` varchar(255) NOT NULL,
  `reward_deadline` varchar(255) NOT NULL,
  `reward_state` varchar(10) NOT NULL,
  `reward_image` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`reward_id`),
  UNIQUE KEY `reward_id` (`reward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES ('20', '1', '444', '444', '4.00', '2018年12月26日 00:05:55', '2018-12-29 00:05:51', '4', null);
INSERT INTO `reward` VALUES ('23', '3', '777', '777', '0.00', '2018年12月26日 00:53:25', '2018-12-27 00:53:14', '2', null);
INSERT INTO `reward` VALUES ('43', '2', '555', '555', '55.00', '2018年12月28日 07:28:12', '2018-12-30 07:28:08', '4', null);
INSERT INTO `reward` VALUES ('47', '1', 'ceshi', 'wohaishiceshi', '11.00', '2018年12月28日 07:52:02', '2018-12-30 07:51:59', '4', null);
INSERT INTO `reward` VALUES ('50', '1', '6666', '6666', '6.00', '2018年12月28日 07:58:18', '2018-12-31 07:58:16', '1', null);
INSERT INTO `reward` VALUES ('51', '1', '777', '777', '7.00', '2018年12月28日 07:58:57', '2018-12-30 07:58:55', '1', null);

-- ----------------------------
-- Table structure for `school`
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `school_id` int(10) NOT NULL,
  `school_name` varchar(20) NOT NULL,
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
  `user_name` varchar(25) NOT NULL,
  `user_password` varchar(25) NOT NULL,
  `school_id` int(20) NOT NULL,
  `user_student_num` varchar(20) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `user_image` varchar(50) NOT NULL,
  `user_money` double(30,2) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaomogui', '120', '1', '2016', '110', 'R.drawble.myhead', '914.00', '60', '0', '0', '0', 'xiaomogui', 'xiaoxigua', '男');
INSERT INTO `user` VALUES ('2', '别人的名字', '16513', '1', '65232620', '1321', 'images/geren.png', '9685.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('3', '技术大牛', '123123', '1', '2017', '120', 'images/geren.png', '11.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('4', 'null', '12345', '1', '2016', '12345', 'images/geren.png', '0.00', '60', '0', '0', 'null', 'null', 'null', 'null');
INSERT INTO `user` VALUES ('5', '12', '123', '1', '2016', '123456', 'images/geren.png', '320.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');

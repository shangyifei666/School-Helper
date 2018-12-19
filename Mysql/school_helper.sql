/*
Navicat MySQL Data Transfer

Source Server         : mySQL
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : school_helper

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2018-12-19 17:49:43
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES ('1', '1', '有人这周日有时间吗？愿不愿意去机场接下人，顺便帮忙拎行李....', '机场接人', '15.00', '', '2018-12-19', '1', null);

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
  `user_money` double(10,2) NOT NULL,
  `user_reputation_value` int(10) NOT NULL,
  `user_took_count` int(10) NOT NULL,
  `user_publish_count` int(10) NOT NULL,
  `user_identification` int(10) NOT NULL,
  `user_signature` varchar(50) NOT NULL DEFAULT '',
  `user_realname` varchar(25) NOT NULL,
  `user_sex` varchar(25) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `school_id` (`school_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'a', '123123', '1', '2016', '110', 'R.drawble.myhead', '0.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('2', '1613', '16513', '1', '65232620', '1321', 'images/geren.png', '0.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('3', 'b', '123123', '1', '2017', '120', 'images/geren.png', '0.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('4', '123', '123', '1', '2016', '12345', 'images/geren.png', '0.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');
INSERT INTO `user` VALUES ('5', '12', '123', '1', '2016', '123456', 'images/geren.png', '0.00', '60', '0', '0', '0', '个性标签', '真实姓名', '男');

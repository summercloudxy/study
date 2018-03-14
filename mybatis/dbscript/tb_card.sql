/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : smartfactory2

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2018-03-14 14:05:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_card
-- ----------------------------
DROP TABLE IF EXISTS `tb_card`;
CREATE TABLE `tb_card` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `card_style_id` int(10) DEFAULT NULL,
  `card_param_value_json` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_card
-- ----------------------------
INSERT INTO `tb_card` VALUES ('1', '1', '{\"tc\":\"1144\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('2', '1', '{\"tc\":\"1143\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('3', '1', '{\"tc\":\"2143\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('4', '1', '{\"tc\":\"2144\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('5', '3', '{\"tc\":\"1301\",\"mc\":\"ASSAY_DATA\",\"type\":\"sample\",\"showbit\":7}');
INSERT INTO `tb_card` VALUES ('6', '1', '{\"tc\":\"1201\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('7', '2', '{\"title\":\"一期配比\",\"tc\":\"Quit_SYS_1\",\"tmc1\":\"COAL_8_DEVICE\",\"tmc2\":\"COAL_13_DEVICE\",\"cmc1\":\"COAL_8_CF\",\"cmc2\":\"COAL_13_CF\"}');
INSERT INTO `tb_card` VALUES ('8', '1', '{\"tc\":\"2201\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('9', '2', '{\"title\":\"二期配比\",\"tc\":\"Quit_SYS_2\",\"tmc1\":\"COAL_8_DEVICE\",\"tmc2\":\"COAL_13_DEVICE\",\"cmc1\":\"COAL_8_CF\",\"cmc2\":\"COAL_13_CF\"}');
INSERT INTO `tb_card` VALUES ('10', '1', '{\"tc\":\"Quit_SYS_1\",\"mcs\":[\"COAL_CAP\",\"CT_C\"],\"ignoretc\":true}');
INSERT INTO `tb_card` VALUES ('11', '1', '{\"tc\":\"1302\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('12', '1', '{\"tc\":\"1301\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('13', '1', '{\"tc\":\"2301\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('14', '1', '{\"tc\":\"2302\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('15', '1', '{\"tc\":\"Quit_SYS_2\",\"mcs\":[\"COAL_CAP\",\"CT_C\"],\"ignoretc\":true}');
INSERT INTO `tb_card` VALUES ('16', '1', '{\"tc\":\"702\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('17', '1', '{\"tc\":\"1552\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('18', '1', '{\"tc\":\"1553\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('19', '1', '{\"tc\":\"1554\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
INSERT INTO `tb_card` VALUES ('20', '1', '{\"tc\":\"701\",\"mcs\":[\"COAL_CAP\",\"CT_C\"]}');
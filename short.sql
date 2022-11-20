/*
 Navicat Premium Data Transfer

 Source Server         : Tencent(Shenzhen)
 Source Server Type    : MySQL
 Source Server Version : 50650 (5.6.50-log)
 Source Host           : 119.91.157.222:3306
 Source Schema         : short

 Target Server Type    : MySQL
 Target Server Version : 50650 (5.6.50-log)
 File Encoding         : 65001

 Date: 20/11/2022 09:41:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db
-- ----------------------------
DROP TABLE IF EXISTS `db`;
CREATE TABLE `db` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` char(6) COLLATE utf8_croatian_ci NOT NULL,
  `o_url` text COLLATE utf8_croatian_ci,
  `url` varchar(20) COLLATE utf8_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`u_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

SET FOREIGN_KEY_CHECKS = 1;

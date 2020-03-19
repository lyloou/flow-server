/*
 Navicat Premium Data Transfer

 Source Server         : lou-jd-114.67.95.131
 Source Server Type    : MariaDB
 Source Server Version : 100406
 Source Host           : 114.67.95.131:3306
 Source Schema         : flow

 Target Server Type    : MariaDB
 Target Server Version : 100406
 File Encoding         : 65001

 Date: 20/03/2020 07:42:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `day` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '哪一天',
  `content` longtext CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL COMMENT '具体事情',
  `gmt_create` datetime(0) NULL DEFAULT current_timestamp COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_userId_day`(`user_id`, `day`) USING BTREE,
  INDEX `idx_userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '大事迹' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL,
  `day` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '哪一天',
  `item` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '发生的哪些具体事情',
  `weather` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '天气情况',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备忘',
  `gmt_create` datetime(0) NULL DEFAULT current_timestamp COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_archived` tinyint(1) NULL DEFAULT 0 COMMENT '是否已经存档',
  `is_disabled` tinyint(1) NULL DEFAULT 0 COMMENT '是否已经删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_userId_day`(`user_id`, `day`) USING BTREE,
  INDEX `idx_userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 489 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '夫路' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名，可登录的',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` bigint(11) NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '大头贴',
  `personal_signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '个性签名',
  `gmt_create` datetime(0) NULL DEFAULT current_timestamp COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_disabled` tinyint(1) NULL DEFAULT 0 COMMENT '是否已经删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime(0) NULL DEFAULT current_timestamp COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_userId`(`user_id`) USING BTREE,
  INDEX `idx_name_password`(`name`, `password`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

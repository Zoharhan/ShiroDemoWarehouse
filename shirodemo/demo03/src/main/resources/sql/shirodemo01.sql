/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : shirodemo01

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 16/04/2020 21:28:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for u_permission
-- ----------------------------
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url描述',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限符',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_permission
-- ----------------------------
INSERT INTO `u_permission` VALUES (4, '/permission/index.shtml', '权限列表', 'system:index');
INSERT INTO `u_permission` VALUES (6, '/permission/addPermission.shtml', '权限添加', 'system:user');
INSERT INTO `u_permission` VALUES (7, '/permission/deletePermissionById.shtml', '权限删除', 'system:user');
INSERT INTO `u_permission` VALUES (8, '/member/list.shtml', '用户列表', 'system:user');
INSERT INTO `u_permission` VALUES (9, '/member/online.shtml', '在线用户', 'system:user');
INSERT INTO `u_permission` VALUES (10, '/member/changeSessionStatus.shtml', '用户Session踢出', 'system:user');
INSERT INTO `u_permission` VALUES (11, '/member/forbidUserById.shtml', '用户激活&禁止', 'system:user');
INSERT INTO `u_permission` VALUES (12, '/member/deleteUserById.shtml', '用户删除', 'system:user');
INSERT INTO `u_permission` VALUES (13, '/permission/addPermission2Role.shtml', '权限分配', 'system:user');
INSERT INTO `u_permission` VALUES (14, '/role/clearRoleByUserIds.shtml', '用户角色分配清空', 'system:user');
INSERT INTO `u_permission` VALUES (15, '/role/addRole2User.shtml', '角色分配保存', 'system:user');
INSERT INTO `u_permission` VALUES (16, '/role/deleteRoleById.shtml', '角色列表删除', 'system:user');
INSERT INTO `u_permission` VALUES (17, '/role/addRole.shtml', '角色列表添加', 'system:user');
INSERT INTO `u_permission` VALUES (18, '/role/index.shtml', '角色列表', 'system:user');
INSERT INTO `u_permission` VALUES (19, '/permission/allocation.shtml', '权限分配', 'system:user');
INSERT INTO `u_permission` VALUES (20, '/role/allocation.shtml', '角色分配', 'system:user');
INSERT INTO `u_permission` VALUES (21, '/page1', '界面1', 'page:page1');
INSERT INTO `u_permission` VALUES (22, '/page2', '界面2', 'page:page2');

-- ----------------------------
-- Table structure for u_role
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES (1, '系统管理员', '888888');
INSERT INTO `u_role` VALUES (3, '权限角色', '100003');
INSERT INTO `u_role` VALUES (4, '用户中心', '100002');
INSERT INTO `u_role` VALUES (5, '角色1', '100003');
INSERT INTO `u_role` VALUES (6, '角色2', '100004');

-- ----------------------------
-- Table structure for u_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `u_role_permission`;
CREATE TABLE `u_role_permission`  (
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_role_permission
-- ----------------------------
INSERT INTO `u_role_permission` VALUES (1, 4);
INSERT INTO `u_role_permission` VALUES (1, 6);
INSERT INTO `u_role_permission` VALUES (1, 7);
INSERT INTO `u_role_permission` VALUES (1, 8);
INSERT INTO `u_role_permission` VALUES (1, 9);
INSERT INTO `u_role_permission` VALUES (1, 10);
INSERT INTO `u_role_permission` VALUES (1, 11);
INSERT INTO `u_role_permission` VALUES (1, 12);
INSERT INTO `u_role_permission` VALUES (1, 13);
INSERT INTO `u_role_permission` VALUES (1, 14);
INSERT INTO `u_role_permission` VALUES (1, 15);
INSERT INTO `u_role_permission` VALUES (1, 16);
INSERT INTO `u_role_permission` VALUES (1, 17);
INSERT INTO `u_role_permission` VALUES (1, 18);
INSERT INTO `u_role_permission` VALUES (1, 19);
INSERT INTO `u_role_permission` VALUES (1, 20);
INSERT INTO `u_role_permission` VALUES (3, 4);
INSERT INTO `u_role_permission` VALUES (3, 6);
INSERT INTO `u_role_permission` VALUES (3, 7);
INSERT INTO `u_role_permission` VALUES (3, 8);
INSERT INTO `u_role_permission` VALUES (3, 9);
INSERT INTO `u_role_permission` VALUES (3, 10);
INSERT INTO `u_role_permission` VALUES (3, 11);
INSERT INTO `u_role_permission` VALUES (3, 12);
INSERT INTO `u_role_permission` VALUES (3, 13);
INSERT INTO `u_role_permission` VALUES (3, 14);
INSERT INTO `u_role_permission` VALUES (3, 15);
INSERT INTO `u_role_permission` VALUES (3, 16);
INSERT INTO `u_role_permission` VALUES (3, 17);
INSERT INTO `u_role_permission` VALUES (3, 18);
INSERT INTO `u_role_permission` VALUES (3, 19);
INSERT INTO `u_role_permission` VALUES (3, 20);
INSERT INTO `u_role_permission` VALUES (4, 8);
INSERT INTO `u_role_permission` VALUES (5, 21);
INSERT INTO `u_role_permission` VALUES (6, 22);

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) NULL DEFAULT 1 COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES (1, '管理员', 'admin', 'admin', '2016-06-16 11:15:33', '2020-04-05 19:28:14', 1);
INSERT INTO `u_user` VALUES (2, 'zohar', 'zoharhan@126.com', 'admin', '2016-06-16 11:15:33', '2016-06-16 11:15:33', 1);
INSERT INTO `u_user` VALUES (11, 'soso', '8446666@qq.com', 'admin', '2016-05-26 20:50:54', '2016-06-16 11:24:35', 1);
INSERT INTO `u_user` VALUES (12, '8446666', '8446666', 'admin', '2016-05-27 22:34:19', '2016-06-15 17:03:16', 1);
INSERT INTO `u_user` VALUES (13, '1', '1', '1', '2020-04-02 18:52:05', '2020-04-05 19:25:56', 1);
INSERT INTO `u_user` VALUES (14, '2', '2', '2', '2020-04-02 18:52:05', '2020-04-02 18:52:05', 1);
INSERT INTO `u_user` VALUES (15, '3', '3', '3', '2020-04-02 18:52:05', '2020-04-02 18:52:05', 1);

-- ----------------------------
-- Table structure for u_user_online
-- ----------------------------
DROP TABLE IF EXISTS `u_user_online`;
CREATE TABLE `u_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  `session_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始的session信息（测试使用）',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for u_user_role
-- ----------------------------
DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role`  (
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_user_role
-- ----------------------------
INSERT INTO `u_user_role` VALUES (12, 4);
INSERT INTO `u_user_role` VALUES (11, 3);
INSERT INTO `u_user_role` VALUES (11, 4);
INSERT INTO `u_user_role` VALUES (1, 1);
INSERT INTO `u_user_role` VALUES (2, 4);
INSERT INTO `u_user_role` VALUES (14, 5);
INSERT INTO `u_user_role` VALUES (15, 6);
INSERT INTO `u_user_role` VALUES (13, 4);

SET FOREIGN_KEY_CHECKS = 1;

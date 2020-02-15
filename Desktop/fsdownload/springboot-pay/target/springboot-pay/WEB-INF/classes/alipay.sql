/*
 Navicat Premium Data Transfer

 Source Server         : xiaochen
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : alipay

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 14/02/2020 21:17:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flow_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水号',
  `order_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `product_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品主键ID',
  `paid_amount` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付金额',
  `paid_method` int(11) NULL DEFAULT NULL COMMENT '支付方式\r\n            1：支付宝\r\n            2：微信',
  `buy_counts` int(11) NULL DEFAULT NULL COMMENT '购买个数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flow
-- ----------------------------
INSERT INTO `flow` VALUES ('20202141811185676', '2020021422001461471000178834', '20202141810457366', '21269682', '200.00', 1, 2, '2020-02-14 18:11:19');
INSERT INTO `flow` VALUES ('20202141826497996', '2020021422001461471000177608', '20202141819423186', '21165953', '10.00', 1, 5, '2020-02-14 18:26:50');
INSERT INTO `flow` VALUES ('202021419149636', '2020021422001461471000175240', '20202141852225866', '77508096', '384.00', 1, 4, '2020-02-14 19:01:49');
INSERT INTO `flow` VALUES ('2020214200375526', '2020021422001461471000179925', '20202141959521166', '21097646', '100.00', 1, 50, '2020-02-14 20:00:39');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态\r\n            10：待付款\r\n            20：已付款',
  `order_amount` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单金额',
  `paid_amount` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际支付金额',
  `product_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品表外键ID',
  `buy_counts` int(11) NULL DEFAULT NULL COMMENT '产品购买的个数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `paid_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2020214171423696', '2020214171423696', '20', '400.0', '400.00', '21269682', 4, '2020-02-14 17:14:02', '2020-02-14 17:15:03');
INSERT INTO `orders` VALUES ('20202141745283716', '20202141745283716', '20', '5000.0', '5000.00', '21269682', 50, '2020-02-14 17:45:28', '2020-02-14 17:46:55');
INSERT INTO `orders` VALUES ('20202141755225326', '20202141755225326', '20', '4.0', '4.00', '21184680', 4, '2020-02-14 17:55:22', '2020-02-14 17:56:48');
INSERT INTO `orders` VALUES ('20202141810457366', '20202141810457366', '20', '200.0', '200.00', '21269682', 2, '2020-02-14 18:10:45', '2020-02-14 18:11:19');
INSERT INTO `orders` VALUES ('202021418161696', '202021418161696', '20', '400.0', '400.00', '21269682', 4, '2020-02-14 18:01:06', '2020-02-14 18:01:40');
INSERT INTO `orders` VALUES ('20202141819423186', '20202141819423186', '20', '10.0', '10.00', '21165953', 5, '2020-02-14 18:19:42', '2020-02-14 18:26:50');
INSERT INTO `orders` VALUES ('20202141825286626', '20202141825286626', '10', '1000.0', '1000.0', '21269682', 10, '2020-02-14 18:25:28', '2020-02-14 18:25:28');
INSERT INTO `orders` VALUES ('20202141852225866', '20202141852225866', '20', '384.0', '384.00', '77508096', 4, '2020-02-14 18:52:22', '2020-02-14 19:01:49');
INSERT INTO `orders` VALUES ('20202141955187216', '20202141955187216', '10', '37400.0', '37400.0', '77432748', 50, '2020-02-14 19:55:18', '2020-02-14 19:55:18');
INSERT INTO `orders` VALUES ('20202141958263796', '20202141958263796', '10', '37400.0', '37400.0', '77432748', 50, '2020-02-14 19:58:26', '2020-02-14 19:58:26');
INSERT INTO `orders` VALUES ('20202141959521166', '20202141959521166', '20', '100.0', '100.00', '21097646', 50, '2020-02-14 19:59:52', '2020-02-14 20:00:38');
INSERT INTO `orders` VALUES ('2020214207485576', '2020214207485576', '10', '748.0', '748.0', '77432748', 1, '2020-02-14 20:07:48', '2020-02-14 20:07:48');

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay`  (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_money` int(255) NULL DEFAULT NULL COMMENT '缴费表id',
  `pay_date` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `pay_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库',
  PRIMARY KEY (`pay_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay
-- ----------------------------
INSERT INTO `pay` VALUES (1, 1, '2019-06-13 23:00:01', 'alipay');
INSERT INTO `pay` VALUES (2, 2, '2019-07-01 09:07:42', 'alipay');
INSERT INTO `pay` VALUES (10, 2, '2019-06-28 12:48:36', 'alipay');
INSERT INTO `pay` VALUES (11, 7, '2019-06-28 15:06:53', 'alipay');
INSERT INTO `pay` VALUES (12, 8, '2019-06-28 21:26:50', 'alipay');
INSERT INTO `pay` VALUES (14, 5, '2019-07-01 09:07:42', 'alipay');
INSERT INTO `pay` VALUES (15, 6, '2019-07-01 09:07:42', 'alipay');
INSERT INTO `pay` VALUES (16, 4, '2019-07-01 09:07:42', 'alipay');
INSERT INTO `pay` VALUES (17, 6, '2019-07-01 09:07:42', 'alipay');
INSERT INTO `pay` VALUES (18, 5, '2019-07-01 09:07:42', 'alipay');
INSERT INTO `pay` VALUES (19, 4, '2019-07-01 09:07:42', 'alipay');
INSERT INTO `pay` VALUES (20, 5, '2019-07-01 09:10:45', 'alipay');
INSERT INTO `pay` VALUES (21, 7, '2019-07-01 10:22:07', 'alipay');
INSERT INTO `pay` VALUES (22, 7, '2019-07-02 15:36:57', 'alipay');
INSERT INTO `pay` VALUES (23, 6, '2019-07-02 15:38:19', 'alipay');
INSERT INTO `pay` VALUES (24, 5, '2019-07-02 15:39:15', 'alipay');
INSERT INTO `pay` VALUES (25, 6, '2019-07-02 16:05:33', 'alipay');
INSERT INTO `pay` VALUES (26, 9, '2019-07-02 17:27:01', 'alipay');
INSERT INTO `pay` VALUES (27, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (28, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (29, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (30, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (31, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (32, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (33, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (34, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (35, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (36, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (37, 4, '2019-07-02 19:05:13', 'alipay');
INSERT INTO `pay` VALUES (38, 19, '2019-07-03 10:36:22', 'alipay');
INSERT INTO `pay` VALUES (39, 23, '2019-07-03 13:13:57', 'alipay');
INSERT INTO `pay` VALUES (40, 1598, '2020-01-16 23:18:05', 'alipay');
INSERT INTO `pay` VALUES (41, 4555, '2020-02-09 01:33:39', 'alipay');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(20) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `price` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (21097646, '热狗', '2');
INSERT INTO `product` VALUES (21122170, '泡面', '5');
INSERT INTO `product` VALUES (21152352, '矿泉水', '2');
INSERT INTO `product` VALUES (21165953, '火腿', '2');
INSERT INTO `product` VALUES (21184680, '纸巾', '1');
INSERT INTO `product` VALUES (21269682, '猪肉', '100');
INSERT INTO `product` VALUES (77184661, '水费', '100');
INSERT INTO `product` VALUES (77273145, '电费', '7273145');
INSERT INTO `product` VALUES (77290269, '燃气费', '7290269');
INSERT INTO `product` VALUES (77381763, '口香糖', '81763');
INSERT INTO `product` VALUES (77432748, '罐头', '748');
INSERT INTO `product` VALUES (77508096, '盐', '96');
INSERT INTO `product` VALUES (78404899, '味精', '899');
INSERT INTO `product` VALUES (78447520, '酱油', '520');
INSERT INTO `product` VALUES (78777082, '耗油', '82');
INSERT INTO `product` VALUES (78911849, '豆子', '849');
INSERT INTO `product` VALUES (79190944, '牙膏', '58');
INSERT INTO `product` VALUES (79521485, '牙刷', '485');
INSERT INTO `product` VALUES (79555076, '碗', '0.76');
INSERT INTO `product` VALUES (79693337, '盘子', '337');
INSERT INTO `product` VALUES (79848405, '酒', '405');
INSERT INTO `product` VALUES (79943526, '白酒', '525');
INSERT INTO `product` VALUES (80709549, '红酒', '0.896');
INSERT INTO `product` VALUES (80856701, '啤酒', '0.896');
INSERT INTO `product` VALUES (80940466, '香槟', '466');
INSERT INTO `product` VALUES (80980320, '泸州老窖', '320');
INSERT INTO `product` VALUES (81013891, '国窖1573', '891');
INSERT INTO `product` VALUES (81026891, '茅台', '891');
INSERT INTO `product` VALUES (81078575, '郎酒', '575');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '男');
INSERT INTO `user` VALUES (2, 'admin', '女');
INSERT INTO `user` VALUES (3, 'admin', '女');
INSERT INTO `user` VALUES (4, 'admin', '女');
INSERT INTO `user` VALUES (5, 'admin', '女');
INSERT INTO `user` VALUES (6, 'admin', '女');
INSERT INTO `user` VALUES (7, 'admin', '女');
INSERT INTO `user` VALUES (8, 'admin', '女');
INSERT INTO `user` VALUES (9, 'admin', '女');
INSERT INTO `user` VALUES (10, 'admin', '女');
INSERT INTO `user` VALUES (11, 'admin', '女');
INSERT INTO `user` VALUES (12, 'admin', '女');
INSERT INTO `user` VALUES (13, 'admin', '女');
INSERT INTO `user` VALUES (14, 'admin', '女');
INSERT INTO `user` VALUES (15, 'admin', '男');
INSERT INTO `user` VALUES (16, 'admin', '男');

SET FOREIGN_KEY_CHECKS = 1;

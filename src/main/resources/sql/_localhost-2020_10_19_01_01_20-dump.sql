-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: faka
-- ------------------------------------------------------
-- Server version	5.7.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ali_pay_config`
--

DROP TABLE IF EXISTS `ali_pay_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ali_pay_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `app_id` varchar(255) DEFAULT NULL COMMENT 'appid',
  `merchant_private_key` text COMMENT '私钥',
  `ali_pay_public_key` text COMMENT '支付宝公钥',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1/正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='支付宝配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ali_pay_config`
--

LOCK TABLES `ali_pay_config` WRITE;
/*!40000 ALTER TABLE `ali_pay_config` DISABLE KEYS */;
INSERT INTO `ali_pay_config` VALUES (1,NULL,'2019041663920099','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',1,'2020-10-19 00:43:10','2020-10-19 00:43:10');
/*!40000 ALTER TABLE `ali_pay_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `content` varchar(255) DEFAULT NULL COMMENT '卡密信息',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0/未售出 1/已售出',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='卡密';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,1,'abcdefghijk',1,'2020-10-19 00:43:15','2020-10-19 00:43:15'),(2,1,'abcdefghijk',1,'2020-10-19 00:43:15','2020-10-19 00:43:15'),(3,3,'abcdefghijk',1,'2020-10-19 00:43:15','2020-10-19 00:43:15'),(4,4,'abcdefghijk',1,'2020-10-19 00:43:15','2020-10-19 00:43:15'),(5,1,'abcdefghijk',1,'2020-10-19 00:43:15','2020-10-19 00:43:15'),(6,2,'abcdefghijk',0,'2020-10-19 00:43:15','2020-10-19 00:43:15'),(8,1,'abcdefghijk',1,'2020-10-19 00:43:15','2020-10-19 00:43:15');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent` bigint(20) DEFAULT NULL COMMENT '父级',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0/下架 1/上架',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'免流',NULL,1,'2020-08-20 22:49:49','2020-09-05 23:38:41'),(10,'翻墙',NULL,1,'2020-09-05 23:38:47','2020-09-11 00:26:19');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `export_file`
--

DROP TABLE IF EXISTS `export_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `export_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态 0/未下载 1/已下载 -1/正在生成',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1295748224634908675 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `export_file`
--

LOCK TABLES `export_file` WRITE;
/*!40000 ALTER TABLE `export_file` DISABLE KEYS */;
INSERT INTO `export_file` VALUES (1295748224634908674,'2020年08月17日至2020年04月24日卡密数据66Vd234345.xlsx','/data/faka/exportFile/2020年08月17日至2020年04月24日卡密数据66Vd234345.xlsx',1,0,'2020-10-19 00:45:01','2020-10-19 00:45:01');
/*!40000 ALTER TABLE `export_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_list`
--

DROP TABLE IF EXISTS `menu_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent` bigint(20) DEFAULT NULL COMMENT '父级',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `path` varchar(255) DEFAULT NULL COMMENT '路由',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_list`
--

LOCK TABLES `menu_list` WRITE;
/*!40000 ALTER TABLE `menu_list` DISABLE KEYS */;
INSERT INTO `menu_list` VALUES (1,NULL,'主页',NULL,1,'main','2020-10-19 00:48:50','2020-10-19 00:48:50'),(2,NULL,'用户管理',NULL,2,'','2020-10-19 00:48:50','2020-10-19 00:48:50'),(3,NULL,'分类管理',NULL,4,'','2020-10-19 00:48:50','2020-10-19 00:48:50'),(4,NULL,'商品管理',NULL,5,'','2020-10-19 00:48:50','2020-10-19 00:48:50'),(5,NULL,'卡密管理',NULL,6,'','2020-10-19 00:48:50','2020-10-19 00:48:50'),(6,NULL,'订单管理',NULL,7,'','2020-10-19 00:48:50','2020-10-19 00:48:50'),(7,NULL,'系统设置',NULL,8,'','2020-10-19 00:48:50','2020-10-19 00:48:50'),(8,NULL,'分类列表',3,1,'categoryList','2020-10-19 00:48:50','2020-10-19 00:48:50'),(9,NULL,'商品列表',4,1,'productList','2020-10-19 00:48:50','2020-10-19 00:48:50'),(10,NULL,'卡密列表',5,1,'cardList','2020-10-19 00:48:50','2020-10-19 00:48:50'),(11,NULL,'订单列表',6,1,'orderList','2020-10-19 00:48:50','2020-10-19 00:48:50'),(17,NULL,'支付宝支付配置',7,1,'alipay','2020-10-19 00:48:50','2020-10-19 00:48:50'),(18,NULL,'用户列表',2,1,'userList','2020-10-19 00:48:50','2020-10-19 00:48:50'),(19,NULL,'权限管理',NULL,3,'','2020-10-19 00:48:50','2020-10-19 00:48:50'),(20,NULL,'角色管理',19,1,'roleList','2020-10-19 00:48:50','2020-10-19 00:48:50'),(21,NULL,'权限管理',19,2,'rightsList','2020-10-19 00:48:50','2020-10-19 00:48:50');
/*!40000 ALTER TABLE `menu_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `quantity` bigint(20) DEFAULT NULL COMMENT '数量',
  `subject` varchar(255) DEFAULT NULL COMMENT '订单标题',
  `outTradeNo` varchar(255) DEFAULT NULL COMMENT '商户订单号',
  `total_amount` varchar(255) DEFAULT NULL COMMENT '总金额',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0：未支付 1：已支付',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1/正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'添加用户','user:add',1,'2020-08-20 23:01:59','2020-08-20 23:02:02'),(2,'删除用户','user:delete',1,'2020-08-20 23:01:59','2020-08-20 23:02:02'),(3,'修改用户','user:update',1,'2020-08-20 23:01:59','2020-08-20 23:02:02'),(4,'查询用户','user:select',1,'2020-08-20 23:01:59','2020-08-20 23:02:02'),(5,'添加管理员','admin:add',1,'2020-08-20 23:01:59','2020-08-20 23:02:02'),(6,'删除管理员','admin:delete',1,'2020-08-20 23:01:59','2020-08-20 23:02:02'),(7,'修改管理员','admin:update',1,'2020-08-20 23:01:59','2020-08-20 23:02:02'),(8,'查询管理员','admin:select',1,'2020-08-20 23:01:59','2020-08-20 23:02:02');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0/下架 1/上架',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,10,'1G1天',2,0,'2020-08-20 22:46:51','2020-10-04 21:26:15'),(2,1,'10G30天',3,1,'2020-08-20 22:46:51','2020-09-29 00:00:54'),(3,1,'30G30天',6,1,'2020-08-20 22:46:51','2020-08-20 22:47:18'),(4,1,'无限流量30天',10,1,'2020-08-20 22:46:51','2020-08-20 22:47:18');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1/正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'系统管理员',1,'2020-08-20 22:41:14','2020-08-21 01:18:02'),(2,'普通用户',1,'2020-08-20 22:41:14','2020-08-21 01:18:02'),(13,'一级代理',1,'2020-08-21 01:18:02','2020-08-21 01:18:02'),(14,'二级代理',1,'2020-08-21 01:18:02','2020-08-21 01:18:02'),(15,'三级代理',1,'2020-08-21 01:18:02','2020-08-21 01:18:02');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1/正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(2,1,2,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(3,1,3,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(4,1,4,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(5,1,5,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(6,1,6,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(7,1,7,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(8,1,8,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(9,2,1,1,'2020-08-20 23:49:27','2020-08-20 23:49:27'),(10,2,2,1,'2020-08-20 23:50:00','2020-08-20 23:50:00'),(11,2,3,1,'2020-08-20 23:50:00','2020-08-20 23:51:18'),(12,2,4,1,'2020-08-20 23:50:00','2020-08-20 23:51:18');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq号',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0/禁止 1/正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'admin','123456','admin@qq.com',NULL,NULL,1,'2020-09-11 00:48:27','2020-09-20 23:46:32'),(2,1,'123','123456','123@qq.com',NULL,NULL,1,'2020-09-11 00:48:27','2020-09-11 00:49:06');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-19  1:01:20

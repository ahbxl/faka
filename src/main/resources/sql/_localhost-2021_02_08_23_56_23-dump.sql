-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: speech
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
-- Table structure for table `file_info`
--

DROP TABLE IF EXISTS `file_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `report_id` bigint(20) DEFAULT NULL COMMENT '报告id',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `content` varchar(255) DEFAULT NULL COMMENT '语音识别后的内容',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `time` int(11) DEFAULT NULL COMMENT '语音时长',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '文件状态 正常/1、异常/0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '报告名称',
  `comment` varchar(255) DEFAULT NULL COMMENT '注释',
  `read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '已读 1/已读 0/未读',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 正常/1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `state` tinyint(4) DEFAULT '1' COMMENT '状态 1/正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sensitive_word`
--

DROP TABLE IF EXISTS `sensitive_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sensitive_word` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 正常/1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='敏感词';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账号状态 禁用用户/0、普通用户/1、管理员/2',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `state` tinyint(4) DEFAULT '1' COMMENT '状态 1/正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色的关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-08 23:56:24

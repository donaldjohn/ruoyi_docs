/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : ry

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2018-08-20 11:14:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_email
-- ----------------------------
DROP TABLE IF EXISTS `sys_email`;
CREATE TABLE `sys_email` (
  `email_id` int(11) NOT NULL auto_increment COMMENT '邮箱编号',
  `email_site` int(1) NOT NULL default '0' COMMENT '0：站内信；1：站外信',
  `form_user` int(11) default NULL COMMENT '用户编号，发送方',
  `to_user` int(11) default NULL COMMENT '用户编号，接收方',
  `to_user_email` varchar(30) default '' COMMENT '接收方邮件地址',
  `email_subject` varchar(30) NOT NULL COMMENT '邮件主题',
  `email_content` text NOT NULL COMMENT '邮件内容',
  `email_status` int(1) default '0' COMMENT '邮件状态(0：正常；1：已删除）',
  `email_type` varchar(50) default '' COMMENT '邮件类型(例：工作，广告，文档等，可在字典中配置)',
  `email_folder` varchar(50) default '1' COMMENT '邮件形式(例：收件箱，重要，草稿，垃圾箱等，可在字典中配置))',
  `email_label` varchar(50) default '' COMMENT '邮件标签(例：朋友；音乐，家庭，自定义标签，可在字典中配置)',
  `send_status` int(1) default '0' COMMENT '发送邮件状态(0：成功；1：失败)',
  `create_by` varchar(20) default '' COMMENT '创建者',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

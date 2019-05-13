/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : ry

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2018-12-27 16:19:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_files
-- ----------------------------
DROP TABLE IF EXISTS `sys_files`;
CREATE TABLE `sys_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT '',
  `status` char(1) DEFAULT '0',
  `file_name` varchar(255) NOT NULL DEFAULT '',
  `remark` varchar(255) NOT NULL DEFAULT '',
  `content` varchar(5000) DEFAULT '' COMMENT '文字内容',
  `suffix` varchar(20) DEFAULT '' COMMENT '后缀',
  `type` varchar(10) NOT NULL DEFAULT '0' COMMENT '类型（0代表图片 1代表视频）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by_name` varchar(50) DEFAULT '' COMMENT '创建人姓名',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by_name` varchar(50) DEFAULT '' COMMENT '修改人名称',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='文件上传表';

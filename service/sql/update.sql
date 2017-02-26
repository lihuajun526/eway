CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `openid` int(11) DEFAULT NULL,
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `name` varchar(64) DEFAULT NULL,
  `roleid` int(1) DEFAULT NULL COMMENT '角色：1超级管理员,2普通管理员,3创业者,4投资人,签约投资人',
  `status` int(1) DEFAULT '1' COMMENT '1新用户,2审核通过,3审核未通过',
  `companyid` int(11) DEFAULT NULL COMMENT '企业id',
  `call_time` int(11) DEFAULT NULL COMMENT '剩余通话时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
-- 2017年2月26日16:43:32
-- 
CREATE TABLE `t_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `summary` varchar(128) DEFAULT NULL COMMENT '摘要',
  `content` text COMMENT '内容文本',
  `html` text COMMENT '内容html',
  `index_pic` varchar(128) DEFAULT NULL COMMENT '首页图片',
  `ishead` int(1) DEFAULT NULL COMMENT '是否头条:0否,1是',
  `orderno` int(1) DEFAULT NULL COMMENT '排序',
  `status` int(1) DEFAULT '1' COMMENT '状态:0删除,1新稿,2发布',
  `enroll_type` int(1) DEFAULT NULL COMMENT '报名权限,0,所有人,1,需审核通过用户',
  `enroll_price` decimal(18,2) DEFAULT NULL COMMENT '报名费',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='活动';
-- 
CREATE TABLE `t_document_enroll` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '报名用户id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '报名用户名',
  `document_id` int(11) DEFAULT NULL COMMENT '报名的活动id',
  `create_time` date DEFAULT NULL COMMENT '报名时间',
  `trade_no` varchar(32) DEFAULT NULL COMMENT '商家订单号',
  `transaction_type` int(1) DEFAULT NULL COMMENT '支付方式,1,支付宝,2,微信',
  `transaction_no` varchar(64) DEFAULT NULL COMMENT '支付宝微信的交易单号',
  `pay_status` int(1) DEFAULT '0' COMMENT '支付状态,0,未支付,1,已支付',
  `pay_time` date DEFAULT NULL COMMENT '支付时间',
  `pay_price` decimal(18,2) DEFAULT NULL COMMENT '支付金额',
  `refund_status` int(1) DEFAULT '0' COMMENT '退款状态,0,未申请退款,1,申请退款中,2,退款处理中,3,退款成功,4,商家拒绝退款',
  `refund_time` date DEFAULT NULL COMMENT '退款状态更新时间',
  `refund_price` decimal(18,2) DEFAULT NULL COMMENT '退款金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动报名';
--
CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `goods_describe` text COMMENT '描述',
  `price` decimal(18,2) DEFAULT NULL COMMENT '价格',
  `items` text COMMENT '产品内容',
  `order_num` varchar(64) DEFAULT NULL COMMENT '排序',
  `status` int(1) DEFAULT '1' COMMENT '状态,0,删除',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `discount_price` decimal(18,2) DEFAULT NULL COMMENT '打折价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品套餐';
--
CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_path` text COMMENT '原文件存放地址',
  `small_path` text COMMENT '缩略图存放地址',
  `user_id` int(11) DEFAULT NULL COMMENT '上传者id',
  `company_id` int(11) DEFAULT NULL COMMENT '上传者公司id',
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
  `is_open` int(1) DEFAULT '1' COMMENT '是否公开,0,不公开,1,公开',
  `status` int(1) DEFAULT '1' COMMENT '是否已删除,0,已删除',
  `original_name` text COMMENT '原文件名',
  `content_type` varchar(32) DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件';
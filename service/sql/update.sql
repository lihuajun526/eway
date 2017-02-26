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
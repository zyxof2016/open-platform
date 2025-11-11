-- 用户服务数据库初始化脚本

-- 创建用户表
CREATE TABLE IF NOT EXISTS `op_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `version` bigint DEFAULT 0 COMMENT '版本号',
  `username` varchar(50) NOT NULL UNIQUE COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` varchar(20) DEFAULT 'ACTIVE' COMMENT '状态',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 插入默认用户数据
INSERT IGNORE INTO `op_user` (`id`, `username`, `password`, `email`, `phone`, `status`, `real_name`, `created_time`, `updated_time`, `version`) VALUES
(1, 'admin', '$2a$10$8K1u.9yL3Z4z9R3oJ1u.9eY5z9R3oJ1u.9yL3Z4z9R3oJ1u.9e', 'admin@example.com', '13800138000', 'ACTIVE', '管理员', NOW(), NOW(), 0),
(2, 'user', '$2a$10$8K1u.9yL3Z4z9R3oJ1u.9eY5z9R3oJ1u.9yL3Z4z9R3oJ1u.9e', 'user@example.com', '13800138001', 'ACTIVE', '普通用户', NOW(), NOW(), 0);
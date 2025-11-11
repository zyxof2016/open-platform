-- 认证服务数据库初始化脚本

-- 创建认证令牌表
CREATE TABLE IF NOT EXISTS `op_auth_token` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint DEFAULT 0 COMMENT '版本号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `token` varchar(500) NOT NULL COMMENT '令牌',
  `refresh_token` varchar(500) DEFAULT NULL COMMENT '刷新令牌',
  `token_type` varchar(20) DEFAULT 'Bearer' COMMENT '令牌类型',
  `expires_in` datetime DEFAULT NULL COMMENT '过期时间',
  `scope` varchar(200) DEFAULT 'read write' COMMENT '权限范围',
  `status` varchar(20) DEFAULT 'ACTIVE' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_token` (`token`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_username` (`username`),
  KEY `idx_expires_in` (`expires_in`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='认证令牌表';

-- 插入默认认证令牌数据
INSERT IGNORE INTO `op_auth_token` (`id`, `user_id`, `username`, `token`, `refresh_token`, `token_type`, `expires_in`, `scope`, `status`, `created_time`, `updated_time`, `version`) VALUES
(1, 1, 'admin', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTUxNjIzOTAyMn0.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c', 'refresh_token_123456', 'Bearer', DATE_ADD(NOW(), INTERVAL 1 HOUR), 'read write', 'ACTIVE', NOW(), NOW(), 0);
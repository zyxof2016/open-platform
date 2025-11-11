-- API管理服务数据库初始化脚本

-- 创建API表
CREATE TABLE IF NOT EXISTS `op_api` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint DEFAULT 0 COMMENT '版本号',
  `name` varchar(100) NOT NULL COMMENT 'API名称',
  `path` varchar(200) NOT NULL COMMENT 'API路径',
  `method` varchar(10) NOT NULL COMMENT '请求方法',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `api_version` varchar(20) DEFAULT '1.0.0' COMMENT 'API版本',
  `status` varchar(20) DEFAULT 'ACTIVE' COMMENT '状态',
  `service_name` varchar(100) NOT NULL COMMENT '服务名称',
  `group_name` varchar(100) DEFAULT 'default' COMMENT '分组名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_path_method` (`path`, `method`),
  KEY `idx_service_name` (`service_name`),
  KEY `idx_status` (`status`),
  KEY `idx_group_name` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='API表';

-- 插入默认API数据
INSERT IGNORE INTO `op_api` (`id`, `name`, `path`, `method`, `description`, `api_version`, `status`, `service_name`, `group_name`, `created_time`, `updated_time`, `version`) VALUES
(1, '用户登录', '/api/v1/auth/login', 'POST', '用户登录接口', '1.0.0', 'ACTIVE', 'auth-service', 'auth', NOW(), NOW(), 0),
(2, '获取用户信息', '/api/v1/users/{id}', 'GET', '根据ID获取用户信息', '1.0.0', 'ACTIVE', 'user-service', 'user', NOW(), NOW(), 0),
(3, '创建用户', '/api/v1/users', 'POST', '创建新用户', '1.0.0', 'ACTIVE', 'user-service', 'user', NOW(), NOW(), 0);
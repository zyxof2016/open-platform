-- 日志服务数据库初始化脚本

-- 创建日志表
CREATE TABLE IF NOT EXISTS `op_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint DEFAULT 0 COMMENT '版本号',
  `trace_id` varchar(50) DEFAULT NULL COMMENT '链路追踪ID',
  `service_name` varchar(100) NOT NULL COMMENT '服务名称',
  `module_name` varchar(100) DEFAULT NULL COMMENT '模块名称',
  `operation` varchar(200) DEFAULT NULL COMMENT '操作',
  `method` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `url` varchar(500) DEFAULT NULL COMMENT '请求URL',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `request_params` text COMMENT '请求参数',
  `response_result` text COMMENT '响应结果',
  `time_consuming` bigint DEFAULT NULL COMMENT '耗时(毫秒)',
  `log_type` varchar(20) DEFAULT 'ACCESS' COMMENT '日志类型',
  `log_level` varchar(20) DEFAULT 'INFO' COMMENT '日志级别',
  `exception_detail` text COMMENT '异常详情',
  `status` varchar(20) DEFAULT 'SUCCESS' COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `idx_service_name` (`service_name`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_username` (`username`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_trace_id` (`trace_id`),
  KEY `idx_log_type` (`log_type`),
  KEY `idx_log_level` (`log_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='日志表';

-- 插入默认日志数据
INSERT IGNORE INTO `op_log` (`id`, `service_name`, `operation`, `method`, `url`, `user_id`, `username`, `log_type`, `log_level`, `status`, `created_time`, `updated_time`, `version`) VALUES
(1, 'user-service', '创建用户', 'POST', '/api/v1/users', 1, 'admin', 'ACCESS', 'INFO', 'SUCCESS', NOW(), NOW(), 0),
(2, 'auth-service', '用户登录', 'POST', '/api/v1/auth/login', 1, 'admin', 'ACCESS', 'INFO', 'SUCCESS', NOW(), NOW(), 0),
(3, 'api-management-service', '获取API列表', 'GET', '/api/v1/apis', 1, 'admin', 'ACCESS', 'INFO', 'SUCCESS', NOW(), NOW(), 0);
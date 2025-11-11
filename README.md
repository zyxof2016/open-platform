# 分布式开放平台微服务架构

基于COLA架构设计的分布式开放平台，采用微服务架构设计，支持高可用、可扩展的企业级应用。

## 项目结构

```
open-platform-microservices/
├── user-service/              # 用户服务
│   ├── user-api/              # API定义模块
│   ├── user-app/              # 应用层模块
│   ├── user-domain/           # 领域层模块
│   ├── user-infrastructure/   # 基础设施层模块
│   ├── user-adapter/          # 适配器层模块
│   └── user-common/           # 通用公共模块
├── auth-service/              # 认证服务
│   ├── auth-api/              # API定义模块
│   ├── auth-app/              # 应用层模块
│   ├── auth-domain/           # 领域层模块
│   ├── auth-infrastructure/   # 基础设施层模块
│   ├── auth-adapter/          # 适配器层模块
│   └── auth-common/           # 通用公共模块
├── api-management-service/    # API管理服务
│   ├── api-management-api/    # API定义模块
│   ├── api-management-app/    # 应用层模块
│   ├── api-management-domain/ # 领域层模块
│   ├── api-management-infrastructure/ # 基础设施层模块
│   ├── api-management-adapter/ # 适配器层模块
│   └── api-management-common/ # 通用公共模块
├── log-service/               # 日志服务
│   ├── log-api/               # API定义模块
│   ├── log-app/               # 应用层模块
│   ├── log-domain/            # 领域层模块
│   ├── log-infrastructure/    # 基础设施层模块
│   ├── log-adapter/           # 适配器层模块
│   └── log-common/            # 通用公共模块
├── gateway-service/           # 网关服务
└── pom.xml                    # 父项目配置
```

## 微服务架构说明

### 服务列表

1. **用户服务 (user-service)**
   - 端口: 8081
   - 功能: 用户管理、个人信息维护
   - 技术: Spring Boot, JPA, MySQL

2. **认证服务 (auth-service)**
   - 端口: 8082
   - 功能: 身份认证、权限管理、JWT令牌颁发
   - 技术: Spring Security, JWT, OAuth2

3. **API管理服务 (api-management-service)**
   - 端口: 8083
   - 功能: API注册、文档管理、访问控制、流量控制
   - 技术: Spring Boot, OpenAPI/Swagger

4. **日志服务 (log-service)**
   - 端口: 8084
   - 功能: 系统日志收集、分析、监控、审计
   - 技术: Spring Boot, Elasticsearch (可选)

5. **网关服务 (gateway-service)**
   - 端口: 9999
   - 功能: API网关、路由转发、负载均衡、安全控制
   - 技术: Spring Cloud Gateway, Nacos

### 技术栈

- **核心框架**: Spring Boot 3.x, Spring Cloud 2022.x
- **微服务治理**: Nacos 2.x (注册中心+配置中心)
- **API网关**: Spring Cloud Gateway
- **数据库**: MySQL 8.0
- **持久化**: MyBatis-Plus 3.5.3.1
- **缓存**: Redis 7.x
- **架构**: COLA 5.x架构 (Adapter/App/Domain/Infrastructure)
- **服务间通信**: OpenFeign + LoadBalancer
- **消息队列**: RabbitMQ / Apache Kafka (可选)
- **监控**: Spring Boot Admin, Micrometer, Prometheus (可选)
- **安全**: Spring Security, JWT, OAuth2

## 快速开始

### 环境准备

1. 安装 Java 17+
2. 安装 Maven 3.8+
3. 安装 MySQL 8.0
4. 安装 Redis 7.x
5. 安装 Nacos 2.x

### 启动顺序

1. 启动 Nacos 服务 (端口 8848)
   ```bash
   # 下载并启动Nacos
   sh startup.sh -m standalone
   ```

2. 启动 MySQL 服务 (端口 3306)
   ```bash
   # 确保MySQL服务已启动
   systemctl start mysql
   ```

3. 启动 Redis 服务 (端口 6379)
   ```bash
   # 启动Redis服务
   redis-server
   ```

4. 初始化数据库
   ```bash
   # 执行数据库脚本
   mysql -u root -p < scripts/db/init_db.sql
   ```

5. 配置Nacos
   ```bash
   # 将配置文件导入Nacos
   # 可通过Nacos控制台或API导入scripts/nacos/目录下的配置文件
   ```

6. 启动各个微服务:
   ```bash
   # 用户服务
   cd user-service && mvn spring-boot:run
   
   # 认证服务
   cd auth-service && mvn spring-boot:run
   
   # API管理服务
   cd api-management-service && mvn spring-boot:run
   
   # 日志服务
   cd log-service && mvn spring-boot:run
   
   # 网关服务
   cd gateway-service && mvn spring-boot:run
   ```

### 访问地址

- 网关服务: http://localhost:9999
- Nacos控制台: http://localhost:8848/nacos
- 用户服务API: http://localhost:8081/api/v1/users
- 认证服务API: http://localhost:8082/api/v1/auth
- API管理服务API: http://localhost:8083/api/v1/apis
- 日志服务API: http://localhost:8084/api/v1/logs

## COLA架构说明

### 四层架构

1. **Adapter层** (适配器层)
   - 负责处理外部请求，如Controller、消息监听器等
   - 对外提供REST API接口
   - 处理HTTP请求/响应转换

2. **Application层** (应用层)
   - 协调业务流程，负责参数校验、上下文组装
   - 调用领域层进行业务处理
   - 事务管理、安全控制

3. **Domain层** (领域层)
   - 封装核心业务逻辑
   - 包含实体、值对象、领域服务、仓储接口
   - 实现领域驱动设计(DDD)

4. **Infrastructure层** (基础设施层)
   - 实现技术细节，如数据库操作、外部服务调用
   - 提供领域层所需的网关实现
   - 数据访问、消息队列、缓存等技术实现

### 架构优势

1. **职责分离**: 各层职责清晰，便于维护和扩展
2. **可测试性**: 领域层独立，便于单元测试
3. **可扩展性**: 支持水平扩展和功能扩展
4. **技术解耦**: 业务逻辑与技术实现分离

### COLA组件

项目现在使用COLA 5.x版本的组件化架构，主要包括：
- `cola-component-extension-starter`：扩展点组件，用于实现业务扩展
- `cola-component-dto`：DTO组件，定义了统一的数据传输格式
- 本地实现了BizException和SysException异常处理类
- 本地实现了Response、SingleResponse和MultiResponse响应类

### 持久化方案

项目采用MyBatis-Plus作为持久化框架，实现业务逻辑与技术实现的分离：
- Domain层实体保持纯净，不包含任何持久化注解
- Infrastructure层定义数据对象(DO)，包含MyBatis-Plus注解
- 提供仓储接口在Domain层，仓储实现类在Infrastructure层
- 通过数据对象与领域实体的转换实现持久化操作

## 服务间通信

服务间通信采用以下机制：

1. **同步通信**: OpenFeign声明式HTTP客户端
2. **异步通信**: RabbitMQ/Kafka消息队列
3. **服务发现**: Nacos服务注册与发现
4. **负载均衡**: Spring Cloud LoadBalancer

### 服务调用示例

```java
// 用户服务调用认证服务示例
@FeignClient(name = "auth-service")
public interface AuthServiceClient {
    
    @PostMapping("/api/v1/auth/validate")
    Result<Boolean> validateToken(@RequestParam String token);
}
```

## 扩展性设计

### 1. 事件驱动架构
- 通过领域事件实现服务解耦
- 支持异步处理和事件溯源

### 2. 扩展点机制
- 支持业务逻辑的动态扩展
- 基于策略模式的插件化设计

### 3. 微服务拆分
- 按业务域拆分服务，支持独立部署和扩展
- 每个服务可独立技术选型

### 4. 配置中心
- 通过Nacos实现配置统一管理
- 支持配置热更新

## 部署与运维

### 容器化部署

每个服务都支持Docker容器化部署：

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Kubernetes部署

支持Kubernetes编排部署：

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-service
spec:
  replicas: 3
  selector:
    matchLabels:
      app: user-service
  template:
    metadata:
      labels:
        app: user-service
    spec:
      containers:
      - name: user-service
        image: openplatform/user-service:latest
        ports:
        - containerPort: 8081
```

### 监控与告警

- 集成Spring Boot Actuator
- 支持Prometheus指标收集
- 集成ELK日志分析
- 支持自定义健康检查

## 开发规范

### 代码规范
1. 遵循Google Java Style Guide
2. 使用Lombok减少样板代码
3. 统一异常处理机制
4. 统一响应格式

### API设计规范
1. 遵循RESTful API设计原则
2. 统一错误码和错误信息
3. API版本控制
4. 统一认证授权机制

### 数据库设计规范
1. 遵循数据库设计范式
2. 统一命名规范
3. 索引优化策略
4. 数据库连接池配置

## 贡献指南

### 开发流程
1. Fork项目
2. 创建功能分支
3. 提交代码
4. 发起Pull Request

### 代码审查
1. 遵循代码规范
2. 确保单元测试覆盖率
3. 提供充分的文档说明
4. 通过CI/CD流水线检查

## 许可证

本项目采用Apache License 2.0许可证。
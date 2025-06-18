# Hydro Meteo Mock Service

这是一个基于Spring Boot的水文气象模拟服务项目。

## 项目结构

```
src/main/java/com/example/hydrometeomock/
├── controller/    # 控制器层
├── service/       # 服务层
├── model/         # 数据模型
└── HydroMeteoMockApplication.java  # 应用程序入口
```

## 技术栈

- Spring Boot 2.7.0
- Java 11
- Maven
- Lombok

## 快速开始

1. 确保已安装Java 11和Maven
2. 克隆项目
3. 运行 `mvn spring-boot:run`
4. 访问 `http://localhost:8080`

## 开发指南

- 遵循标准的Spring Boot项目结构
- 使用Lombok简化代码
- 遵循RESTful API设计规范 

## Docker 一键部署

### 1. 安装 Docker
- 请先在本机安装并启动 Docker Desktop（[官方下载地址](https://www.docker.com/products/docker-desktop/)）。

### 2. 打包 Spring Boot 项目
```bash
mvn clean package -DskipTests
```

### 3. 构建 Docker 镜像
```bash
docker build -t hydro-meteo-mock:latest .
```

### 4. 运行 Docker 容器
```bash
docker run -d -p 11096:11096 --name hydro-meteo-mock hydro-meteo-mock:latest
```

### 5. 访问服务
浏览器访问：http://localhost:11096

---
如需自定义端口或外部配置，请修改 application.yml 和 Dockerfile 中的端口配置。 
# 使用官方 OpenJDK 11 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制jar包到容器中（请确保jar包名与实际一致）
COPY target/hydro-meteo-mock-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口（与application.yml一致）
EXPOSE 11096

# 启动Spring Boot应用
ENTRYPOINT ["java", "-jar", "app.jar"] 
# Spring-dubbo-skeleton

这是一个基于 Spring Dubbo 的快速开发脚手架。

## 服务架构图

![image](https://images.cnblogs.com/cnblogs_com/yxhblogs/1674406/o_200602001310Spring-Dubbo.png)

`skeleton-base` 主要功能是：基本组件及依赖版本的定义、配置项、Maven 仓库。   
`skeleton-parent` 主要功能是：定义父级依赖。   
`skeleton-foundation` 主要功能是：定义基础组件。   
`skeleton-user-service` 主要功能是：实现用户的相关功能，例如：登录、注册等。   
`skeleton-order-service` 主要功能是：实现订单的创建、查询等。   
`skeleton-outside-service` 主要功能是：调用第三方服务。   
`skeleton-gateway-service` 主要功能是：聚合所有的功能，给终端提供 `Restful` 接口。   

## `Docker` 方式部署 `MySQL` 数据库

这里尽量使用 `MySQL 5.7` 版本，`8.0` 版本貌似有坑。

```
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```

创建相应数据库及数据表

可以使用这里面的 [SQL 语句](https://github.com/yxhsea/spring-dubbo-skeleton/blob/master/nacos_config.sql)。

## `Docker` 方式部署单机版的 `Nacos`

```
docker run -d \
--name nacos-server \
-e PREFER_HOST_MODE=hostname \
-e MODE=standalone \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=127.0.0.1 \
-e MYSQL_SERVICE_PORT=3306 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=123456 \
-e MYSQL_SERVICE_DB_NAME=nacos_config \
-p 8848:8848 \
nacos/nacos-server
```

## 本地访问 Nacos 控制台 

- 访问地址：http://localhost:8848/nacos
- 账号密码：nacos / nacos

## 在 `Nacos` 配置相应的服务

- [skeleton-user-service 相应配置](https://github.com/yxhsea/spring-dubbo-skeleton/blob/master/skeleton-user-service/README.md)
- [skeleton-order-service 相应配置](https://github.com/yxhsea/spring-dubbo-skeleton/blob/master/skeleton-order-service/README.md)
- [skeleton-outside-service 相应配置](https://github.com/yxhsea/spring-dubbo-skeleton/blob/master/skeleton-outside-service/README.md)
- [skeleton-gateway-service 相应配置](https://github.com/yxhsea/spring-dubbo-skeleton/blob/master/skeleton-gateway-service/README.md)

## `Docker` 方式部署 `Zookeeper` 注册中心

```
docker run -d -p 2181:2181 --name=zookeeper --privileged zookeeper
```

## `Docker` 方式部署 `Dubbo Admin`

```
docker run -d \
-p 8080:8080 \
-e dubbo.registry.address=zookeeper://127.0.0.1:2181 \
-e dubbo.admin.root.password=root \
-e dubbo.admin.guest.password=guest \
chenchuxin/dubbo-admin 
```

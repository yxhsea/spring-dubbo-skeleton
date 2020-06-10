# skeleton-user-service

用户服务

## Nacos 配置

### Dubbo 配置

- Data ID

`dubbo.properties`

- Group

`user-service`

- 配置内容

```
user.service.dubbo.registry.address=zookeeper://127.0.0.1:2181
user.service.dubbo.registry.timeout=3000
user.service.dubbo.registry.protocol=zookeeper
user.service.dubbo.scan.base-packages=com.skeleton.**
user.service.dubbo.protocol=dubbo
user.service.dubbo.protocol.port=20881
user.service.dubbo.provider.timeout=3000
user.service.dubbo.provider.retries=0
user.service.dubbo.provider.loadbalance=leastactive
user.service.dubbo.consumer.check=false
user.service.dubbo.monitor.protocol=registry
```

### DB 配置

- Data ID 

`db.properties`

- Group

`user-service`

- 配置内容

```
datasource.driver-class-name=com.mysql.cj.jdbc.Driver
datasource.url=jdbc:mysql://127.0.0.1:3308/user_service?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&useSSL=true
datasource.username=root
datasource.password=123456
```

### Redis 配置

- Data ID 

`db.properties`

- Group

`user-service`

- 配置内容

```
redis.host=127.0.0.1
redis.port=6376
redis.auth=
redis.maxTotal=100
redis.maxIdle=20
redis.maxWaitMillis=10000
redis.testOnBorrow=true
redis.testOnReturn=true
redis.dbIndex=1
```

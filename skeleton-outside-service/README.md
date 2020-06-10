# skeleton-outside-service

第三方平台接入服务

## Nacos 配置

### Dubbo 配置

Data ID:

`dubbo.properties`

Group：

`outside.service`

配置内容：

```
outside.service.dubbo.registry.address=zookeeper://120.77.78.63:20016
outside.service.dubbo.registry.timeout=3000
outside.service.dubbo.registry.protocol=zookeeper
outside.service.dubbo.scan.base-packages=com.skeleton.**
outside.service.dubbo.protocol=dubbo
outside.service.dubbo.protocol.port=20883
outside.service.dubbo.provider.timeout=3000
outside.service.dubbo.provider.retries=0
outside.service.dubbo.provider.loadbalance=leastactive
outside.service.dubbo.consumer.check=false
outside.service.dubbo.monitor.protocol=registry
```

### 第三方配置

Data ID:

`third_party.properties`

Group：

`outside.service`

配置内容：

```
outside.url=http://127.0.0.1:9090
outside.access-token=73f7835ea3c20db1a56257e27b6ce40
```

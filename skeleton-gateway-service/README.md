# skeleton-gateway-service

网关服务

## Nacos 配置

### Dubbo 配置

Data ID:

`dubbo.properties`

Group：

`gateway.service`

配置内容：

```
outside.service.dubbo.registry.address=zookeeper://120.77.78.63:20016
outside.service.dubbo.registry.timeout=3000
outside.service.dubbo.registry.protocol=zookeeper
outside.service.dubbo.scan.base-packages=com.skeleton.**
outside.service.dubbo.protocol=dubbo
outside.service.dubbo.protocol.port=20880
outside.service.dubbo.provider.timeout=3000
outside.service.dubbo.provider.retries=0
outside.service.dubbo.provider.loadbalance=leastactive
outside.service.dubbo.consumer.check=false
outside.service.dubbo.monitor.protocol=registry
```

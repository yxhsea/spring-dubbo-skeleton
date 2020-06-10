# skeleton-order-service

订单服务

## Nacos 配置

### Dubbo 配置

- Data ID

`dubbo.properties`

- Group

`order-service`

- 配置内容

```
user.service.dubbo.registry.address=zookeeper://127.0.0.1:2181
user.service.dubbo.registry.timeout=3000
user.service.dubbo.registry.protocol=zookeeper
user.service.dubbo.scan.base-packages=com.skeleton.**
user.service.dubbo.protocol=dubbo
user.service.dubbo.protocol.port=20882
user.service.dubbo.provider.timeout=3000
user.service.dubbo.provider.retries=0
user.service.dubbo.provider.loadbalance=leastactive
user.service.dubbo.consumer.check=false
user.service.dubbo.monitor.protocol=registry
```

# RabbitMQ Java
使用idea运行RabbitMQ 并实现通讯
## 环境要求
* CentOS-Stream9
* Java 17
* Apache Maven 3.8.7

## 参数设置

### 修改代码中个人配置信息以达成互相通讯的功能


## 启动rabbitmq-server
```bash
systemctl start rabbitmq-server
```

## 查看rabbitmq服务状态
```bash
systemctl status rabbitmq-server
```

## 在idea中运行receive
```bash
Waiting for messages. To exit press CTRL+C
Received 'Hello World!'

```

## 在idea中再运行send实现通讯目的，打印配置文件中的内容
```bash
Sent 'Hello World!'
```

## 此时便达成一个简单的rabbitmq通讯
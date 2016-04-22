#当前：
1.  启动时检查
    1.1 直连时启动检查
        provider配置：registry address="N/A" consumer配置：url="dubbo://127.0.0.1:20880/xxxxService
    1.2 注册中心选择
        1.2.1   simple （不可用，无提供类）
        1.2.2   multicast
                提供者：address选址范围必须在 224.0.0.0 - 239.255.255.255
                消费者：
                1.2.2.1 可以参考直连 注意端口的选用不是广播的地址而是提供服务的地址
                1.2.2.2 使用muiticast  address选址和提供者的ip一致 端口也和多播的端口一致
                其他：如果一个机器上同时启了多个消费者进程，消费者需声明unicast=false，否则只会有一个消费者能收到消息
        1.2.3   zookeeper
                1.2.3.1 注意事项：
                    1.2.3.1.1   当设置<dubbo:registry check="false" />时，记录失败注册和订阅请求，后台定时重试。
                    1.2.3.1.2   可通过<dubbo:registry username="admin" password="1234" />设置zookeeper登录信息。
                    1.2.3.1.3   可通过<dubbo:registry group="lance" />设置zookeeper的根节点，不设置将使用dubbo作为根节点。
                    1.2.3.1.4   支持*号通配符<dubbo:reference group="*" version="*" />，可订阅服务的所有分组和所有版本的提供者。
                    1.2.3.1.5   必须使用zkclient无论是zkclient还是curator模式com.github.sgroschupf
                    1.2.3.1.6   必须使用netflix的curator客户端而不能使用apache的，不必使用zookeeper客户端
                    1.2.3.1.7   当使用zk作为注册中心，无法连接时会一直重试直到最大值

                1.2.3.2 使用zkclient作为客户端
                1.2.3.2 只用curator作为客户端
                1.2.3.3 同一注册中心分为多组
                        <dubbo:registry id="chinaRegistry" protocol="zookeeper" address="10.20.153.10:2181" group="china" />
                        <dubbo:registry id="intlRegistry" protocol="zookeeper" address="10.20.153.10:2181" group="intl" />
                1.2.3.4 zookeeper集群版的配置
        1.2.4   redis
2.  集群容错
3.  负载均衡
4.  线程模型
5.  直连提供者
6.  只订阅
7.  只注册
8.  静态服务
9.  多协议
10. 多注册中心
11. 服务分组
12. 多版本
13. 分组聚合
14. 参数验证
15. 结果缓存
16. 泛化引用
17. 泛化实现
18. 回声测试
19. 上下文信息
20. 隐式传参
21. 异步调用
22. 本地调用
23. 参数回调
24. 事件通知
25. 本地存根
26. 本地伪装
27. 延迟暴露
28. 并发控制
29. 连接控制
30. 延迟连接
31. 粘滞连接
32. 令牌验证
33. 路由规则
34. 配置规则
35. 服务降级
36. 优雅停机
37. 主机绑定
38. 日志查找
39. 服务容器
40. 引用缓存


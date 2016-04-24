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
                1.2.3.1 注意事项
                    1.2.3.1.1   使用jedis的版本不要太高因为高版本引用commons-pool2 dubbo使用commons-pool 推荐2.0.0
                    1.2.3.1.2   使用redis作为注册中心要注意日志打印，一旦失去连接会打印大量日志
2.  集群容错
    2.1 Failover Cluster    failover（默认）    失败自动切换，当出现失败，重试其它服务器
        2.1.1   应用场景：通常用于读操作，但重试会带来更长延迟
        2.1.2   使用方式：可通过retries="2"来设置重试次数(不含第一次)
        2.1.3   场景复现：provider模拟超时时间，接口调用时记录调用次数
    2.2 Failfast Cluster    failfast    快速失败，只发起一次调用，失败立即报错
        2.2.1   应用场景：通常用于非幂等性的写操作，比如新增记录
    2.3 Failsafe Cluster    failsafe    失败安全，出现异常时，直接忽略
        2.3.1   应用场景：   通常用于写入审计日志等操作
    2.4 Failback Cluster    failback    失败自动恢复，后台记录失败请求，定时重发
        2.4.1   应用场景：   通常用于消息通知操作
    2.5 Forking Cluster     forking     并行调用多个服务器，只要一个成功即返回
        2.5.1   应用场景：   通常用于实时性要求较高的读操作，但需要浪费更多服务资源
        2.5.2   使用方式：   可通过forks="2"来设置最大并行数
    2.6 Broadcast Cluster   broadcast   广播调用所有提供者，逐个调用，任意一台报错则报错
        2.6.1   应用场景：   通常用于通知所有提供者更新缓存或日志等本地资源信息
    2.7 注意事项
        2.7.1   配置的优先顺序 (作用于 timeout retries, loadbalance, actives)
                reference method --> service method --> reference --> service --> consumer --> provider
        2.7.2   原则上超时时间应该在服务放配置，因为一个方法需要执行多长时间，服务提供方更清楚，
                如果一个消费方同时引用多个服务，就不需要关心每个服务的超时设置
3.  负载均衡
    3.1 Random LoadBalance  random  随机，按权重设置随机概率
        3.1.1   应用场景    随机，按权重设置随机概率
        3.1.2   场景复现    provider启动5个然后使用消费者循环调用
    3.2 RoundRobin LoadBalance  roundrobin 轮循，按公约后的权重设置轮循比率。
        3.2.1   应用场景
        3.2.2   场景复线    provider启动5个然后使用消费者循环调用 通知增大提供者的处理时间
4.  线程模型（性能调优）
    4.1 线程数量通过 threads 来指定
    4.1 threadpool
        4.1.1   fixed   线程为固定模式 如果线程不够的话就会报错Thread pool is EXHAUSTED
        4.1.2   cache   缓存线程池，空闲一分钟自动删除，需要时重建
        4.1.3   limited  可伸缩线程池，但池中的线程数只会增长不会收缩 为避免收缩时突然来了大流量引起的性能问题
    4.2 dispatcher
        4.2.1   all         所有消息都派发到线程池，包括请求，响应，连接事件，断开事件，心跳等
        4.2.2   direct      所有消息都不派发到线程池，全部在IO线程上直接执行
        4.2.3   message     只有请求响应消息派发到线程池，其它连接断开事件，心跳等消息，直接在IO线程上执行
        4.2.4   execution   只请求消息派发到线程池，不含响应，响应和其它连接断开事件，心跳等消息，直接在IO线程上执行 
        4.2.5   connection  在IO线程上，将连接断开事件放入队列，有序逐个执行，其它消息派发到线程池
5.  直连提供者(参考1）
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


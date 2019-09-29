# java-service-exercises

Java相关工具库、中间件等使用案例

### 编译构建

* jse-maven
  * mvn-shade-package（参考《Maven 实战》第 3/4/7/8 章）
    * maven-shade-plugin 打包插件的使用
    * 依赖/插件/聚合/继承
  * mvn-assembly-package（参考《Maven 实战》第 14 章）
    * maven-assembly-plugin 打包插件的使用
    * maven-dependency-plugin 插件的使用
    * resource/filtering/profile
  * mvn-jetty-webapp
    * jetty-maven-plugin 插件的使用
  * mvn-sample-webapp（参考《Maven 实战》第 4/5/8/10/12 章）
    * 使用 maven 构建 web 应用
    
### 开发框架

* jse-spring
  * knights（参考《Spring 实战》第 1 章）
    * DI/AOP/xml 配置
    * Mockito 的使用
  * soundsystem（参考《Spring 实战》第 2 章）
    * 自动化装配 bean（scan）
    * 通过 Java 代码装配 bean（code）
    * 通过 xml 装配 bean（xml）
  * restfun（参考《Spring 实战》第 2/3 章）
    * 导入和混合配置
    * 环境与 profile
    * 条件化的 bean
  * desserteater（参考《Spring 实战》第 3 章）
    * 标示首选的 bean
    * 限定自动装配的 bean
  * person（参考《Spring 实战》第 3 章）
    * 运行时注入，包括 Spring 的 Environment/属性占位符/SpEL 表达式
  * aop（参考《Spring 实战》第 4 章）
    * 通过 scan 和 code 方式实现 aop（concert）
    * 通过 xml 方式实现 aop（soundsystem）
  * conflict
    * 解决 bean id 冲突
    * @Autowired 与 @Resource 的区别
  * mockito
    * 使用 mockito 对 bean 进行 mock 和 spy
    * 将 mock 和 spy 注入到代理 bean

* jse-springmvc
  * springmvc-spittr（参考《Spring实战》第 5/7 章）
    * 控制器、为控制器添加通知
    * 请求输入、表单处理、multipart 形式数据处理
    * 异常处理、跨重定向请求传递数据
    * 请求过滤、用户认证、请求拦截
        
### 日志

* jse-log
  * log4j 的使用
  * log4j2 的使用
  * slf4j 搭配 log4j 或 log4j2 输出日志
  
### 单元测试

* jse-junit（参考 [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/#writing-tests)）
  * 初始化和销毁（StandardTests）
  * 断言（AssertionsDemo）
  * 前置条件（AssumptionsDemo）
  * 跳过测试（DisabledDemo）
  
* jse-mockito（参考 [mockito 2.22.0](https://static.javadoc.io/org.mockito/mockito-core/2.22.0/org/mockito/Mockito.html)）
  * 返回期望值（StubbingTest）
  * 验证行为（VerifyTest）
  * 参数匹配（ArgumentMatchersTest）
  * 监控真实对象（SpyTest）
  * 注解的使用（AnnotationsTest）

### 实用工具

* jse-lombok（参考 [Lombok features](https://www.projectlombok.org/features/all)）
  * 语法糖相关（`val/var/@NunNull/@Cleanup`）
  * POJO 相关（`@Getter/@Setter/@ToString/@EqualsAndHashCode/@Data/@Value`）
  * 构造相关（`@NoArgsConstructor/@RequiredArgsConstructor/@AllArgsConstructor/@Builder`）
  * 异常相关（`@SneakyThrows`）
  * 并发相关（`@Synchronized`）
  * 日志相关（`@Log`）
  
* jse-cglib（参考 [cglib: The missing manual](http://mydailyjava.blogspot.com/2013/11/cglib-missing-manual.html)）
  * 回调过滤（`CallbackFilter`）
  * 拦截器（`FixedValue/MethodInterceptor/NoOp.INSTANCE`）
  * 延迟加载(`LazyLoader/Dispatcher/ProxyRefDispatcher`)
  * bean 操作（`ImmutableBean/BeanGenerator/BeanCopier/BeanMap`）
  * 类型整合（`Mixin`）
  
* jse-serialize（参考 [kryo](https://github.com/EsotericSoftware/kryo)\/[protobuf](https://developers.google.cn/protocol-buffers/docs/overview)\/[protostuff](https://protostuff.github.io/docs/)\/[jvm-serializers](https://github.com/eishay/jvm-serializers/wiki)）
  * 使用原生方式进行序列化、反序列化
  * 使用 kryo 进行序列化、反序列化
  * 使用 protobuf 进行序列化、反序列化（proto2/proto3）
  * 使用 protostuff 进行序列化、反序列化（`ProtobufIOUtil/ProtostuffIOUtil`）
  * protobuf/protostuff 相互序列化、反序列化测试
  * 不同序列化方式的性能测试

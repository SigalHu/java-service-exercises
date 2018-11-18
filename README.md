# java-service-exercises

Java相关工具库、中间件等使用案例

### 编译构建

* jse-maven
  * mvn-shade-package（参考《Maven实战》第3/4/7/8章）
    * maven-shade-plugin打包插件的使用
    * 依赖/插件/聚合/继承
  * mvn-assembly-package（参考《Maven实战》第14章）
    * maven-assembly-plugin打包插件的使用
    * maven-dependency-plugin插件的使用
    * resource/filtering/profile
  * mvn-jetty-webapp
    * jetty-maven-plugin插件的使用
  * mvn-sample-webapp（参考《Maven实战》第4/5/8/10/12章）
    * 使用maven构建web应用
    
### 开发框架

  * jse-spring
    * knights（参考《Spring实战》第1章）
      * DI/AOP/xml配置
      * Mockito的使用
    * soundsystem（参考《Spring实战》第2章）
      * 自动化装配bean（scan）
      * 通过Java代码装配bean（code）
      * 通过xml装配bean（xml）
    * restfun（参考《Spring实战》第2/3章）
      * 导入和混合配置
      * 环境与profile
      * 条件化的bean
    * desserteater（参考《Spring实战》第3章）
      * 标示首选的bean
      * 限定自动装配的bean
    * person（参考《Spring实战》第3章）
      * 运行时注入，包括Spring的Environment/属性占位符/SpEL表达式
    * aop（参考《Spring实战》第4章）
      * 通过scan和code方式实现aop（concert）
      * 通过xml方式实现aop（soundsystem）
    * conflict
      * 解决bean id冲突
      * @Autowired与@Resource的区别
    * mockito
      * 使用mockito对bean进行mock和spy
      * 将mock和spy注入到代理bean
      
  * jse-springmvc
    * springmvc-spittr（参考《Spring实战》第5/7章）
      * 控制器、为控制器添加通知
      * 请求输入、表单处理、multipart形式数据处理
      * 异常处理、跨重定向请求传递数据
      * 请求过滤、用户认证、请求拦截
        
### 日志

* jse-log
  * log4j的使用
  * log4j2的使用
  * slf4j搭配log4j或log4j2输出日志
  
### 单元测试

* jse-junit（参考[junit5官网](https://junit.org/junit5/docs/current/user-guide/#writing-tests)）
  * 初始化和销毁（StandardTests）
  * 断言（AssertionsDemo）
  * 前置条件（AssumptionsDemo）
  * 跳过测试（DisabledDemo）
  
* jse-mockito（参考[mockito 2.22.0](https://static.javadoc.io/org.mockito/mockito-core/2.22.0/org/mockito/Mockito.html)）
  * 返回期望值（StubbingTest）
  * 验证行为（VerifyTest）
  * 参数匹配（ArgumentMatchersTest）
  * 监控真实对象（SpyTest）
  * 注解的使用（AnnotationsTest）

### 实用工具

* jse-lombok（参考[lombok官网](https://www.projectlombok.org/features/all)）
  * 语法糖相关（`val/var/@NunNull/@Cleanup`）
  * POJO相关（`@Getter/@Setter/@ToString/@EqualsAndHashCode/@Data/@Value`）
  * 构造相关（`@NoArgsConstructor/@RequiredArgsConstructor/@AllArgsConstructor/@Builder`）
  * 异常相关（`@SneakyThrows`）
  * 并发相关（`@Synchronized`）
  * 日志相关（`@Log`）
  
* jse-cglib（参考[CGLib 使用手册](https://www.jianshu.com/p/76a12f333e7a)）
  * 回调过滤（`CallbackFilter`）
  * 拦截器（`FixedValue/MethodInterceptor/NoOp.INSTANCE`）
  * 延迟加载(`LazyLoader/Dispatcher/ProxyRefDispatcher`)
  * bean操作（`ImmutableBean/BeanGenerator/BeanCopier`）

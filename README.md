# java-service-exercises

Java相关工具库、中间件等使用案例

### 编译构建

* jse-maven
  * mvn-shade-package（参考《Maven实战》第3/4/7/8章），主要包括：
    * maven-shade-plugin打包插件的使用
    * 依赖/插件/聚合/继承
  * mvn-assembly-package（参考《Maven实战》第14章），主要包括：
    * maven-assembly-plugin打包插件的使用
    * maven-dependency-plugin插件的使用
    * resource/filtering/profile
  * mvn-jetty-webapp
    * jetty-maven-plugin插件的使用
  * mvn-sample-webapp（参考《Maven实战》第4/5/8/10/12章）
    * 使用maven构建web应用
    
### 开发框架

  * jse-spring
    * knights（参考《Spring实战》第1章），主要包括：
      * DI/AOP/xml配置
      * Mockito的使用
    * soundsystem（参考《Spring实战》第2章），主要包括：
      * 自动化装配bean（scan）
      * 通过Java代码装配bean（code）
      * 通过xml装配bean（xml）
    * restfun（参考《Spring实战》第2/3章），主要包括：
      * 导入和混合配置
      * 环境与profile
      * 条件化的bean
    * desserteater（参考《Spring实战》第3章），主要包括：
      * 标示首选的bean
      * 限定自动装配的bean
    * person（参考《Spring实战》第3章），主要包括：
      * 运行时注入，包括Spring的Environment/属性占位符/SpEL表达式
    * aop（参考《Spring实战》第4章），主要包括：
      * 通过scan和code方式实现aop（concert）
      * 通过xml方式实现aop（soundsystem）
    * conflict
      * 解决bean id冲突
      * @Autowired与@Resource的区别
        
### 日志

* jse-log，主要包括：
  * log4j的使用
  * log4j2的使用
  * slf4j搭配log4j或log4j2输出日志
  
### 单元测试

* jse-junit（参考[junit5官网](https://junit.org/junit5/docs/current/user-guide/#writing-tests)），主要包括：
  * 初始化和销毁（StandardTests）
  * 断言（AssertionsDemo）
  * 前置条件（AssumptionsDemo）
  * 跳过测试（DisabledDemo）
  
* jse-mockito（参考[mockito 2.22.0](https://static.javadoc.io/org.mockito/mockito-core/2.22.0/org/mockito/Mockito.html)）

### 实用工具

* jse-lombok（参考[lombok官网](https://www.projectlombok.org/features/all)），主要包括：
  * 语法糖相关（`val/var/@NunNull/@Cleanup`）
  * POJO相关（`@Getter/@Setter/@ToString/@EqualsAndHashCode/@Data/@Value`）
  * 构造相关（`@NoArgsConstructor/@RequiredArgsConstructor/@AllArgsConstructor/@Builder`）
  * 异常相关（`@SneakyThrows`）
  * 并发相关（`@Synchronized`）
  * 日志相关（`@Log`）

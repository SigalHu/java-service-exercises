# java-service-exercises

Java相关工具库、中间件等使用案例

### 构建

* jse-maven
  * mvn-shade-package（参考《Maven实战》第3/4/7/8章），主要包括：
    * maven-shade-plugin打包插件的使用
    * 依赖、插件、聚合、继承
  * mvn-assembly-package（参考《Maven实战》第14章），主要包括：
    * maven-assembly-plugin打包插件的使用
    * maven-dependency-plugin插件的使用
    * resource、filtering、profile
  * mvn-jetty-webapp
    * jetty-maven-plugin插件的使用
  * mvn-sample-webapp（参考《Maven实战》第4/5/8/10/12章）
    * 使用maven构建web应用
    
### 日志

* jse-log，主要包括：
  * log4j的使用
  * log4j2的使用
  * slf4j搭配log4j或log4j2输出日志

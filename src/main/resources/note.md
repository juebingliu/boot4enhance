1、springboot项目启动时，只会扫描启动类路径下的所有包，可以在启动类上显示指定扫描路径：@ComponentScan(basePackages = {"com.blog.*,com.blog.ty.*"})

2、Thymeleaf的主要目标在于提供一种可被浏览器正确显示的、格式良好的模板创建方式，因此也可以用作静态建模。src/main/resources/templates默认是在这个目录下存放一下页面模板

3、springboot内部做到了静态资源的拦截设置

4、通过restController返回的是json对象，而要用到一般的页面渲染则用一般的controller注解就行

5、@Ignore("not ready yet")跳过该注解注释的测试方法

6、http://localhost:8080/swagger-ui.html swagger维护的restful api 文档

7、JPA使用：
spring.jpa.properties.hibernate.hbm2ddl.auto是hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：
create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

8、@Value("${属性名}"),可以直接读到属性值

9、在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，其中{profile}对应你的环境标识。spring.profiles.active=test'，启动命令：java -jar xxx.jar --spring.profiles.active=test

10、@Async与isDone()

11、execution表达式

12、EnableCaching注解按照顺序去找缓存提供者

13、aop切面实现多数据源

14、spring 状态机

15、LDAP

16、boot启动时间顺序,可以在服务启动之初添加一些加载项
    ApplicationStartingEvent
    ApplicationEnvironmentPreparedEvent
    ApplicationPreparedEvent
    ApplicationStartedEvent <= 新增的事件
    ApplicationReadyEvent
    ApplicationFailedEvent
    
17、关于Environment中的属性来源分散在启动的若干个阶段，并且按照特定的优先级顺序，也就是说一个属性值可以在不同的地方配置，但是优先级高的值会覆盖优先级低的值。

18、轮廓-profile


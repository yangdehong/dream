# 作业
## mybatis
### 题目
一、编程题
请完善自定义持久层框架IPersistence，在现有代码基础上添加、修改及删除功能。【需要采用getMapper方式】
二、作业资料说明：
1、提供资料：代码工程、验证及讲解视频。
2、讲解内容包含：题目分析、实现思路、代码讲解。
3、效果视频验证
### 说明
注意：这里不注重缓存、扩展、动态sql等，也不做异常捕获，只有一个主要流程，也就是使用getMapper方式的方式处理CRUD就可以。
补充接口ResultHandler和实现类SimpleResultHandler用来处理结果（TypeHandler不单独做了，直接在这里面）；接口ParamsHandler和SimpleParamsHandler用来处理参数；
0、在UserMapper.java和UserMapper.xml中新增CRUD的方法和标签sql
1、新增一个sql类型，用来标记sql要执行的操作
`public enum SqlTypeEnum {
UNKNOWN, INSERT, UPDATE, DELETE, SELECT;
}`
2、在XMLMapperBuilder解析出来的MappedStatement新增SqlTypeEnum类型的参数sqlTypeEnum
3、在getMapper的时候，要先通过通过sqlTypeEnum判断sql的类型
4、将生产PreparedStatement的代码放到ParamsHandler中
5、在SqlSession中新增CRUD的api，在Executor和实现类中补充CRUD的业务操作代码
6、新增的一个Date类型的参数，所以需要新增类型装换，就使用新增的ResultHandler来处理，简单些类型转换类，不做TypeHandler处理
### 代码模块
self_mybatis

## spring
### 题目
一、编程题
学员自定义@Service、@Autowired、@Transactional注解类，完成基于注解的IOC容器（Bean对象创建及依赖注入维护）和声明式事务控制，
写到转账工程中，并且可以实现转账成功和转账异常时事务回滚
注意考虑以下情况：
1）注解有无value属性值【@service（value=""） @Repository（value=""）】
2）service层是否实现接口的情况【jdk还是cglib】
二、作业资料说明：
1、提供资料：代码工程、验证及讲解视频、SQL脚本。
2、讲解内容包含：题目分析、实现思路、代码讲解。
3、效果视频验证
1）实现转账成功和转账异常时事务回滚。
2）展示和讲解自定义@Service、@Autowired、@Transactional注解类。
### 说明
分三步
1、使用注解将有bean对象加载到map缓存中，
1.1、定义出@MyComponent、@MyRepository、@MyService三个生成bean的注解
1.2、扫描启动类（SelfSpringApplication）的包以及子包，将有注解的类加载到map中（BeanFactory中）
2、使用注解将对象属性依赖注入到bean中
2.1、定义出@MyAutowired注入属性的注解
2.2、将1.2扫描出来的bean（如果没有扫描出来，是无法使用注解注入属性的），设置@MyAutowired注入的属性（BeanFactory中）
3、使用注解做出声明式的事务
3.1、定义出@MyTransactional作为事务的注解
3.2、在动态代理中直接获取注解，看是否存在，不存在则直接执行invoke
3.3、根据接口判断，这个是在1.2加入map的时候实例化判断是否存在@MyTransactional，如果存在就实例化动态代理对象，再根据是否有接口判断jdk还是cglib
由于我这里是springboot的测试类，这里不好处理，就不多写了
### 代码
self_spring  -->  ControllerTests

## springMVC
### 题目
一、编程题
手写MVC框架基础上增加如下功能
1）定义注解@Security（有value属性，接收String数组），该注解用于添加在Controller类或者Handler方法上，表明哪些用户拥有访问该Handler方法的权限（注解配置用户名）
2）访问Handler时，用户名直接以参数名username紧跟在请求的url后面即可，比如http://localhost:8080/demo/handle01?username=zhangsan
3）程序要进行验证，有访问权限则放行，没有访问权限在页面上输出
注意：自己造几个用户以及url，上交作业时，文档提供哪个用户有哪个url的访问权限
二、作业资料说明：
1、提供资料：代码工程、验证及讲解视频。
2、讲解内容包含：题目分析、实现思路、代码讲解。
3、效果视频验证
1）展示关键实现代码
2）有访问权限则放行，没有访问权限在页面上输出
### 说明
就是拦截器拦截权限注解内容
1、定义一个拦截器注解@MySecurity配置在Controller层
2、在MyDispatcherServlet初始自定义拦截器
2.1、定义抽象拦截器abstract class YdhInterceptor只有简单的一个拦截方法
2.2、定义@Secerity拦截器class SecurityInterceptor extends YdhInterceptor，详细的拦截业务
2.3、在初始化MyDispatcherServlet的时候加入到参数interceptors
3、MyDispatcherServlet的请求处理之前处理需要拦截的请求
### 代码
self_springmvc ->DemoController上设置拦截名称

## springboot
### 代码
self_springboot    http://localhost/index

## tomcat
### 题目
一、编程题
开发Minicat V4.0，在已有Minicat基础上进一步扩展，模拟出webapps部署效果 磁盘上放置一个webapps目录，webapps中可以有多个项目，例如demo1、demo2、demo3... 每个项目中含有servlet，可以根据请求url定位对应servlet进一步处理。
作业具体要求参考以下链接文档：
https://gitee.com/lagouedu/alltestfile/raw/master/tomcat/Tomcat%E4%BD%9C%E4%B8%9A%E5%A4%A7%E9%A2%98.pdf
作业资料说明：
1、提供资料：工程代码和自己的webapps以及访问路径、功能演示和原理讲解视频。
2、讲解内容包含：题目分析、实现思路、代码讲解。
3、效果视频验证：实现模拟tomcat多项目部署效果，访问多个不同项目可获得动态返回的内容。
### 说明
1、要定义mapper，mapper是数据service层的对象
2、有子项目demo，需要解析子项目的web.xml
### 代码
self_tomcat



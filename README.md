# dream

## self_mybatis
自定义mybatis框架

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
6、新增的一个Date类型的参数，所以需要新增类型装换，就使用新增的ResultHandler来处理

## mybatis
mybatis学习


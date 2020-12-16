package com.ydh.redsheep.self_spring.factory;

import com.fasterxml.jackson.core.util.Separators;
import com.ydh.redsheep.self_spring.SelfSpringApplication;
import com.ydh.redsheep.self_spring.anno.*;
import javafx.scene.control.Separator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author yangdehong
 *
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {

    /**
     * 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
     * 任务二：对外提供获取实例对象的接口（根据id获取）
     */
    private static Map<String,Object> map = new HashMap<>();  // 存储对象

    static {

        try {
            // 获取启动类的包
            String packageName = SelfSpringApplication.class.getPackage().getName();
            // 获取路径地址
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            // 迭代去加载有注解的类到map
            dirs.forEach(dir -> {
                try {
                    getClasses(dir, packageName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            System.out.println("======="+map.size());

            // 这里开始设置属性
            map.keySet().forEach(key -> {
                Object o = map.get(key);
                // 属性
                Field[] fields = o.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    MyAutowired annotation = field.getAnnotation(MyAutowired.class);
                    if (annotation != null) {
                        // 获取属性名字
                        String name = field.getName();
                        Object value = map.get(name);
                        try {
                            field.set(o, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            System.out.println("=======");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        // 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
//        // 加载xml
//        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
//        // 解析xml
//        SAXReader saxReader = new SAXReader();
//        try {
//            Document document = saxReader.read(resourceAsStream);
//            Element rootElement = document.getRootElement();
//            List<Element> beanList = rootElement.selectNodes("//bean");
//            for (int i = 0; i < beanList.size(); i++) {
//                Element element =  beanList.get(i);
//                // 处理每个bean元素，获取到该元素的id 和 class 属性
//                String id = element.attributeValue("id");        // accountDao
//                String clazz = element.attributeValue("class");  // com.lagou.edu.dao.impl.JdbcAccountDaoImpl
//                // 通过反射技术实例化对象
//                Class<?> aClass = Class.forName(clazz);
//                Object o = aClass.newInstance();  // 实例化之后的对象
//                // 存储到map中待用
//                map.put(id,o);
//            }
//
//            // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
//            // 有property子元素的bean就有传值需求
//            List<Element> propertyList = rootElement.selectNodes("//property");
//            // 解析property，获取父元素
//            for (int i = 0; i < propertyList.size(); i++) {
//                Element element =  propertyList.get(i);   //<property name="AccountDao" ref="accountDao"></property>
//                String name = element.attributeValue("name");
//                String ref = element.attributeValue("ref");
//
//                // 找到当前需要被处理依赖关系的bean
//                Element parent = element.getParent();
//
//                // 调用父元素对象的反射功能
//                String parentId = parent.attributeValue("id");
//                Object parentObject = map.get(parentId);
//                // 遍历父对象中的所有方法，找到"set" + name
//                Method[] methods = parentObject.getClass().getMethods();
//                for (int j = 0; j < methods.length; j++) {
//                    Method method = methods[j];
//                    if(method.getName().equalsIgnoreCase("set" + name)) {  // 该方法就是 setAccountDao(AccountDao accountDao)
//                        method.invoke(parentObject,map.get(ref));
//                    }
//                }
//                // 把处理之后的parentObject重新放到map中
//                map.put(parentId,parentObject);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static void getClasses(File dir, String packageName) throws Exception {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                // 是文件，判断是否是java文件
                boolean isJava = file.getName().contains(".class");
                if (isJava) {
                    try {
                        String realName = file.getName().replace(".class", "");
                        Class<?> aClass = Class.forName(packageName+"."+realName);
                        addToMap(aClass);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // 是文件夹
                getClasses(file, packageName+"."+file.getName());
            }
        }
    }

    private static void addToMap(Class<?> aClass) throws Exception {
        MyService annotation = aClass.getAnnotation(MyService.class);
        if (annotation != null && !aClass.isAnnotation()) {
            String value = annotation.value();
            doAddToMap(aClass, value);
        }
        MyRepository annotation2 = aClass.getAnnotation(MyRepository.class);
        if (annotation2 != null && !aClass.isAnnotation()) {
            String value = annotation2.value();
            doAddToMap(aClass, value);
        }
        MyComponent annotation3 = aClass.getAnnotation(MyComponent.class);
        if (annotation3 != null && !aClass.isAnnotation()) {
            String value = annotation3.value();
            doAddToMap(aClass, value);
        }
    }

    private static void doAddToMap(Class<?> aClass, String value) throws Exception {
        if (StringUtils.isEmpty(value)) {
            Class<?>[] interfaces = aClass.getInterfaces();
            // 接口数太多，暂时不做
            if (interfaces.length > 1) {
                throw new Exception("不确定的接口的实现");
            }
            String simpleName;
            // 接口数=1，直接使用接口的名字
            if (interfaces.length == 1) {
                Class<?> anInterface = interfaces[0];
                simpleName = anInterface.getSimpleName();
            } else {
                // 没有接口，直接使用自己的名字
                simpleName = aClass.getSimpleName();
            }
            String name = toLowerCaseFirstOne(simpleName);
            Object o = aClass.newInstance();  // 实例化之后的对象
            map.put(name, o);
        } else {
            Object o = aClass.newInstance();  // 实例化之后的对象
            // 存储到map中待用
            map.put(value, o);
        }
    }

    public static String toLowerCaseFirstOne(String s)  {
        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static  Object getBean(String id) {
        return map.get(id);
    }

}

package util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jpengyu
 * @create 2020-10-09 下午1:00
 * good luck
 **/
public class MyClassLoader extends ClassLoader {
//    @Override
    public Class<?> findClass(String rexName, String name) {
//        String myPath = "file:/projects/my/lagou_homework/阶段二/模块一/code/Minicat_new/src/webapps/" + rexName + "/" + name.replace(".","/") + ".class";
        String myPath = "file:///Users/yangdehong/IdeaProjects/dream/self_tomcat/src/main/webapps/" + rexName + "/" + name.replace(".", "/") + ".class";
//        String myPath = "file:///Users/yangdehong/IdeaProjects/dream/self_tomcat/src/main/webapps/demo1/server/YdhServlet.class";
        System.out.println(myPath);
        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(myPath));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
        return clazz;
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.findClass("demo1", "server.YdhServlet");
        System.out.println(aClass);
    }
}

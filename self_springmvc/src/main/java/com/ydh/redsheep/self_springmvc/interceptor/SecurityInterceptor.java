package com.ydh.redsheep.self_springmvc.interceptor;

import com.ydh.redsheep.self_springmvc.framework.annotations.MySecurity;
import com.ydh.redsheep.self_springmvc.pojo.InterceptorHandler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SecurityInterceptor extends YdhInterceptor{

    @Override
    public boolean doInterceptor(HttpServletRequest request, HttpServletResponse response, InterceptorHandler interceptorHandler) {
        String username = request.getParameter("username");
        // 方法的注解优先级高
        Method method = interceptorHandler.getMethod();
        boolean methodAnnotationPresent = method.isAnnotationPresent(MySecurity.class);
        if (methodAnnotationPresent) {
            MySecurity annotation = method.getAnnotation(MySecurity.class);
            String[] value = annotation.value();
            return StringUtils.isEmpty(username) || Arrays.asList(value).contains(username);
        }

        // 如果方法上面没有注解就去找类上面的注解
        Object object = interceptorHandler.getObject();
        Class<?> aClass = object.getClass();
        // 判断注解是否存在
        boolean annotationPresent = aClass.isAnnotationPresent(MySecurity.class);
        if (annotationPresent) {
            MySecurity annotation = aClass.getAnnotation(MySecurity.class);
            String[] value = annotation.value();
            return StringUtils.isEmpty(username) || Arrays.asList(value).contains(username);
        }

        return true;
    }

}

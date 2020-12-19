package com.ydh.redsheep.self_springmvc.interceptor;

import com.ydh.redsheep.self_springmvc.pojo.InterceptorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class YdhInterceptor {

    abstract boolean doInterceptor(HttpServletRequest request, HttpServletResponse response, InterceptorHandler interceptorHandler);

}

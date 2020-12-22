package com.ydh.redsheep.self_tomcat.servlet.commom;

import com.ydh.redsheep.self_tomcat.server.Request;
import com.ydh.redsheep.self_tomcat.server.Response;

public interface Servlet {

    void init() throws Exception;

    void destory() throws Exception;

    void service(Request request, Response response) throws Exception;
}

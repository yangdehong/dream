package com.ydh.redsheep.self_tomcat.servlet.commom;

import com.ydh.redsheep.self_tomcat.server.Request;
import com.ydh.redsheep.self_tomcat.server.Response;

public abstract class HttpServlet implements Servlet{

    public abstract void doGet(Request request, Response response);
    public abstract void doPost(Request request,Response response);

    @Override
    public void service(Request request, Response response) throws Exception {
        if("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }
}

package com.ydh.redsheep.self_tomcat.servlet;

import com.ydh.redsheep.self_tomcat.server.Request;
import com.ydh.redsheep.self_tomcat.server.Response;
import com.ydh.redsheep.self_tomcat.servlet.commom.HttpServlet;
import com.ydh.redsheep.self_tomcat.util.HttpProtocolUtil;

import java.io.IOException;

public class YdhServlet extends HttpServlet {
    @Override
    public void doGet(Request request, Response response) {
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String content = "<h1>YdhServlet get</h1>";
        try {
            response.output((HttpProtocolUtil.getHttpHeader200(content.getBytes().length) + content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        String content = "<h1>YdhServlet post</h1>";
        try {
            response.output((HttpProtocolUtil.getHttpHeader200(content.getBytes().length) + content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destory() throws Exception {

    }
}

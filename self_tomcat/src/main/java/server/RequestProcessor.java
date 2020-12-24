package server;

import server.pojo.Mapper;

import java.io.File;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

public class RequestProcessor extends Thread {

    private Socket socket;
    private Map<String, HttpServlet> servletMap;
    private Mapper mapper;

    public RequestProcessor(Socket socket, Map<String, HttpServlet> servletMap, Mapper mapper) {
        this.socket = socket;
        this.servletMap = servletMap;
        this.mapper = mapper;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();

            // 封装Request对象和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());

            // 静态资源处理
            String url = request.getUrl();
            if (servletMap.get(url) == null) {
                String[] split = url.split(File.separator);
                String path = mapper.getUrl2AppBase().get(split[1]);
                response.outputHtml(path+request.getUrl());
            } else {
                // 动态资源servlet请求
                HttpServlet httpServlet = servletMap.get(url);
                httpServlet.service(request, response);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

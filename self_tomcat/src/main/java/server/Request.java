package server;

import java.io.IOException;
import java.io.InputStream;

/**
 * 把请求信息封装为Request对象（根据InputSteam输入流封装）
 */
public class Request {

    private String hostName;
    private String port;
    private String method; // 请求方式，比如GET/POST
    private String url;  // 例如 /,/index.html

    private InputStream inputStream;  // 输入流，其他属性从输入流中解析出来

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Request() {
    }

    // 构造器，输入流传入
    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        // 从输入流中获取请求信息
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }

        byte[] bytes = new byte[count];
        inputStream.read(bytes);

        String inputStr = new String(bytes);
        // 获取第一行请求头信息
        String firstLineStr = inputStr.split("\\n")[0];  // GET / HTTP/1.1
        String[] strings = firstLineStr.split(" ");
        // 获取第二行请求头信息
        String secondLineStr = inputStr.split("\\n")[1].replace("\r", "");  // Host: localhost:8080
        String[] secondStrings = secondLineStr.split(" ");

        this.hostName = secondStrings[1].split(":")[0];
        this.port = secondStrings[1].split(":")[1];
        this.method = strings[0];
        this.url = strings[1];

        System.out.println("=====>>hostName:" + hostName);
        System.out.println("=====>>port:" + port);
        System.out.println("=====>>method:" + method);
        System.out.println("=====>>url:" + url);

    }
}

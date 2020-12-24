package server;

import org.dom4j.DocumentException;
import server.pojo.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.MyClassLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

/**
 * Minicat的主类
 */
public class Bootstrap {

    private Mapper mapper = new Mapper();
    private List<ServerSocket> serverSockets = new ArrayList<>();

    /**
     * Minicat启动需要初始化展开的一些操作
     */
    public void start() throws Exception {
        // 定义一个线程池
        int corePoolSize = 10;
        int maximumPoolSize = 50;
        long keepAliveTime = 100L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        for (ServerSocket serverSocket : serverSockets) {
            // 我们这里只有一个，也就是一个线程阻塞，不做错处理
            System.out.println("=========>>>>>>使用线程池进行多线程改造");
            /**
             多线程改造（使用线程池）
             */
            while (true) {
                Socket socket = serverSocket.accept();
                RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap, mapper);
                threadPoolExecutor.execute(requestProcessor);
            }
        }

//        Integer port = 8080;
//        ServerSocket serverSocket = new ServerSocket(port);
//        System.out.println("=====>>>Minicat start on port：" + port);
        /**
            完成Minicat 1.0版本
            需求：浏览器请求http://localhost:8080,返回一个固定的字符串到页面"Hello Minicat!"
         */
//        while(true) {
//            Socket socket = serverSocket.accept();
//            // 有了socket，接收到请求，获取输出流
//            OutputStream outputStream = socket.getOutputStream();
//            String data = "Hello Minicat!";
//            String responseText = HttpProtocolUtil.getHttpHeader200(data.getBytes().length) + data;
//            outputStream.write(responseText.getBytes());
//            socket.close();
//        }
        /**
         * 完成Minicat 2.0版本
         * 需求：封装Request和Response对象，返回html静态资源文件
         */
//        while(true) {
//            Socket socket = serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//            // 封装Request对象和Response对象
//            Request request = new Request(inputStream);
//            Response response = new Response(socket.getOutputStream());
//            response.outputHtml(request.getUrl());
//            socket.close();
//        }
        /**
         * 完成Minicat 3.0版本
         * 需求：可以请求动态资源（Servlet）
         */
//        while(true) {
//            Socket socket = serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//            // 封装Request对象和Response对象
//            Request request = new Request(inputStream);
//            Response response = new Response(socket.getOutputStream());
//            // 静态资源处理
//            if(servletMap.get(request.getUrl()) == null) {
//                response.outputHtml(request.getUrl());
//            }else{
//                // 动态资源servlet请求
//                HttpServlet httpServlet = servletMap.get(request.getUrl());
//                httpServlet.service(request,response);
//            }
//            socket.close();
//        }
        /**
            多线程改造（不使用线程池）
         */
//        while(true) {
//            Socket socket = serverSocket.accept();
//            RequestProcessor requestProcessor = new RequestProcessor(socket,servletMap);
//            requestProcessor.start();
//        }

//        System.out.println("=========>>>>>>使用线程池进行多线程改造");
//        /**
//            多线程改造（使用线程池）
//         */
//        while (true) {
//            Socket socket = serverSocket.accept();
//            RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap);
//            //requestProcessor.start();
//            threadPoolExecutor.execute(requestProcessor);
//        }

    }

    private Map<String, HttpServlet> servletMap = new HashMap<>();

    private void load(){
        // 解析server.xml
        loadServer();
        // 解析web.xml
        loadServlet();
    }

    /**
     * 解析server.xml
     */
    private void loadServer() {
        List<Service> serviceList = new ArrayList<>();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("server.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> selectNodes = rootElement.selectNodes("//Service");
            for (int i = 0; i < selectNodes.size(); i++) {
                List<Connector> connectorList = new ArrayList<>();
                Element element = selectNodes.get(i);
                // Connector
                List<Element> connectorElements = element.selectNodes("Connector");
                for (Element connectorElement : connectorElements) {
                    Connector connector = new Connector();
                    String port = connectorElement.attribute("port").getValue();
                    connector.setPort(port);
                    ServerSocket serverSocket = new ServerSocket(Integer.valueOf(connector.getPort()));
                    System.out.println("=====>>>Minicat start on port：" + port);
                    serverSockets.add(serverSocket);
                    connectorList.add(connector);
                }
                // Engine只有一个
                Engine engine = new Engine();
                Element engineElement = (Element) element.selectSingleNode("Engine");
                // host
                List<Host> hostList = new ArrayList<>();
                List<Element> hostNodes = engineElement.selectNodes("//Host");
                for (Element hostNode : hostNodes) {
                    String name = hostNode.attribute("name").getValue();
                    String appBase = hostNode.attribute("appBase").getValue();
                    hostList.add(new Host(name, appBase));
                }
                engine.setHostList(hostList);
                serviceList.add(new Service(connectorList, engine));
            }
            mapper.setServiceList(serviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载解析web.xml，初始化Servlet
     */
    private void loadServlet() {
        List<Service> serviceList = mapper.getServiceList();
        for (Service service : serviceList) {
            Engine engine = service.getEngine();
            List<Host> hostList = engine.getHostList();
            for (Host host : hostList) {
                String appBase = host.getAppBase();
                File file = new File(appBase);
                File[] fs = file.listFiles();
                Map<String, String> url2AppBase = new HashMap<>();
                for (File f : fs) {
                    if (f.isDirectory()) {
                        url2AppBase.put(f.getName(), appBase);
                        File webFile = new File(appBase + "/" + f.getName() + "/web.xml");
                        SAXReader saxReader = new SAXReader();
                        Document document = null;
                        try {
                            document = saxReader.read(webFile);
                            Element rootElement = document.getRootElement();
                            List<Element> selectNodes = rootElement.selectNodes("//servlet");
                            for (int i = 0; i < selectNodes.size(); i++) {
                                Element element =  selectNodes.get(i);
                                //<servlet-name>ydh</servlet-name>
                                Element servletnameElement = (Element) element.selectSingleNode("servlet-name");
                                String servletName = servletnameElement.getStringValue();
                                //<servlet-class>server.YdhServlet</servlet-class>
                                Element servletclassElement = (Element)element.selectSingleNode("servlet-class");
                                // 根据serlvet-name的值找到url-pattern
                                Element servletMapping = (Element) rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
                                //  /lagou
                                String urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();
                                // 加工urlPattern
                                urlPattern = "/" + f.getName() + urlPattern;
                                MyClassLoader myloader = new MyClassLoader();
                                Class<?> aClass = myloader.findClass(f.getName(),servletclassElement.getStringValue());
                                servletMap.put(urlPattern, (HttpServlet) aClass.newInstance());
                            }
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mapper.setUrl2AppBase(url2AppBase);
            }
        }
    }
//    private void loadServlet() {
//        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("web.xml");
//        SAXReader saxReader = new SAXReader();
//        try {
//            Document document = saxReader.read(resourceAsStream);
//            Element rootElement = document.getRootElement();
//            List<Element> selectNodes = rootElement.selectNodes("//servlet");
//            for (int i = 0; i < selectNodes.size(); i++) {
//                Element element = selectNodes.get(i);
//                // <servlet-name>ydh</servlet-name>
//                Element servletnameElement = (Element) element.selectSingleNode("servlet-name");
//                String servletName = servletnameElement.getStringValue();
//                // <servlet-class>xxxx.YdhServlet</servlet-class>
//                Element servletclassElement = (Element) element.selectSingleNode("servlet-class");
//                String servletClass = servletclassElement.getStringValue();
//                // 根据servlet-name的值找到url-pattern
//                Element servletMapping = (Element) rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
//                // servlet
//                String urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();
//                servletMap.put(urlPattern, (HttpServlet) Class.forName(servletClass).newInstance());
//            }
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Minicat 的程序启动入口
     *
     * @param args
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.load();
            // 启动Minicat
            bootstrap.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.ydh.redsheep.distributed.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author : yangdehong
 * @date : 2020/12/25 13:18
 */
public class HashDemo {

    /**
     * 普通hash
     */
    public void generalHash(String[] clients, int serverCount) {
        // hash(ip)%node_counts=index
        //根据index锁定应该路由到的tomcat服务器
        for (String client : clients) {
            int hash = Math.abs(client.hashCode());
            int index = hash % serverCount;
            System.out.println("客户端：" + client + " 被路由到服务器编号为：" + index);
        }
    }

    /**
     * 一致性hash-无虚拟节点
     */
    public void consistentHashNoVirtual(String[] tomcatServers, String[] clients) {
        //step1 初始化：把服务器节点IP的哈希值对应到哈希环上
        // 定义服务器ip
        SortedMap<Integer, String> hashServerMap = new TreeMap<>();
        for (String tomcatServer : tomcatServers) {
            // 求出每一个ip的hash值，对应到hash环上，存储hash值与ip的对应关系
            int serverHash = Math.abs(tomcatServer.hashCode());
            // 存储hash值与ip的对应关系
            hashServerMap.put(serverHash, tomcatServer);
        }
        //step2 针对客户端IP求出hash值
        // 定义客户端IP
        for (String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            //step3 针对客户端,找到能够处理当前客户端请求的服务器（哈希环上顺时针最近）
            // 根据客户端ip的哈希值去找出哪一个服务器节点能够处理（）
            SortedMap<Integer, String> integerStringSortedMap = hashServerMap.tailMap(clientHash);
            if (integerStringSortedMap.isEmpty()) {
                // 取哈希环上的顺时针第一台服务器
                Integer firstKey = hashServerMap.firstKey();
                System.out.println("==========>>>>客户端：" + client + " 被路由到服务器：" + hashServerMap.get(firstKey));
            } else {
                Integer firstKey = integerStringSortedMap.firstKey();
                System.out.println("==========>>>>客户端：" + client + " 被路由到服务器：" + hashServerMap.get(firstKey));
            }
        }
    }
    /**
     * 一致性hash-有虚拟节点
     */
    public void consistentHashWithVirtual(String[] tomcatServers, String[] clients, int virtaulCount) {
        //step1 初始化：把服务器节点IP的哈希值对应到哈希环上
        // 定义服务器ip
        SortedMap<Integer,String> hashServerMap = new TreeMap<>();
        for(String tomcatServer: tomcatServers) {
            // 求出每一个ip的hash值，对应到hash环上，存储hash值与ip的对应关系
            int serverHash = Math.abs(tomcatServer.hashCode());
            // 存储hash值与ip的对应关系
            hashServerMap.put(serverHash,tomcatServer);
            // 处理虚拟节点
            for(int i = 0; i < virtaulCount; i++) {
                int virtualHash = Math.abs((tomcatServer + "#" + i).hashCode());
                hashServerMap.put(virtualHash,"----由虚拟节点"+ i  + "映射过来的请求："+ tomcatServer);
            }

        }
        //step2 针对客户端IP求出hash值
        // 定义客户端IP
        for(String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            //step3 针对客户端,找到能够处理当前客户端请求的服务器（哈希环上顺时针最近）
            // 根据客户端ip的哈希值去找出哪一个服务器节点能够处理（）
            SortedMap<Integer, String> integerStringSortedMap = hashServerMap.tailMap(clientHash);
            if(integerStringSortedMap.isEmpty()) {
                // 取哈希环上的顺时针第一台服务器
                Integer firstKey = hashServerMap.firstKey();
                System.out.println("==========>>>>客户端：" + client + " 被路由到服务器：" + hashServerMap.get(firstKey));
            }else{
                Integer firstKey = integerStringSortedMap.firstKey();
                System.out.println("==========>>>>客户端：" + client + " 被路由到服务器：" + hashServerMap.get(firstKey));
            }
        }
    }

}

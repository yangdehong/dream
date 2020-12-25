package com.ydh.redsheep.distributed;

import com.ydh.redsheep.distributed.hash.HashDemo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HashTests {

    /**
     * 普通hash
     */
    @Test
    void generalHashTest() {
        // 定义客户端IP
        String[] clients = new String[]{"10.78.12.3","113.25.63.1","126.12.3.8"};
        // 定义服务器数量
        int serverCount = 5;// (编号对应0，1，2)
        HashDemo hashDemo = new HashDemo();
        hashDemo.generalHash(clients, serverCount);
    }

    /**
     * 一致性hash-无虚拟节点
     */
    @Test
    void consistentHashNoVirtualTest() {
        //step1 初始化：把服务器节点IP的哈希值对应到哈希环上
        // 定义服务器ip
        String[] tomcatServers = new String[]{"123.111.0.0","123.101.3.1","111.20.35.2","123.98.26.3"};
        //step2 针对客户端IP求出hash值
        // 定义客户端IP
        String[] clients = new String[]{"10.78.12.3","113.25.63.1","126.12.3.8"};
        HashDemo hashDemo = new HashDemo();
        hashDemo.consistentHashNoVirtual(tomcatServers, clients);
    }

    /**
     * 一致性hash-有虚拟节点
     */
    @Test
    void consistentHashWithVirtualTest() {
        //step1 初始化：把服务器节点IP的哈希值对应到哈希环上
        // 定义服务器ip
        String[] tomcatServers = new String[]{"123.111.0.0","123.101.3.1","111.20.35.2","123.98.26.3"};
        //step2 针对客户端IP求出hash值
        // 定义客户端IP
        String[] clients = new String[]{"10.78.12.3","113.25.63.1","126.12.3.8"};
        // 定义针对每个真实服务器虚拟出来几个节点
        int virtaulCount = 3;
        HashDemo hashDemo = new HashDemo();
        hashDemo.consistentHashWithVirtual(tomcatServers, clients, virtaulCount);
    }

}

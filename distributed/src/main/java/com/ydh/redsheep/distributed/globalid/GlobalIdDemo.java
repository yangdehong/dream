package com.ydh.redsheep.distributed.globalid;

import java.util.UUID;

public class GlobalIdDemo {

    /**
     * uuid生成唯一id
     */
    public void uuid() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }


}

package com.ydh.redsheep.self_springboot;

import com.ydh.redsheep.self_springboot.pojo.TArticlePO;
import com.ydh.redsheep.self_springboot.service.TArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SelfSpringbootApplicationTests {

    @Resource
    private TArticleService tArticleService;

    @Test
    void contextLoads() {
        List<TArticlePO> tArticlePOS = tArticleService.selectByPage(1, 1);
        System.out.println(tArticlePOS);
    }

}

package com.ydh.redsheep.self_springboot.controller;

import javax.annotation.Resource;

import com.ydh.redsheep.self_springboot.pojo.TArticlePO;
import com.ydh.redsheep.self_springboot.service.TArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* @author : yangdehong
* @date : 2020-12-21T18:35:08.799
*/
@Controller
@RequestMapping("")
public class TArticleController {

    @Resource
    private TArticleService tArticleService;

    /**
     * @Description: 分页查询列表
     * @Param:
     * @return:
     * @Author: yangdehong
     * @Date: 2020-12-21T18:35:08.799
    */
    @GetMapping("/index")
    public String selectTArticlePageList(Model model, TArticlePO tArticlePO,
                                         @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize){
        List<TArticlePO> tArticlePOS = tArticleService.selectByPage(pageNum, pageSize);
        model.addAttribute("list", tArticlePOS);
        return "client/index";
    }

}

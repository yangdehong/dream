package com.ydh.redsheep.self_springboot.service.impl;

import java.lang.Long;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydh.redsheep.self_springboot.mapper.TArticleMapper;
import com.ydh.redsheep.self_springboot.pojo.TArticlePO;
import com.ydh.redsheep.self_springboot.service.TArticleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 *
 * @author : yangdehong
 * @date : 2020-12-21T18:35:08.803
 */
@Service
public class TArticleServiceImpl implements TArticleService {

    @Resource
    private TArticleMapper tArticleMapper;

    @Override
    public List<TArticlePO> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TArticlePO> select = tArticleMapper.selectAll();
        PageInfo<TArticlePO> tArticlePOPageInfo = new PageInfo<>(select);
        System.out.println(tArticlePOPageInfo);
        return select;
    }
}

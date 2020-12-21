package com.ydh.redsheep.self_springboot.service;

import com.ydh.redsheep.self_springboot.pojo.TArticlePO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 *
 * @author : yangdehong
 * @date : 2020-12-21T18:35:08.802
 */
public interface TArticleService {

    List<TArticlePO> selectByPage(Integer pageNum, Integer pageSize);


}

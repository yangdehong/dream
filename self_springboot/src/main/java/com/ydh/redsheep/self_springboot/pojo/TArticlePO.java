package com.ydh.redsheep.self_springboot.pojo;

import lombok.Data;

import java.lang.*;
import java.util.*;
import java.math.*;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author : yangdehong
 * @date : 2020-12-21T18:35:08.807
 */
@Data
@Table(name = "t_article")
public class TArticlePO implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id; //
    private String title; // 文章标题
    private String content; // 文章具体内容
    private Date created; // 发表时间
    private Date modified; // 修改时间
    private String categories; // 文章分类
    private String tags; // 文章标签
    private Integer allowComment; // 是否允许评论
    private String thumbnail; // 文章缩略图



}

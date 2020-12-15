package com.ydh.redsheep.self_spring.service;

/**
 * @author yangdehong
 */
public interface TransferService {

    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}

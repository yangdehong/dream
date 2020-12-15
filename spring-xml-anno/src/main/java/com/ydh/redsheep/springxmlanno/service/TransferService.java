package com.ydh.redsheep.springxmlanno.service;

/**
 * @author yangdehong
 */
public interface TransferService {

    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}

package com.ydh.redsheep.springtx.service;

/**
 * @author yangdehong
 */

public interface TransferService {

    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}

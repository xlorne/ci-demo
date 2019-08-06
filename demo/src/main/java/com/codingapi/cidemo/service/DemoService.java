package com.codingapi.cidemo.service;

import com.codingapi.cidemo.collection.Order;
import com.codingapi.cidemo.domain.Demo;
import com.codingapi.cidemo.vo.LoginReq;
import com.codingapi.cidemo.vo.LoginRes;

import java.util.List;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
public interface DemoService {

    boolean save(Demo demo);

    List<Demo> list();

    LoginRes login(LoginReq loginReq);

    void test();


    List<Order> findAll();

    void save(Order demo);
}

package com.codingapi.cidemo.service;

import com.codingapi.cidemo.domain.Demo;

import java.util.List;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
public interface DemoService {

    boolean save(Demo demo);

    List<Demo> list();

}

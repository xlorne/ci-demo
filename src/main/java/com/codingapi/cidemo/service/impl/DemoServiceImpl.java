package com.codingapi.cidemo.service.impl;

import com.codingapi.cidemo.domain.Demo;
import com.codingapi.cidemo.mapper.DemoMapper;
import com.codingapi.cidemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;


    @Override
    public boolean save(Demo demo) {
        return demoMapper.save(demo)>0;
    }

    @Override
    public List<Demo> list() {
        return demoMapper.list();
    }
}

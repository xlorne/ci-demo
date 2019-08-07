package com.codingapi.cidemo.service.impl;

import com.codingapi.cidemo.collection.Logger;
import com.codingapi.cidemo.domain.Demo;
import com.codingapi.cidemo.exception.UserNameNotFoundException;
import com.codingapi.cidemo.mapper.DemoMapper;
import com.codingapi.cidemo.repository.OrderRepository;
import com.codingapi.cidemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Long login(String name) {
        Demo demo =  demoMapper.getByName(name);
        if(demo==null){
            throw new UserNameNotFoundException("用户不存在");
        }
        Logger logger = new Logger();
        logger.setId(demo.getId());
        logger.setTime(new Date());
        logger.setUserName(name);
        logger.setInfo("user:"+name);
        orderRepository.save(logger);
        return demo.getId();
    }
}

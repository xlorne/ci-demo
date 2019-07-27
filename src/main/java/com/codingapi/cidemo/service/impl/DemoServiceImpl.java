package com.codingapi.cidemo.service.impl;

import com.codingapi.cidemo.domain.Demo;
import com.codingapi.cidemo.exception.UserNameNotFoundException;
import com.codingapi.cidemo.mapper.DemoMapper;
import com.codingapi.cidemo.service.DemoService;
import com.codingapi.cidemo.vo.LoginReq;
import com.codingapi.cidemo.vo.LoginRes;
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

    @Override
    public LoginRes login(LoginReq loginReq) {
        Demo demo = demoMapper.getByName(loginReq.getName());
        if (demo == null) {
            throw new UserNameNotFoundException(String.format("not exits %s", loginReq.getName()));
        }
        return demo.toBean(new LoginRes());
    }

    @Override
    public void test() {
        int res = 100 / 1;
        System.out.println("xxxx");
    }
}

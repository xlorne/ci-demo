package com.codingapi.cidemo.service.impl;

import com.codingapi.test.annotation.TestMethod;
import com.codingapi.cidemo.domain.Demo;
import com.codingapi.cidemo.exception.UserNameNotFoundException;
import com.codingapi.test.listener.JunitMethodListener;
import com.codingapi.cidemo.service.DemoService;
import com.codingapi.cidemo.vo.LoginReq;
import com.codingapi.cidemo.vo.LoginRes;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@TestExecutionListeners({JunitMethodListener.class,
        DependencyInjectionTestExecutionListener.class})
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    private String userName = UUID.randomUUID().toString();

    @Test
    @TestMethod()
    public void save(){
        Demo demo = new Demo();
        demo.setName(userName);
        boolean res = demoService.save(demo);
        Assert.isTrue(res,"save error.");
    }

    @Test
    @Order(1)
    public void list(){
        List<Demo> list = demoService.list();
        log.info("list - > {}",list);
        Assert.notEmpty(list,"select error .");
    }


    @Test
    public void login_success() {
        LoginRes loginRes = demoService.login(new LoginReq(userName));
        log.info("login - > {}", loginRes);
        Assert.notNull(loginRes, "login error .");
    }

    @Test
    public void login_error() {
        try {
            LoginRes loginRes = demoService.login(new LoginReq("null"));
            log.info("login - > {}", loginRes);
            Assert.isNull(loginRes, "login error success .");
        } catch (UserNameNotFoundException exp) {
            exp.printStackTrace();
        }
    }

    @Test
    public void test() {
        demoService.test();
    }
}

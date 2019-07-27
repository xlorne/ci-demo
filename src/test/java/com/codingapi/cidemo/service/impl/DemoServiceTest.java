package com.codingapi.cidemo.service.impl;

import com.codingapi.cidemo.domain.Demo;
import com.codingapi.cidemo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void save(){
        Demo demo = new Demo();
        demo.setName(UUID.randomUUID().toString());
        boolean res = demoService.save(demo);
        Assert.isTrue(res,"save error.");
    }

    @Test
    public void list(){
        List<Demo> list = demoService.list();
        log.info("list - > {}",list);
        Assert.notEmpty(list,"select error .");
    }

}

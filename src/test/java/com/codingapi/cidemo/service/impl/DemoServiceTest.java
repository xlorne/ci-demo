package com.codingapi.cidemo.service.impl;

import com.codingapi.cidemo.collection.Logger;
import com.codingapi.cidemo.exception.UserNameNotFoundException;
import com.codingapi.cidemo.service.DemoService;
import com.codingapi.test.annotation.CheckMongoData;
import com.codingapi.test.annotation.Expected;
import com.codingapi.test.annotation.TestMethod;
import com.codingapi.test.listener.JunitMethodListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.util.Assert;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@TestExecutionListeners({JunitMethodListener.class,
        DependencyInjectionTestExecutionListener.class})
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;


    @Test
    @TestMethod(
            enablePrepare = true,
            prepareData = {"t_demo.xml"},
            enableCheck = true,
            checkMongoData =
            @CheckMongoData(
                    primaryVal = "user:123",
                    primaryKey = "info",
                    type = CheckMongoData.Type.STRING,
                    desc = "数据不存在",
                    bean = Logger.class,
                    expected = @Expected(key = "id",value = "1")),
            enableClear = true,
            clearCollectionNames = {"logger"},
            clearTableNames = {"t_demo"}
    )
    public void login_success() {
        try {
            Long id  = demoService.login("123");
            log.info("login - > {}", id);
            Assert.isTrue(id==1 ,"login success .");
        } catch (UserNameNotFoundException exp) {
            exp.printStackTrace();
        }
    }

}

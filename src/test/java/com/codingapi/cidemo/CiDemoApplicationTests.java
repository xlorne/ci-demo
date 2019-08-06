package com.codingapi.cidemo;

import com.codingapi.test.listener.JunitMethodListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ActiveProfiles("test")
@Slf4j
@TestExecutionListeners({JunitMethodListener.class,
        DependencyInjectionTestExecutionListener.class})

@RunWith(SpringRunner.class)
@SpringBootTest
public class CiDemoApplicationTests {



    @Test
    public void contextLoads() {

    }

}

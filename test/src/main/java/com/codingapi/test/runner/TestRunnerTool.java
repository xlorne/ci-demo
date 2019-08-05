package com.codingapi.test.runner;

import com.codingapi.test.TestThreadLocal;
import com.codingapi.test.annotation.TestMethod;
import com.codingapi.test.config.TestConfig;
import com.codingapi.test.xml.XmlInfo;
import com.codingapi.test.xml.XmlUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.test.context.TestContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Slf4j
public class TestRunnerTool {


    public static void check(TestMethod testMethod, TestContext testContext) {
        System.out.println(testContext);
    }

    public static void clean(TestMethod testMethod, TestContext testContext) {
        System.out.println(testContext);
    }

    public static <T> void prepare(TestMethod testMethod, TestContext testContext) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        TestConfig testConfig =  testContext.getApplicationContext().getBean(TestConfig.class);
        String path =  testConfig.getOutPath();
        if(testMethod.prepareData().length>0){
            for(String xmlFile:testMethod.prepareData()){
                String xml = FileUtils.readFileToString(new File(path+"/"+xmlFile));
                XmlInfo<T> parser = XmlUtils.parser(xml);
                TestThreadLocal testThreadLocal = new TestThreadLocal();
                testThreadLocal.getList().add(parser);
                log.info("xml->{}",parser);
            }
        }
    }
}

package com.codingapi.test.listener;


import com.codingapi.test.annotation.TestMethod;
import com.codingapi.test.config.TestConfig;
import com.codingapi.test.runner.TestRunnerTool;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.lang.reflect.Method;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
public class JunitMethodListener extends AbstractTestExecutionListener {



    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {

        TestConfig testConfig =  testContext.getApplicationContext().getBean(TestConfig.class);

        Method jdkMethod = testContext.getTestMethod();
        if (jdkMethod == null) {
            return;
        }

        TestMethod testMethod = jdkMethod.getAnnotation(TestMethod.class);
        if (testMethod == null) {
            return;
        }

        if (testMethod.enablePrepare()) {
            TestRunnerTool.prepare(testMethod,testConfig);
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        TestConfig testConfig =  testContext.getApplicationContext().getBean(TestConfig.class);
        boolean hasException = (testContext.getTestException() != null) ? true : false;

        Method jdkMethod = testContext.getTestMethod();
        if (jdkMethod == null) {
            return;
        }

        TestMethod testMethod = jdkMethod.getAnnotation(TestMethod.class);
        if (testMethod == null) {
            return;
        }

        if(!hasException){
            TestRunnerTool.check(testMethod,testConfig);
        }

        if (testMethod.enablePrepare()) {
            TestRunnerTool.clean(testMethod,testConfig);
        }

    }
}

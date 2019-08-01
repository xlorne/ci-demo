package com.codingapi.cidemo.listener;

import com.codingapi.cidemo.TestRunnerTool;
import com.codingapi.cidemo.annotation.TestMethod;
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

        Method jdkMethod = testContext.getTestMethod();
        if (jdkMethod == null) {
            return;
        }

        TestMethod testMethod = jdkMethod.getAnnotation(TestMethod.class);
        if (testMethod == null) {
            return;
        }

        if (testMethod.enablePrepare()) {
            TestRunnerTool.prepare(testMethod);
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {

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
            TestRunnerTool.check(testMethod);
        }

        if (testMethod.enablePrepare()) {
            TestRunnerTool.clean(testMethod);
        }

    }
}

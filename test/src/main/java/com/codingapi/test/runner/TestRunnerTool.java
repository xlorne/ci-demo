package com.codingapi.test.runner;

import com.codingapi.test.annotation.TestMethod;
import com.codingapi.test.config.TestConfig;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
public class TestRunnerTool {


    public static void check(TestMethod testMethod, TestConfig testConfig) {
        System.out.println(testConfig);
    }

    public static void clean(TestMethod testMethod, TestConfig testConfig) {
        System.out.println(testConfig);
    }

    public static void prepare(TestMethod testMethod, TestConfig testConfig) {
        Class<?>[] clazzes = testMethod.prepareDate();
        System.out.println(testConfig);
    }
}

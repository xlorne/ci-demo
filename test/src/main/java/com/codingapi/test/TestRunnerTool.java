package com.codingapi.test;

import com.codingapi.test.annotation.TestMethod;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
public class TestRunnerTool {


    public static void check(TestMethod testMethod) {

    }

    public static void clean(TestMethod testMethod) {

    }

    public static void prepare(TestMethod testMethod) {
        switch (testMethod.prepareDateType()){
            case XML2DB:{
                for(String path:testMethod.prepareDateConfig()){


                }

                break;
            }
            default:{
                break;
            }
        }
    }
}

package com.codingapi.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Target(ElementType.METHOD)
public @interface TestMethod {

   boolean enablePrepare() default true;

    PrepareDataType  prepareDateType() default PrepareDataType.XML2DB;


    String[] prepareDateConfig() default {};


    boolean enableCheck() default false;

    String[] checkConfigFiles() default {};
}

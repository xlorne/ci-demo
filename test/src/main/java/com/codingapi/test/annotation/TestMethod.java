package com.codingapi.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMethod {

    boolean enablePrepare() default true;

    String[] prepareData() default {};

    boolean enableCheck() default false;

    String[] checkConfigFiles() default {};

    boolean enableClear() default true;

}

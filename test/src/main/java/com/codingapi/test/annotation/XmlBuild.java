package com.codingapi.test.annotation;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@IndexAnnotated
public @interface XmlBuild {

    String initCmd() default "";

    DBType dbType() default DBType.Mysql;

    String path() default "";

    String name() default "";

}

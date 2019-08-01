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
@Retention(RetentionPolicy.SOURCE)
@IndexAnnotated
public @interface XmlBuild {


}
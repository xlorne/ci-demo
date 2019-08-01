package com.codingapi.test;

import com.codingapi.test.annotation.XmlBuild;
import com.codingapi.test.config.TestConfig;
import org.atteo.classindex.ClassIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Component
@ConditionalOnProperty(name = "test.enable",havingValue = "true")
public class TestRunner implements CommandLineRunner {

    @Autowired
    private TestConfig testConfig;


    @Override
    public void run(String... args) {
        String packageName = testConfig.getEntityPackage();
        System.out.println(packageName);


        Iterable<Class<?>> annotated = ClassIndex.getAnnotated(XmlBuild.class);
        Iterator<Class<?>> iterator = annotated.iterator();

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n");
        while (iterator.hasNext()){
            Class<?> clazz=  iterator.next();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field:fields){
                String key = field.getName();
                sb.append(String.format("\t<%s>%s</%s>\n",key,key,key));
            }
        }
        sb.append("</xml>");
        System.out.println(sb);
    }
}

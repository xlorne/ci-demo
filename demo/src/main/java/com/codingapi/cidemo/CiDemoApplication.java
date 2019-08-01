package com.codingapi.cidemo;

import com.codingapi.test.annotation.XmlBuild;
import org.atteo.classindex.ClassIndex;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.util.Iterator;

@SpringBootApplication
@MapperScan("com.codingapi.*.mapper")
public class CiDemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(CiDemoApplication.class, args);

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

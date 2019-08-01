package com.codingapi.test.xml;

import com.codingapi.test.annotation.XmlBuild;

import java.lang.reflect.Field;

public class XmlUtils {

    public static  String create(Class<?> clazz, XmlBuild xmlBuild) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n");
        Field[] fields = clazz.getDeclaredFields();
        sb.append(String.format("<list initCmd=\"%s\" dbType=\"%s\">\n",xmlBuild.initCmd(),xmlBuild.dbType()));
        sb.append("\t<item>\n");
        for (Field field:fields){
            String key = field.getName();
            sb.append(String.format("\t\t<%s>%s</%s>\n",key,key,key));
        }
        sb.append("\t</item>\n");
        sb.append("</list>");
        return sb.toString();
    }
}

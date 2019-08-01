package com.codingapi.test.runner;

import com.codingapi.test.annotation.XmlBuild;
import com.codingapi.test.config.TestConfig;
import com.codingapi.test.xml.XmlUtils;
import org.apache.commons.io.FileUtils;
import org.atteo.classindex.ClassIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
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
    public void run(String... args) throws IOException {
        String outPath = testConfig.getOutPath();
        Iterable<Class<?>> annotated = ClassIndex.getAnnotated(XmlBuild.class);
        Iterator<Class<?>> iterator = annotated.iterator();

        while (iterator.hasNext()){
            Class<?> clazz=  iterator.next();
            File file = new File(outPath+"/"+clazz.getSimpleName()+".xml");
            FileUtils.writeStringToFile(file, XmlUtils.create(clazz,clazz.getAnnotation(XmlBuild.class)),false);
        }

    }
}

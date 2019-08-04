package com.codingapi.test.runner;

import com.codingapi.test.annotation.XmlBuild;
import com.codingapi.test.config.TestConfig;
import com.codingapi.test.xml.XmlInfo;
import com.codingapi.test.xml.XmlUtils;
import org.apache.commons.io.FileUtils;
import org.atteo.classindex.ClassIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Component
@ConditionalOnProperty(name = "test.coverXml",havingValue = "true")
public class XmlBuildRunner implements CommandLineRunner {

    @Autowired
    private TestConfig testConfig;

    @Override
    public void run(String... args) throws IOException, IllegalAccessException, InstantiationException {
        String outPath = testConfig.getOutPath();
        Iterable<Class<?>> annotated = ClassIndex.getAnnotated(XmlBuild.class);
        Iterator<Class<?>> iterator = annotated.iterator();

        while (iterator.hasNext()){
            Class<?> clazz=  iterator.next();
            XmlBuild xmlBuild =  clazz.getAnnotation(XmlBuild.class);
            String filePath = clazz.getName()+".xml";
            if(!StringUtils.isEmpty(xmlBuild.path())){
                filePath = xmlBuild.path();
            }
            XmlInfo xmlInfo = new XmlInfo();
            xmlInfo.setName(clazz.getName());
            xmlInfo.setPath(filePath);
            xmlInfo.setInitCmd(xmlBuild.initCmd());
            xmlInfo.setDbType(xmlBuild.dbType());
            Object obj = clazz.newInstance();
            xmlInfo.getList().add(obj);
            File file = new File(outPath+"/"+filePath);
            FileUtils.writeStringToFile(file, XmlUtils.create(xmlInfo),false);
        }

    }
}

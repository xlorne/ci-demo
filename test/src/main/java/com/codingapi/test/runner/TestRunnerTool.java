package com.codingapi.test.runner;

import com.codingapi.test.TestThreadLocal;
import com.codingapi.test.annotation.DBType;
import com.codingapi.test.annotation.TestMethod;
import com.codingapi.test.config.TestConfig;
import com.codingapi.test.utils.SqlUtils;
import com.codingapi.test.xml.XmlInfo;
import com.codingapi.test.xml.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestContext;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;



/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Slf4j
public class TestRunnerTool {


    public static void check(TestMethod testMethod, TestContext testContext) {
        System.out.println(testContext);
    }

    public static <T> void clean(TestMethod testMethod, TestContext testContext)  throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, SQLException {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        TestConfig testConfig = applicationContext.getBean(TestConfig.class);
        String path =  testConfig.getOutPath();
        TestThreadLocal testThreadLocal = new TestThreadLocal();
        if(testMethod.prepareData().length>0){
            for(String xmlFile:testMethod.prepareData()){
                String xml = FileUtils.readFileToString(new File(path+"/"+xmlFile));
                XmlInfo<T> xmlInfo = XmlUtils.parser(xml);
                testThreadLocal.getList().add(xmlInfo);

                if(xmlInfo.getDbType().equals(DBType.Mysql)&& !StringUtils.isEmpty(xmlInfo.getClearCmd())){
                    DataSource dataSource = applicationContext.getBean(DataSource.class);
                    QueryRunner queryRunner = new QueryRunner();
                    queryRunner.execute(dataSource.getConnection(),xmlInfo.getClearCmd());

                }

                if(xmlInfo.getDbType().equals(DBType.Mongo)){
                    MongoTemplate mongoTemplate = applicationContext.getBean(MongoTemplate.class);
                    mongoTemplate.dropCollection(xmlInfo.getName());
                }

            }
        }

    }

    public static <T> void prepare(TestMethod testMethod, TestContext testContext) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, SQLException {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        TestConfig testConfig = applicationContext.getBean(TestConfig.class);
        String path =  testConfig.getOutPath();
        TestThreadLocal testThreadLocal = new TestThreadLocal();
        if(testMethod.prepareData().length>0){
            for(String xmlFile:testMethod.prepareData()){
                String xml = FileUtils.readFileToString(new File(path+"/"+xmlFile));
                XmlInfo<T> xmlInfo = XmlUtils.parser(xml);
                testThreadLocal.getList().add(xmlInfo);

                if(xmlInfo.getDbType().equals(DBType.Mysql)){
                    DataSource dataSource = applicationContext.getBean(DataSource.class);
                    QueryRunner queryRunner = new QueryRunner();
                    for(Object object : xmlInfo.getList()) {
                        SqlUtils.SqlParam sqlParam = SqlUtils.parser(xmlInfo.getInitCmd(),object);
                        Object rows = queryRunner.insert(dataSource.getConnection(), sqlParam.getSql(),new ScalarHandler<>(),sqlParam.getParams());
                        log.info("mysql->{},rows:{}",sqlParam.getSql(),rows);
                    }

                }

                if(xmlInfo.getDbType().equals(DBType.Mongo)){
                    MongoTemplate mongoTemplate = applicationContext.getBean(MongoTemplate.class);
                    for(Object object : xmlInfo.getList()) {
                        Object res = mongoTemplate.save(object,xmlInfo.getName());
                        log.info("mongodb->,rows:{}",res);
                    }

                }

            }
        }
    }
}

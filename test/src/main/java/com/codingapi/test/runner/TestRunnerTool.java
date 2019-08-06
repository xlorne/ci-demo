package com.codingapi.test.runner;

import com.alibaba.fastjson.JSON;
import com.codingapi.test.annotation.*;
import com.codingapi.test.config.TestConfig;
import com.codingapi.test.utils.SqlUtils;
import com.codingapi.test.xml.XmlInfo;
import com.codingapi.test.xml.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.TestContext;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Slf4j
public class TestRunnerTool {


    /**
     * 检查指令
     * @param testMethod   注解
     * @param testContext  springTest管理器
     * @param <T>       bean 泛型
     * @throws IllegalAccessException 检查异常
     * @throws SQLException     Sql异常
     */
    public static <T> void check(TestMethod testMethod, TestContext testContext) throws  IllegalAccessException, SQLException {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        for(CheckMysqlData checkMysqlData :testMethod.checkMysqlData()){
            String sql = checkMysqlData.sql();
            DataSource dataSource = applicationContext.getBean(DataSource.class);
            QueryRunner queryRunner = new QueryRunner();
            List<Map<String,Object>> res =  queryRunner.query(dataSource.getConnection(),sql,new MapListHandler());
            log.info("mysql - check=> sql:{},res:{}",sql,res);
            Expected expecteds []= checkMysqlData.expected();
            checkVal(res,expecteds,checkMysqlData.desc());
        }

        for(CheckMongoData checkMongoData :testMethod.checkMongoData()){
            String key = checkMongoData.primaryKey();
            String val = checkMongoData.primaryVal();
            Object pkval = checkMongoData.type().equals(CheckMongoData.Type.Integer)?Integer.parseInt(val):val;
            MongoTemplate mongoTemplate = applicationContext.getBean(MongoTemplate.class);
            Query query = Query.query(
                    Criteria.where(key).is(pkval));
            List<Object> res = null;

            if(StringUtils.isEmpty(checkMongoData.collection())){
                res= mongoTemplate.find(query,checkMongoData.bean());
            }else {
                res= mongoTemplate.find(query,checkMongoData.bean(),checkMongoData.collection());
            }

            log.info("mongo - check=> sql:{},res:{}",query,res);
            Expected expecteds[]= checkMongoData.expected();
            checkVal(res,expecteds,checkMongoData.desc());
        }
    }

    private static void checkVal(List res , Expected[] expecteds, String desc) throws IllegalAccessException {
        for(int i=0;i<expecteds.length;i++){
            Expected expected = expecteds[i];
            Object val = res.get(i);
            Map<String,Object>  map = null;
            if(val instanceof Map){
                map = (Map<String,Object>)val;
            }else{
                map =  (Map<String,Object>)JSON.toJSON(val);
            }
            Object mval = map.get(expected.key());
            if(expected.type().equals(Expected.Type.Int)){
                Integer _val = (Integer)mval;
                if(Integer.parseInt(expected.value()) != _val){
                    throw new IllegalAccessException(desc);
                }
            }

            if(expected.type().equals(Expected.Type.String)){
                String _val = (String)mval;
                if(!expected.value().equals(_val)){
                    throw new IllegalAccessException(desc);
                }
            }
        }
    }

    public static <T> void clean(TestMethod testMethod, TestContext testContext)  throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, SQLException {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        TestConfig testConfig = applicationContext.getBean(TestConfig.class);
        String path =  testConfig.getOutPath();
        if(testMethod.prepareData().length>0){
            for(String xmlFile:testMethod.prepareData()){
                String xml = FileUtils.readFileToString(new File(path+"/"+xmlFile));
                XmlInfo<T> xmlInfo = XmlUtils.parser(xml);

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
        if(testMethod.prepareData().length>0){
            for(String xmlFile:testMethod.prepareData()){
                String xml = FileUtils.readFileToString(new File(path+"/"+xmlFile));
                XmlInfo<T> xmlInfo = XmlUtils.parser(xml);

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

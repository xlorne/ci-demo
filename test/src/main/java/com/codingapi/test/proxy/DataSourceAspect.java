/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codingapi.test.proxy;

import com.codingapi.test.TestThreadLocal;
import com.codingapi.test.annotation.DBType;
import com.codingapi.test.utils.SqlUtils;
import com.codingapi.test.xml.XmlInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

/**
 * create by lorne on 2018/1/5
 * @author lorne
 */
@Aspect
@Component
@Slf4j
public class DataSourceAspect {


    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object res = point.proceed();
        TestThreadLocal testThreadLocal = TestThreadLocal.get();
        if(testThreadLocal!=null){
            Connection connection = (Connection) res;
            List<XmlInfo> list =  testThreadLocal.getList();
            for(XmlInfo xmlInfo:list){
                if(xmlInfo.getDbType().equals(DBType.Mysql)){
                    QueryRunner queryRunner = new QueryRunner();
                    for(Object object : xmlInfo.getList()) {
                        SqlUtils.SqlParam sqlParam = SqlUtils.parser(xmlInfo.getInitCmd(),object);
                        Object rows = queryRunner.insert(connection, sqlParam.getSql(),new ScalarHandler<>(),sqlParam.getParams());
                        log.info("sql->{},rows:{}",sqlParam.getSql(),rows);
                    }
                }
            }
        }
        return res;
    }


}

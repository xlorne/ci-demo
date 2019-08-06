package com.codingapi.cidemo;

import com.codingapi.cidemo.collection.Order;
import com.codingapi.test.annotation.CheckMongoData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CiDemoApplicationTests {



    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        Query query = Query.query(
                Criteria.where("id").is(1));

        List<Order> res =  mongoTemplate.find(query, Order.class);


        System.out.println(res);

    }


}

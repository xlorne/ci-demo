package com.codingapi.cidemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.cidemo.domain.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CiDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        String initCmd = "insert into t_demo values(id,name) values(#{id},#{name})";
        Demo demo = new Demo();
//        demo.setName("name");
        demo.setId(1L);


        Map<String,Object> map = (Map) JSONObject.toJSON(demo);

        for (String key:map.keySet()){
            Object val = map.get(key);
            String valStr = (val==null?"null":"'"+val.toString()+"'");
            initCmd = initCmd.replaceAll("\\#\\{"+key+"}",valStr);
            System.out.println(initCmd);
        }

    }
}

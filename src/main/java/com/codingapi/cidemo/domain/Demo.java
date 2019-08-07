package com.codingapi.cidemo.domain;


import com.codingapi.test.annotation.DBType;
import com.codingapi.test.annotation.XmlBuild;
import lombok.Data;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@Data
@XmlBuild(name = "t_demo",dbType= DBType.RELATIONAL)
public class Demo  {

    private Long id;

    private String name;



}

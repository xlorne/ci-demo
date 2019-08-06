package com.codingapi.cidemo.collection;

import com.codingapi.test.annotation.DBType;
import com.codingapi.test.annotation.XmlBuild;
import lombok.Data;

/**
 * @author lorne
 * @date 2019/8/6
 * @description
 */
@Data
@XmlBuild(name = "order",dbType = DBType.Mongo)
public class Order {

    private Long id;
    private String number;

}

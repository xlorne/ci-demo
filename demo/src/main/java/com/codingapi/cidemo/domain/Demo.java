package com.codingapi.cidemo.domain;


import com.codingapi.cidemo.vo.BaseVO;
import com.codingapi.test.annotation.DBType;
import com.codingapi.test.annotation.XmlBuild;
import lombok.Data;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@Data
@XmlBuild(initCmd = "insert into t_demo(id,name) values(#{id},#{name})",dbType= DBType.Mysql)
public class Demo extends BaseVO {

    private Long id;

    private String name;

}

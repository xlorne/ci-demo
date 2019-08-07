package com.codingapi.cidemo.collection;

import com.codingapi.test.annotation.DBType;
import com.codingapi.test.annotation.XmlBuild;
import lombok.Data;

import java.util.Date;

/**
 * @author lorne
 * @date 2019/8/6
 * @description
 */
@Data
@XmlBuild(name = "logger",dbType = DBType.MONGODB,colType = XmlBuild.ColType.UNDERLINE)
public class Logger {

    private Long id;

    private Date time;

    private String info;

    private String userName;


}

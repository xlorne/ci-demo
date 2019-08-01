package com.codingapi.cidemo.domain;


import com.codingapi.cidemo.vo.BaseVO;
import com.codingapi.test.annotation.XmlBuild;
import lombok.Data;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@Data
@XmlBuild
public class Demo extends BaseVO {

    private Long id;

    private String name;
}

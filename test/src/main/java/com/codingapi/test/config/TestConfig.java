package com.codingapi.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lorne
 * @date 2019/8/1
 * @description
 */
@Data
@ConfigurationProperties("test")
public class TestConfig {

    /**
     * 是否开启
     */
    private boolean enable;

    /**
     * 扫描的包路径,若多个可用逗号分隔
     */
    private String entityPackage;

    /**
     * 输出xml的路径
     */
    private String outPath;



}

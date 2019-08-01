package com.codingapi.cidemo.vo;

import com.codingapi.cidemo.domain.Demo;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
public class DemoTest {

    public static void main(String[] args) {
        Demo demo = new Demo();

        demo.setId(13l);

        LoginRes loginRes = demo.toBean(new LoginRes());
        System.out.println(loginRes.getId());
    }
}

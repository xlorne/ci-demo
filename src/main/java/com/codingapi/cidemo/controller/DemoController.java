package com.codingapi.cidemo.controller;

import com.codingapi.cidemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@RestController
public class DemoController {


    @Autowired
    private DemoService demoService;

    @PostMapping("/login")
    public Long login(@RequestBody String name) {
        return demoService.login(name);
    }


}

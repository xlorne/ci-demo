package com.codingapi.cidemo.controller;

import com.codingapi.cidemo.domain.Demo;
import com.codingapi.cidemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@RestController
public class DemoController {


    @Autowired
    private DemoService demoService;

    @PostMapping("/save")
    public boolean save(@RequestBody Demo demo){
        return demoService.save(demo);
    }

    @GetMapping("/list")
    public List<Demo> list(){
        return demoService.list();
    }
}

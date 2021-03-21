package com.sunshine.learn.web.spring.controller;

import com.sunshine.learn.web.spring.service.HelloService;
import com.sunshine.learn.web.spring.service.TxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    private HelloService helloService;

    @Autowired
    private TxService txService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping("/")
    public String home() {
        return helloService.welcome("World");
    }

    @RequestMapping("/tx")
    public String tx() {
        txService.someServiceMethod();
        return "success";
    }

    @RequestMapping("/tx1")
    public String tx1() {
        txService.txMethod();
        return "success";
    }
}

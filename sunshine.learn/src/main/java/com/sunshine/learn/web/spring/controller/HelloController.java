package com.sunshine.learn.web.spring.controller;

import com.sunshine.learn.web.spring.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

  private HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @RequestMapping("/")
  public String home() {
    return helloService.welcome("World");
  }

}

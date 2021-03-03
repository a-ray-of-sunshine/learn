package com.sunshine.learn.web.spring.service.impl;

import com.sunshine.learn.web.spring.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

  @Override
  public String welcome(String name) {
    return "Hello " + name;
  }
}

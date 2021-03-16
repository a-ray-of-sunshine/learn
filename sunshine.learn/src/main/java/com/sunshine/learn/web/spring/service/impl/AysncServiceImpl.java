package com.sunshine.learn.web.spring.service.impl;

import com.sunshine.learn.web.spring.service.AysncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AysncServiceImpl implements AysncService {

  @Async
  @Override
  public void asyncHello() {
    log.info("asyncHello");
  }
}

package com.sunshine.learn.web.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringApp {

    public SpringApp() {
        log.info("app init");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }
}

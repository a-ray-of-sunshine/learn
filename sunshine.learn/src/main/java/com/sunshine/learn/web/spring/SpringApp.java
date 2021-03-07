package com.sunshine.learn.web.spring;

import com.sunshine.learn.web.spring.controller.HelloController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringApp {

    public SpringApp() {
        log.info("app init");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringApp.class, args);
        HelloController helloController = context.getBean("helloController", HelloController.class);
        // helloController.home();
    }
}

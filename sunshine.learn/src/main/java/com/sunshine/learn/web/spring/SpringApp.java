package com.sunshine.learn.web.spring;

import com.sunshine.learn.web.spring.conf.MyApplicationContextInitializer;
import com.sunshine.learn.web.spring.controller.HelloController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableAsync
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringApp {

  public SpringApp() {
    log.info("app init");
  }

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(SpringApp.class);
    application.addInitializers(new MyApplicationContextInitializer());
    ConfigurableApplicationContext context = application.run(args);
    HelloController helloController = context.getBean("helloController", HelloController.class);
    Assert.assertNotNull(helloController);
  }
}

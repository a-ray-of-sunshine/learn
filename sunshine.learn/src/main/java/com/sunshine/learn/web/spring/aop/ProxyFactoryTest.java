package com.sunshine.learn.web.spring.aop;

import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryTest {

  public static void main(String[] args) {
    Biz biz = new Biz();
    ProxyFactory proxyFactory = new ProxyFactory(biz);
    proxyFactory.addAdvice(new MyMethodInterceptor());
    biz = (Biz) proxyFactory.getProxy();
    biz.biz1();
  }

}

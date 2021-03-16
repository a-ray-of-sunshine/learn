package com.sunshine.learn.web.spring.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryTest {

  public static void main(String[] args) {
    Biz biz = new Biz();
    ProxyFactory proxyFactory = new ProxyFactory(biz);
    proxyFactory.addAdvice(new MyMethodInterceptor());
    biz = (Biz) proxyFactory.getProxy();
    biz.biz1();

    // 代理对象可以转换成 Advised 对象
    // 此对象可以操作对象。例如添加 Advice
    Advised biz1 = (Advised) biz;
    Advisor[] advisors = biz1.getAdvisors();
    Advisor advisor = advisors[0];
    System.out.println(advisor.getAdvice());

  }

}

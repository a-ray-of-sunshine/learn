package com.sunshine.learn.web.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println("before invoke");
    Object result = invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
    System.out.println("after invoke");
    return result;
  }
}

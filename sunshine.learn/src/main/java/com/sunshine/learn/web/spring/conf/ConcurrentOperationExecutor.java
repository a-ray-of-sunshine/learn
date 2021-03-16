package com.sunshine.learn.web.spring.conf;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ConcurrentOperationExecutor implements Ordered {

  private AtomicInteger count = new AtomicInteger();

  @Override
  public int getOrder() {
    return 0;
  }

  @Pointcut("execution(* com.sunshine.learn.web.spring.service.impl.HelloServiceImpl.*(..))")
  public void statOperation() {
  }

  @Around("statOperation()")
  public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
    Object result = pjp.proceed();
    log.info("access method count: {}", count.incrementAndGet());
    return result;
  }
}

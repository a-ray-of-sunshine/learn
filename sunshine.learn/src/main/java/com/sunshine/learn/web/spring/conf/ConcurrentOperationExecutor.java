package com.sunshine.learn.web.spring.conf;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ConcurrentOperationExecutor implements Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    @Pointcut("execution(* com.sunshine.learn.web.spring.service..*.*(..))")
    public void statOperation() {
    }

    @Around("statOperation()")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int numAttempts = 0;
        PessimisticLockingFailureException lockFailureException;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (PessimisticLockingFailureException ex) {
                lockFailureException = ex;
            }
        } while (numAttempts <= 5);
        throw lockFailureException;
    }
}

package com.yrkim.springwithaws.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect implements Ordered {
    @Around("execution( * com.yrkim.springwithaws.controller.*.*(..))")
    public Object doAroundRequest(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        log.debug("This : '" + point.getThis() + "'");
        log.debug("Kind : '" + point.getKind() + "'");
        log.debug("Args : '" + point.getArgs() + "'");
        log.debug("Target: '" + point.getTarget() + "'");

        long finish = System.currentTimeMillis();
        long timeMs = finish - start;
        log.debug("[ " + point.getSignature().getDeclaringTypeName() + " ] :" + point.getSignature().getName() + "()'");
        log.debug("Doing Time - " + timeMs/1000 + " Second");
        return point.proceed();
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}

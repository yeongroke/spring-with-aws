package com.yrkim.springwithaws.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Aspect
@Component
public class TransactionAspect implements Ordered {
    @Around("execution( * com.yrkim.springwithaws.service.*.*(..))")
    public Object logging(ProceedingJoinPoint point) throws Throwable {
        String typeName = point.getSignature().getDeclaringTypeName();
        String toName = typeName + "." + point.getSignature().getName();
        log.debug("================== transaction start {} {}", toName, TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        Object o = point.proceed();
        log.debug("================== transaction end {} {}", toName, TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        return o;
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}

package com.example.mytraining.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class CommodityAop {

    @Before("aspect()")
    public void before() {
        log.info("开始>>>");
    }

    @After("aspect()")
    public void after() {
        log.info("结束>>>");
    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        log.info("start >>> ");
        try {
            Object proceed = point.proceed();
            log.info("end >>> ,耗时：{}ms，方法：{}", System.currentTimeMillis() - start, point.getSignature().toShortString());
            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Pointcut("execution(* com.example.mytraining.controller.*.*(..))")
    public void aspect() {

    }
}

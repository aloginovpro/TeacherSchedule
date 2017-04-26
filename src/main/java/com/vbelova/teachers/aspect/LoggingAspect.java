package com.vbelova.teachers.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger("services");

    @Around("execution(* com.vbelova.teachers.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        logger.info("method call {} took {} ms", joinPoint.getSignature().toShortString(), stopWatch.getTotalTimeMillis());
        return result;
    }

}

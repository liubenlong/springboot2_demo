package com.example.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("execution(public * com.example.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        log.info("METHOD : {} ; REQUEST：{} ; RESPONSE : {}",
                joinPoint.getSignature().toShortString(),
                JSONObject.toJSONString(joinPoint.getArgs()),
                JSONObject.toJSONString(proceed));
        return proceed;
    }

}
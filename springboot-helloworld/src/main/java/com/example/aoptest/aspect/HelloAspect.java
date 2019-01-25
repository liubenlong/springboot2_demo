package com.example.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP
 */
@Component
@Aspect
public class HelloAspect {

  @Pointcut("execution(* com.example.service.HelloService.getVal())")
  public void pointcut() {
  }

  @Around("pointcut()")
  public String changeGetVal(ProceedingJoinPoint pjp) {
    return "aopResult";//简单起见，这里直接模拟一个返回值了
  }

}

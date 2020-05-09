package com.example.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

@Component
@Aspect
public class RequestParamValidAspect {
    @Pointcut("execution(* com.example.controller.*.*(..))")
    public void controllerBefore() {
    }

    @Before("controllerBefore()")
    public void before(JoinPoint point) {
        Object target = point.getThis();
        // 获得切入方法参数
        Object[] args = point.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        ExecutableValidator executableValidator = factory.getValidator().forExecutables();

        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = executableValidator.validateParameters(target, method, args);
        //如果有校验不通过的
        if (!validResult.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            validResult.forEach(objectConstraintViolation -> {
                builder.append(objectConstraintViolation.getMessage()).append(";");
            });
            String result = builder.toString();
            throw new IllegalArgumentException(result.substring(0, result.length() - 1));
        }
    }
}
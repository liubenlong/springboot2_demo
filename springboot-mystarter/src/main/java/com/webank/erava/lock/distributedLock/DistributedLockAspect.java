package com.webank.erava.lock.distributedLock;

import com.webank.erava.lock.service.DistributedLockService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Component
@Aspect
public class DistributedLockAspect {

    @Autowired
    private DistributedLockService distributedLockService;

    private ExpressionEvaluator<String> evaluator = new ExpressionEvaluator<>();

    @Pointcut("@annotation(com.webank.erava.lock.distributedLock.DistributedLock)")
    public void pointcout() {
    }

    @Around("pointcout() && @annotation(distributedLock)")
    public void distributedLock(ProceedingJoinPoint point, DistributedLock distributedLock) throws Throwable {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            String key = distributedLock.el() ? getKey(point) : distributedLock.key(); // 获取
            if (distributedLockService.tryLock(key, distributedLock.expire(), uuid)) {
                point.proceed();
            } else {
                throw new RuntimeException("请勿重复提交！");
            }
        } finally {
            distributedLockService.releaseLock(distributedLock.key(), uuid);
        }
    }


    private DistributedLock getDistributeExceptionHandler(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(DistributedLock.class);
    }

    private String getKey(JoinPoint joinPoint) {
        DistributedLock handler = getDistributeExceptionHandler(joinPoint);
        if (joinPoint.getArgs() == null) {
            return null;
        }
        EvaluationContext evaluationContext = evaluator.createEvaluationContext(joinPoint.getTarget(), joinPoint.getTarget().getClass(), ((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs());
        AnnotatedElementKey methodKey = new AnnotatedElementKey(((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getTarget().getClass());
        return evaluator.condition(handler.key(), methodKey, evaluationContext, String.class);
    }
}
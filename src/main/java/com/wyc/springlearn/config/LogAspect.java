package com.wyc.springlearn.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogAspect {

    public LogAspect() {
        log.info("LogAspect init");
    }

    @Pointcut("execution(* com.wyc.springlearn.web.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String params;
        if (args == null) {
            params = "";
        } else {
            params = args.length == 1 ? JSON.toJSONString(args[0]) : JSON.toJSONString(args);
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("LogAspect error");
            throw throwable;
        } finally {
            String res = JSON.toJSONString(result);
            log.info("\nmethod: \t\t{}.{}  \nrequest: \t\t{} \nresponse: \t\t{} \nconsume: \t\t{} ms",
                    method.getDeclaringClass().getSimpleName(), method.getName(), params, res
                    , System.currentTimeMillis() - start);
        }
        return result;
    }
}

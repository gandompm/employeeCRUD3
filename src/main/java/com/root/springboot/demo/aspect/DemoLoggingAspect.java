package com.root.springboot.demo.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger myLogger = Logger.getLogger(this.getClass().getName());

    public DemoLoggingAspect() {
    }

    @Pointcut("execution(* com.root.springboot.demo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.root.springboot.demo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.root.springboot.demo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        this.myLogger.info("=====>> in @Before: calling method: " + theMethod);
        Object[] args = theJoinPoint.getArgs();
        Object[] var4 = args;
        int var5 = args.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Object tempArg = var4[var6];
            this.myLogger.info("=====>> argument: " + String.valueOf(tempArg));
        }

    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        this.myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);
        this.myLogger.info("=====>> result: " + String.valueOf(theResult));
    }
}

package com.study.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

  @Around("execution(* com.study.aop.order..*(..))")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
    //join point 시그니처
    log.info("[log] {}", joinPoint.getSignature());
    return joinPoint.proceed();
  }

}

package com.study.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

  //이렇게 나누어서 사용할 수도 있다.

  //만약에 포인트컷을 모아서 사용할 경우 private 말고 상황에 맞게 쓰면 된다.
  //com.study.aop.order 패키지와 하위 패키지
  @Pointcut("execution(* com.study.aop.order..*(..))")
  private void allOrder(){} //pointcut signature

  @Around("allOrder()")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
    //join point 시그니처
    log.info("[log] {}", joinPoint.getSignature());
    return joinPoint.proceed();
  }

}

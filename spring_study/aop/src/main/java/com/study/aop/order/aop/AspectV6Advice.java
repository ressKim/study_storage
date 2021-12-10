package com.study.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

  //@Around 는 다 있다고 보면 된다.
  //대신 Around 는 joinPoint.proceed() 를 호출하여야 한다. 호출안하면 target 이 호출이 안된다.... 매우 치명
  // 위에실수를 하면 그냥 망한다 생각하자.
  @Around("com.study.aop.order.aop.Pointcuts.orderAndService()")
  public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

    try {
      //@Before
      log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
      Object result = joinPoint.proceed();
      //@AfterReturning
      log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
      return result;
    } catch (Exception e) {
      //@AfterThrowing
      log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
      throw e;
    } finally {
      //@After
      log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
    }
  }

  //아래처럼 각각 표기를 한다면 명확하게 알 수 있다는 장점이 있다.** 명확성은 매우 중요
  // ++ joinPoint.proceed 도 호출 안해도 된다.
  //제약 덕분에 의도가 명확해진다!

  //Before 는 실행해 준다.
  @Before("com.study.aop.order.aop.Pointcuts.orderAndService()")
  public void doBefore(JoinPoint joinpoint) {
    log.info("[before] {}", joinpoint.getSignature());
  }

  @AfterReturning(value = "com.study.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
  public void doReturn(JoinPoint joinpoint, Object result) {
    log.info("[return] {} return = {}", joinpoint.getSignature(), result);
  }

  //반환타입이 일치하지 않으면 출력이 안된다.
  //부모타입이면 자식타입은 인정이 된다.
  @AfterReturning(value = "com.study.aop.order.aop.Pointcuts.allOrder()", returning = "result")
  public void doReturn2(JoinPoint joinpoint, Integer result) {
    log.info("[return22] {} return = {}", joinpoint.getSignature(), result);
  }

  @AfterThrowing(value = "com.study.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
  public void doThrowing(JoinPoint joinPoint, Exception ex) {
    log.info("[ex] {} message ={}", joinPoint.getSignature(), ex);
  }

  @After(value = "com.study.aop.order.aop.Pointcuts.orderAndService()")
  public void doAfter(JoinPoint joinPoint) {
    log.info("[after] {}", joinPoint.getSignature());
  }
}

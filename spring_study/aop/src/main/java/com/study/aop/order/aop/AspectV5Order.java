package com.study.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV5Order {

  /**
   * 여기서 두 개의 순서가 어떤 순서로 지정하는지 보장하지 않는다. 클래스 내에서 두개 이상 있을 시 메소드에 order를 지정하더라도 먹히지 않는다(order는 클래스
   * 단위에 적용시키는것) 그래서 클래스 단위로 별도의 클래스로 나누어서 정의해야 된다.
   * 그래서 아래에서 내부 클래스로 적용을 시켜 보았다.<p></p>
   */

  @Aspect
  @Order(1)
  public static class LogAspect {

    @Around("com.study.aop.order.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
      //join point 시그니처
      log.info("[log] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
  }

  @Aspect
  @Order(2)
  public static class TxAspect {

    @Around("com.study.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

      try {
        log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
        Object result = joinPoint.proceed();
        log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
        return result;
      } catch (Exception e) {
        log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
        throw e;
      } finally {
        log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
      }
    }
  }

}

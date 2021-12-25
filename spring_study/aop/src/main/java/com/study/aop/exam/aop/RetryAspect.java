package com.study.aop.exam.aop;

import com.study.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

  /**
   * 재시도에 관해서는 Exception 보다 상위라 어쩔 수 없다.
   * 보통 외부 서버가 간헐적으로 튀거나 할 때 를 대비해서 할 수 있다.
   * 그런데 반드시 횟수 제한을 두어야 된다.
   */
  @Around("@annotation(retry)")
  public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {

    log.info("[retry] {} retry = {}", joinPoint.getSignature(), retry);

    int maxRetry = retry.value();
    Exception exceptionHolder = null;

    for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
      try {

        log.info("[retry] try count = {}/{}", retryCount, maxRetry);
        return joinPoint.proceed();

      } catch (Exception e) {
        exceptionHolder = e;
      }
    }
    throw exceptionHolder;


  }
}

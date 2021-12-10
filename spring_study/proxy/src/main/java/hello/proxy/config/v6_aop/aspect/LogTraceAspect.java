package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogTraceAspect {

  private final LogTrace logTrace;

  public LogTraceAspect(LogTrace logTrace) {
    this.logTrace = logTrace;
  }

  /**
   * 어드바이져 = 포인트컷 + 어드바이스 이다.
   * 자동 프록시 생성기 (' AnnotationAwareAspectJAutoProxyCreator ')은
   * @Aspect 를 찾아서 이것을 'Advisor' 로 만들어 주는 기능도 있다.
   * 그래서 두가지 동작을 한다.
   * 1. @Aspect 를 확인하고 Advisor 로 변환해서 저장.
   * 2. 어드바이저를 기반으로 프록시를 생성.
   */
  @Around("execution(* hello.proxy.app..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

    //Advice 로직

    TraceStatus status = null;
    try {
      String message = joinPoint.getSignature().toShortString();
      status = logTrace.begin(message);

      //로직 호출
      Object result = joinPoint.proceed();
      logTrace.end(status);
      return result;

    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }

  }
}

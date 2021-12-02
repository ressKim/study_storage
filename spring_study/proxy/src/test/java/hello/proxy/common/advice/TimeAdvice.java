package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    log.info("TimeProxy 실행");
    long startTime = System.currentTimeMillis();

    //proceed 를 호출하면 target 을 찾아서 인수를 넘겨준다.
    // proxyFactory 를 만들때 target 을 넘겨주기때문에 여기서 넣을 필요가 없다.
    Object result = invocation.proceed();

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("TimeProxy 종료 result = {}", resultTime);

    return result;

  }
}

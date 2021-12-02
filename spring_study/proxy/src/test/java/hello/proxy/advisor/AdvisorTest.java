package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

@Slf4j
public class AdvisorTest {

  @Test
  void advisorTest1() {
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
    proxyFactory.addAdvisor(advisor);
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();
  }

  @Test
  @DisplayName("직접 만든 포인트컷")
  void advisorTest2() {
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
    proxyFactory.addAdvisor(advisor);
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();
  }

  @Test
  @DisplayName("스프링이 제공하는 포인트컷")
  void advisorTest3() {
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    /**
     * 스프링은 포인트컷을 많은 종류를 제공을 한다.
     * 대표적으로 몇가지만 알아보자.
     * NameMethodPointCut - 메서드 이름을 기반으로 매칭한다. 내부에서는 'PatternMatchUtils'를 사용을 한다.
     * JdkRegexMethodPointcut - JDK 정규 표현식을 기반으로 포인트컷을 매칭한다.
     * TruePointcut - 항상 참을 반환.
     * AnnotationMatchingPointcut - 어노테이션으로 매칭
     * AspectJExpressionPointcut - aspectJ 표현식으로 매칭. --> 제일 중요 제일 많이 사용.(기능이 가장 많음)
     */
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    //메서드 이름이 save 인 경우 적용
    pointcut.setMappedName("save");
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
    proxyFactory.addAdvisor(advisor);
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    proxy.save();
    proxy.find();
  }

  static class MyPointcut implements Pointcut {

    @Override
    public ClassFilter getClassFilter() {
      return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
      return new MyMethodMatcher();
    }
  }

  static class MyMethodMatcher implements MethodMatcher {

    private String matchName = "save";

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
      boolean result = method.getName().equals(matchName);
      log.info("포인트컷 호출 method = {} targetClass= {}", method.getName(), targetClass);
      log.info("포인트컷 결과 result = {}", result);
      return result;
    }

    @Override
    public boolean isRuntime() {
      return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
      throw new UnsupportedOperationException();
    }
  }

}

package hello.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.*;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

  @Test
  @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
  void interfaceProxy() {
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("targetClass = {}", target.getClass());
    log.info("proxyClass = {}", proxy.getClass());

    proxy.save();

    /*
    이 경우 인터페이스를 이용하는것이다 보니
    JdkDynamicProxy 는 true
    CglibProxy 는 false 가 된다.
     */
    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
  }

  @Test
  @DisplayName("구체 클래스만 있으면 Cglib 사용")
  void concreteProxy() {
    ConcreteService target = new ConcreteService();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
    log.info("targetClass = {}", target.getClass());
    log.info("proxyClass = {}", proxy.getClass());

    proxy.call();

    /*
    이 경우 구체 클래스만 이용하니깐
    JdkDynamicProxy 는 false
    CglibProxy 는 true 가 된다.
     */
    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }

  @Test
  @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 Cglib 를 사용하고, 클래스 기반 프록시 사용")
  void proxyTargetClass() {
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("targetClass = {}", target.getClass());
    log.info("proxyClass = {}", proxy.getClass());

    proxy.save();

    /*
    이 경우는 ProxyTargetClass 옵션을 true 로 주어서 CGLIB 로 동작을 한다.
    JdkDynamicProxy 는 true
    CglibProxy 는 false 가 된다.
     */
    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }
}

package hello.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.*;

import hello.proxy.common.advice.TimeAdvice;
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

    //프록시 팩토리를 통해 생성한것들에 한해 아래 명령어로 확인 가능
    /*
    이 경우 인터페이스를 이용하는것이다 보니
    JdkDynamicProxy 는 true
    CglibProxy 는 false 가 된다.
     */
    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
  }

}

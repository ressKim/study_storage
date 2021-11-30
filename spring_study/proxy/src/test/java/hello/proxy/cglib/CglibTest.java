package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

  //CGLIB 는 구체 클래스를 받아서 프록시를 생성할 수 있다.
  /*
   클래스 기반 프록시는 상속을 사용하기 때문에 몇가지 제약이 있다.
   - 부모 클래스의 생성자를 체크해야 한다. -> CGLIB 는 자식 클래스를 동적으로 생성하기 떄문에 기본 생성자가 필요.
   - 클래스에 'final' 키워드가 붙으면 상속 불가 -> CGLIB 에서 예외 발생
   - 메서드에 'final' 키워드가 붙으면 해당 메서드 오버라이딩 불가 -> CGLIB 에서 프록시 로직이 동작하지 않는다.
   
   */

  @Test
  void cglib(){
    ConcreteService target = new ConcreteService();

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(ConcreteService.class);
    enhancer.setCallback(new TimeMethodInterceptor(target));
    ConcreteService proxy = (ConcreteService) enhancer.create();
    log.info("targetClass = {}", target.getClass());
    log.info("proxyClass = {}", proxy.getClass());

    proxy.call();
  }

}

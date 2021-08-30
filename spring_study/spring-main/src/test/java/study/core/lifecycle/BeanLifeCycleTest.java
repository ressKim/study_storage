package study.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.core.annotation.MainDiscountPolicy;

class BeanLifeCycleTest {

  /**
   * 스프링 빈의 이벤트 라이프 사이클 - (싱글톤에 관해서)
   * 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
   *
   * - 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
   * - 소멸전 콜백 : 빈이 소멸되기 직전에 호출
   *
   * ++ 참고 : 객체의 생성과 초기화를 분리하자.
   * >> 생성자는 필수정보(파라미터) 를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면에 초기화는 이렇게 생성된 값들을 활용해
   * 외부 커넥션을 연결하는 등 무거운 동작을 수행한다.
   *
   * >> 따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화 하는 부분을 명확하게 나누는것이 유지보수 관점에서 좋다.
   * 물론 초기화 작업이 내부 값들만 약간 변경하는 정도라면은 생성자에서 한번에 처리하는게 더 나을 수 있다.
   */

  @Test
  @DisplayName("bean life cycle 확인용")
  void lifeCycleTest(){
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);

    NetworkClient client = ac.getBean(NetworkClient.class);
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig{
    @Bean
    public NetworkClient networkClient(){
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dd");
      return networkClient;
    }

  }

}

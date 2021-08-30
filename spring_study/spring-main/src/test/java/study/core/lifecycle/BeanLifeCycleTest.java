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

    /**
     * 설정 정보 사용의 특징
     * 메서드 이름을 자유롭게 줄 수 있다.
     * 스프링 빈이 스프링 코드에 의존하지 않는다.
     * 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
     */

    /**
     * 추가로 종료메서드 추론 이라는게 있다.
     * @Bean 의 destroyMethod 에는 특별한 기능이 있다.
     * 라이브러리는 대부분 'close', 'shutdown' 이라는 종료 메서드를 사용을 한다.
     * @Bean 의 'destoryMethod' 는 기본값이 '(inferred)'(추론) 으로 등록되어 있다.
     * 이 추론 기능은 'close', 'shutdown'라는 이름의 메서드를 자동으로 호출 해 준다.
     * 이름 그대로 종료 메서드를 추론해서 호출 해 준다.
     * 따라서 직접 스프링 빈으로 등록하면 종료 메서드는 적어주지 않아도 되는경우가 발생한다.
     * 추론 기능을 사용하기 싫다면 'destroyMethod=""' 처럼 빈 공백을 사용하면 된다.
     *
     */
//    @Bean(initMethod = "init", destroyMethod = "close")
    @Bean
    public NetworkClient networkClient(){
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dd");
      return networkClient;
    }

  }

}

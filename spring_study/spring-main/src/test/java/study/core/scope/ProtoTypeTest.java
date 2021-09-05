package study.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ProtoTypeTest {

  /**
   * 프로토타입 스코프는 빈을 조회시에 생성되고 초기화 메서드도 실행된다.
   * 그리고 나면 완전히 관리를 떼버려서 @PreDestory 같은 종료 메서드가 전혀 실행되지 않는다.
   */

  @Test
  void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class);
    System.out.println("find prototypeBean1");
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    System.out.println("find prototypeBean2");
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);
    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

//    prototypeBean1.destroy(); //필요하다면 수동으로 해제
    ac.close();
  }

  @Scope("prototype")
  static class PrototypeBean {

    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }

}

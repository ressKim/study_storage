package study.core.autowired;

import java.util.Optional;
import javax.swing.text.html.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import study.core.member.Member;

public class AutowiredTest {

  @Test
  void AutowiredOption() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

  }

  static class TestBean {

    //의존관계가 없으면 호출 자체가 안된다.
    @Autowired(required = false)
    public void setNoBean1(Member member) {
      System.out.println("member = " + member);
    }

    //null
    @Autowired
    public void setNoBean2(@Nullable Member member) {
      System.out.println("member2= " + member);
    }

    //Optional.empty
    @Autowired
    public void setNoBean3(Optional<Member> member) {
      System.out.println("member3= " + member);
    }
  }

}

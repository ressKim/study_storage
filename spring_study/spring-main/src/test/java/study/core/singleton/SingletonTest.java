package study.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.core.AppConfig;
import study.core.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    /**
     * 싱글톤이 적용되지 않은 객체는 new 를 할 때마다 새로운 객체를 생성한다.
     * 그러면 100만명의 사용자가 접근을 했을 때에는 100만 개의 객체가 새로 생성이 된다.
     * 이러면 메모리가 만들어졌다 지워졌다 낭비가 매우 심하게 된다.
     * <p>
     * 이런 것을 보안하기 위해 싱글톤 을 사용해야 한다.
     */

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();


        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        /**
         * isSameAs ==
         * isEqualAs equals
         */
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        /**
         * sping 에서는 등록한거를 자동으로 싱글톤 컨테이너 방식으로 적용하기 때문에 Bean 등록 후에는 spring 이
         * 내부적으로 같은 객체를 재 사용한다. 이렇게 해서 SOLID 방식을 최대한 지킨다.
         */

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);

    }
}


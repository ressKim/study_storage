package study.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.core.AppConfig;
import study.core.member.MemberService;

public class SingletonTest {

    /**
     * 싱글톤이 적용되지 않은 객체는 new 를 할 때마다 새로운 객체를 생성한다.
     * 그러면 100만명의 사용자가 접근을 했을 때에는 100만 개의 객체가 새로 생성이 된다.
     * 이러면 메모리가 만들어졌다 지워졌다 낭비가 매우 심하게 된다.
     *
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
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

}


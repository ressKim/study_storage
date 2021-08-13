package study.core.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import study.core.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    /**
     * xml 기반의 appConfig.xml 스프링 설정 정보와
     * 자바 코드인 AppConfig.java 설정 정보를 비교해보면 거의 비슷하다.
     *
     * xml 은 필요하다면 공식 레퍼런스 문서를 참고하자.
     * https://spring.io/projects/spring-framework
     */

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

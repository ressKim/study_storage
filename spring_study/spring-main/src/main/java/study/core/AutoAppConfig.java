package study.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import study.core.member.MemberRepository;
import study.core.member.MemoryMemberRepository;

@Configuration
//보통은 그냥 다 등록하는데 지금 공부하기위해 (아니면 전에껄 다 지워야 되니깐) ComponentScan 을 scan 대상에서 제외한다.
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

  /**
   * annotation 에는 상속 관계라는 것이 없다. 그래서 annotation 을 들고있는것을 인식하는것은 자바가 지원하는게 아니고 스프링이 지원하는 것 이다.
   * <p>
   * 다음 어노테이션들은 componentScan 역할 뿐 아니라 다음의 추가 기능들을 수행한다
   *
   * @Controller - spring MVC 컨트롤러로 인식
   * @Repository - 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
   * @Configuration - 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 해준다.
   * @Service - service 는 특별한 처리를 하지 않고, 코드를 보는 개발자들이 핵심 비지니스 로직이 여기있구나 하고 계층 인식에 도움을 준다.
   * <p>
   * 참고 - useDefaultFilters 옵션은 기본으로 켜져있는데 이 옵션을 끄면 기본 스캔 대상에서 제외된다. 잘 쓸일은 없는데 알아는 두자.
   */

  /**
   * 이렇게 자동, 수동 같이 등록 될 경우 수동이 오버라이드해서 우선권을 가진다.
   *
   * 의도해서 이렇게 작성을 했다면 괜찮겠지만 보통은 의도적이지 않게 덮어져버리는 경우가 있다.
   * 이렇게 의도하지 않게 이런 결과가 만들어지면,
   * *** 그러면 매우매우 잡기 어려운 버그가 발생한다. 이렇게 잡기 어려운 버그는 애매한 버그이다!
   *
   * 그리고 springboot application 을 실행시키면 실행이 되지않고, 기본적으로 이렇게 충돌이 일어나면
   * 추가 설정을 해 주지 않으면 오류가 뜨면서 실행이 되지 않게 설정 되어 있다.
   *
   * 여려명이서 개발하기때문이기도 하고 어떤 경우가 있을 지 모르기때문에 이렇게 애매한 추상화 같은 것들은
   * 피하고 명확하게 구현하도록 하자, 당장 보기에는 깔끔해 보일 지 모르지만 나중에 버그터지거나 딴 사람이 볼 때 매우 힘들 수 있다.
   */
//  @Bean(name = "memoryMemberRepository")
  MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

}

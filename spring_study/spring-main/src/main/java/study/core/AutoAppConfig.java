package study.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//보통은 그냥 다 등록하는데 지금 공부하기위해 (아니면 전에껄 다 지워야 되니깐) ComponentScan 을 scan 대상에서 제외한다.
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /**
     * annotation 에는 상속 관계라는 것이 없다. 그래서 annotation 을 들고있는것을 인식하는것은
     * 자바가 지원하는게 아니고 스프링이 지원하는 것 이다.
     *
     * 다음 어노테이션들은 componentScan 역할 뿐 아니라 다음의 추가 기능들을 수행한다
     * @Controller - spring MVC 컨트롤러로 인식
     * @Repository - 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
     * @Configuration - 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 해준다.
     * @Service - service 는 특별한 처리를 하지 않고, 코드를 보는 개발자들이 핵심 비지니스 로직이 여기있구나 하고 계층 인식에 도움을 준다.
     *
     * 참고 - useDefaultFilters 옵션은 기본으로 켜져있는데 이 옵션을 끄면 기본 스캔 대상에서 제외된다. 잘 쓸일은 없는데 알아는 두자.
    */

}

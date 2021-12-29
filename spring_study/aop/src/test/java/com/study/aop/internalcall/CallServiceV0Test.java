package com.study.aop.internalcall;

import static org.junit.jupiter.api.Assertions.*;

import com.study.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

  //얘는 프록시가 올라가게 된다.
  @Autowired CallServiceV0 callService;


  /**
   * 이렇게 실행을 해 보면 external 은 aop 가 걸려있는데
   * 내부 호출인 internal 은 aop 가 안걸린 것을 확인 할 수 있다.
   * 내부 호출은 별도의 참조가 없으면 this. 을 생략한 상태인데
   * 이것은 자기 자신의 인스턴스를 가리키는 것이다.
   * 즉, 이렇게 내부호출로 접근하게 되면 proxy를 거치지 않은 직접적인 객체를 호출하게 된다.
   * 그래서 spring aop를 사용하려는곳 (aop 를 사용하는 transactional 도 마찬가지이다.)
   * 에서 내부 호출을 한다면 내부 호출한 대상은 aop 가 안먹히는 현상을 확인할 수 있다.****
   *
   *
   * 추가로 중요한 것은 실제 코드에 aop 를 직접 적용하는 AspectJ 에서는 이런 문제가 발생하지 않는다.
   * proxy를 활용한 것이 아닌 해당 코드에 직접 붙이는 방식이기 때문이다.
   * 그렇지만 AspectJ 를 직접 사용하는것은 로드타임위빙 등을 사용하여 보다 많이 복잡하고 JVM 옵션도 주어야 되는 부담감이 있다.
   * 그래서 AspectJ 를 직접 이용하는 aop 는 실무에서 거의 사용을 안한다.
   *
   * 대신, spring aop 에서의 proxy 방식의 문제점을 다른 방식으로 해결하는 방법을 공부해 보자.
   */
  @Test
  void external() {
    callService.external();
  }

  @Test
  void internal() {
    callService.internal();
  }
}
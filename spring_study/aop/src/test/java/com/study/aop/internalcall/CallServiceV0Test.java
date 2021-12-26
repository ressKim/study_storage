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
   * 그래서 aop를 사용하려는곳 (aop 를 사용하는 transactional 도 마찬가지이다.)
   * 에서 내부 호출을 한다면 내부 호출한 대상은 aop 가 안먹히는 현상을 확인할 수 있다.****
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
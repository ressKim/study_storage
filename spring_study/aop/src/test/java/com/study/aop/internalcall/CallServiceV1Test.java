package com.study.aop.internalcall;

import com.study.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV1Test {

  @Autowired
  CallServiceV1 callService;

  //자기 자신 주입으로 하면  실행을 해 보면 internal 도 프록시로 오는것을 볼 수 있다.
  @Test
  void external() {
    callService.external();
  }

  @Test
  void internal() {
    callService.internal();
  }
}
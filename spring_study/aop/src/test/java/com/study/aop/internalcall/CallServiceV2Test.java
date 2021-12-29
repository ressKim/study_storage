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
class CallServiceV2Test {

  @Autowired
  CallServiceV2 callService;

  @Test
  void external() {
    callService.external();
  }

  @Test
  void internal() {
    callService.internal();
  }
}
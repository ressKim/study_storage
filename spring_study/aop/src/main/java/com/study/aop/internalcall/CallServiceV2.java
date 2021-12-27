package com.study.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

  //가능은 한데 ApplicationContext 를 통째로 들고오기에는 너무 무게가 무겁다..
//  private final ApplicationContext applicationContext;

  private final ObjectProvider<CallServiceV2> callServiceProvider;

  public CallServiceV2(
      ObjectProvider<CallServiceV2> callServiceProvider) {
    this.callServiceProvider = callServiceProvider;
  }


  public void external() {
    log.info("call external");
//    CallServiceV2 callService = applicationContext.getBean(CallServiceV2.class);
    CallServiceV2 callService = callServiceProvider.getObject();
    callService.internal(); // 외부 메서드 호출
  }

  public void internal() {
    log.info("call internal");
  }

}

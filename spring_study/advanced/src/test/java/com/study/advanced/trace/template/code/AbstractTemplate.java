package com.study.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

  /**
   * 템플릿 메서드 패턴 - 템플릿을 사용하는 방식
   * 변하지 않는 부분들을 모아두고,
   * 변하는 부분들을 상속을 해서 재정의하며 사용을 한다.
   */

  public void execute() {
    long startTime = System.currentTimeMillis();
    //비즈니스 로직 실행
    call();//상속
    //비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  protected abstract void call();

}

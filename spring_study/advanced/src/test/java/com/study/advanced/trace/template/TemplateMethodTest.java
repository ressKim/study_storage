package com.study.advanced.trace.template;

import com.study.advanced.trace.template.code.AbstractTemplate;
import com.study.advanced.trace.template.code.SubClassLogic1;
import com.study.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    //비즈니스 로직 실행
    log.info("비즈니스 로직1 실행");
    //비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    //비즈니스 로직 실행
    log.info("비즈니스 로직2 실행");
    //비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  /**
   * 템플릿 메서드 패턴 적용한것
   */

  @Test
  void templateMethodV1() {
    AbstractTemplate template1 = new SubClassLogic1();
    template1.execute();

    AbstractTemplate template2 = new SubClassLogic2();
    template2.execute();
  }

  //익명 내부 클래스를 이용해서 새로운 클래스를 만들지 않고 이렇게 사용 가능
  @Test
  void templateMethodV2() {

    AbstractTemplate template1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비스지스 로직1 실행");
      }
    };
    log.info("클래스이름 1 = {}", template1.getClass());
    template1.execute();

    AbstractTemplate template2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비스지스 로직2 실행");
      }
    };
    log.info("클래스이름 2 = {}", template2.getClass());
    template2.execute();
  }

}

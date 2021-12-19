package com.study.aop.pointcut;

import com.study.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * application.properties
 * spring.aop.proxy-target-class=true CGLIB
 * spring.aop.proxy-target-class=false JDK 동적 프록시
 */
@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") //jdk 동적 프록시
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB -  (생략해도 cglib)
public class ThisTargetTest {

  @Autowired
  MemberService memberService;

  @Test
  void success(){
    log.info("memberService Proxy = {}", memberService.getClass());
    memberService.hello("helloA");
  }

  @Slf4j
  @Aspect
  static class ThisTargetAspect {

    //부모 타입 허용
    @Around("this(com.study.aop.member.MemberService)")
    public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this-interface] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("target(com.study.aop.member.MemberService)")
    public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target-interface] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }

    //이 경우는 dynamic proxy 로 적용 시키면 작동을 안하는 것을 볼 수 있다. 각각(다이나믹, cglib)이 어떻게 돌아가는지를 생각해 보자.
    //동적 프록시는 인터페이스를 기반으로 생성이 되기 때문에 구현 클래스를 알수 없다.
    @Around("this(com.study.aop.member.MemberServiceImpl)")
    public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this-impl] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }

    @Around("target(com.study.aop.member.MemberServiceImpl)")
    public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target-impl] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }




  }

}

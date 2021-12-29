package com.study.aop.proxyvs;

import com.study.aop.member.MemberService;
import com.study.aop.member.MemberServiceImpl;
import com.study.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})//jdk 동적 프록시
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})//cglib 로 동작
@SpringBootTest
/**
 * 스프링 부트 2.0 부터는 CGLIB 가 default 로 동작하게 되어 있다.
 */
@Import(ProxyDIAspect.class)
public class ProxyDITest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberServiceImpl memberServiceImpl;

  @Test
  void go(){
    log.info("memberService class={}", memberService.getClass());
    log.info("memberServiceImpl class{}", memberServiceImpl.getClass());
    memberServiceImpl.hello("hello");
  }



}

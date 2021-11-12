package com.study.advanced;

import com.study.advanced.trace.logtrace.FieldLogTrace;
import com.study.advanced.trace.logtrace.LogTrace;
import com.study.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {


  //싱글톤으로 등록
  @Bean
  public LogTrace logTrace() {
//    return new FieldLogTrace();
    //이렇게 하나만 바꾸면 밑에꺼로 적용이 된다.
    //이렇게 하나만 바꿔도 작동이 되게 코드를 짜는걸 생각을 해 보자.
    return new ThreadLocalLogTrace();
  }

}

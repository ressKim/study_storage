package com.study.advanced;

import com.study.advanced.trace.logtrace.FieldLogTrace;
import com.study.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  //싱글톤으로 등록
  @Bean
  public LogTrace logTrace() {
    return new FieldLogTrace();
  }

}

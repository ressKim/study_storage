package com.study.advanced.app.v3;

import com.study.advanced.trace.TraceStatus;
import com.study.advanced.trace.hellotrace.HelloTraceV2;
import com.study.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

  private final LogTrace trace;

  public void save(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepository.save()");
      //저장로직
      if (itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생");
      }

      sleep(1000);

      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

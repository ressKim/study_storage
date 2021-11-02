package com.study.advanced.app.v2;

import com.study.advanced.trace.TraceStatus;
import com.study.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

  private final OrderServiceV2 orderService;
  private final HelloTraceV2 trace;

  /*
  보면 적용은 됬지만 많이 지저분하고 따른 추가 로직이 너무 많이 필요하다.
   */
  @GetMapping("/v2/request")
  public String request(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderController.request()");
      orderService.orderItem(status.getTraceId(), itemId);
      trace.end(status);
      return "ok";
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}

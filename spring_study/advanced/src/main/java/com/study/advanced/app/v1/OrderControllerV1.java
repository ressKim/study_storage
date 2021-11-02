package com.study.advanced.app.v1;

import com.study.advanced.trace.TraceStatus;
import com.study.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

  private final OrderServiceV1 orderService;
  private final HelloTraceV1 trace;

  @GetMapping("/v1/request")
  public String request(String itemId) {
    TraceStatus status = null;
    //정상은 상관이없는데 예외처리도 로그를 찍어줘야 하기 때문에 이런 형식이 생기게 된다.
    try {
      status = trace.begin("OrderController.request()");
      orderService.orderItem(itemId);
      trace.end(status);
      return "ok";
    } catch (Exception e) {
      trace.exception(status, e);
      //예외를 꼭 다시 던저주어야 한다.(try 로 묶었기 때문)
      //로그가 어플리케이션 로직에 영향을 주면 안되기때문에 예외를 실행시킨다.
      throw e;
    }
  }
}

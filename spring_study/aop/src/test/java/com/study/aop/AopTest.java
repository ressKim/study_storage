package com.study.aop;

import com.study.aop.order.OrderRepository;
import com.study.aop.order.OrderService;
import com.study.aop.order.aop.AspectV1;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AspectV1.class)
public class AopTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void aopInfo(){
    log.info("isAopProxy, OrderService={}", AopUtils.isAopProxy(orderService));
    log.info("isAopProxy, OrderRepository={}", AopUtils.isAopProxy(orderRepository));
  }

  @Test
  void success(){
    orderService.orderItem("itemA");
  }

  @Test
  void exception(){
    Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
        .isInstanceOf(IllegalStateException.class);
  }


}

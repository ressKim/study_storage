package study.core.order;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import study.core.discount.FixDiscountPolicy;
import study.core.member.Grade;
import study.core.member.Member;
import study.core.member.MemoryMemberRepository;

class OrderServiceImplTest {

  @Test
  void createOrder() {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    memoryMemberRepository.save(new Member(1L, "name1", Grade.VIP));

    OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository,
        new FixDiscountPolicy());
    Order order = orderService.createOrder(1L, "itemA", 10000);

    assertThat(order.getDiscountPrice()).isEqualTo(1000);

  }
}
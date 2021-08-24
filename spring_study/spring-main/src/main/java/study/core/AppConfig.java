package study.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.core.discount.DiscountPolicy;
import study.core.discount.FixDiscountPolicy;
import study.core.member.MemberService;
import study.core.member.MemberServiceImpl;
import study.core.member.MemoryMemberRepository;
import study.core.order.OrderService;
import study.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    /**
     * @Bean memberService -> new MemoryMemberRepository()
     * @Bean orderService -> new MemoryMemberRepository()
     *
     * 여기서 보면 두군데에서 각각 new 를 해서 두번 생성하는거 아닌가? 생각 할 수 있는데
     * 확인 해 보면 같은 객체인것을 확인 할 수 있다.
     * 이 이유는 스프링 내부에서
    */

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    /**
     * 나중에 변경할 일이 생길 때 따른곳은 변경하지 않고, 여기 AppConfig 만 변경하면 된다.
     */

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }
}
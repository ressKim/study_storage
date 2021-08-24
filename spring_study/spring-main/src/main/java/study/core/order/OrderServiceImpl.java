package study.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.core.discount.DiscountPolicy;
import study.core.member.Member;
import study.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    //의존관계를 주입하는 방법에는 다양한 방법들이 있다.
    //이렇게 넣는거를 수정자 주입이라고 한다 . (사실 알아만 두자)
    //밑에 setXX 를 나눠서 할 수 있기때문에 선택적으로 주입을 할 수도 있다.
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //이렇게 되면 이게 먼저 호출되고 그다음 위에 setXX 두개가 호출된다.
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

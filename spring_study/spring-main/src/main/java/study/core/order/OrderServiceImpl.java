package study.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import study.core.discount.DiscountPolicy;
import study.core.member.Member;
import study.core.member.MemberRepository;

/**
 * 생성자 주입을 생각해 보자. - 이걸 쓰는게 좋다. 생성자 주입을 선택하고, 수정자 주입이 필요한 경우가 있다면 그때 선택해서 사용하자.
 */
//@Component
//public class OrderServiceImpl implements OrderService {

/**
 * @Autowired -- 의존성 주입 방법중 얼마전까지 많이 사용되던 필드 주입 방식이다 - 이 방식은 테스트 코드를 짠다고 가정해서 생각하면 관련된 test 코드 전체가
 * 스프링에 의존하는 경우가 발생하게 된다. 어플리케이션과 관계없는 테스트도 말이다. 그래서 이거의 사용은 왠만하면 피하자.
 */
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

//의존관계를 주입하는 방법에는 다양한 방법들이 있다.
//이렇게 넣는거를 수정자 주입이라고 한다 . (사실 알아만 두자)
//밑에 setXX 를 나눠서 할 수 있기때문에 선택적으로 주입을 할 수도 있다.
    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
//        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //이렇게 되면 이게 먼저 호출되고 그다음 위에 setXX 두개가 호출된다.
//        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

     */

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  /**
   * @param discountPolicy
   * @Autowired - 만약 component 가 중복되어 있다면 Autowired 는 그 다음행동으로 타입이 여러개 이면 필드명, 파라미터명을 확인해서 같은게있으면
   * 우선으로 찾아준다.
   * @Qualifier("XX") - 각각에 Qualifies 를 지정해서 사용할 것을 지정해주어도 된다. 만약 XX 를 못찾으면 XX 이름의 스프링 빈을 추가로 찾는다.
   * -> Qualifier 는 Qualifier 를 찾는 용도로만 명확하게 사용하는게 좋다.
   * @Primary - 이 어노테이션이 붙은 빈이 먼저 사용되게 한다.
   * -> ex) main DB , sub DB 가 있다고 치면 main DB 에 보통 @Primary 로 쓰고 보조 DB 를 사용할때 @Qualifier 로 이름을 붙이는 등을 할 수 있다.
   *
   * 느낌은 @Primary 는 기본값으로 동작하는것이고 , @Qualifier 는 상세하게 지정하는것이다.
   * 같이 사용한다면 더 상세한 @Qualifier 가 우선권을 가진다.
   */
  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository,
//      @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//    DiscountPolicy rateDiscountPolicy) {
      DiscountPolicy discountPolicy) {
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

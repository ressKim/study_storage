package study.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import study.core.member.Grade;
import study.core.member.Member;

/**
 * 이게 Component 가 두개가 되버리면 뭘 써야할 지 인식을 못해서 오류가 난다.
 * 방법으로는 수동으로 Bean 을 등록 할 수도 있고,
 * @Autowired , @Quilifier, @Primary 방법들도 있다.
 */
@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;//1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}

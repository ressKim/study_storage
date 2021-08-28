package study.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import study.core.annotation.MainDiscountPolicy;
import study.core.member.Grade;
import study.core.member.Member;

@Component
@Primary // 얘가 우선권을 가진다
//@Qualifier("mainDiscountPolicy") // -> 이렇게 되면 안에 들어있는게 String 이여서 오타 등이 날 수 있다. 그래서
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

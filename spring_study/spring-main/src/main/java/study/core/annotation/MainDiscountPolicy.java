package study.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Qualifier;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {

  /*
  이렇게 하면 추적 오타 등에서 효율적으로 사용할 수 있다.
  그래도 너무 무분별하게 사용하면 안좋다.
  spring 이 기본적으로 잘 제공하는 annotation 을 건드리는것은 가독성 등에서 안좋고,
  너무 많은 annotation 을 생성하면 또한 다른 개발자가 이 어노테이션이 먼지 알아야 되는 그런경우가 많기 때문에
  꼭 필요한거 아니면 피하자

   */
}

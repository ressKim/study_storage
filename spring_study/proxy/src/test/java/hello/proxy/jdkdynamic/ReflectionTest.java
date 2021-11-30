package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

  /**
   * 리플렉션 기술은 왠만하면 쓰지 말아야 된다.
   * 왜냐하면 컴파일 시점에서 오류가 발생하지 않고
   * 실행하고나서 동작중에 문제가 발생하기때문에 버그가 나올 수 있다.
   *
   * 가장 좋은 방법은 항상 컴파일 시점에 잡을 수 있는 코드를 작성하는 것이다.
   */

  @Test
  void reflection0() {
    Hello target = new Hello();

    //공통로직1 시작
    log.info("start");
    String result1 = target.callA();//호출하는 메서드만 다름
    log.info("result = {}", result1);
    //공통로직1 종료

    //공통로직2 시작
    log.info("start");
    String result2 = target.callB();//호출하는 메서드만 다름
    log.info("result = {}", result2);
    //공통로직2 종료

  }

  @Test
  void reflection1() throws Exception {
    //클래스 정보
    Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

    Hello target = new Hello();
    //callA 메서드 정보
    Method methodCallA = classHello.getMethod("callA");
    Object result1 = methodCallA.invoke(target);
    log.info("result1={}", result1);

    //callB 메서드 정보
    Method methodCallB = classHello.getMethod("callB");
    Object result2 = methodCallB.invoke(target);
    log.info("result2={}", result2);
  }

  @Test
  void reflection2() throws Exception {
    //클래스 정보
    Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

    Hello target = new Hello();
    //callA 메서드 정보
    Method methodCallA = classHello.getMethod("callA");
    dynamicCall(methodCallA, target);

    //callB 메서드 정보
    Method methodCallB = classHello.getMethod("callB");
    dynamicCall(methodCallB, target);
  }

  private void dynamicCall(Method method, Object target) throws Exception {
    log.info("start");
    Object result = method.invoke(target);
    log.info("result = {}", result);
  }


  @Slf4j
  static class Hello {

    public String callA() {
      log.info("callA");
      return "A";
    }

    public String callB() {
      log.info("callB");
      return "B";
    }
  }
}

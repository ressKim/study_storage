package hello.proxy.pureproex.decorator;

import hello.proxy.pureproex.decorator.code.Component;
import hello.proxy.pureproex.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproex.decorator.code.MessageDecorator;
import hello.proxy.pureproex.decorator.code.RealComponent;
import hello.proxy.pureproex.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.MessageSourceAccessor;

@Slf4j
public class DecoratorPatternTest {

  @Test
  void noDecorator() {
    Component realComponent = new RealComponent();
    DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
    client.execute();
  }

  @Test
  void decorator1() {
    Component realComponent = new RealComponent();
    Component messageComponent = new MessageDecorator(realComponent);
    DecoratorPatternClient client = new DecoratorPatternClient(messageComponent);
    client.execute();

  }

  @Test
  void decorator2() {
    Component realComponent = new RealComponent();
    Component messageDecorator = new MessageDecorator(realComponent);
    TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
    DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
    client.execute();

  }

}

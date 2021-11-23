package hello.proxy.pureproex.decorator;

import hello.proxy.pureproex.decorator.code.Component;
import hello.proxy.pureproex.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproex.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

  @Test
  void noDecorator(){
    Component realComponent = new RealComponent();
    DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
    client.execute();
  }

}

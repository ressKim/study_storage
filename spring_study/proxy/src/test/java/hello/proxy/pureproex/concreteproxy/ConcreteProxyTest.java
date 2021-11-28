package hello.proxy.pureproex.concreteproxy;

import hello.proxy.pureproex.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproex.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

  @Test
  void noProxy() {
    ConcreteLogic concreteLogic = new ConcreteLogic();
    ConcreteClient client = new ConcreteClient(concreteLogic);
    client.execute();
  }
}

package hello.proxy.pureproex.proxy;

import hello.proxy.pureproex.proxy.code.ProxyPatternClient;
import hello.proxy.pureproex.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

  @Test
  void noProxyTest(){
    RealSubject subject = new RealSubject();
    ProxyPatternClient client = new ProxyPatternClient(subject);
    client.execute();
    client.execute();
    client.execute();
  }

}

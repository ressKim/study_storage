package hello.proxy.pureproex.proxy;

import hello.proxy.pureproex.proxy.code.CacheProxy;
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

  // client -> 프록시 -> subject 이런식으로 구성이 된다.
  // 여기서 프록시에 값이 있다면 바로바로 불러주는 식이기때문에 여러번 호출한다면 속도가 매우 빠르다.
  // 캐시라고 보면 된다.
  @Test
  void cacheProxyTest(){
    RealSubject realSubject = new RealSubject();
    CacheProxy cacheProxy = new CacheProxy(realSubject);
    ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
    client.execute();
    client.execute();
    client.execute();
  }

}

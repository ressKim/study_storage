package study.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

  private String url;

  public NetworkClient() {
    System.out.println("url = " + url);
    connect();
    call("초기화 연결 메시지");


  }

  public void setUrl(String url) {
    this.url = url;
  }

  //서비스 시작시 호출
  public void connect() {
    System.out.println("connect : " + url);
  }

  public void call(String message) {
    System.out.println("call = " + url + "message = " + message);

  }

  //서비스 종료시 호출
  public void disconnect() {
    System.out.println("disconnect = " + url);
  }

  public void init(){
    System.out.println("NetworkClient.init");
    connect();
    call("초기화 연결 메시지");
  }

  public void close(){
    System.out.println("NetworkClient.close");
    disconnect();
  }








  /*
  implements InitializingBean, DisposableBean 사용 시
  초기화, 소멸 인터페이스의 단점
  - 이 인터페이스는 스프링 전용 인터페이스이기 때문에, 스프링 전용 인터페이스에 의존하게 된다.
  - 초기화, 소멸 메소드의 이름을 변경할 수 없다.
  - 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수가 없다.

  -->> 인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법들이고, 지금은 더 나은 방법들이 있어서 거의 사용하지는 않는다.
   */

//  @Override
//  public void afterPropertiesSet() throws Exception {
//    System.out.println("NetworkClient.afterPropertiesSet");
//    connect();
//    call("초기화 연결 메시지");
//  }
//
//  @Override
//  public void destroy() throws Exception {
//    System.out.println("NetworkClient.destroy");
//    disconnect();
//  }
}

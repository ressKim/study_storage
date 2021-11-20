package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식할 수 있다.
@RequestMapping
@ResponseBody
public interface OrderControllerV1 {

  @GetMapping("/v1/request")
  //controller 에서 이렇게 인터페이스를 구현할 일은 거의 없겠지만, 이렇게 구현을 한다면 @RequestParam 을 붙여줘야 한다.
  String request(@RequestParam("itemId") String itemId);

  @GetMapping("/v1/no-log")
  String noLog();

}

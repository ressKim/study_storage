package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//이렇게 해서 범위 밖의 설정파일들을 추가할려고 한다.
//@Import(AppV1Config.class)
@Import({AppV1Config.class, AppV2Config.class})
//이렇게 지정을 했는데 이거는 나중에 설정파일만을 변경하기 위해서 하위의 app 만 componentScan 을 하게 둔다
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}

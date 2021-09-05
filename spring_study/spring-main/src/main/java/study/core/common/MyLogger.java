package study.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * proxy 로 MyLogger 넣기
 * - 가짜 객체를 넣어서 동작
 * 가짜 프록시 객체는 내부에서 진짜 MyLogger 를 찾는 방법을 알고있다.
 * 가짜 프록시 객체는 원본 클래스를 상속 받아서 만들어졌기떄문에
 * 클라이언트 입장에서는 프록시인지 진짜인지 모르게 동일하게 동작을 할 수 있다.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + " [" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}

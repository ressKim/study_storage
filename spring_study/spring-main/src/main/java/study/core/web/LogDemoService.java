package study.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.core.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;


    public void logic(String id) {
       myLogger.log("service log" + id);
    }
}

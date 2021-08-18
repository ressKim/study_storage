package study.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//보통은 그냥 다 등록하는데 지금 공부하기위해 (아니면 전에껄 다 지워야 되니깐) ComponentScan 을 scan 대상에서 제외한다.
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}

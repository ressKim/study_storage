package hello.springtx.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RollbackTest {

  @Autowired
  RollbackService service;

  @Test
  void runtimeException() {
    assertThatThrownBy(() -> service.runtimeException())
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  void checkedException(){
    assertThatThrownBy(() -> service.checkedException())
        .isInstanceOf(MyCheckedException.class);
  }

  @Test
  void rollbackFor(){
    assertThatThrownBy(() -> service.rollbackFor())
        .isInstanceOf(MyCheckedException.class);
  }


  @TestConfiguration
  static class RollbackTestConfig {

    @Bean
    RollbackService rollbackService() {
      return new RollbackService();
    }
  }



  @Slf4j
  static class RollbackService {

    //runtime 예외 발생 : 롤백
    @Transactional
    public void runtimeException() {
      log.info("call runtimeException");
      throw new RuntimeException();
    }

    //체크예외 발생 : 커밋
    @Transactional
    public void checkedException() throws MyCheckedException {
      log.info("call checkedException");
      throw new MyCheckedException();
    }

    //체크 예외 rollbackFor 지정 : 롤백
    @Transactional(rollbackFor = MyCheckedException.class)
    public void rollbackFor() throws MyCheckedException {
      log.info("call runtimeException");
      throw new MyCheckedException();
    }

  }

  static class MyCheckedException extends Exception {

  }

}

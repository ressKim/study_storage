package com.study.jdbc.exception.basic;

import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UnCheckedAppTest {

  @Test
  void checked() {
    Controller controller = new Controller();
    Assertions.assertThatThrownBy(() -> controller.request())
        .isInstanceOf(Exception.class);
  }

  @Test
  void printEx(){
    Controller controller = new Controller();
    try {
      controller.request();
    } catch (Exception e) {
      log.info("ex", e);
    }
  }

  static class Controller {

    Service service = new Service();

    public void request() {
      service.logic();
    }
  }
  static class Service {

    Repository repository = new Repository();
    NetworkClient networkClient = new NetworkClient();

    public void logic() {
      repository.call();
      networkClient.call();
    }

  }

  static class NetworkClient {

    public void call() {
      throw new RuntimeConnectionException("연결 실패");
    }

  }

  static class Repository {

    public void call() {
      try {
        runSQL();
      } catch (SQLException e) {
        throw new RuntimeSqlException(e);
      }
    }

    public void runSQL() throws SQLException {
      throw new SQLException("ex");
    }
  }

  static class RuntimeConnectionException extends RuntimeException {

    public RuntimeConnectionException(String message) {
      super(message);
    }

  }

  static class RuntimeSqlException extends RuntimeException {

    public RuntimeSqlException(String message) {
      super(message);
    }


    public RuntimeSqlException(Throwable cause) {
      super(cause);
    }
  }

}

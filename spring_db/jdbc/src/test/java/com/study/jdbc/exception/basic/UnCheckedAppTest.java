package com.study.jdbc.exception.basic;

import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnCheckedAppTest {

  @Test
  void checked() {
    Controller controller = new Controller();
    Assertions.assertThatThrownBy(() -> controller.logic())
        .isInstanceOf(Exception.class);
  }

  static class Controller {

    Service service = new Service();

    public void logic() {
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

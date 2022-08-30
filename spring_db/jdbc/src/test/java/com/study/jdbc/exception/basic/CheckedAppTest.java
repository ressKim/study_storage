package com.study.jdbc.exception.basic;

import com.study.jdbc.exception.basic.CheckedAppTest.Service.Controller;
import java.net.ConnectException;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckedAppTest {

  @Test
  void checked() {
    Controller controller = new Controller();

    Assertions.assertThatThrownBy(() -> controller.logic())
        .isInstanceOf(Exception.class);
  }

  static class Service {

    static class Controller {

      Service service = new Service();

      public void logic() throws SQLException, ConnectException {
        service.logic();
      }
    }

    Repository repository = new Repository();
    NetworkClient networkClient = new NetworkClient();

    public void logic() throws SQLException, ConnectException {
      repository.call();
      networkClient.call();
    }

  }

  static class NetworkClient {

    public void call() throws ConnectException {
      throw new ConnectException("연결 실패");
    }

  }

  static class Repository {

    public void call() throws SQLException {
      throw new SQLException("ex");
    }
  }

}

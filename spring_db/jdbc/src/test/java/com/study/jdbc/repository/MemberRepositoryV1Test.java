package com.study.jdbc.repository;

import static com.study.jdbc.connection.ConnectionConst.PASSWORD;
import static com.study.jdbc.connection.ConnectionConst.URL;
import static com.study.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.study.jdbc.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import jdk.jfr.Threshold;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
class MemberRepositoryV1Test {

  MemberRepositoryV1 repository;

  @BeforeEach
  void beforeEach(){
    //기본 DriverManager - 항상 새로운 커넥션 획득
//    DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME,
//        PASSWORD);

    //커넥션 풀링
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPoolName(PASSWORD);

    repository = new MemberRepositoryV1(dataSource);


  }


  //실행해 보면 wrapping conn0 를 돌려쓰는것을 확인할 수 있음(재사용의 장점)
  @Test
  void crud() throws SQLException, InterruptedException {

    //save
    Member member = new Member("memberV100", 10000);
    repository.save(member);

    //findById
    Member findMember = repository.findById(member.getMemberId());
    log.info("findMember={}", findMember);
    log.info("member == findMember {}", member == findMember);

    assertThat(findMember).isEqualTo(member);

    //update: money : 10000 -> 20000
    repository.update(member.getMemberId(), 20000);
    Member updatedMember = repository.findById(member.getMemberId());

    assertThat(updatedMember.getMoney()).isEqualTo(20000);

    //delete
    repository.delete(member.getMemberId());
    assertThatThrownBy(() -> repository.findById(member.getMemberId()))
        .isInstanceOf(NoSuchElementException.class);

    Thread.sleep(1000);

  }
}
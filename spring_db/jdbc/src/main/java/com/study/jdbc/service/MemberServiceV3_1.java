package com.study.jdbc.service;

import com.study.jdbc.domain.Member;
import com.study.jdbc.repository.MemberRepositoryV3;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 트랜잭션 - 트랜잭션 매니저
 */
@RequiredArgsConstructor
@Slf4j
public class MemberServiceV3_1 {

//  private final DataSource dataSource;
  private final PlatformTransactionManager transactionManager;
  private final MemberRepositoryV3 memberRepository;


  public void accountTransfer(String fromId, String toId, int money) throws SQLException {
    //트랜잭션 시작
    TransactionStatus status = transactionManager.getTransaction(
        new DefaultTransactionDefinition());

    try {
      //비즈니스 로직 시작
      bizLogic(fromId, toId, money);
      transactionManager.commit(status);//성공시 커밋
    } catch (Exception e) {
      transactionManager.rollback(status);//실패시 롤백
      throw new IllegalStateException(e);
    }
  }

  private void bizLogic(String fromId, String toId, int money)
      throws SQLException {
    Member fromMember = memberRepository.findById(fromId);
    Member toMember = memberRepository.findById(toId);

    memberRepository.update(fromId, fromMember.getMoney() - money);
    validation(toMember);
    memberRepository.update(toId, toMember.getMoney() + money);
  }

  private void release(Connection con) {
    if (con != null) {
      try {
        con.setAutoCommit(true);//보통은 true 이기 때문에 돌려줘야 한다.
        con.close();
      } catch (Exception e) {
        //exception 을 로그로 남기면 {} 안쓰고 이렇게 써도 된다
        log.info("error", e);
      }
    }
  }

  private void validation(Member toMember) {
    if (toMember.getMemberId().equals("ex")) {
      throw new IllegalStateException("이체중 예외 발생");
    }
  }

}

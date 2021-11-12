package com.study.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

  //스레드로컬을 사용할 때는 조심해야 될 것이 있다.
  //해당 쓰레드가 쓰레드 로컬을 모두 사용하고 나면 remove 로 쓰레드 로컬에 저장된 값을 제거해야 된다.
  private ThreadLocal<String> nameStore = new ThreadLocal<>();

  public String logic(String name) {
    log.info("저장 name={} -> nameStore={}", name, nameStore.get());
    nameStore.set(name);
    sleep(1000);
    log.info("조회 nameStore={}", nameStore.get());
    return nameStore.get();
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


}

package com.study.advanced.trace.callback;

import com.study.advanced.trace.TraceStatus;
import com.study.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {

  private final LogTrace trace;

  public TraceTemplate(LogTrace logTrace) {
    this.trace = logTrace;
  }

  public <T> T execute(String message, TraceCallback<T> callback) {

    TraceStatus status = null;
    try {
      status = trace.begin(message);

      //로직 호출
      T result = callback.call();

      trace.end(status);
      return result;

    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }


}

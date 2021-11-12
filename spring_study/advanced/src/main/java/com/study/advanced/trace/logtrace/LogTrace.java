package com.study.advanced.trace.logtrace;

import com.study.advanced.trace.TraceStatus;

public interface LogTrace {

  TraceStatus begin(String message);

  void end(TraceStatus status);

  void exception(TraceStatus status, Exception e);

}

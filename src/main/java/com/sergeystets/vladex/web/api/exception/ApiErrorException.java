package com.sergeystets.vladex.web.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiErrorException extends RuntimeException {

  private final ErrorCode errorCode;

}

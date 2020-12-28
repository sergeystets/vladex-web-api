package com.sergeystets.vladex.web.api.exception;

import lombok.Data;

@Data
public class ErrorResponse {

  private final String errorMessage;
  private final ErrorCode errorCode;
  private final int httpStatusCode;
  private final String httpStatusText;
}

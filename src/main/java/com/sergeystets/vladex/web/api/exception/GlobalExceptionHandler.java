package com.sergeystets.vladex.web.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(ApiErrorException.class)
  public ResponseEntity<?> handleException(ApiErrorException apiError) {
    final ErrorCode errorCode = apiError.getErrorCode();
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(new ErrorResponse(
            errorCode.getErrorMessage(),
            errorCode,
            errorCode.getHttpStatus().value(),
            errorCode.getHttpStatus().name()));
  }
}
package com.sergeystets.vladex.web.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  PHONE_NUMBER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "The phone number is already registered"),
  PHONE_NUMBER_IS_NOT_REGISTERED(HttpStatus.NOT_FOUND, "The phone number is not registered"),
  OTP_IS_NOT_VALID_OR_EXPIRED(HttpStatus.BAD_REQUEST, "Provided OTP (one time password) is not valid or expired");

  private final HttpStatus httpStatus;
  private final String errorMessage;

  ErrorCode(HttpStatus httpStatus, String errorMessage) {
    this.httpStatus = httpStatus;
    this.errorMessage = errorMessage;
  }
}

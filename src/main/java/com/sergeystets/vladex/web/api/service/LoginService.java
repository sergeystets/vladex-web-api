package com.sergeystets.vladex.web.api.service;

import static com.sergeystets.vladex.web.api.exception.ErrorCode.OTP_IS_NOT_VALID_OR_EXPIRED;
import static com.sergeystets.vladex.web.api.exception.ErrorCode.PHONE_NUMBER_IS_NOT_REGISTERED;

import com.sergeystets.vladex.web.api.model.LoginRequest;
import com.sergeystets.vladex.web.api.entity.PendingVerificationEntity;
import com.sergeystets.vladex.web.api.exception.ApiErrorException;
import com.sergeystets.vladex.web.api.model.LoginResponse;
import com.sergeystets.vladex.web.api.model.OtpToken;
import com.sergeystets.vladex.web.api.repository.PendingVerificationRepository;
import com.sergeystets.vladex.web.api.repository.UserRepository;
import com.sergeystets.vladex.web.api.service.otp.OtpGenerator;
import com.sergeystets.vladex.web.api.service.otp.OtpSender;
import java.time.Clock;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final Clock clock;
  private final OtpSender otpSender;
  private final OtpGenerator otpGenerator;
  private final UserRepository userRepository;
  private final PendingVerificationRepository pendingVerificationRepository;

  @Value("${app.security.otp.expiration.seconds}")
  private long otpExpirationSeconds;

  @Transactional
  public LoginResponse login(LoginRequest loginRequest) {
    final String phoneNumber = loginRequest.getPhoneNumber();
    if (!userRepository.existsByPhoneNumber(phoneNumber)) {
      throw new ApiErrorException(PHONE_NUMBER_IS_NOT_REGISTERED);
    }

    // delete old OTP codes (if any)
    pendingVerificationRepository.deleteByPhoneNumber(phoneNumber);
    pendingVerificationRepository.flush();

    // generate new OTP code
    final String otp = otpGenerator.generate();

    // save new OTP code
    final PendingVerificationEntity pendingVerification = new PendingVerificationEntity();
    pendingVerification.setExpiration(LocalDateTime.now(clock).plusSeconds(otpExpirationSeconds));
    pendingVerification.setOtp(otp);
    pendingVerification.setPhoneNumber(phoneNumber);
    pendingVerificationRepository.save(pendingVerification);

    // send OTP to a client
    otpSender.sendOtp(new OtpToken(otp, phoneNumber));

    return new LoginResponse(otpExpirationSeconds);
  }


  @Transactional(dontRollbackOn = ApiErrorException.class)
  public void verify(String otp) {
    final PendingVerificationEntity pendingVerification = pendingVerificationRepository.findOneByOtp(otp);
    if (pendingVerification == null) {
      throw new ApiErrorException(OTP_IS_NOT_VALID_OR_EXPIRED);
    }
    if (pendingVerification.getExpiration().isBefore(LocalDateTime.now(clock))) {
      pendingVerificationRepository.deleteByOtp(otp);
      throw new ApiErrorException(OTP_IS_NOT_VALID_OR_EXPIRED);
    }
  }
}

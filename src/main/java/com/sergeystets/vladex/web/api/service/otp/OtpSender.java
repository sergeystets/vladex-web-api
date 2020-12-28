package com.sergeystets.vladex.web.api.service.otp;

import com.sergeystets.vladex.web.api.model.OtpToken;

public interface OtpSender {

  void sendOtp(OtpToken otpToken);

}

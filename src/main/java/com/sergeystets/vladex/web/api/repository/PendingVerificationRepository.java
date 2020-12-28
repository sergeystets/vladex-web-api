package com.sergeystets.vladex.web.api.repository;

import com.sergeystets.vladex.web.api.entity.PendingVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingVerificationRepository extends JpaRepository<PendingVerificationEntity, Long> {

  void deleteByPhoneNumber(String phoneNumber);

  PendingVerificationEntity findOneByOtp(String otp);

  void deleteByOtp(String otp);

  PendingVerificationEntity findOneByPhoneNumber(String userPhone);
}

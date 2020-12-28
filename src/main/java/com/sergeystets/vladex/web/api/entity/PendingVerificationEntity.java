package com.sergeystets.vladex.web.api.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pending_verification")
public class PendingVerificationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(30)")
  private String phoneNumber;

  @Column(name = "otp", nullable = false, unique = true, columnDefinition = "varchar(8)")
  private String otp;

  @Column(name = "expiration", nullable = false)
  private LocalDateTime expiration;

}

package com.sergeystets.vladex.web.api.entity;

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
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "phone_number", nullable = false, unique = true, columnDefinition = "varchar(30)")
  private String phoneNumber;

  @Column(name = "name", nullable = false, columnDefinition = "varchar(100)")
  private String name;

  @Column(name = "surname", columnDefinition = "varchar(100)")
  private String surname;

}

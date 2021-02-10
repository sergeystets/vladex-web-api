package com.sergeystets.vladex.web.api.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chat")
public class ChatEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chat_id")
  private Long id;

  @Column(name = "chat_name", nullable = false, columnDefinition = "varchar(255)")
  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "chat_members",
      joinColumns = {@JoinColumn(name = "chat_id")},
      inverseJoinColumns = {@JoinColumn(name = "member_id")})
  private List<UserEntity> members;

}

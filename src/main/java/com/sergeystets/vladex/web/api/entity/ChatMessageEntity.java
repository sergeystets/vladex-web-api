package com.sergeystets.vladex.web.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chat_message")
@Accessors(chain = true)
public class ChatMessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "message_id", columnDefinition = "bigint")
  private Long messageId;

  @Column(name = "content", columnDefinition = "longtext")
  private String content;

  @ManyToOne
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private UserEntity author;

  @Column(name = "seen", columnDefinition = "boolean")
  private boolean seen;

  @Column(name = "chat_id", nullable = false, columnDefinition = "bigint")
  private Long chatId;

  @Column(name = "timestamp", nullable = false, columnDefinition = "bigint")
  private Long timestamp;

}

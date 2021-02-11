package com.sergeystets.vladex.web.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMessage {

  private long id;
  private String content;
  private Contact user;
  private long chatId;
  private boolean seen;
  private long timestamp;
}

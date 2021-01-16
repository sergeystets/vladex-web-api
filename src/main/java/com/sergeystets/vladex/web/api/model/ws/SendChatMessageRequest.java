package com.sergeystets.vladex.web.api.model.ws;

import lombok.Data;

@Data
public class SendChatMessageRequest {

  private String content;
  private long chatId;

}
package com.sergeystets.vladex.web.api.model.ws;

import lombok.Data;

@Data
public class ChatMessage {

  private String content;
  private String from;
  private String to;

}
package com.sergeystets.vladex.web.api.model.ws.signaling;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerResponse {

  private long chatId;
  private String calleeName;
  private String sdp;
  private String type;

  public AnswerResponse(Answer answer, String calleeName) {
    chatId = answer.getChatId();
    this.calleeName = calleeName;
    this.sdp = answer.getSdp();
    this.type = answer.getType();
  }
}

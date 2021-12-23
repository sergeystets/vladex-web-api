package com.sergeystets.vladex.web.api.model.ws.signaling;

import lombok.Data;

@Data
public class IceCandidate {

  private long chatId;
  private String candidate;

}

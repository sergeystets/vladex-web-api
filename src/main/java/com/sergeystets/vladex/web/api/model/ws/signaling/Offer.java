package com.sergeystets.vladex.web.api.model.ws.signaling;

import lombok.Data;

@Data
public class Offer {

  private long chatId;
  private String sdp;
  private String type;

}

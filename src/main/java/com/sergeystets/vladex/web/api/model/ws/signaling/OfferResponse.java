package com.sergeystets.vladex.web.api.model.ws.signaling;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OfferResponse {

  private long chatId;
  private String callerName;
  private String sdp;
  private String type;

  public OfferResponse(Offer offerRequest, String callerName) {
    chatId = offerRequest.getChatId();
    this.callerName = callerName;
    this.sdp = offerRequest.getSdp();
    this.type = offerRequest.getType();
  }
}

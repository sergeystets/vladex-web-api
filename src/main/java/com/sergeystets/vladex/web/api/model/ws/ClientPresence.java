package com.sergeystets.vladex.web.api.model.ws;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientPresence {

  private boolean online;
  private long userId;

}

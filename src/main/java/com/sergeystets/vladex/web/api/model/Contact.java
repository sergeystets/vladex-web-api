package com.sergeystets.vladex.web.api.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Contact {

  private long id;
  private String name;
  private boolean online;
}

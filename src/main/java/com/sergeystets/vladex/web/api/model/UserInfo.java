package com.sergeystets.vladex.web.api.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfo {

  private Long id;
  private String username;
  private String phoneNumber;
  private long chatIdToLoad;
}

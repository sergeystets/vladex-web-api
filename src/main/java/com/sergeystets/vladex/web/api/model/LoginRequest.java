package com.sergeystets.vladex.web.api.model;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

  @NotNull
  private String phoneNumber;

}

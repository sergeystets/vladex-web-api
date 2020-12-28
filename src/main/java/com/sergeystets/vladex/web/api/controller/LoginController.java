package com.sergeystets.vladex.web.api.controller;

import com.sergeystets.vladex.web.api.model.LoginRequest;
import com.sergeystets.vladex.web.api.model.LoginResponse;
import com.sergeystets.vladex.web.api.service.LoginService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1")
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public LoginResponse register(@RequestBody @Valid LoginRequest loginRequest) {
    return loginService.login(loginRequest);
  }
}

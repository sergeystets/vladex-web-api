package com.sergeystets.vladex.web.api.controller;

import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1")
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public UserInfo me(Authentication authentication) {
    final String phone = authentication.getName();
    log.info("User requested information about his profile {}", phone);
    return userService.findUserByPhone(phone);
  }
}

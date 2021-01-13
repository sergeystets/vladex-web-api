package com.sergeystets.vladex.web.api.controller;

import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.service.ChatsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "v1/chat")
@Slf4j
public class ChatsController {

  private final ChatsService chatsService;

  @GetMapping
  public List<Chat> getChats(Authentication authentication) {
    final String phone = String.valueOf(((JwtAuthenticationToken) authentication)
        .getToken().getClaims().get("user_name"));
    log.info("Get available chats for {}", phone);
    return chatsService.getChats(42);
  }

  @GetMapping("/{id}")
  public List<ChatMessage> loadChat(Authentication authentication, @PathVariable long id) {
    final String phone = String.valueOf(((JwtAuthenticationToken) authentication)
        .getToken().getClaims().get("user_name"));
    log.info("Load messages for chat with id = {} for {}", id, phone);
    return chatsService.loadChat(id);
  }
}

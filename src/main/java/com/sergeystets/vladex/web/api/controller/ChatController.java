package com.sergeystets.vladex.web.api.controller;

import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "v1/chat")
@Slf4j
public class ChatController {

  private final ChatService chatService;

  @GetMapping
  public List<Chat> getChats(Authentication authentication) {
    final String phone = authentication.getName();
    log.info("Get available chats for {}", phone);
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long userId = (Long) token.getClaims().get("user_id");
    return chatService.getChats(userId);
  }

  @GetMapping("/{id}/messages")
  public List<ChatMessage> loadChatMessages(Authentication authentication, @PathVariable long id) {
    final String phone = authentication.getName();
    log.info("Load messages for chat with id = {} for {}", id, phone);
    return chatService.loadChatMessages(id);
  }
}

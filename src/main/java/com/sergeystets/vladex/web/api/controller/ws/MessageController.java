package com.sergeystets.vladex.web.api.controller.ws;

import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.model.ws.SendChatMessageRequest;
import com.sergeystets.vladex.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

  @Autowired
  private UserService userService;

  @MessageMapping("/ws")
  @SendTo("/topic/chat")
  public ChatMessage sendMessage(Authentication authentication, SendChatMessageRequest message) {
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long userId = (Long) token.getClaims().get("user_id");
    final UserInfo user = userService.findUserById(userId);
    return new ChatMessage()
        .setChatId(message.getChatId())
        .setContent(message.getContent())
        .setId(1)
        .setUser(new Contact()
            .setId(userId)
            .setUsername(user.getUsername())
        );
  }
}

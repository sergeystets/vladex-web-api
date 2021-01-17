package com.sergeystets.vladex.web.api.controller.ws;

import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.model.ws.SendChatMessageRequest;
import com.sergeystets.vladex.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

  @Autowired
  private UserService userService;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/ws")
  public void sendMessage(Authentication authentication, SendChatMessageRequest message) {
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long userId = (Long) token.getClaims().get("user_id");
    final UserInfo user = userService.findUserById(userId);
    final ChatMessage chatMessage = new ChatMessage()
        .setChatId(message.getChatId())
        .setContent(message.getContent())
        .setId(1)
        .setUser(new Contact()
            .setId(userId)
            .setUsername(user.getUsername())
        );

    messagingTemplate.convertAndSendToUser(user.getPhoneNumber(), "/queue/chat", chatMessage);
  }
}

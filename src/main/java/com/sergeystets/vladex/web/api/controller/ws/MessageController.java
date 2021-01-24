package com.sergeystets.vladex.web.api.controller.ws;

import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.model.ws.SendChatMessageRequest;
import com.sergeystets.vladex.web.api.service.ChatsService;
import com.sergeystets.vladex.web.api.service.UserService;
import java.util.List;
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

  @Autowired
  private ChatsService chatsService;

  @MessageMapping("/ws")
  public void sendMessage(Authentication authentication, SendChatMessageRequest message) {
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long userId = (Long) token.getClaims().get("user_id");
    final UserInfo fromUser = userService.findUserById(userId);
    final List<Chat> availableChats = chatsService.getChats(userId);
    final Chat destinationChat = availableChats.stream()
        .filter(chat -> chat.getId().equals(message.getChatId())).findFirst()
        .orElseThrow(RuntimeException::new);
    final UserInfo toUser = userService.findUserById(destinationChat.getContactId());

    final ChatMessage chatMessage = new ChatMessage()
        .setChatId(message.getChatId())
        .setContent(message.getContent())
        .setId(1)
        .setUser(new Contact()
            .setId(userId)
            .setUsername(fromUser.getUsername())
        );

    messagingTemplate.convertAndSendToUser(fromUser.getPhoneNumber(), "/queue/chat", chatMessage);
    messagingTemplate.convertAndSendToUser(toUser.getPhoneNumber(), "/queue/chat", chatMessage);
  }
}

package com.sergeystets.vladex.web.api.controller.ws;

import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.model.ws.SendChatMessageRequest;
import com.sergeystets.vladex.web.api.service.ChatService;
import com.sergeystets.vladex.web.api.service.UserService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MessageController {

  private final UserService userService;
  private final ChatService chatService;
  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/ws")
  public void sendMessage(Authentication authentication, SendChatMessageRequest message) {
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long userId = (Long) token.getClaims().get("user_id");
    final UserInfo fromUser = userService.findUserById(userId);
    final Chat chat = chatService.getChat(userId, message.getChatId());

    final ChatMessage chatMessage = new ChatMessage()
        .setSeen(false)
        .setTimestamp(new Date().getTime())
        .setChatId(message.getChatId())
        .setContent(message.getContent())
        .setUser(new Contact()
            .setId(userId)
            .setUsername(fromUser.getUsername())
        );

    chatService.save(chatMessage);

    // broadcast message to all chat members
    for (Contact chatMember : chat.getMembers()) {
      messagingTemplate.convertAndSendToUser(chatMember.getPhoneNumber(), "/queue/chat", chatMessage);
    }
  }
}

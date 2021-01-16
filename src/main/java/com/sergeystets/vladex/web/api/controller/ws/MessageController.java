package com.sergeystets.vladex.web.api.controller.ws;

import com.sergeystets.vladex.web.api.model.ws.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

  @MessageMapping("/ws")
  public ChatMessage sendMessage(Authentication authentication, ChatMessage message) {
    return message;
  }
}

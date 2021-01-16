package com.sergeystets.vladex.web.api.config.ws;

import com.sergeystets.vladex.web.api.model.ws.ClientPresence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
@Slf4j
public class SessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public void onApplicationEvent(SessionConnectedEvent event) {
    log.info("ws connected event {}", event);
    simpMessagingTemplate.convertAndSend("/topic/presence",
        new ClientPresence().setOnline(true).setUserId(2));
  }
}

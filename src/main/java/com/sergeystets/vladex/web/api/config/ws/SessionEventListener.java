package com.sergeystets.vladex.web.api.config.ws;

import com.sergeystets.vladex.web.api.model.ws.ClientPresence;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class SessionEventListener implements ApplicationListener<AbstractSubProtocolEvent> {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Override
  public void onApplicationEvent(AbstractSubProtocolEvent event) {
    if (event instanceof SessionConnectedEvent) {
      log.info("ws connected event {}", event);
      onPresenceChanged(event, true);
    }
    if (event instanceof SessionDisconnectEvent) {
      log.info("ws disconnected event {}", event);
      onPresenceChanged(event, false);
    }
  }

  private void onPresenceChanged(AbstractSubProtocolEvent event, boolean online) {
    final Jwt token = ((JwtAuthenticationToken) Objects.requireNonNull(event.getUser())).getToken();
    final long userId = (Long) token.getClaims().get("user_id");
    final ClientPresence presence = new ClientPresence().setOnline(online).setUserId(userId);
    messagingTemplate.convertAndSend("/topic/presence", presence);
  }
}

package com.sergeystets.vladex.web.api.controller.ws.signaling;

import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.model.ws.signaling.Answer;
import com.sergeystets.vladex.web.api.model.ws.signaling.AnswerResponse;
import com.sergeystets.vladex.web.api.model.ws.signaling.IceCandidate;
import com.sergeystets.vladex.web.api.model.ws.signaling.Offer;
import com.sergeystets.vladex.web.api.model.ws.signaling.OfferResponse;
import com.sergeystets.vladex.web.api.service.ChatService;
import com.sergeystets.vladex.web.api.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SignalingController {

  private final SimpMessagingTemplate messagingTemplate;
  private final ChatService chatService;
  private final UserService userService;

  @MessageMapping("/ws/signaling/offer")
  public void createOffer(Authentication authentication, Offer offer) {
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long callerId = (Long) token.getClaims().get("user_id");
    final UserInfo caller = userService.findUserById(callerId);
    final Chat chat = chatService.getChat(callerId, offer.getChatId());
    log.info("[offer] user {} chat {}", callerId, chat.getId());

    if (chat.isPeerToPeer()) {
      Optional<Contact> calleeOpt = chat.getMembers().stream().filter(m -> m.getId() != callerId).findAny();
      calleeOpt.ifPresent(callee -> messagingTemplate.convertAndSendToUser(
          callee.getPhoneNumber(),
          "/queue/signaling-offer",
          new OfferResponse(offer, caller.getUsername()))
      );
    }
  }

  @MessageMapping("/ws/signaling/answer")
  public void answer(Authentication authentication, Answer answer) {
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long calleeId = (Long) token.getClaims().get("user_id");
    final UserInfo callee = userService.findUserById(calleeId);
    final Chat chat = chatService.getChat(calleeId, answer.getChatId());
    log.info("[answer] user {} chat {}", calleeId, chat.getId());

    if (chat.isPeerToPeer()) {
      Optional<Contact> callerOpt = chat.getMembers().stream().filter(m -> m.getId() != calleeId).findAny();
      callerOpt.ifPresent(caller -> messagingTemplate.convertAndSendToUser(
          caller.getPhoneNumber(),
          "/queue/signaling-answer",
          new AnswerResponse(answer, callee.getUsername()))
      );
    }
  }

  @MessageMapping("/ws/signaling/ice-candidate")
  public void iceCandidateOffer(Authentication authentication, IceCandidate iceCandidate) {
    final Jwt token = ((Jwt) authentication.getPrincipal());
    final long callerId = (Long) token.getClaims().get("user_id");
    final Chat chat = chatService.getChat(callerId, iceCandidate.getChatId());

    log.info("[ice-candidate] user {} chat {}", callerId, chat.getId());

    if (chat.isPeerToPeer()) {
      Optional<Contact> calleeOpt = chat.getMembers().stream().filter(m -> m.getId() != callerId).findAny();
      calleeOpt.ifPresent(callee -> messagingTemplate.convertAndSendToUser(
          callee.getPhoneNumber(),
          "/queue/signaling-ice-candidate",
          iceCandidate)
      );
    }
  }
}

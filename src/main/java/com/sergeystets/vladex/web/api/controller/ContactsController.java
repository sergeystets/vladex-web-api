package com.sergeystets.vladex.web.api.controller;

import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.service.ContactsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "v1/contact")
@Slf4j
public class ContactsController {

  private final ContactsService contactsService;

  @GetMapping
  public List<Contact> getContacts(Authentication authentication) {
    final String phone = String.valueOf(((JwtAuthenticationToken) authentication)
        .getToken().getClaims().get("user_name"));
    log.info("Get contacts for {}", phone);
    return contactsService.getContacts(42);
  }
}

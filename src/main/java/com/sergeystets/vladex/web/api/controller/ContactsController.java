package com.sergeystets.vladex.web.api.controller;

import com.google.common.collect.ImmutableList;
import com.sergeystets.vladex.web.api.model.Contact;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/contact")
@Slf4j
public class ContactsController {

  @GetMapping("/")
  public List<Contact> getContacts(Authentication authentication) {
    log.info("authentication {}", authentication);
    return ImmutableList.of(new Contact().setId(1).setName("Pavle Burykh").setOnline(true));
  }

}

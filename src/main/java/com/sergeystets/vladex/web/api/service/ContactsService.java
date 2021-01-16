package com.sergeystets.vladex.web.api.service;

import com.google.common.collect.ImmutableList;
import com.sergeystets.vladex.web.api.model.Contact;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {

  public List<Contact> getContacts(long userId) {
    return ImmutableList.of(
        new Contact().setId(1).setUsername("Pavle Burykh").setOnline(true)
            .setAvatar("https://randomuser.me/api/portraits/men/79.jpg"),
        new Contact().setId(2).setUsername("Valeriia Stets").setOnline(false)
            .setAvatar("https://randomuser.me/api/portraits/women/57.jpg"),
        new Contact().setId(3).setUsername("Andrii Chypur").setOnline(false)
            .setAvatar("https://randomuser.me/api/portraits/men/56.jpg")
    );
  }
}

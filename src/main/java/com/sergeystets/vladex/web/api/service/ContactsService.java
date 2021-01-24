package com.sergeystets.vladex.web.api.service;

import static java.util.Collections.emptyList;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.sergeystets.vladex.web.api.model.Contact;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {

  public List<Contact> getContacts(long userId) {
    final ImmutableMap<Long, List<Contact>> contacts = ImmutableMap.of(
        42L,
        ImmutableList.of(
            new Contact().setId(0).setUsername("Pavel Burykh").setOnline(true)
                .setAvatar("https://randomuser.me/api/portraits/men/79.jpg"),
            new Contact().setId(1).setUsername("Valeriia Stets").setOnline(false)
                .setAvatar("https://randomuser.me/api/portraits/women/57.jpg"),
            new Contact().setId(2).setUsername("Andrii Chypur").setOnline(false)
                .setAvatar("https://randomuser.me/api/portraits/men/56.jpg")),

        0L,
        ImmutableList.of(
            new Contact().setId(42).setUsername("Sergii Stets").setOnline(true)
                .setAvatar("https://randomuser.me/api/portraits/men/80.jpg"),
            new Contact().setId(1).setUsername("Valeriia Stets").setOnline(false)
                .setAvatar("https://randomuser.me/api/portraits/women/57.jpg"),
            new Contact().setId(2).setUsername("Andrii Chypur").setOnline(false)
                .setAvatar("https://randomuser.me/api/portraits/men/56.jpg")),
        1L,
        ImmutableList.of(
            new Contact().setId(42).setUsername("Sergii Stets").setOnline(true)
                .setAvatar("https://randomuser.me/api/portraits/men/80.jpg"),
            new Contact().setId(0).setUsername("Pavel Burykh").setOnline(true)
                .setAvatar("https://randomuser.me/api/portraits/men/79.jpg"),
            new Contact().setId(2).setUsername("Andrii Chypur").setOnline(false)
                .setAvatar("https://randomuser.me/api/portraits/men/56.jpg")),
        2L,
        ImmutableList.of(
            new Contact().setId(42).setUsername("Sergii Stets").setOnline(true)
                .setAvatar("https://randomuser.me/api/portraits/men/80.jpg"),
            new Contact().setId(0).setUsername("Pavel Burykh").setOnline(true)
                .setAvatar("https://randomuser.me/api/portraits/men/79.jpg"),
            new Contact().setId(2).setUsername("Valeriia Stets").setOnline(false)
                .setAvatar("https://randomuser.me/api/portraits/women/57.jpg"))
    );

    return contacts.getOrDefault(userId, emptyList());
  }
}

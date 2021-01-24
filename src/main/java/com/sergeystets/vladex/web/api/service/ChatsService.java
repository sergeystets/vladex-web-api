package com.sergeystets.vladex.web.api.service;

import static java.util.Collections.emptyList;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.model.Contact;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ChatsService {

  public List<Chat> getChats(long userId) {
    final Map<Long, List<Chat>> chats = ImmutableMap.of(
        42L, // Sergii Stets
        ImmutableList.of(
            new Chat(0L, "Pavel Burykh", 0L, true),
            new Chat(1L, "Valeriia Stets", 1L, false),
            new Chat(2L, "Andrii Chupyr", 2L, false)
        ), 0L, // Pavel Burykh
        ImmutableList.of(
            new Chat(0L, "Sergii Stets", 42L, true),
            new Chat(3L, "Valeriia Stets", 1L, false),
            new Chat(4L, "Andrii Chupyr", 2L, false)
        ), 1L, // Valeriia Stets
        ImmutableList.of(
            new Chat(1L, "Sergii Stets", 42L, true),
            new Chat(3L, "Pavel Burykh", 0L, true),
            new Chat(5L, "Andrii Chupyr", 2L, false)
        ), 2L, // Andrii Chupyr
        ImmutableList.of(
            new Chat(2L, "Sergii Stets", 42L, true),
            new Chat(4L, "Pavel Burykh", 0L, true),
            new Chat(5L, "Valerii Stets", 1L, false)
        ));

    return chats.getOrDefault(userId, emptyList());
  }

  public List<ChatMessage> loadChat(long id) {
    final Map<Long, List<ChatMessage>> chats = ImmutableMap.<Long, List<ChatMessage>>builder()
        .put(
            0L, // Sergii Stets vs Pavel Burykh
            ImmutableList.of(
                new ChatMessage(0, "Hello :)",
                    new Contact().setId(0).setUsername("Pavel Burykh"), 0, true),
                new ChatMessage(1, "How are you?",
                    new Contact().setId(0).setUsername("Pavel Burykh"), 0, true),
                new ChatMessage(2, "I'm fine, thank you!",
                    new Contact().setId(42).setUsername("Sergii Stets"), 0, true)
            ))
        .put(
            1L, // Vererii Stets vs Sergii Stets
            ImmutableList.of(
                new ChatMessage(0, "Hi",
                    new Contact().setId(1).setUsername("Valeriia Stets"), 1, true),
                new ChatMessage(1, ":)",
                    new Contact().setId(1).setUsername("Valeriia Stets"), 1, true),
                new ChatMessage(2, "Hello",
                    new Contact().setId(42).setUsername("Sergii Stets"), 1, true)))
        .put(

            2L, // Andrii Chupyr vs Sergii Stets
            ImmutableList.of(
                new ChatMessage(0, "Hi, this is cool messenger",
                    new Contact().setId(2).setUsername("Andrii Chupyr"), 2, true),
                new ChatMessage(1, "Thank you",
                    new Contact().setId(42).setUsername("Sergii Stets"), 2, true),
                new ChatMessage(2, ":)",
                    new Contact().setId(42).setUsername("Sergii Stets"), 2, true)
            ))
        .put(
            3L, //  Valeriia Stets vs Pavel Burykh
            ImmutableList.of(
                new ChatMessage(0, "Hi",
                    new Contact().setId(1).setUsername("Valeriia Stets"), 3, true),
                new ChatMessage(1, ":)",
                    new Contact().setId(0).setUsername("Pavel Burykh"), 3, true),
                new ChatMessage(2, ":)",
                    new Contact().setId(1).setUsername("Valeriia Stets"), 3, true)
            ))
        .put(
            4L, //  Pavel Burykh vs Andrii Chupyr
            ImmutableList.of(
                new ChatMessage(0, "Hi",
                    new Contact().setId(2).setUsername("Andrii Chupyr"), 4, true),
                new ChatMessage(1, ":)",
                    new Contact().setId(0).setUsername("Pavel Burykh"), 4, true),
                new ChatMessage(2, ":)",
                    new Contact().setId(2).setUsername("Andrii Chupyr"), 4, true)
            ))
        .put(
            5L, //  Valeriia Stets vs Andrii Chupyr
            ImmutableList.of(
                new ChatMessage(0, "Hi",
                    new Contact().setId(1).setUsername("Valeriia Stets"), 5, true),
                new ChatMessage(1, ":)",
                    new Contact().setId(1).setUsername("Valeriia Stets"), 5, true),
                new ChatMessage(2, ":)",
                    new Contact().setId(2).setUsername("Andrii Chupyr"), 5, true)))
        .build();

    return chats.getOrDefault(id, emptyList());
  }
}

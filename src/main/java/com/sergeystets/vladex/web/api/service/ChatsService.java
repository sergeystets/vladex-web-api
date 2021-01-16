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
    return ImmutableList.of(
        new Chat(0L, "Pavel Burykh"),
        new Chat(1L, "Valeriia Stets"),
        new Chat(2L, "Andrii Chupyr"),
        new Chat(3L, "Chat #3"),
        new Chat(4L, "Chat #4"),
        new Chat(5L, "Chat #5"),
        new Chat(6L, "Chat #6"),
        new Chat(7L, "Chat #7"),
        new Chat(8L, "Chat #8"),
        new Chat(9L, "Chat #9")
    );
  }

  public List<ChatMessage> loadChat(long id) {
    final Map<Long, List<ChatMessage>> chats = ImmutableMap.of(
        0L,
        ImmutableList.of(
            new ChatMessage(1, "Hello :)",
                new Contact().setId(1).setUsername("Pavel Burykh"), 0),
            new ChatMessage(2, "How are you?",
                new Contact().setId(1).setUsername("Pavel Burykh"), 0),
            new ChatMessage(3, "I'm fine, thank you!",
                new Contact().setId(42).setUsername("Sergii Stets"), 0)
        ),
        1L,
        ImmutableList.of(
            new ChatMessage(1, "Hi",
                new Contact().setId(2).setUsername("Valeriia Stets"), 1),
            new ChatMessage(2, ":)",
                new Contact().setId(2).setUsername("Valeriia Stets"), 1),
            new ChatMessage(3, "Hello",
                new Contact().setId(42).setUsername("Sergii Stets"), 1)),

        2L,
        ImmutableList.of(
            new ChatMessage(1, "Hi, this is cool messenger",
                new Contact().setId(3).setUsername("Andrii Chupyr"), 2),
            new ChatMessage(2, "Thank you",
                new Contact().setId(42).setUsername("Sergii Stets"), 2),
            new ChatMessage(3, ":)",
                new Contact().setId(42).setUsername("Sergii Stets"), 2)
        ));

    return chats.getOrDefault(id, emptyList());
  }
}

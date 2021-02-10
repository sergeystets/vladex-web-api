package com.sergeystets.vladex.web.api.service;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.sergeystets.vladex.web.api.mapper.ChatMapper;
import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.repository.ChatRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatMapper chatMapper;
  private final ChatRepository chatRepository;

  @Transactional(readOnly = true)
  public List<Chat> getChats(long userId) {
    return chatRepository.findAllByUserId(userId).map(chatMapper::toChatDto).collect(toList());
  }

  @Transactional(readOnly = true)
  public Chat getChat(long userId, long chatId) {
    return getChats(userId)
        .stream()
        .filter(chat -> chat.getId() == chatId).findFirst()
        .orElseThrow(RuntimeException::new);
  }

  @Transactional(readOnly = true)
  public List<ChatMessage> loadChatMessages(long chatId) {
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

    return chats.getOrDefault(chatId, emptyList());
  }
}

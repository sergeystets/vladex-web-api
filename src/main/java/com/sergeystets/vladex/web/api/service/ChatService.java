package com.sergeystets.vladex.web.api.service;

import static java.util.stream.Collectors.toList;

import com.sergeystets.vladex.web.api.entity.ChatMessageEntity;
import com.sergeystets.vladex.web.api.entity.UserEntity;
import com.sergeystets.vladex.web.api.mapper.ChatMapper;
import com.sergeystets.vladex.web.api.mapper.ChatMessageMapper;
import com.sergeystets.vladex.web.api.model.Chat;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import com.sergeystets.vladex.web.api.repository.ChatMessageRepository;
import com.sergeystets.vladex.web.api.repository.ChatRepository;
import com.sergeystets.vladex.web.api.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatMapper chatMapper;
  private final ChatMessageMapper chatMessageMapper;
  private final ChatRepository chatRepository;
  private final ChatMessageRepository chatMessageRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<Chat> getChats(long userId) {
    return chatRepository.findAllByUserId(userId)
        .map(chatMapper::toChatDto)
        .collect(toList());
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
    return chatMessageRepository.findAllByChatId(chatId)
        .map(chatMessageMapper::toChatMessageDto)
        .collect(toList());
  }

  @Transactional
  public void save(ChatMessage message) {
    final ChatMessageEntity messageEntity = chatMessageMapper.toEntity(message);
    final UserEntity author = userRepository.findById(message.getUser().getId()).orElse(null);
    messageEntity.setAuthor(author);
    chatMessageRepository.save(messageEntity);
  }
}

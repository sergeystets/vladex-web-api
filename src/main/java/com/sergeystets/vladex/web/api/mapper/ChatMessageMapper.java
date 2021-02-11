package com.sergeystets.vladex.web.api.mapper;

import com.sergeystets.vladex.web.api.entity.ChatMessageEntity;
import com.sergeystets.vladex.web.api.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageMapper {

  private final UserMapper userMapper;

  public ChatMessage toChatMessageDto(ChatMessageEntity chatMessageEntity) {
    return new ChatMessage()
        .setId(chatMessageEntity.getMessageId())
        .setTimestamp(chatMessageEntity.getTimestamp())
        .setUser(userMapper.toContact(chatMessageEntity.getAuthor()))
        .setContent(chatMessageEntity.getContent())
        .setSeen(chatMessageEntity.isSeen())
        .setChatId(chatMessageEntity.getChatId());
  }

  public ChatMessageEntity toEntity(ChatMessage messageDto) {
    return new ChatMessageEntity()
        .setTimestamp(messageDto.getTimestamp())
        .setChatId(messageDto.getChatId())
        .setContent(messageDto.getContent())
        .setSeen(messageDto.isSeen());
  }
}

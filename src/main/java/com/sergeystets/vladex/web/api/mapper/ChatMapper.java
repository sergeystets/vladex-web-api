package com.sergeystets.vladex.web.api.mapper;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

import com.sergeystets.vladex.web.api.entity.ChatEntity;
import com.sergeystets.vladex.web.api.model.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMapper {

  private final UserMapper userMapper;

  public Chat toChatDto(ChatEntity chatEntity) {
    return new Chat()
        .setId(chatEntity.getId())
        .setName(chatEntity.getName())
        .setPeerToPeer(emptyIfNull(chatEntity.getMembers()).size() == 2)
        .setMembers(emptyIfNull(chatEntity.getMembers())
            .stream()
            .map(userMapper::toContact)
            .collect(toList())
        );
  }
}

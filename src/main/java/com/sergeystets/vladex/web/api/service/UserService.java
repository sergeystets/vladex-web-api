package com.sergeystets.vladex.web.api.service;

import com.google.common.collect.ImmutableMap;
import com.sergeystets.vladex.web.api.entity.UserEntity;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.repository.UserRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final Map<Long, Long> lastChats = ImmutableMap.<Long, Long>builder()
      .put(42L, 0L)
      .put(0L, 0L)
      .put(1L, 1L)
      .put(2L, 2L)
      .build();

  public UserInfo findUserByPhone(String phoneNumber) {
    final UserEntity user = userRepository.findOneByPhoneNumber(phoneNumber);

    return new UserInfo()
        .setPhoneNumber(user.getPhoneNumber())
        .setUsername(user.getName())
        .setId(user.getId());
  }

  public UserInfo findUserById(long userId) {
    final UserEntity user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
    return new UserInfo()
        .setPhoneNumber(user.getPhoneNumber())
        .setUsername(user.getName())
        .setId(user.getId()).setChatIdToLoad(lastChats.getOrDefault(userId, 0L));
  }
}

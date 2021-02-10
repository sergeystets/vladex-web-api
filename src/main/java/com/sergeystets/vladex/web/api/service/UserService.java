package com.sergeystets.vladex.web.api.service;

import com.sergeystets.vladex.web.api.entity.UserEntity;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public UserInfo findUserByPhone(String phoneNumber) {
    final UserEntity user = userRepository.findOneByPhoneNumber(phoneNumber);

    return new UserInfo()
        .setPhoneNumber(user.getPhoneNumber())
        .setUsername(user.getName())
        .setId(user.getId());
  }

  @Transactional(readOnly = true)
  public UserInfo findUserById(long userId) {
    final UserEntity user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
    return new UserInfo()
        .setPhoneNumber(user.getPhoneNumber())
        .setUsername(user.getName())
        .setId(user.getId());
  }
}

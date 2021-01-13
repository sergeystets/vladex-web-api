package com.sergeystets.vladex.web.api.service;

import com.sergeystets.vladex.web.api.entity.UserEntity;
import com.sergeystets.vladex.web.api.model.UserInfo;
import com.sergeystets.vladex.web.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserInfo findUserByPhone(String phoneNumber) {
    final UserEntity user = userRepository.findOneByPhoneNumber(phoneNumber);

    return new UserInfo()
        .setPhoneNumber(user.getPhoneNumber())
        .setUsername(user.getName())
        .setId(user.getId());
  }
}

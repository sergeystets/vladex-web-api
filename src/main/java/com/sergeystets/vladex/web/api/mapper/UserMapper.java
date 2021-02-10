package com.sergeystets.vladex.web.api.mapper;

import com.sergeystets.vladex.web.api.entity.UserEntity;
import com.sergeystets.vladex.web.api.model.Contact;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

  public Contact toContact(UserEntity userEntity) {
    return new Contact()
        .setUsername(userEntity.getName())
        .setPhoneNumber(userEntity.getPhoneNumber())
        .setId(userEntity.getId())
        .setAvatar(userEntity.getAvatar())
        .setOnline(userEntity.isOnline());
  }
}

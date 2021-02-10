package com.sergeystets.vladex.web.api.service;

import static java.util.stream.Collectors.toList;

import com.sergeystets.vladex.web.api.mapper.UserMapper;
import com.sergeystets.vladex.web.api.model.Contact;
import com.sergeystets.vladex.web.api.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactsService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<Contact> getContacts(long userId) {
    return userRepository.findAllContacts(userId).map(userMapper::toContact).collect(toList());
  }
}

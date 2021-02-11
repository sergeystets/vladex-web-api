package com.sergeystets.vladex.web.api.repository;

import com.sergeystets.vladex.web.api.entity.ChatMessageEntity;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessageEntity, Long> {

  Stream<ChatMessageEntity> findAllByChatId(long chatId);
}

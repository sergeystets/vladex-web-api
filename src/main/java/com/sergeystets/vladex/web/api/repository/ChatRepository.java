package com.sergeystets.vladex.web.api.repository;

import com.sergeystets.vladex.web.api.entity.ChatEntity;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<ChatEntity, Long> {

  @Query(nativeQuery = true, value =
      "select distinct c.chat_id, chat_name, user_id from chat c "
          + "join user_chats uc on c.chat_id = uc.chat_id "
          + "join chat_members cm on cm.chat_id = uc.chat_id "
          + "where uc.user_id = :user_id")
  Stream<ChatEntity> findAllByUserId(@Param("user_id") long userId);
}

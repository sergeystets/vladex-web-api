package com.sergeystets.vladex.web.api.repository;

import com.sergeystets.vladex.web.api.entity.UserEntity;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findOneByPhoneNumber(String phoneNumber);

  @Query(nativeQuery = true, value = ""
      + "select * from user u "
      + "join user_contacts us "
      + "on u.id = us.contact_id "
      + "where us.user_id = :user_id")
  Stream<UserEntity> findAllContacts(@Param("user_id") long userId);
}

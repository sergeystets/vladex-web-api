package com.sergeystets.vladex.web.api.repository;

import com.sergeystets.vladex.web.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findOneByPhoneNumber(String phoneNumber);
}

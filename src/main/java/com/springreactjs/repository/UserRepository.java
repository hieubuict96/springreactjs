package com.springreactjs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springreactjs.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOneById(Long id);

  Optional<User> findOneByPhoneNumber(String phoneNumber);

  Optional<User> findOneByEmail(String email);

  Optional<User> findOneByUserIdFacebook(String userIdFacebook);

  List<User> findOneByFirstName(String firstName);
}

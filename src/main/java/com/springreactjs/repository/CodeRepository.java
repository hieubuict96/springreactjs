package com.springreactjs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springreactjs.models.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
  Optional<Code> findOneByPhoneNumber(String phoneNumber);
  Optional<Code> findOneByEmail(String email);
}

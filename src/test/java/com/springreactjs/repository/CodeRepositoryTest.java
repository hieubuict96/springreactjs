package com.springreactjs.repository;

import com.springreactjs.models.Code;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CodeRepositoryTest {
  @Autowired
  private  CodeRepository codeRepository;

  @Test
  public void testCode() {
    Optional<Code> code = codeRepository.findOneByPhoneNumber("+84383207498");
    System.out.println(!code.isPresent());
  }
}


package com.springreactjs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springreactjs.payload.request.auth.SendPhoneNumberDto;
import com.springreactjs.repository.CodeRepository;
import com.springreactjs.security.config.WebSecurityConfig;
import com.springreactjs.security.jwt.AuthEntryPoint;
import com.springreactjs.security.services.UserDetailsServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.springreactjs.repository.UserRepository;
import com.springreactjs.security.jwt.TokenProvider;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AuthController.class)
@Import(WebSecurityConfig.class)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthenticationManager authenticationManager;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private CodeRepository codeRepository;

  @MockBean
  private TokenProvider tokenProvider;

  @MockBean
  private AuthEntryPoint authEntryPoint;

  @MockBean
  private UserDetailsServiceImplement userDetailsServiceImplement;

  private JacksonTester<SendPhoneNumberDto> jsonSendPhoneNumber;

  @BeforeEach
  public void setup() {
    // We would need this line if we would not use the MockitoExtension
    // MockitoAnnotations.initMocks(this);
    // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
    JacksonTester.initFields(this, new ObjectMapper());
    // MockMvc standalone approach
//    mvc = MockMvcBuilders.standaloneSetup(superHeroController)
//            .setControllerAdvice(new SuperHeroExceptionHandler())
//            .addFilters(new SuperHeroFilter())
//            .build();
  }

  @Test
  public void sendPhoneNumber_basic() throws Exception {
//    RequestBuilder requestBuilder = post("/api/auth/signup/send-phone-number").accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(post("/api/auth/signup/send-phone-number").contentType(MediaType.APPLICATION_JSON).content(jsonSendPhoneNumber.write(new SendPhoneNumberDto("+84383207498")).getJson())).andExpect(status().isOk()).andExpect(content().json("{\"success\": \"success\"}")).andReturn();
  }
}

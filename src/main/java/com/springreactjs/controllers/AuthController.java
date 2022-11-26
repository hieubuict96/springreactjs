package com.springreactjs.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springreactjs.exception.BadRequestException;
import com.springreactjs.models.Code;
import com.springreactjs.models.User;
import com.springreactjs.payload.request.auth.SendCodeDto;
import com.springreactjs.payload.request.auth.SendPhoneNumberDto;
import com.springreactjs.payload.response.SuccessResponse;
import com.springreactjs.repository.CodeRepository;
import com.springreactjs.repository.UserRepository;
import com.springreactjs.security.jwt.TokenProvider;
import com.springreactjs.exception.UnauthorizedException;
import com.springreactjs.models.ERole;
import com.springreactjs.models.Role;
import com.springreactjs.payload.request.auth.SigninDto;
import com.springreactjs.payload.request.auth.SignupDto;
import com.springreactjs.payload.response.auth.SigninResponse;
import com.springreactjs.repository.RoleRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CodeRepository codeRepository;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleRepository roleRepository;

  @PostMapping("/signup/send-phone-number")
  public ResponseEntity<SuccessResponse> sendPhoneNumber(@RequestBody @Valid SendPhoneNumberDto phoneNumberDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new BadRequestException("phoneNumber");
    }

    String phoneNumber = phoneNumberDto.getPhoneNumber();

    Optional<User> user = userRepository.findOneByPhoneNumber(phoneNumber);
    if (user.isPresent()) {
      throw new BadRequestException("phoneNumberAlreadyUse");
    }

    Random random = new Random();
    int intCode = random.nextInt(999999);
    String code = String.format("%06d", intCode);
    Long timeSendCode = System.currentTimeMillis();

    Optional<Code> codeExist = codeRepository.findOneByPhoneNumber(phoneNumber);

    if (!codeExist.isPresent()) {
      Code newCode = new Code(phoneNumber, code, timeSendCode);
      codeRepository.save(newCode);
    } else {
      codeExist.get().setCode(code);
      codeExist.get().setTimeSendCode(timeSendCode);
      codeRepository.save(codeExist.get());
    }

    return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
  }

  @PostMapping("/signup/send-code")
  public ResponseEntity<SuccessResponse> sendCode(@RequestBody @Valid SendCodeDto sendCodeDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new BadRequestException(bindingResult.getFieldError().getField());
    }

    String phoneNumber = sendCodeDto.getPhoneNumber();
    String code = sendCodeDto.getCode();

    Optional<Code> codeExist = codeRepository.findOneByPhoneNumber(phoneNumber);

    if (!codeExist.isPresent()) {
      throw new BadRequestException("phoneNumber");
    }

    Long timeVerifyCode = System.currentTimeMillis();

    if (timeVerifyCode - codeExist.get().getTimeSendCode() < 300000) {
      if (code.equals(codeExist.get().getCode())) {
        return new ResponseEntity<>(new SuccessResponse("verifySuccess"), HttpStatus.OK);
      }

      throw new BadRequestException("codeIncorrect");
    }

    throw new BadRequestException("timeoutVerifyCode");
  }

  @PostMapping("/signup/resend-code")
  public ResponseEntity<SuccessResponse> resendCode(@RequestBody @Valid SendPhoneNumberDto sendPhoneNumberDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new BadRequestException("phoneNumber");
    }

    String phoneNumber = sendPhoneNumberDto.getPhoneNumber();

    Optional<User> user = userRepository.findOneByPhoneNumber(phoneNumber);
    if (user.isPresent()) {
      throw new BadRequestException("phoneNumberAlreadyUse");
    }

    Random random = new Random();
    int intCode = random.nextInt(999999);
    String code = String.format("%06d", intCode);
    Long timeSendCode = System.currentTimeMillis();

    Optional<Code> codeExist = codeRepository.findOneByPhoneNumber(phoneNumber);

    if (!codeExist.isPresent()) {
      Code newCode = new Code(phoneNumber, code, timeSendCode);
      codeRepository.save(newCode);
    } else {
      codeExist.get().setCode(code);
      codeExist.get().setTimeSendCode(timeSendCode);
      codeRepository.save(codeExist.get());
    }

    return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
  }

  @PostMapping("/signup")
  public ResponseEntity<SigninResponse> signup(@RequestBody @Valid SignupDto signupDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new BadRequestException(bindingResult.getFieldError().getField());
    }

    String firstName = signupDto.getFirstName();
    String lastName = signupDto.getLastName();
    String password = signupDto.getPassword();
    String phoneNumber = signupDto.getPhoneNumber();

    String hashPassword = passwordEncoder.encode(password);

    Role role = roleRepository.findOneByName(ERole.USER.toString()).get();

    User user = new User(firstName, lastName, hashPassword, phoneNumber, role);
    User newUser = userRepository.save(user);

    String token = tokenProvider.generateJwtToken(newUser.getPhoneNumber());

    return new ResponseEntity<>(new SigninResponse(newUser, token), HttpStatus.CREATED);
  }

  @PostMapping("/signin")
  public void signin(@RequestBody @Valid SigninDto signinDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new BadRequestException(bindingResult.getFieldError().getField());
    }

    String user = signinDto.getUser();
    String password = signinDto.getPassword();

    Authentication authentication;

    try {
      authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
    } catch (BadCredentialsException e) {
      throw new BadRequestException("signinFail");
    }

    UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

    String token = tokenProvider.generateJwtToken(user);
  }

  @GetMapping("/test")
  public void test() {
    List<User> a = userRepository.findOneByFirstName("hie");
    System.out.println(a);
  }
}

package com.springreactjs.security.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springreactjs.models.User;
import com.springreactjs.repository.UserRepository;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Matcher matcher = Pattern.compile("^\\+[0-9]{5,20}$").matcher(username);
    User user;

    if (matcher.find()) {
      user = userRepository.findOneByPhoneNumber(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number or email: " + username));

      return new UserDetailsImplement(user, false);
    } else {
      user = userRepository.findOneByEmail(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number or email: " + username));

      return new UserDetailsImplement(user, true);
    }
  }
}

package com.springreactjs.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {
  private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

  @Value("${springreactjs.jwtSecret}")
  private String jwtSecret;

  @Value("${springreactjs.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(String username) {
    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public String getUsernameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid Jwt signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid Jwt token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("Jwt token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("Jwt token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("Jwt claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}

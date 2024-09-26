package com.partimestudy.assignment.domain.token;

public interface TokenProvider {
    String createAccessToken(String userToken);

    void validateToken(String token);
}

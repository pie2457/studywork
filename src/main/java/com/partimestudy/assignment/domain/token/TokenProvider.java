package com.partimestudy.assignment.domain.token;

import java.util.Map;

public interface TokenProvider {
    String createAccessToken(String userToken);

    void validateToken(String token);

    Map<String, Object> extractClaims(String token);
}

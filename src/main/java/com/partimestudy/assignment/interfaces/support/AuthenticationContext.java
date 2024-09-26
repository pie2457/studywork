package com.partimestudy.assignment.interfaces.support;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthenticationContext {
    private String userToken;

    public Optional<String> getPrincipal() {
        return Optional.ofNullable(userToken);
    }

    public void setUserToken(Map<String, Object> claims) {
        this.userToken = claims.get("userToken").toString();
    }
}

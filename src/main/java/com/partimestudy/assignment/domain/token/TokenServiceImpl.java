package com.partimestudy.assignment.domain.token;

import org.springframework.stereotype.Service;

import com.partimestudy.assignment.domain.user.UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenProvider provider;

    @Override
    public TokenInfo createToken(UserInfo info) {
        String accessToken = provider.createAccessToken(info.getUserToken());
        return new TokenInfo(accessToken);
    }
}

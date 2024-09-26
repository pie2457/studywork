package com.partimestudy.assignment.domain.token;

import com.partimestudy.assignment.domain.user.UserInfo;

public interface TokenService {
    TokenInfo createToken(UserInfo info);
}

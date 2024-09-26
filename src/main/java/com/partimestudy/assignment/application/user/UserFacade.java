package com.partimestudy.assignment.application.user;

import org.springframework.stereotype.Service;

import com.partimestudy.assignment.domain.token.TokenInfo;
import com.partimestudy.assignment.domain.token.TokenService;
import com.partimestudy.assignment.domain.user.UserCommand;
import com.partimestudy.assignment.domain.user.UserInfo;
import com.partimestudy.assignment.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final TokenService tokenService;

    public UserInfo signup(UserCommand.Signup command) {
        return userService.signup(command);
    }

    public TokenInfo login(UserCommand.Login command) {
        UserInfo info = userService.login(command);
        return tokenService.createToken(info);
    }
}

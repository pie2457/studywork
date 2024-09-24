package com.partimestudy.assignment.application.user;

import org.springframework.stereotype.Service;

import com.partimestudy.assignment.domain.user.UserCommand;
import com.partimestudy.assignment.domain.user.UserInfo;
import com.partimestudy.assignment.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public UserInfo signup(UserCommand command) {
        return userService.signup(command);
    }
}

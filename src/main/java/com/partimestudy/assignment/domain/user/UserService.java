package com.partimestudy.assignment.domain.user;

public interface UserService {

    UserInfo signup(UserCommand.Signup command);
}

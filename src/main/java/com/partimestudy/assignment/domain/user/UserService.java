package com.partimestudy.assignment.domain.user;

public interface UserService {
    UserInfo signup(UserCommand.Signup command);

    UserInfo login(UserCommand.Login command);

    UserInfo.Details details(String userToken);
}

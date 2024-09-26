package com.partimestudy.assignment.domain.user;

public interface UserReader {
    void checkDuplicationLoginId(String loginId);

    User findByLoginId(String loginId);

    User findByUserToken(String userToken);
}

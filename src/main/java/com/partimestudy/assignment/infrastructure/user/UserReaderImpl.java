package com.partimestudy.assignment.infrastructure.user;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.exception.NotFoundException;
import com.partimestudy.assignment.domain.user.User;
import com.partimestudy.assignment.domain.user.UserReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

    @Override
    public void checkDuplicationLoginId(String loginId) {
        if (userRepository.existsByLoginId(loginId)) {
            throw new BadRequestException(ErrorCode.ALREADY_EXIST_ID);
        }
    }

    @Override
    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public User findByUserToken(String userToken) {
        return userRepository.findByUserToken(userToken)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER));
    }
}

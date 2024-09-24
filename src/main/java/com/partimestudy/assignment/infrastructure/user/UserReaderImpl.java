package com.partimestudy.assignment.infrastructure.user;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;
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
}

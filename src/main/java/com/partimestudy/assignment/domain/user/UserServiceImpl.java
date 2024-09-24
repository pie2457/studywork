package com.partimestudy.assignment.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partimestudy.assignment.application.encrypt.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserReader userReader;
    private final UserStore userStore;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserInfo signup(UserCommand command) {
        userReader.checkDuplicationLoginId(command.loginId());

        EncryptedPassword encrypted = passwordEncoder.encode(command.password());
        User user = userStore.store(command.toEntity(encrypted));
        return new UserInfo(user);
    }
}

package com.partimestudy.assignment.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partimestudy.assignment.domain.encrypt.PasswordEncoder;
import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;

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
    public UserInfo signup(UserCommand.Signup command) {
        userReader.checkDuplicationLoginId(command.loginId());

        EncryptedPassword encrypted = passwordEncoder.encode(command.password());
        User user = userStore.store(command.toEntity(encrypted));
        return new UserInfo(user);
    }

    @Override
    public UserInfo login(UserCommand.Login command) {
        User user = userReader.findByLoginId(command.loginId());
        validationUserInfo(user, command);
        return new UserInfo(user);
    }

    private void validationUserInfo(User user, UserCommand.Login command) {
        String encrypted = passwordEncoder.encodeWithSalt(command.password(), user.getSalt());
        if (!user.validatePassword(encrypted)) {
            throw new BadRequestException(ErrorCode.MISMATCHED_PASSWORD);
        }
    }
}

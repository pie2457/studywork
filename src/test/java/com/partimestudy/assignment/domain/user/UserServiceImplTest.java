package com.partimestudy.assignment.domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.partimestudy.assignment.domain.encrypt.PasswordEncoder;
import com.partimestudy.assignment.domain.exception.BadRequestException;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserReader userReader;
    @Mock
    private UserStore userStore;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @DisplayName("회원가입에 성공한다.")
    @Test
    void whenSignup_thenSuccess() {
        // given
        UserCommand.Signup command = new UserCommand.Signup("id", "password", "name", "공무원");
        EncryptedPassword password = new EncryptedPassword("password", "1234");

        willDoNothing().given(userReader).checkDuplicationLoginId(command.loginId());
        given(passwordEncoder.encode(command.password())).willReturn(password);
        given(userStore.store(any())).willReturn(command.toEntity(password));

        // when
        UserInfo result = userService.signup(command);

        // then
        assertThat(result.getUserToken()).startsWith("user_");
        then(userReader).should(times(1)).checkDuplicationLoginId(command.loginId());
        then(passwordEncoder).should(times(1)).encode(command.password());
        then(userStore).should(times(1)).store(any());
    }

    @DisplayName("중복된 아이디로 인해 회원가입에 실패한다.")
    @Test
    void givenDuplicatedId_whenSignup_thenThrowsException() {
        // given
        UserCommand.Signup command = new UserCommand.Signup("id", "password", "name", "공무원");

        willThrow(BadRequestException.class).given(userReader).checkDuplicationLoginId(command.loginId());

        // when & then
        assertThatThrownBy(() -> userService.signup(command))
            .isInstanceOf(BadRequestException.class);
    }

    @DisplayName("로그인에 성공한다.")
    @Test
    void whenLogin_thenSuccess() {
        // given
        UserCommand.Login command = new UserCommand.Login("testId", "testPassword");
        String encrypted = "encryptedPassword";

        User userMock = mock(User.class);
        given(userMock.getSalt()).willReturn("salt");
        given(passwordEncoder.encodeWithSalt(command.password(), userMock.getSalt())).willReturn(encrypted);
        given(userMock.validatePassword(encrypted)).willReturn(true);
        given(userReader.findByLoginId(command.loginId())).willReturn(userMock);

        // when
        userService.login(command);

        // then
        then(userReader).should(times(1)).findByLoginId(command.loginId());
        then(userMock).should(times(1)).validatePassword(encrypted);
    }

    @DisplayName("비밀번호가 일치하지않으면 로그인에 실패한다.")
    @Test
    void givenMismatchedPassword_whenLogin_thenThrowsException() {
        // given
        UserCommand.Login command = new UserCommand.Login("testId", "testPassword");
        String notMatchedPassword = "password";

        User userMock = mock(User.class);
        given(userMock.getSalt()).willReturn("salt");
        given(userReader.findByLoginId(command.loginId())).willReturn(userMock);
        given(passwordEncoder.encodeWithSalt(command.password(), userMock.getSalt())).willReturn(notMatchedPassword);
        given(userMock.validatePassword(notMatchedPassword)).willReturn(false);

        // when & then
        assertThatThrownBy(() -> userService.login(command))
            .isInstanceOf(BadRequestException.class);
    }
}

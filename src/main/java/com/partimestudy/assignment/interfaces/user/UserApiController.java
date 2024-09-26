package com.partimestudy.assignment.interfaces.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partimestudy.assignment.application.user.UserFacade;
import com.partimestudy.assignment.domain.token.TokenInfo;
import com.partimestudy.assignment.domain.user.UserCommand;
import com.partimestudy.assignment.domain.user.UserInfo;
import com.partimestudy.assignment.domain.user.docs.UserApiControllerDocs;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController implements UserApiControllerDocs {
    private final UserDtoMapper mapper;
    private final UserFacade userFacade;

    @PostMapping("/signup")
    public ResponseEntity<UserDto.SignupResponse> signup(@RequestBody @Valid UserDto.SignupRequest request) {
        UserCommand.Signup command = mapper.of(request);
        UserInfo info = userFacade.signup(command);
        UserDto.SignupResponse response = mapper.of(info);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto.LoginResponse> login(@RequestBody @Valid UserDto.LoginRequest request) {
        UserCommand.Login command = mapper.of(request);
        TokenInfo info = userFacade.login(command);
        UserDto.LoginResponse response = mapper.of(info);
        return ResponseEntity.ok().body(response);
    }
}

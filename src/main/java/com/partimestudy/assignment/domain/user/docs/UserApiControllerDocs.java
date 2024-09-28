package com.partimestudy.assignment.domain.user.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.partimestudy.assignment.interfaces.support.Auth;
import com.partimestudy.assignment.interfaces.user.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "사용자 API", description = "회원가입, 로그인, 내 정보 조회를 위한 API")
public interface UserApiControllerDocs {
    @Operation(summary = "회원가입", description = "사용자의 회원가입을 위한 API 입니다.")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = UserDto.SignupResponse.class)))
    ResponseEntity<UserDto.SignupResponse> signup(@RequestBody @Valid UserDto.SignupRequest request);

    @Operation(summary = "로그인", description = "사용자의 로그인을 위한 API 입니다. 로그인을 완료하면 accessToken을 부여받습니다.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserDto.LoginResponse.class)))
    ResponseEntity<UserDto.LoginResponse> login(@RequestBody @Valid UserDto.LoginRequest request);

    @Operation(summary = "내 정보 조회", description = "로그인된 사용자의 정보를 조회하는 API 입니다. 내 정보를 조회 시 로그인 아이디, 사용자명, 공부 목표가 보여집니다.")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserDto.UserDetailsResponse.class)))
    ResponseEntity<UserDto.UserDetailsResponse> userDetails(@Auth String userToken);
}

package com.partimestudy.assignment.domain.user.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.partimestudy.assignment.interfaces.user.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "사용자 API", description = "회원가입, 로그인, 내 정보 조회를 위한 API")
public interface UserApiControllerDocs {

    @Operation(summary = "회원가입", description = "request 시 받아야 할 data: 로그인 아이디, 비밀번호, 사용자명, 공부목표")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = UserDto.SignupResponse.class)))
    ResponseEntity<UserDto.SignupResponse> signup(@RequestBody @Valid UserDto.SignupRequest request);
}

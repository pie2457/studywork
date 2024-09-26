package com.partimestudy.assignment.interfaces.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @Schema(title = "회원가입 요청 DTO")
    public record SignupRequest(
        @Schema(defaultValue = "로그인 아이디", example = "bruuuni")
        @NotBlank(message = "로그인 아이디를 입력해주세요.")
        @Size(min = 4, max = 10, message = "최소 4자 이상, 10자 이하로 입력해주세요.")
        String loginId,

        @Schema(defaultValue = "비밀번호", example = "a1s2d3f4!")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 15, message = "최소 8자 이상, 15자 이하로 입력해주세요.")
        @Pattern(regexp = "[a-zA-Z0-9\\W_]{8,15}", message = "영문, 숫자, 특수문자 조합으로 이루어진 8~15자의 비밀번호를 입력해주세요.")
        String password,

        @Schema(defaultValue = "사용자명", example = "두번째호빵")
        @NotBlank(message = "사용하실 닉네임을 입력해주세요.")
        String name,

        @Schema(defaultValue = "공부 목표", example = "공무원")
        @NotBlank(message = "공부목표를 설정해주세요.")
        String purpose
    ) {

    }

    @Schema(title = "회원가입 응답 DTO")
    public record SignupResponse(
        @Schema(defaultValue = "사용자의 정보가 담긴 ID 대신 사용할 랜덤토큰", example = "user_mc1W2wPqM9ZAFLq")
        String userToken
    ) {

    }

    @Schema(title = "로그인 요청 DTO")
    public record LoginRequest(
        @Schema(defaultValue = "로그인 아이디", example = "bruuuni")
        @NotBlank(message = "로그인 아이디를 입력해주세요.")
        @Size(min = 4, max = 10, message = "최소 4자 이상, 10자 이하로 입력해주세요.")
        String loginId,

        @Schema(defaultValue = "비밀번호", example = "a1s2d3f4!")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 15, message = "최소 8자 이상, 15자 이하로 입력해주세요.")
        @Pattern(regexp = "[a-zA-Z0-9\\W_]{8,15}", message = "영문, 숫자, 특수문자 조합으로 이루어진 8~15자의 비밀번호를 입력해주세요.")
        String password
    ) {

    }

    @Schema(title = "로그인 응답 DTO")
    public record LoginResponse(
        @Schema(defaultValue = "accessToken", example = "afASFsaFasf.asdWeffrtgt.dsfEOassfEW")
        String accessToken
    ) {

    }
}

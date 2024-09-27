package com.partimestudy.assignment.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // JWT
    INVALID_AUTH_HEADER("Authorization 헤더의 정보가 유효하지 않습니다."),
    EXPIRED_TOKEN("만료된 토큰입니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),

    // Auth
    NOT_LOGIN("로그인 상태가 아닙니다."),

    // USER
    ALREADY_EXIST_ID("이미 존재하는 아이디입니다."),
    NOT_FOUND_USER("회원을 찾을 수 없습니다."),

    // PASSWORD
    PASSWORD_ENCRYPTION_FAIL("비밀번호 암호화에 실패했습니다."),
    MISMATCHED_PASSWORD("비밀번호가 일치하지 않습니다."),

    // CHALLENGE
    NOT_FOUND_CHALLENGE("해당 챌린지를 찾을 수 없습니다."),
    INACTIVE_CHALLENGE("종료된 챌린지 입니다."),
    INVALID_STARTED_AT("이미 지난 날짜는 선택할 수 없습니다."),
    ALREADY_EXIST_CHALLENGE("이미 신청한 챌린지 입니다."),
    INCORRECT_NAME("챌린지명이 일치하지 않습니다."),

    // DEPOSIT
    INVALID_DEPOSIT_RANGE("최소 보증금과 최대 보증금을 확인해주세요."),

    // PURPOSE
    INVALID_PURPOSE("올바른 타입이 아닙니다.");

    private final String message;
}

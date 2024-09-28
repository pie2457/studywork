package com.partimestudy.assignment.interfaces.order;

import java.time.LocalDate;

import com.partimestudy.assignment.domain.order.payment.PayMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderDto {

    @Schema(title = "챌린지 주문(신청) 요청 DTO")
    public record RegisterRequest(
        @Positive
        @Schema(defaultValue = "챌린지 아이디", example = "1")
        Integer challengeId,
        @NotBlank
        @Schema(defaultValue = "챌린지명", example = "월요일 1시간 공부하기")
        String challengeName,
        @Min(value = 10000, message = "최소 보증금은 10,000입니다.")
        @Schema(defaultValue = "보증금", example = "10000")
        int deposit,
        @NotNull(message = "시작 일자를 입력해주세요.")
        @Schema(defaultValue = "시작 일자", example = "2024-09-30")
        LocalDate startedAt,
        @Positive(message = "공부 시간을 입력해주세요.")
        @Schema(defaultValue = "공부 시간", example = "1")
        int studyTime,
        @NotNull
        @Schema(defaultValue = "결제 방법(종류)", example = "NAVER_PAY")
        PayMethod payMethod
    ) {

    }

    @Schema(title = "챌린지 주문(신청) 응답 DTO")
    public record RegisterResponse(
        @Schema(defaultValue = "주문 아이디", example = "1")
        Integer orderId
    ) {

    }
}

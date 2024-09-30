package com.partimestudy.assignment.interfaces.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.partimestudy.assignment.domain.order.OrderInfo;
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

    @Schema(title = "챌린지 주문(신청) 내역 조회 응답 DTO")
    public record RetrieveResponse(
        @Schema(defaultValue = "챌린지 이름", example = "월요일 1시간 공부하기")
        String challengeName,
        @Schema(defaultValue = "시작 일자", example = "2024-09-30")
        LocalDate startedAt,
        @Schema(defaultValue = "공부 시간", example = "1")
        Integer studyTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        @Schema(defaultValue = "생성 일자", example = "2024-09-28 17:43")
        LocalDateTime createdAt,
        @Schema(defaultValue = "보증금", example = "10000")
        int deposit,
        @Schema(defaultValue = "결제 금액", example = "10000")
        int amount
    ) {

    }

    @Schema(title = "챌린지 주문(신청) 전체 내역 조회 응답 DTO")
    public record RetrieveAllResponse(
        @Schema(defaultValue = "챌린지 이름", example = "월요일 1시간 공부하기")
        String challengeName,
        @Schema(defaultValue = "시작 일자", example = "2024-09-30")
        LocalDate startedAt,
        @Schema(defaultValue = "공부 시간", example = "1")
        Integer studyTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        @Schema(defaultValue = "생성 일자", example = "2024-09-28 17:43")
        LocalDateTime createdAt,
        @Schema(defaultValue = "보증금", example = "10000")
        int deposit,
        @Schema(defaultValue = "결제 금액", example = "10000")
        int amount
    ) {

        public static List<OrderDto.RetrieveAllResponse> from(List<OrderInfo.RetrieveAll> info) {
            return info.stream()
                .map(retrieve -> new OrderDto.RetrieveAllResponse(
                    retrieve.challengeName(),
                    retrieve.startedAt(),
                    retrieve.studyTime(),
                    retrieve.createdAt(),
                    retrieve.deposit(),
                    retrieve.amount()
                )).collect(Collectors.toList());
        }
    }
}

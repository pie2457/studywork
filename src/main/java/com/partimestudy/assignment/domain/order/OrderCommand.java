package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;

import com.partimestudy.assignment.domain.order.payment.PayMethod;

public class OrderCommand {

    public record Register(
        Integer challengeId,
        String challengeName,
        int deposit,
        LocalDate startedAt,
        int studyTime,
        PayMethod payMethod
    ) {
        public Order toEntity(String userToken, OrderCommand.Register command) {
            return Order.builder()
                .userToken(userToken)
                .challengeId(command.challengeId())
                .challengeName(command.challengeName())
                .deposit(command.deposit())
                .startedAt(command.startedAt())
                .studyTime(command.studyTime())
                .build();
        }
    }

    public record Payment(
        Integer orderId,
        PayMethod payMethod,
        int deposit
    ) {

    }
}

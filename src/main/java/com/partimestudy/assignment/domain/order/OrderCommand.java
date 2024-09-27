package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;

public class OrderCommand {

    public record Register(
        Integer challengeId,
        String challengeName,
        int deposit,
        LocalDate startedAt,
        int studyTime
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
}

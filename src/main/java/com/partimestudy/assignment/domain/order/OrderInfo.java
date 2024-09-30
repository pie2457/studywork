package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderInfo {

    public record Register(
        Integer orderId
    ) {

    }

    public record Retrieve(
        String challengeName,
        LocalDate startedAt,
        Integer studyTime,
        LocalDateTime createdAt,
        int deposit,
        int amount
    ) {

        public static OrderInfo.Retrieve from(Order order) {
            return new OrderInfo.Retrieve(
                order.getChallengeName(),
                order.getStartedAt(),
                order.getStudyTime(),
                order.getCreatedAt(),
                order.getDeposit(),
                order.getDeposit()
            );
        }
    }

    public record RetrieveAll(
        String challengeName,
        LocalDate startedAt,
        Integer studyTime,
        LocalDateTime createdAt,
        int deposit,
        int amount
    ) {

    }
}

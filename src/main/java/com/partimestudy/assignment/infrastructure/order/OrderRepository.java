package com.partimestudy.assignment.infrastructure.order;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.partimestudy.assignment.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    boolean existsByUserTokenAndChallengeIdAndStartedAt(
        String userToken,
        Integer challengeId,
        LocalDate startedAt);
}

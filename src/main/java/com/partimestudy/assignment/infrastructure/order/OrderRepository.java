package com.partimestudy.assignment.infrastructure.order;

import static com.partimestudy.assignment.domain.order.QOrder.*;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.partimestudy.assignment.domain.order.Order;
import com.querydsl.core.types.dsl.BooleanExpression;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    boolean existsByUserTokenAndChallengeIdAndStartedAt(
        String userToken,
        Integer challengeId,
        LocalDate startedAt);

    default BooleanExpression equalUserToken(String userToken) {
        if (userToken == null) {
            return null;
        }
        return order.userToken.eq(userToken);
    }

    default BooleanExpression lessThenDeposit(int deposit) {
        if (deposit == 0) {
            return null;
        }
        return order.deposit.lt(deposit);
    }

    default BooleanExpression containsChallengeName(String challengeName) {
        if (challengeName == null) {
            return null;
        }
        return order.challengeName.contains(challengeName);
    }

}

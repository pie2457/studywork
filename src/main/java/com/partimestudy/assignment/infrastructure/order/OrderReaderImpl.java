package com.partimestudy.assignment.infrastructure.order;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.exception.NotFoundException;
import com.partimestudy.assignment.domain.order.Order;
import com.partimestudy.assignment.domain.order.OrderReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {
    private final OrderRepository orderRepository;

    @Override
    public boolean existsByUserTokenAndChallengeIdAndStartedAt(
        String userToken,
        Integer challengeId,
        LocalDate startedAt
    ) {
        return orderRepository.existsByUserTokenAndChallengeIdAndStartedAt(userToken, challengeId, startedAt);
    }

    @Override
    public Order findByOrderId(Integer orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ORDER));
    }
}

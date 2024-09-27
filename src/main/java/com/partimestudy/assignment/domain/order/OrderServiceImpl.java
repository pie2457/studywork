package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderReader orderReader;
    private final OrderStore orderStore;

    @Override
    @Transactional
    public OrderInfo.Register register(String userToken, OrderCommand.Register command) {
        if (command.startedAt().isBefore(LocalDate.now())) {
            throw new BadRequestException(ErrorCode.INVALID_STARTED_AT);
        }
        if (orderReader.existsByUserTokenAndChallengeIdAndStartedAt(
            userToken, command.challengeId(), command.startedAt())
        ) {
            throw new BadRequestException(ErrorCode.ALREADY_EXIST_CHALLENGE);
        }
        Order order = orderStore.register(command.toEntity(userToken, command));
        return new OrderInfo.Register(order.getId());
    }
}

package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.order.payment.PaymentProcessor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final PaymentProcessor paymentProcessor;

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
        paymentProcessor.pay(
            order,
            new OrderCommand.Payment(order.getId(), command.payMethod(), command.deposit())
        );
        return new OrderInfo.Register(order.getId());
    }

    @Override
    public OrderInfo.Retrieve retrieve(OrderCommand.Retrieve command) {
        Order order = orderReader.findByOrderId(command.orderId());
        order.validateUserToken(command.userToken());
        return OrderInfo.Retrieve.from(order);
    }

    @Override
    public List<OrderInfo.RetrieveAll> retrieveAll(OrderCommand.RetrieveAll command) {
        return orderReader.findAllOrdersBySearchParams(command);
    }
}

package com.partimestudy.assignment.infrastructure.order.payment;

import java.util.List;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.order.Order;
import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.payment.PaymentProcessor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {
    private final List<PaymentApiCaller> paymentApiCallers;

    @Override
    public void pay(Order order, OrderCommand.Payment command) {
        PaymentApiCaller payApiCaller = routingApiCaller(command);
        payApiCaller.pay(command);
    }

    private PaymentApiCaller routingApiCaller(OrderCommand.Payment command) {
        return paymentApiCallers.stream()
            .filter(paymentApiCaller -> paymentApiCaller.support(command.payMethod()))
            .findFirst()
            .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_PAY_METHOD));
    }
}

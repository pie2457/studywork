package com.partimestudy.assignment.infrastructure.order.payment;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.payment.PayMethod;

@Component
public class PgCardApiCaller implements PaymentApiCaller {

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.CARD == payMethod;
    }

    @Override
    public void pay(OrderCommand.Payment command) {
        // 결제 로직
    }
}

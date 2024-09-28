package com.partimestudy.assignment.infrastructure.order.payment;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.payment.PayMethod;

@Component
public class NaverPayApiCaller implements PaymentApiCaller {

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.NAVER_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.Payment command) {
        // 결제 로직
    }
}

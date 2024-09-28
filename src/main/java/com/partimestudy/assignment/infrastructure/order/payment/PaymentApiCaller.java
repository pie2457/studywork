package com.partimestudy.assignment.infrastructure.order.payment;

import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.payment.PayMethod;

public interface PaymentApiCaller {
    boolean support(PayMethod payMethod);

    void pay(OrderCommand.Payment command);
}

package com.partimestudy.assignment.domain.order.payment;

import com.partimestudy.assignment.domain.order.Order;
import com.partimestudy.assignment.domain.order.OrderCommand;

public interface PaymentProcessor {
    void pay(Order order, OrderCommand.Payment command);
}

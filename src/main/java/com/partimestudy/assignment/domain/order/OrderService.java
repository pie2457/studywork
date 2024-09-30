package com.partimestudy.assignment.domain.order;

public interface OrderService {
    OrderInfo.Register register(String userToken, OrderCommand.Register command);

    OrderInfo.Retrieve retrieve(OrderCommand.Retrieve command);
}

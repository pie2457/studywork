package com.partimestudy.assignment.domain.order;

import java.util.List;

public interface OrderService {
    OrderInfo.Register register(String userToken, OrderCommand.Register command);

    OrderInfo.Retrieve retrieve(OrderCommand.Retrieve command);

    List<OrderInfo.RetrieveAll> retrieveAll(OrderCommand.RetrieveAll command);
}

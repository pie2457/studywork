package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;
import java.util.List;

public interface OrderReader {
    boolean existsByUserTokenAndChallengeIdAndStartedAt(
        String userToken,
        Integer challengeId,
        LocalDate startedAt);

    Order findByOrderId(Integer orderId);

    List<OrderInfo.RetrieveAll> findAllOrdersBySearchParams(OrderCommand.RetrieveAll command);
}

package com.partimestudy.assignment.infrastructure.order;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.order.Order;
import com.partimestudy.assignment.domain.order.OrderStore;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {
    private final OrderRepository orderRepository;

    @Override
    public Order register(Order order) {
        return orderRepository.save(order);
    }
}

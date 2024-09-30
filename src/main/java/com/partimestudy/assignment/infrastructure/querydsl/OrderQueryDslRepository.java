package com.partimestudy.assignment.infrastructure.querydsl;

import static com.partimestudy.assignment.domain.order.QOrder.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.OrderInfo;
import com.partimestudy.assignment.infrastructure.order.OrderRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    private final OrderRepository orderRepository;

    public List<OrderInfo.RetrieveAll> findByUserTokenAndSearchParams(OrderCommand.RetrieveAll command) {
        return queryFactory.select(Projections.constructor(OrderInfo.RetrieveAll.class,
                order.challengeName,
                order.startedAt,
                order.studyTime,
                order.createdAt,
                order.deposit,
                order.deposit))
            .from(order)
            .where(
                orderRepository.equalUserToken(command.userToken()),
                orderRepository.equalChallengeName(command.challengeName()),
                orderRepository.lessThenDeposit(command.deposit())
            )
            .orderBy(order.createdAt.desc())
            .fetch();
    }
}

package com.partimestudy.assignment.application.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.partimestudy.assignment.domain.challenge.Challenge;
import com.partimestudy.assignment.domain.challenge.ChallengeService;
import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.OrderInfo;
import com.partimestudy.assignment.domain.order.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final ChallengeService challengeService;

    public OrderInfo.Register register(String userToken, OrderCommand.Register command) {
        Challenge challenge = challengeService.findByChallengeId(command.challengeId());
        challengeValidation(command, challenge);
        return orderService.register(userToken, command);
    }

    public OrderInfo.Retrieve retrieve(OrderCommand.Retrieve command) {
        return orderService.retrieve(command);
    }

    public List<OrderInfo.RetrieveAll> retrieveAll(OrderCommand.RetrieveAll command) {
        return orderService.retrieveAll(command);
    }

    private void challengeValidation(OrderCommand.Register command, Challenge challenge) {
        challenge.validateStatus();
        challenge.validateName(command.challengeName());
        challenge.validateDeposit(command.deposit());
    }
}

package com.partimestudy.assignment.domain.order;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.BaseException;
import com.partimestudy.assignment.domain.order.payment.PayMethod;
import com.partimestudy.assignment.domain.order.payment.PaymentProcessor;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderReader orderReader;
    @Mock
    private OrderStore orderStore;
    @Mock
    private PaymentProcessor paymentProcessor;
    @InjectMocks
    private OrderServiceImpl orderService;

    @DisplayName("챌린지 주문(신청)에 성공한다.")
    @Test
    void whenRegisterOrder_thenSuccess() {
        // given
        OrderCommand.Register command = new OrderCommand.Register(
            1, "원하는 일정으로 공부하기", 10000, LocalDate.now(), 1, PayMethod.CARD);
        String userToken = "userToken";

        given(orderReader.existsByUserTokenAndChallengeIdAndStartedAt(
            userToken, command.challengeId(), command.startedAt()
        )).willReturn(false);
        given(orderStore.register(any())).willReturn(command.toEntity(userToken, command));
        willDoNothing().given(paymentProcessor).pay(any(), any());

        // when
        orderService.register(userToken, command);

        // then
        then(orderReader).should(times(1))
            .existsByUserTokenAndChallengeIdAndStartedAt(userToken, command.challengeId(), command.startedAt());
        then(orderStore).should(times(1)).register(any());
        then(paymentProcessor).should(times(1)).pay(any(), any());
    }

    @DisplayName("이미 신청한 챌린지를 주문(신청) 시 주문(신청) 요청에 실패한다.")
    @Test
    void givenAlreadyExistsChallenge_whenRegisterOrder_thenThrowsException() {
        // given
        OrderCommand.Register command = new OrderCommand.Register(
            1, "원하는 일정으로 공부하기", 10000, LocalDate.now(), 1, PayMethod.NAVER_PAY);
        String userToken = "userToken";

        willThrow(BadRequestException.class)
            .given(orderReader)
            .existsByUserTokenAndChallengeIdAndStartedAt(userToken, command.challengeId(), command.startedAt());

        // when & then
        assertThatThrownBy(() -> orderService.register(userToken, command)).isInstanceOf(BaseException.class);
    }
}

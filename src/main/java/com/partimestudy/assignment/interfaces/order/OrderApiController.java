package com.partimestudy.assignment.interfaces.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partimestudy.assignment.application.order.OrderFacade;
import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.OrderInfo;
import com.partimestudy.assignment.domain.order.docs.OrderApiControllerDocs;
import com.partimestudy.assignment.interfaces.support.Auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderApiController implements OrderApiControllerDocs {
    private final OrderFacade orderFacade;
    private final OrderDtoMapper mapper;

    @PostMapping
    public ResponseEntity<OrderDto.RegisterResponse> register(
        @Auth String userToken,
        @RequestBody @Valid OrderDto.RegisterRequest request
    ) {
        OrderCommand.Register command = mapper.of(request);
        OrderInfo.Register info = orderFacade.register(userToken, command);
        OrderDto.RegisterResponse response = mapper.of(info);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

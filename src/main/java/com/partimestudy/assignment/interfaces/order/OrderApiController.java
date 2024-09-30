package com.partimestudy.assignment.interfaces.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto.RetrieveResponse> retrieve(
        @Auth String userToken,
        @PathVariable Integer orderId
    ) {
        OrderCommand.Retrieve command = mapper.of(orderId, userToken);
        OrderInfo.Retrieve info = orderFacade.retrieve(command);
        OrderDto.RetrieveResponse response = mapper.of(info);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto.RetrieveAllResponse>> retrieveAll(
        @Auth String userToken,
        @RequestParam(required = false, defaultValue = "0") int deposit,
        @RequestParam(required = false) String challengeName
    ) {
        OrderCommand.RetrieveAll command = mapper.of(userToken, deposit, challengeName);
        List<OrderInfo.RetrieveAll> info = orderFacade.retrieveAll(command);
        List<OrderDto.RetrieveAllResponse> response = OrderDto.RetrieveAllResponse.from(info);
        return ResponseEntity.ok().body(response);
    }
}

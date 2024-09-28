package com.partimestudy.assignment.domain.order.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.partimestudy.assignment.interfaces.order.OrderDto;
import com.partimestudy.assignment.interfaces.support.Auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "챌린지 주문(신청) API", description = "챌린지 주문(신청), 챌린지 주문(신청) 정보 조회를 위한 API")
public interface OrderApiControllerDocs {
    @Operation(summary = "챌린지 주문(신청) 요청", description = "챌린지를 주문(신청)하기 위한 API입니다.")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = OrderDto.RegisterResponse.class)))
    ResponseEntity<OrderDto.RegisterResponse> register(
        @Auth String userToken,
        @RequestBody @Valid OrderDto.RegisterRequest request
    );
}

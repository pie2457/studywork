package com.partimestudy.assignment.domain.order.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Operation(summary = "챌린지 주문(신청) 내역 조회", description = "챌린지의 주문(신청)을 조회하기 위한 API")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OrderDto.RetrieveResponse.class)))
    ResponseEntity<OrderDto.RetrieveResponse> retrieve(
        @Auth String userToken,
        @PathVariable Integer orderId
    );

    @Operation(summary = "챌린지 주문(신청) 내역 전체 조회", description = "챌린지의 주문(신청) 내역을 전체 조회하기 위한 API")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OrderDto.RetrieveAllResponse.class)))
    ResponseEntity<List<OrderDto.RetrieveAllResponse>> retrieveAll(
        @Auth String userToken,
        @RequestParam(required = false, defaultValue = "0") int deposit,
        @RequestParam(required = false) String challengeName
    );
}

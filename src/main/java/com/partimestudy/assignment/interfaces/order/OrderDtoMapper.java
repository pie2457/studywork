package com.partimestudy.assignment.interfaces.order;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.partimestudy.assignment.domain.order.OrderCommand;
import com.partimestudy.assignment.domain.order.OrderInfo;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {
    OrderCommand.Register of(OrderDto.RegisterRequest request);

    OrderDto.RegisterResponse of(OrderInfo.Register info);

    OrderCommand.Retrieve of(Integer orderId, String userToken);

    OrderDto.RetrieveResponse of(OrderInfo.Retrieve info);

    OrderCommand.RetrieveAll of(String userToken, int deposit, String challengeName);
}

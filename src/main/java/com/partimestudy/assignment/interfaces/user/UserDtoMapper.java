package com.partimestudy.assignment.interfaces.user;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.partimestudy.assignment.domain.token.TokenInfo;
import com.partimestudy.assignment.domain.user.UserCommand;
import com.partimestudy.assignment.domain.user.UserInfo;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserDtoMapper {
    UserCommand.Signup of(UserDto.SignupRequest request);

    UserDto.SignupResponse of(UserInfo info);

    UserCommand.Login of(UserDto.LoginRequest request);

    UserDto.LoginResponse of(TokenInfo info);

    UserDto.UserDetailsResponse of(UserInfo.Details info);
}

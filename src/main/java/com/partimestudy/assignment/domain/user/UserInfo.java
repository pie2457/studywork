package com.partimestudy.assignment.domain.user;

import lombok.Getter;

@Getter
public class UserInfo {
    private final String userToken;

    public UserInfo(User user) {
        this.userToken = user.getUserToken();
    }

    public record Details(
        String loginId,
        String name,
        String purpose
    ) {

        public static UserInfo.Details from(User user) {
            return new UserInfo.Details(user.getLoginId(), user.getName(), user.getPurpose().getContent());
        }
    }
}

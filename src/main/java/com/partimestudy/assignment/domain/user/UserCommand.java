package com.partimestudy.assignment.domain.user;

public class UserCommand {

    public record Signup(
        String loginId,
        String password,
        String name,
        String purpose
    ) {
        public User toEntity(EncryptedPassword encrypted) {
            return User.builder()
                .loginId(loginId)
                .name(name)
                .password(encrypted.encoded())
                .purpose(User.Purpose.from(purpose))
                .salt(encrypted.salt())
                .build();
        }
    }

    public record Login(
        String loginId,
        String password
    ) {

    }
}

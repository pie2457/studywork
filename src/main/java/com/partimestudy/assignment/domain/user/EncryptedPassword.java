package com.partimestudy.assignment.domain.user;

public record EncryptedPassword(
    String encoded,
    String salt
) {

}

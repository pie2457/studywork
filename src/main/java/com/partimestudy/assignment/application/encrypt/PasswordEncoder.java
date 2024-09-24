package com.partimestudy.assignment.application.encrypt;

import com.partimestudy.assignment.domain.user.EncryptedPassword;

public interface PasswordEncoder {
    EncryptedPassword encode(String plainText);

    String encodeWithSalt(String plainText, String salt);
}

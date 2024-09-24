package com.partimestudy.assignment.infrastructure.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.application.encrypt.PasswordEncoder;
import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.exception.InternalServerException;
import com.partimestudy.assignment.domain.user.EncryptedPassword;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Sha256Encoder implements PasswordEncoder {
    private static final int SALT_LENGTH = 20;
    private static final String ALGORITHM = "SHA-256";

    @Override
    public EncryptedPassword encode(String plainText) {
        String salt = getSalt();
        return new EncryptedPassword(
            getEncrypt(plainText, salt),
            salt
        );
    }

    @Override
    public String encodeWithSalt(String plainText, String salt) {
        return getEncrypt(plainText, salt);
    }

    private String getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);

        return bytesToHex(salt);
    }

    private String getEncrypt(String plainText, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update((plainText + salt).getBytes());
            byte[] hashedBytes = digest.digest();

            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("""
                | 비밀번호 암호화 실패
                | Error : {}, {}
                """, e, e.getMessage());
            throw new InternalServerException(ErrorCode.PASSWORD_ENCRYPTION_FAIL);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

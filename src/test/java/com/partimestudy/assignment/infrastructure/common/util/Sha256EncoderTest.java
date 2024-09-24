package com.partimestudy.assignment.infrastructure.common.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.partimestudy.assignment.domain.user.EncryptedPassword;

class Sha256EncoderTest {

    @DisplayName("같은 문자열을 같은 salt를 이용해 암호화하면 동일한 암호화된 문자열이된다.")
    @Test
    void encryptedPassword() {
        // given
        Sha256Encoder encoder = new Sha256Encoder();
        String plainText = "password test";

        // when
        EncryptedPassword password = encoder.encode(plainText);

        // then
        assertThat(password.encoded())
            .isEqualTo(encoder.encodeWithSalt(plainText, password.salt()));
    }
}

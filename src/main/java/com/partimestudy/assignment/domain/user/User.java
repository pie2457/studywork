package com.partimestudy.assignment.domain.user;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.infrastructure.common.util.TokenGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private static final String PREFIX_USER = "user_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userToken;
    private String loginId;
    private String password;
    private String name;
    private String salt;

    @CreatedDate
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Getter
    @RequiredArgsConstructor
    public enum Purpose {
        UNIVERSITY("대입"),
        EMPLOYMENT("취업"),
        PUBLIC_OFFICIAL("공무원"),
        CERTIFICATE("자격증"),
        ETC("기타");

        private final String content;

        public static Purpose from(String purpose) {
            return Arrays.stream(Purpose.values())
                .filter(p -> p.content.equals(purpose))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_PURPOSE));
        }
    }

    @Builder
    public User(String loginId, String password, Purpose purpose, String name, String salt) {
        this.userToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_USER);
        this.loginId = loginId;
        this.password = password;
        this.purpose = purpose;
        this.name = name;
        this.salt = salt;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}

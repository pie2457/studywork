package com.partimestudy.assignment.domain.user;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name = "users")
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
    }
}

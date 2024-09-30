package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.exception.ForbiddenException;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userToken;
    private Integer challengeId;
    private String challengeName;
    private Integer deposit;
    private LocalDate startedAt;
    private Integer studyTime;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Order(String userToken, Integer challengeId, String challengeName,
        int deposit, LocalDate startedAt, int studyTime) {
        this.userToken = userToken;
        this.challengeId = challengeId;
        this.challengeName = challengeName;
        this.deposit = deposit;
        this.startedAt = startedAt;
        this.studyTime = studyTime;
    }

    public void validateUserToken(String userToken) {
        if (!this.userToken.equals(userToken)) {
            throw new ForbiddenException(ErrorCode.PERMISSION_DENIED_ORDER);
        }
    }
}

package com.partimestudy.assignment.domain.challenge;

import com.partimestudy.assignment.domain.exception.BadRequestException;
import com.partimestudy.assignment.domain.exception.ErrorCode;

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
@Table(name = "challenges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer minDeposit;
    private Integer maxDeposit;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ACTIVE("활성화"),
        INACTIVE("비활성화");

        private final String status;
    }

    public void validateStatus() {
        if (this.status == Status.INACTIVE) {
            throw new BadRequestException(ErrorCode.INACTIVE_CHALLENGE);
        }
    }

    public void validateDeposit(int deposit) {
        if (deposit < minDeposit && deposit > maxDeposit) {
            throw new BadRequestException(ErrorCode.INVALID_DEPOSIT_RANGE);
        }
    }

    public void validateName(String name) {
        if (this.name.equals(name)) {
            throw new BadRequestException(ErrorCode.INCORRECT_NAME);
        }
    }
}

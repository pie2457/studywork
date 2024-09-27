package com.partimestudy.assignment.domain.order;

import java.time.LocalDate;

public interface OrderReader {
    boolean existsByUserTokenAndChallengeIdAndStartedAt(
        String userToken,
        Integer challengeId,
        LocalDate startedAt);
}

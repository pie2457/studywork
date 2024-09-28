package com.partimestudy.assignment.domain.challenge;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeReader challengeReader;

    @Override
    public Challenge findByChallengeId(Integer challengeId) {
        return challengeReader.findById(challengeId);
    }
}

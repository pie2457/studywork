package com.partimestudy.assignment.infrastructure.challenge;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.challenge.Challenge;
import com.partimestudy.assignment.domain.challenge.ChallengeReader;
import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChallengeReaderImpl implements ChallengeReader {
    private final ChallengeRepository challengeRepository;

    @Override
    public Challenge findById(Integer challengeId) {
        return challengeRepository.findById(challengeId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CHALLENGE));
    }
}

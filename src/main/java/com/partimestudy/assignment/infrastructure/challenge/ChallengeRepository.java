package com.partimestudy.assignment.infrastructure.challenge;

import org.springframework.data.jpa.repository.JpaRepository;

import com.partimestudy.assignment.domain.challenge.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
}

package com.partimestudy.assignment.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.partimestudy.assignment.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginId(String loginId);
}

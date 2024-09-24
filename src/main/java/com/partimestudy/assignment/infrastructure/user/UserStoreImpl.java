package com.partimestudy.assignment.infrastructure.user;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.user.User;
import com.partimestudy.assignment.domain.user.UserStore;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {
    private final UserRepository userRepository;

    @Override
    public User store(User user) {
        return userRepository.save(user);
    }
}

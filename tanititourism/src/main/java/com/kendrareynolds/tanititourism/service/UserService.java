package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.User;
import com.kendrareynolds.tanititourism.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String getUsersFirstName(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Username " + username + " not found");
        }
        User user = optionalUser.get();
        return user.getFirstName();
    }
}

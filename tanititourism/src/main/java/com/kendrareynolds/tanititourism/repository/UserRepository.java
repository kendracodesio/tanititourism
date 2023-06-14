package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUsername(String username);


}

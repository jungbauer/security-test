package com.jungbauer.securitytest.repository;

import com.jungbauer.securitytest.model.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestUserRepository extends JpaRepository<TestUser, Long> {

    Optional<TestUser> findByEmail(String email);
    Optional<TestUser> findByUsername(String username);
}

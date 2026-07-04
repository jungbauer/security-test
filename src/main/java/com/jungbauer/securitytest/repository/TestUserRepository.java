package com.jungbauer.securitytest.repository;

import com.jungbauer.securitytest.model.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<TestUser, Long> {

    TestUser findByEmail(String email);
}

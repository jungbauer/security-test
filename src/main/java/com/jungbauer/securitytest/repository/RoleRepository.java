package com.jungbauer.securitytest.repository;

import com.jungbauer.securitytest.model.entity.Role;
import com.jungbauer.securitytest.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}

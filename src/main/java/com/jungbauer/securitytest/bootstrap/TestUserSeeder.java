package com.jungbauer.securitytest.bootstrap;

import com.jungbauer.securitytest.model.entity.Role;
import com.jungbauer.securitytest.model.entity.TestUser;
import com.jungbauer.securitytest.model.enums.RoleEnum;
import com.jungbauer.securitytest.repository.RoleRepository;
import com.jungbauer.securitytest.repository.TestUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class TestUserSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final TestUserRepository testUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public TestUserSeeder(TestUserRepository testUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.testUserRepository = testUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createTestUser();
        this.createAdminUser();
        this.createOwnerUser();
    }

    private void createTestUser() {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        Optional<TestUser> optionalTestUser = testUserRepository.findByEmail("test@test.com");

        if (optionalRole.isEmpty() || optionalTestUser.isPresent()) {
            return;
        }

        final TestUser testUser = new TestUser();

        testUser.setFirstName("Test");
        testUser.setLastName("Test");
        testUser.setEmail("test@test.com");
        testUser.setUsername(testUser.getFirstName().toLowerCase() + "." + testUser.getLastName().toLowerCase());
        testUser.setPassword(passwordEncoder.encode("test"));

        optionalRole.ifPresent(role -> {
            testUser.setRoles(Set.of(role));
        });

        testUserRepository.save(testUser);
    }

    private void createAdminUser() {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        Optional<TestUser> optionalTestUser = testUserRepository.findByEmail("admin@admin.com");

        if (optionalRole.isEmpty() || optionalTestUser.isPresent()) {
            return;
        }

        final TestUser testUser = new TestUser();

        testUser.setFirstName("Admin");
        testUser.setLastName("Admin");
        testUser.setUsername(testUser.getFirstName().toLowerCase() + "." + testUser.getLastName().toLowerCase());
        testUser.setEmail("admin@admin.com");
        testUser.setPassword(passwordEncoder.encode("admin"));

        optionalRole.ifPresent(role -> {
            testUser.setRoles(Set.of(role));
        });

        testUserRepository.save(testUser);
    }

    private void createOwnerUser() {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.OWNER);
        Optional<TestUser> optionalTestUser = testUserRepository.findByEmail("owner@owner.com");

        if (optionalRole.isEmpty() || optionalTestUser.isPresent()) {
            return;
        }

        final TestUser testUser = new TestUser();

        testUser.setFirstName("Owner");
        testUser.setLastName("Owner");
        testUser.setUsername(testUser.getFirstName().toLowerCase() + "." + testUser.getLastName().toLowerCase());
        testUser.setEmail("owner@owner.com");
        testUser.setPassword(passwordEncoder.encode("owner"));

        optionalRole.ifPresent(role -> {
            testUser.setRoles(Set.of(role));
        });

        testUserRepository.save(testUser);
    }
}

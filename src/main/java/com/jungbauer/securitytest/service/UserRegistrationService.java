package com.jungbauer.securitytest.service;

import com.jungbauer.securitytest.exception.UserAlreadyExistsException;
import com.jungbauer.securitytest.model.dto.UserRegDto;
import com.jungbauer.securitytest.model.entity.Role;
import com.jungbauer.securitytest.model.entity.TestUser;
import com.jungbauer.securitytest.model.enums.RoleEnum;
import com.jungbauer.securitytest.repository.RoleRepository;
import com.jungbauer.securitytest.repository.TestUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserRegistrationService {

    private final TestUserRepository testUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserRegistrationService(TestUserRepository testUserRepository, PasswordEncoder passwordEncoder,
                                   RoleRepository roleRepository) {
        this.testUserRepository = testUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public TestUser registerNewUserAccount(final UserRegDto userDto) throws UserAlreadyExistsException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        if (usernameExists(userDto.getFirstName().toLowerCase() + "." + userDto.getLastName().toLowerCase())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        final TestUser user = new TestUser();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        optionalRole.ifPresent(role -> {
            user.setRoles(Set.of(role));
        });

        return testUserRepository.save(user);
    }

    private boolean emailExists(String email) {
        Optional<TestUser> optionalTestUser = testUserRepository.findByEmail(email);
        return optionalTestUser.isPresent();
    }

    private boolean usernameExists(String username) {
        Optional<TestUser> optionalTestUser = testUserRepository.findByUsername(username);
        return optionalTestUser.isPresent();
    }

}

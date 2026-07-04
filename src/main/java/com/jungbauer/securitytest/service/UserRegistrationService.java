package com.jungbauer.securitytest.service;

import com.jungbauer.securitytest.exception.UserAlreadyExistsException;
import com.jungbauer.securitytest.model.dto.UserRegDto;
import com.jungbauer.securitytest.model.entity.TestUser;
import com.jungbauer.securitytest.repository.TestUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import java.util.Set;

@Service
public class UserRegistrationService {

    private final TestUserRepository testUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(TestUserRepository testUserRepository, PasswordEncoder passwordEncoder) {
        this.testUserRepository = testUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TestUser registerNewUserAccount(final UserRegDto userDto) throws UserAlreadyExistsException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        final TestUser user = new TestUser();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setRoles(Set.of(roleRepository.findByName(UserRoles.USER.getText())));

        return testUserRepository.save(user);
    }

    private boolean emailExists(String email) {
        return testUserRepository.findByEmail(email) != null;
    }

}

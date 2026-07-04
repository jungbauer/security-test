package com.jungbauer.securitytest.service;

import com.jungbauer.securitytest.model.entity.TestUser;
import com.jungbauer.securitytest.repository.TestUserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TestUserRepository testUserRepository;

    public CustomUserDetailsService(TestUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {

        TestUser user = testUserRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }

        // todo remove when these are in user entity
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
    }
}

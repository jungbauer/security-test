package com.jungbauer.securitytest.service;

import com.jungbauer.securitytest.model.entity.Role;
import com.jungbauer.securitytest.model.entity.TestUser;
import com.jungbauer.securitytest.repository.TestUserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TestUserRepository testUserRepository;

    public CustomUserDetailsService(TestUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {

        Optional<TestUser> optionalTestUser = testUserRepository.findByEmail(email);
        if (optionalTestUser.isEmpty()) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }

        // These are required by UserDetails. This could be implemented in TestUser, but I don't want to.
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = getAuthorities(optionalTestUser.get().getRoles());

        return new org.springframework.security.core.userdetails.User(
                optionalTestUser.get().getEmail(), optionalTestUser.get().getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
    }

    private List<GrantedAuthority> getAuthorities (Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            // The ROLE_ prefix is important. I don't fully understand where it's used but if
            // missing method based security functions like hasRole() seem to break.
            authorities.add(new SimpleGrantedAuthority(role.getName().getText()));
        }
        return authorities;
    }
}

package com.vbelova.teachers.service;

import com.google.common.collect.ImmutableSet;
import com.vbelova.teachers.entity.User;
import com.vbelova.teachers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final GrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        name = name.trim();
        User user =  userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("Not found " + name);
        }
        return new org.springframework.security.core.userdetails.User(
                user.name,
                user.password,
                user.enabled,
                true, true, true,
                ImmutableSet.of(new SimpleGrantedAuthority(user.role))
        );
    }

    public boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(ADMIN);
    }

}

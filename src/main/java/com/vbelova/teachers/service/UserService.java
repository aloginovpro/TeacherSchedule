package com.vbelova.teachers.service;

import com.google.common.collect.ImmutableSet;
import com.vbelova.teachers.entity.User;
import com.vbelova.teachers.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        name = name.trim();
        User user =  userRepo.findByName(name);
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

}
